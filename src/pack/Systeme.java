package pack;
/*
import java.awt.AlphaComposite;
import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TexturePaint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;*/

import java.awt.Container;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Astres.Asteroide;
import Astres.Astre;
import Astres.Etoile;
import Astres.Jupiter;
import Astres.Mercure;
import Astres.Naine;
import Astres.Neptune;
import Astres.Terre;
import enums.CarteMod;
import enums.Ressource;
import enums.StarType;


import enums.StarType.*;

public class Systeme extends InWorldObj {
	
	private static int incrementNumSyst=0;
	
	@Deprecated
	double systemeAge;//age de la simulation en SECONDE, ne pas confondre avec age!!!!
	
	private double age;//age en milliard d'année
	private boolean selected;
	
	private StarType starType;
	private double lum;//luminosité


	private double moyLum;//luminosité moyenne pour les etoiles de ce type //TODO utile?

	private int num;
	
	private boolean visible=true;
	
	protected Astre mainAstre;//Etoile principale (si etoile)
	protected ArrayList<Astre> astres=new ArrayList<Astre>();//Liste des cellules presentes sur le terrain


	/**Creation de Systeme avec une seul etoile principale
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException */
	public  Systeme(int xPm, int yPm,StarType st) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		super(xPm,yPm,(int)Constantes.Pm);
		starType=st;
		genAge();
		num=incrementNumSyst;
		incrementNumSyst+=1;
		setImage(Images.FLARE);
		setPropFromType();
		
		creatMainAstre();
		creatAstres();

		selected=false;
	}
	
	private void genAge(){
		if(starType==StarType.T){
			age=Math.random()*13;
		}else if(starType==StarType.M){
			age=Math.random()*13;
		}else if (starType==StarType.K){
			age=Math.random()*13;
		}else if (starType==StarType.G){
			age=Math.random()*9;
		}else if (starType==StarType.F){
			age=Math.random()*3;
		}else if (starType==StarType.A){
			age=Math.random()*0.4;
		}else if (starType==StarType.B){
			age=Math.random()*0.03;
		}else if (starType==StarType.O){
			age=Math.random()*0.005;
		}
		//Autre
		else if (starType==StarType.D){//Naine Blanche
			age=Math.random()*10;
		}else if (starType==StarType.GR){
			age=Math.random()*0.05;//Au pif
		}else if (starType==StarType.SGR){
			age=Math.random()*0.05;//Au pif
		}else if (starType==StarType.Q){
			age=Math.random()*13;
		}else if (starType==StarType.X){
			age=Math.random()*13;
		}else if (starType==StarType.GX){
			age=10+Math.random()*3;
		}
	}
	
	/**Creation de l'astre Principal*/
	private void creatMainAstre(){//TODO Gerer les cas ou l'astre principal n'est pas une etoile
		Etoile e=new Etoile(this);
		e.setColor(R, V, B);
		mainAstre=e;
		//addAstre(mainAstre);
	}
	/**Creation des astres en orbites*/
	public void creatAstres() {
		int nbPlanete=(int)Global.randExtreme(0, 4*Constantes.nbPlanetes,2);
		int nbAster=(int)Global.randExtreme(0, 4*Constantes.nbAsteroide,2);
		double distMin=2*mainAstre.getDiam();//distance utilisé pour calculer la distance des astres a l'etoile
		
		Class type;
		//type.cast(Astre.class);
		//Creation des planetes{
		for(int i=0;i<nbPlanete;i++){
			double r=Math.random();
			if(r<Constantes.nbJupiter){
				type=Jupiter.class;
				//type.cast(Jupiter.class);
			}else if(r<Constantes.nbJupiter+Constantes.nbNeptune){
				type=Neptune.class;
				//type.cast(Neptune.class);
			}else if(r<Constantes.nbJupiter+Constantes.nbNeptune+Constantes.nbTerre){
				type=Terre.class;
				//type.cast(Terre.class);
			}else{
				type=Mercure.class;
				//type.cast(Mercure.class);
			}
			
			Constructor<Astre> c=(Constructor<Astre>) type.getConstructors()[0];//recuperation du constructeur
			Astre a;//astre a creer

			try {
				a = c.newInstance(Global.randExtreme(distMin, (5*distMin), 2) ,this);
				astres.add(a);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//}Fin de creation des planetes
		//Creation des Asteroides et planetes naines{
				for(int i=0;i<nbAster;i++){
					double r=Math.random();
					if(r<Constantes.nbAsteroide){
						type=Asteroide.class;
						//type.cast(Jupiter.class);
					}else{
						type=Naine.class;
						//type.cast(Mercure.class);
					}
					
					Constructor<Astre> c=(Constructor<Astre>) type.getConstructors()[0];//recuperation du constructeur
					Astre a;//astre a creer

					try {
						a = c.newInstance(Global.randExtreme(distMin, (5*distMin), 2) ,this);
						astres.add(a);
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				//}Fin de creation des asteroides
		
		
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	/**Verifie si la sourie est dans le systeme*/
	public boolean verifyMouseIn(double x, double y){
		double dx2=(posX-x)*(posX-x);
		double dy2=(posY-y)*(posY-y);
		if(dx2+dy2<(size2)/(81*Global.zoom)){//81 pris au pif, mais marche plutot bien, ici on fait le clic a distance variable
			return true;
		}else if(dx2+dy2<Constantes.Pm*Constantes.Pm){//Ici, clic constant dans le systeme
			return true;
		}
		return false;
	}
	
	/**On verifie si le clic a lieu et si oui, on selectionne*/
	public void clickOn(double worldX, double worldY){
		if(verifyMouseIn(worldX,worldY)){
				Global.selectedThing=this;
				for(int i=0;i<Constantes.nbSystemes;i++){
					Global.Systemes[i].clickOff();
				}
				for(int k=0;k<astres.size();k++){
					astres.get(k).clickOn(worldX,worldY);
				}
				selected=true;
		}
		/*Iterator<Astre> i=astres.iterator();
		while(i.hasNext()){
			i.next().clickOn(worldX, worldY);
		}*/

		
	}
	public void clickOff(){
		selected=false;
		for(int i=0;i<astres.size();i++){
			astres.get(i).clickOff();
		}
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	/**Fonction d'actualisation*/
	@Override
	public void actualize(double posX2, double posY2, double zoom,short screenCenterX,short screenCenterY){
		super.actualize(posX2, posY2, zoom, screenCenterX, screenCenterY);
		
		double lum2=0.01*lum*Math.pow(zoom,0.5)*Global.exposition;
		
		//Simulation de la luminosité de l'etoile
		/*if(lum2<zoom){//Proche de l'etoile//TODO Problemes a regler
			screenSizeX=(int) ( ((int)sizeX*lum2));
			screenSizeY=(int) ( ((int)sizeY*lum2));
		}else{//Loin de l'etoile
			System.out.println(Global.exposition);
			screenSizeX=(int) ( ((int)sizeX)*(zoom*lum2));
			screenSizeY=(int) ( ((int)sizeY)*(zoom*lum2));
		}*/
		
		if(lum2>9e-5){//defini si sera visible ou trop eloigné
			visible=true;
		}else{
			visible=false;
		}
		
		screenSizeX=(int) ( ((int)sizeX*lum2));
		screenSizeY=(int) ( ((int)sizeY*lum2));
		
		mainAstre.actualize(posX2, posY2, zoom, screenCenterX, screenCenterY);
		for(int i=0;i<astres.size();i++){
			astres.get(i).actualize(posX2, posY2, zoom, screenCenterX, screenCenterY);
		}
}
	
	/**fonction d'affichage*/
	public void afficher(Graphics g) {
		if(isVisible()){
			afficherAstres(g);
			super.afficher(g);
			mainAstre.afficher(g);
			//System.out.println(astres.size());
		}
		afficherOnClick(g);

	}
	
	public void afficherAstres(Graphics g){
		for(int i=0;i<astres.size();i++){
			astres.get(i).afficher(g);
		}
	}
	
	
	public void afficherOnClick(Graphics g){
		if(selected==true){
			int imgx=(screenPosX-screenSizeX)-1;
			int imgy=(screenPosY-screenSizeY)-1;
			int imgw=screenSizeX+2;//2* carl'image doit deboder
			int imgh=screenSizeY+2;
			
			g.setColor(Color.red);
			try {
				g.drawRoundRect(imgx+imgw/2, imgy+imgh/2, imgw, imgh, screenSizeX/3);

			} catch (Exception e) {}

		}
	}
	
	public void setPropFromType(){
		if(starType==StarType.T){
			//lum=4;
			lum=Global.rand(3, 5);
			setColor(91, 33, 0);
		}else if(starType==StarType.M){
			//lum=7;
			lum=Global.rand(5, 9);
			setColor(255, 0, 0);
		}else if (starType==StarType.K){
			//lum=13;
			lum=Global.rand(9, 14);
			setColor(255, 165, 0);
		}else if (starType==StarType.G){
			//lum=15;
			lum=Global.rand(14, 17);
			setColor(255, 255, 165);
		}else if (starType==StarType.F){
			//lum=18;
			lum=Global.rand(17, 22);
			setColor(255, 255, 255);
		}else if (starType==StarType.A){
			//lum=24;
			lum=Global.rand(22, 26);
			setColor(220, 220, 255);
		}else if (starType==StarType.B){
			//lum=30;
			lum=Global.rand(26, 32);
			setColor(150, 150, 255);
		}else if (starType==StarType.O){
			//lum=35;
			lum=Global.rand(32, 40);
			setColor(0, 0, 255);
		}
		//Autre
		else if (starType==StarType.D){//Naine Blanche
			//lum=4;
			lum=Global.rand(3, 6);
			setColor(255, 255, 255);
		}else if (starType==StarType.GR){
			//lum=35;
			lum=Global.rand(30, 40);
			setColor(220, 165, 0);
		}else if (starType==StarType.SGR){
			//lum=40;
			lum=Global.rand(40, 60);
			setColor(255, 55, 0);
		}else if (starType==StarType.Q){
			//lum=3;
			lum=Global.rand(2, 4);
			setColor(255, 255, 255);
		}else if (starType==StarType.X){
			//lum=3;
			lum=Global.rand(2, 4);
			setColor(255, 165, 165);
		}else if (starType==StarType.GX){
			lum=500;
			setColor(255, 255, 165);
		}
	}
	
	/**fonction de boucle*/
	public void loop(){
		super.loop();
		
		systemeAge=systemeAge+Constantes.timeFramS;
		for(int i=0;i<astres.size();i++){
			astres.get(i).loop();
		}
		
	}

	public int getNbAstres() {
		return astres.size();
	}
	public Astre getAstre(int i) {
		return astres.get(i);
	}
	public void addAstre(Astre astre) {
		astres.add(astre);
	}
	public void removeAstre(Astre astre){
		astres.remove(astre);
	}
	public boolean containsAstre(Astre astre){
		if(astres.contains(astre)){
			return true;
		}
		return false;
	}

	public Astre getMainAstre(){
		return mainAstre;
	}
	
	@Override
	public String toString() {
		return "Systeme " + num ;
	}

	public double getAge(){
		return age;
	}
	
	public double getLum() {
		return lum;
	}
	
	public StarType getStarType(){
		return starType;
	}
}
