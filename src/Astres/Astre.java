package Astres;

import static pack.Global.randint;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Territoire.Secteur;
import Territoire.Tuile;
import enums.AstreType;
import pack.Constantes;
import pack.Global;
import pack.Images;
import pack.InWorldObj;
import pack.Systeme;

public abstract class Astre extends InWorldObj{
	
	private static final long serialVersionUID = 1L;
	
	protected double masse;//masse terrestres
	protected double diam;//diametre terrestre
	protected double dens;
	protected double multFactDiam=0.01;//facteur pour multiplier le diametre
	protected int TBase;//temperature sans atmosphere en kelvin
	protected int num=0;
	protected double dist;
	
	protected AstreType type;//TODO Temporaire, remplacé par classe hérité
	protected boolean inCollision=false;//TODO A enlever?
	
	protected Systeme syst;
	
	protected double speedX=0; 
	protected double speedY=0;
	
	protected Atmosphere atmos;
	
	protected List<Tuile> tuiles;
	protected List<Secteur> secteurs;
	
	
	//dist=distance a l'etoile
	public  Astre(double dist, Systeme syst){
		super();
		this.syst=syst;
		this.dist=dist;
		double r=2*Math.PI*Math.random();//angle par rapport a l'etoile
		double x=(int) (dist*Math.cos(r));//position dans le systeme
		double y=(int) (dist*Math.sin(r));


		posX=x+syst.getX();posY=y+syst.getY();
		//FIXME Probleme dans l'attribution des valeurs de pos case?//TODO A retester, normalmenet régler
		posSystXPm=syst.getSystPosXPm();//Math.floorDiv(x, Constantes.Pm);// (((float) x)/((float) Constantes.casesSize));
		posSystYPm=syst.getSystPosXPm();//Math.floorDiv(y, Constantes.Pm);
		
		//System.out.println("("+posX+","+posY+";"+posCaseX+","+posCaseY+")");
		
		init( syst);
		/**
		 * V=4/3 *pi *r^3
		 * (masse/dens)=4/3 *pi *r^3
		 * r=racine3((3*masse)/(4*pi*dens))
		 * diam=2*racine3((3*masse)/(4*pi*dens))
		 * */
		int size=(int) (multFactDiam*500*diam/2);
		sizeX=size;sizeY=size;
		size2=size*size;
		couleur=new Color(255,255,255);
		setImage(Images.PLANETE_VENUS0);
		if(posSystXPm<0){
			posSystXPm=0;
		}
		if(posSystYPm<0){
			posSystYPm=0;
		}
	}
	
	
	protected void init( Systeme syst){
		this.syst=syst;
		genTBase();
		genDens();
		genMasse();
		genDiam();
		genAtmosphere();

		
						
				
		speedX=0; 
		speedY=0;
	}
	
	protected abstract void genAtmosphere();
	
	protected abstract void genDens();
	protected abstract void genMasse();

	protected abstract void genDiam();
	
	protected void genTBase(){
		TBase=0;
		if(! (this instanceof Etoile)){
			double lum=syst.getLum()*syst.getLum()*(syst.getMainAstre().getDiam()/300);
			double d=(dist+syst.getMainAstre().getDiam()/2)/20;
			TBase=(int) ( 10000f*(lum/(d*d)));
		}
	}
	
	
	@Override
	public void actualize(double posX2, double posY2, double zoom, short screenCenterX, short screenCenterY) {
		super.actualize(posX2, posY2, zoom, screenCenterX, screenCenterY);
		sizeX=(float) Math.sqrt(size2);
		sizeY=(float) Math.sqrt(size2);
		
	}
	
	public void loop(){

	}
	public Object clone() {
	        Astre c= null;
	         
	        c = (Astre) super.clone();
			c.clickOff();
	        return c;
	    }
	
	public Atmosphere getAtmosphere(){
		return atmos;
	}
	
	public void afficherOnClick(Graphics g) {
		if(selected==true){
			int imgx=(screenPosX-screenSizeX)-1;
			int imgy=(screenPosY-screenSizeY)-1;
			int imgw=screenSizeX+2;//2* carl'image doit deboder
			int imgh=screenSizeY+2;
			
			g.setColor(Color.yellow);
			//g.drawRoundRect(imgx+imgw, imgy+imgh, imgw, imgh, screenSizeX/3);
			g.drawOval(imgx+imgw/2, imgy+imgh/2, imgw, imgh);
			
		}
	}
	
	
	public void afficher(Graphics g) {
		super.afficher(g);
		afficherOnClick(g);
	}

	
	/**verifie si un point (x,y) est dans la cellule*/
	public boolean cellInCell(Astre c){
		double dx2=(posX-c.getX())*(posX-c.getX());
		double dy2=(posY-c.getY())*(posY-c.getY());
		if(dx2+dy2<size2){
			return true;
		}
		return false;
	}
	/**verifie si un la souris en position (x,y) est dans la cellule*/
	public boolean mouseInAstre(double x, double y){
		double dx2=(posX-x)*(posX-x);
		double dy2=(posY-y)*(posY-y);
		if(dx2+dy2<size2/4){
			return true;
		}
		return false;
	}
		
	public int getSizeCarre(){
		return size2;
	}

	public int getTemperature(){
		if(atmos!=null){
			return TBase+atmos.getTSerre();
		}else{
			return TBase;
		}
	}
	
	public boolean hasAtmopshere(){
		if(atmos==null){
			return false;
		}else if(atmos.getPressure()==0){
			return false;
		}
		return true;
	}
	
	public void clickOn(double worldX, double worldY){
		System.out.println(1);
		if(mouseInAstre(worldX,worldY)){
			System.out.println(2);
			/*for(int k=0;k<Global.Systemes[posSystXAl][posSystYAl].getNbAstres();k++){
				Global.Systemes[posSystXAl][posSystYAl].clickOff();
			}*/
			//La deselection est effectuée par le systeme
			selected=true;
			Global.selectedThing=this;
		}
	}
	
	public void clickOff(){
		selected=false;
		if(this==Global.selectedThing){
			Global.selectedThing=null;
		}
	}

	public boolean isSelected(){
		return selected;
	}

	@Override
	public String toString() {
		return syst.toString() +" "+num;
	}
	
	public void setNum(int n){
		num=n;
	}
	
	public double getDist(){
		return dist;
	}
	
	public double getMasse(){
		return masse;
	}
	
	public double getDiam(){
		return diam;
	}
	
	public double getDens(){
		return dens;
	}
	
	public void setImg(Image i){
		img=i;
	}
}
