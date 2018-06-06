package pack;


import java.io.Serializable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;



public class InWorldObj implements Serializable, Cloneable{

	/**
	 * 
	 */
	//variable valable dans le monde
	 protected double posX;
	 protected double posY;
	 protected double posCentX;//position du centre de l'objet
	 protected double posCentY;
	 
	 protected int size2;
		


	 protected int posSystXPm;
	 protected int posSystYPm;
	 protected double sizeX;
	 protected double sizeY;
	//memes valeurs mais pour l'ecran
	 protected int screenPosX;
	 protected int screenPosY;
	 protected int screenSizeX;
	 protected int screenSizeY;
	//demi taille de l'ecran
	 protected int screenCenterX;
	 protected int screenCenterY;
	
	 protected short R;protected short V;protected short B;
	
	 protected Color couleur;
	 
	 protected Image img=Images.PIXEL;
	
	 protected static final long serialVersionUID = 1L;
	
	 protected boolean selected=false;


	public  InWorldObj(){
		super();
		
		
	}
	
	public  InWorldObj(int x, int y,int size){
		super();
		posX=x*Constantes.Pm;posY=y*Constantes.Pm;posSystXPm=x;posSystYPm=y;sizeX=size;sizeY=size;
		size2=size*size;
		couleur=new Color(255,255,255);
		setImage(Images.PIXEL);
		
	}
	/**Fonction de mise a jour des données de la fenetre contenant l'objet*/
	public void actualize(double posX2, double posY2, double zoom,short screenCenterX,short screenCenterY){
			//actualise la position a l'ecran
		
			int ws=Constantes.galaxieDiamUB;

		
			//screenPosX= (short) ( ( ( (int) Global.mod(posX-posX2+ws/2,ws)-ws/2 )*zoom) +screenCenterX);
			screenPosY=  (int) ( ( ( (int) Global.mod(posY-posY2+ws/2,ws)-ws/2 )*zoom) +screenCenterY);
			
			screenPosX= (int) ( ( ((int)posX-posX2) *zoom) +screenCenterX);
			screenPosY= (int) ( ( ((int)posY-posY2) *zoom) +screenCenterY);
			screenSizeX=(int) ( ((int)sizeX)*zoom);
			screenSizeY=(int) ( ((int)sizeY)*zoom);
			
			this.screenCenterX=(int) screenCenterX;
			this.screenCenterY=(int) screenCenterY;
			
			
	}
	
	/**
	 * Fonction d'affichage
	 * */
	public void afficher(Graphics g) {
		int imgx=(screenPosX-screenSizeX)-1;
		int imgy=(screenPosY-screenSizeY)-1;
		int imgw=screenSizeX+2;//2* carl'image doit deboder
		int imgh=screenSizeY+2;
	
		//On dessine le terrain
		
		if(img!=null){
			img.draw(imgx+imgw/2, imgy+imgh/2, imgw, imgh,couleur);		
		}else{
			System.out.println("L'un des objet a afficher n'a pas d'image");
		}
	}
	
	public void afficher2(Graphics g) {
		int imgx=(screenPosX-screenSizeX*2)-1;
		int imgy=(screenPosY-screenSizeY*2)-1;
		int imgw=screenSizeX*2+2;//2* carl'image doit deboder
		int imgh=screenSizeY*2+2;
	
		//On dessine le terrain
		
		if(img!=null){
			img.draw(imgx+imgw, imgy+imgh, imgw, imgh,couleur);
			
		}
	}
	
	public void setImage(Image image){
		img=image;
	}
	/**
	 * Fonction de deplacement suivant x et y
	 * 
	 * */
	/*public void move(int speedX, int speedY){//vitesse en cases par seconde
		posX+=speedX*(Constantes.timeFramS);
		posY+=speedY*(Constantes.timeFramS);
	}*/
	//Accesseurs
	public double getX(){
		return posX;
	}
	public double getY(){
		return posY;
	}
	public int getSystPosXPm(){
		return posSystXPm;
	}
	public int getSystPosYPm(){
		return posSystYPm;
	}
	public void setX(float x){
		posX=x;
	}
	public void setY(float y){
		posY=y;
	}
	public double getSizeX(){
		return sizeX;
	}
	public double getSizeY(){
		return sizeY;
	}
	//return int, pour calculs plus simple d'affichage
	public double getCentX(){
		return  posCentX;
	}
	public double getCentY(){
		return  posCentY;
	}
	public int getIntSizeX(){
		return (int) sizeX;
	}
	public int getIntSizeY(){
		return (int) sizeY;
	}

	public short getImageX(){
		return (short) ((screenPosX));//-screenSizeX/2)-1);
	}
	public short getImageY(){
		return (short) ((screenPosY));//-screenSizeY/2)-1);
	}
	public short getImageSize(){
		return (short) (screenSizeX+2);
	}

	public void loop(){
		posCentX=posX+sizeX/2;
		posCentY=posY+sizeY/2;

	}
	
	//couleurs
		public short getR(){
			return (short) couleur.getRed();
		}
		public short getV(){
			return (short) couleur.getGreen();
		}
		public short getB(){
			return (short) couleur.getBlue();
		}
		public void setR(int R){
			this.R=(short) R;
			couleur.r=((float)R)/255f;

		}
		public void setV(int V){
			this.V=(short) V;
			couleur.g=((float)V)/255f;

		}
		public void setB(int B){
			this.B=(short) B;
			couleur.b=((float)B)/255f;
		}

		public void setColor(int R, int V, int B){
			this.R=(short) R;this.V=(short) V; this.B=(short) B;
			couleur=new Color(R,V,B);
		}
		
		public Color getColor(){
			return couleur;
		}


		public Object clone() {
	        InWorldObj c= null;
	         
	        try {
	            c = (InWorldObj) super.clone();
	        } catch(CloneNotSupportedException cnse) {
	 
	            cnse.printStackTrace(System.err);
	        }
	        return c;
	    }

}
