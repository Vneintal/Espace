package pack;

import static pack.Global.mod;
import static pack.Global.randint;
import static pack.Global.Systemes;

import static pack.Global.posX;
import static pack.Global.posY;
import static pack.Global.zoom;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static pack.Global.gConteneur;


import pack.Images;

import org.lwjgl.util.Timer;

//import java.awt.Color;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import enums.CarteMod;



public class Fenetre extends BasicGame {

	//GameContainer gConteneur;
	
	//variables de position de l'ecran
	
	private double dzoom=1f;//variation de zoom
	private double dx=0;//variation de posx
	private double dy=0;//variation de posy
	
	//variables du clavier
	boolean ctrl;//indique si la touche ctrl est utilisé
	boolean alt;//indique si la touche alt est utilisé
	boolean shift;//indique si la touche shift est utilisé
	//variables de la souris
	boolean clicMolette;//indique si le clic molette est utilisé
	
	
	//varibles de timer
	private Timer tCam;
	private Timer tGame;//frequence du jeu
	
	public Fenetre(String title) {
		super(title);
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Global.gConteneur=gc;
		
		
		//On charge les images
		Images.charger();

		
		posX=Constantes.galaxieDiamUB/2;
		posY=Constantes.galaxieDiamUB/2;
		zoom=0.000001f;
		HUD.setScreenProp(posX,posY,zoom);
		//On cree l'arbre de tech
		
		//this.addKeyListener(new AllKey());//ajout du KeyListener
	
	
		
		
		//on cree le monde
		Univers.creatNewUniverse();

		

		
		//on initialise les timers
		tCam=new Timer();
		tGame=new Timer();

	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		//les tailles d'ecran
		short largeur = (short) gc.getWidth();
		short hauteur = (short) gc.getHeight();
		short largeur2 = (short) (largeur/2);//largeur/2
		short hauteur2 = (short) (hauteur/2);//hauteur/2
		
		
		Images.PIXEL.draw(0, 0, largeur, hauteur, Color.black);
		
		//La taille est specifié en nombre de cases
		/*short maxPosXC=(short) ((int) (posX+largeur2/zoom)/Constantes.casesSize);
		short minPosXC=(short) ((int) (posX-largeur2/zoom)/Constantes.casesSize);
		short maxPosYC=(short) ((int) (posY+hauteur2/zoom)/Constantes.casesSize);
		short minPosYC=(short) ((int) (posY-hauteur2/zoom)/Constantes.casesSize);*/
		
		int wsc=Constantes.nbSystemes;
		
		long maxPosXC=((long) (posX+0.5*largeur/zoom)/Constantes.Pm)+1;//+1 pour permettre de voir aussi les systemes d'a coté si besoin
		long minPosXC=((long) (posX-0.5*largeur/zoom)/Constantes.Pm)-1;
		long maxPosYC=((long) (posY+0.5*hauteur/zoom)/Constantes.Pm)+1;
		long minPosYC=((long) (posY-0.5*hauteur/zoom)/Constantes.Pm)-1;

		//g.drawRoundRect( (int)(largeur/zoom)/2, (int)(hauteur/zoom)/2, (int)(largeur/zoom)/2, (int)(hauteur/zoom)/2,10);
		//g.drawRoundRect( 0, 0, (int)(largeur*zoom), (int)(hauteur*zoom),10);

		
		//int x;//boucles de pos
		//int y;//boucles de pos	
		
		//Affichage de l'eau
		//InFenDebug.println(Float.toString(zoom));
		//if(zoom>0.001){
			//for(x=(minPosXC-5);x<maxPosXC+5;x++){
			//	for(y=minPosYC-5;y<maxPosYC+5;y++){
					//if(Global.Systemes[mod(x,wsc)][mod(y,wsc)].getNbCells()>0){
						//Global.Systemes[mod(x,wsc)][mod(y,wsc)].setZoomColor(zoom);
		
		//int nbaff=0;//TODO temporaire, test le nombre d'etoiles affichées
		for(int i=0;i<Constantes.nbSystemes;i++){
			if(minPosXC<=Global.Systemes[i].getSystPosXPm() && Global.Systemes[i].getSystPosXPm()<=maxPosXC){
				if(minPosYC<=Global.Systemes[i].getSystPosYPm() && Global.Systemes[i].getSystPosYPm()<=maxPosYC){
					Global.Systemes[i].actualize(posX, posY, zoom, largeur2,hauteur2);
					Global.Systemes[i].afficher(g);
					//nbaff+=1;
				}
			}
		}
		
		//System.out.println(nbaff);
		
		//affichage des cellules

		/*for(x=(minPosXC-5);x<maxPosXC+5;x++){
			for(y=minPosYC-5;y<maxPosYC+5;y++){
				Global.Systemes[mod(x,wsc)][mod(y,wsc)].afficherCells(g,zoom);
			}
		}*/
		
		
		HUD.afficher(gc,g);
	}

	
	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
			
		/*if(posY>Constantes.galaxieDiam){
			posY-=Constantes.galaxieDiam;
		}else if(posY<0){
			posY+=Constantes.galaxieDiam;
		}
		if(posX>Constantes.galaxieDiam){
			posX-=Constantes.galaxieDiam;
		}else if(posX<0){
			posX+=Constantes.galaxieDiam;
		}
		if(zoom<0.001){
			zoom=0.001f;
		}else if(zoom>100){
			zoom=100f;
		}*/
		HUD.setScreenProp(posX,posY,zoom);

		
		//les tailles d'ecran
		short largeur = (short) gConteneur.getScreenWidth();
		short hauteur = (short) gConteneur.getScreenHeight();
		short largeur2 = (short) (largeur/2);//largeur/2
		short hauteur2 = (short) (hauteur/2);//hauteur/2
		
		//La taille est specifié en nombre de cases
		short maxPosXC=(short) ((int) (posX+largeur2/zoom)/Constantes.Pm);
		short minPosXC=(short) ((int) (posX-largeur2/zoom)/Constantes.Pm);
		short maxPosYC=(short) ((int) (posY+hauteur2/zoom)/Constantes.Pm);
		short minPosYC=(short) ((int) (posY-hauteur2/zoom)/Constantes.Pm);

		//On met a jour les timers
		Timer.tick();
		
		/******Experimental*********/
		/**if(gc.getFPS()<30 && gc.getFPS()>0){
			if(gc.getFPS()<10){
				Constantes.timeFramMs=400;//9000/(gc.getFPS()*gc.getFPS());
			}else{
				Constantes.timeFramMs=50;//9000/(gc.getFPS()*gc.getFPS());
			}
		}else{
			Constantes.timeFramMs=10;
		}*/
		
		moveCam(gc);//met a jour la position de la camera
		//System.out.println(tGame.getTime());
		float time=tGame.getTime();
		if(time>Constantes.timeFramS){//On met a jour les objet du monde a une frequence specifique
			//System.out.println(time);
			//On effectue la boucle principale autant de fois qu'elle est sensé avoir eu lieu
			for(float i=0;i<time;i+=Constantes.timeFramS){
				loopEveryTick();
			}

			
			tGame.reset();
		}

	}
	
	public void loopEveryTick(){
		//les objets du monde agissent
		for(int i=0;i<Constantes.nbSystemes;i++){
			Global.Systemes[i].loop();			
		}
		
	
		//On effectue les actions journalieres
		if((int) Global.seconde != Global.lastSeconde){//On verifie que le jour a changer
			
			//Les action de mise a jours doivent etre faites pour chaques jours ecoulé, quelque soit la vitesse du jeu


			
			Global.lastSeconde=(int) Global.seconde;//On actualise lastDay
		}

		
		
		//Les jours passent
		Global.seconde+=Global.gameSpeed*Constantes.timeFramS;
		//Les mois passent
		if(Global.seconde>60){
			Global.minute+=1;
			Global.seconde-=60;
		}
		//les annees passent
		if(Global.minute>60){
			Global.heure+=1;
			Global.minute-=60;
		}
		//il n'y a pas d'an 0, seulement -1 et 1
		/*if(Global.year==0){
			Global.year=1;
		}*/
		
		
		//La position de la camera ne doit pas etre trop excentree
		/*if(posX>Constantes.galaxieDiam){
			posX-=Constantes.galaxieDiam;
		}else if(posX<0){
			posX+=Constantes.galaxieDiam;
		}*/
	}
	
	public void moveCam(GameContainer gc) {
		if(tCam.getTime()>0.01){
			zoom=zoom*dzoom;
			posX=posX+dx;
			posY=posY+dy;
			tCam.reset();//On remet le timer a 0
			
			mouseMotion(gc);
		}
	}
	
	/*//TODO A priori corriger
	public void setCarteMod(CarteMod cm){
		Global.carteMod=cm;
		for(int x = 0;x<Constantes.worldSizeC;x++){
			for(int y = 0;y<Constantes.worldSizeCd2;y++){
					worldCases[x][y].setColor();			
			}
		}
	}*/

	


	
	
	public void keyReleased(int key, char c) {
			    
			//ctrl alt shift
	    	if (key == Input.KEY_LCONTROL || key == Input.KEY_RCONTROL) {
	            ctrl=false;
	        }
	    	if (key == Input.KEY_LALT || key == Input.KEY_RALT) {
	            alt=false;
	        }
	    	if (key == Input.KEY_LSHIFT || key == Input.KEY_RSHIFT) {
	            shift=false;
	        }
		
		
	    	//stop mvnt
	    	
	        if (key == Input.KEY_Q || key == Input.KEY_LEFT) {
	            dx = 0;
	        }
	        if (key == Input.KEY_D || key == Input.KEY_RIGHT) {
	            dx = 0;
	        }
	        if (key == Input.KEY_Z || key == Input.KEY_UP) {
	            dy = 0;
	        }
	        if (key == Input.KEY_S || key == Input.KEY_DOWN) {
	            dy = 0;
	        }
	        if(key== Input.KEY_R || key== Input.KEY_ADD){
				dzoom=1;
			}
			if(key==Input.KEY_F || key== Input.KEY_SUBTRACT){
				dzoom=1;
			}
	    }
	    @Override
    public void keyPressed(int key, char c) {
	    	//ctrl alt shift
	    	if (key == Input.KEY_LCONTROL || key == Input.KEY_RCONTROL) {
	            ctrl=true;
	        }
	    	if (key == Input.KEY_LALT || key == Input.KEY_RALT) {
	            alt=true;
	        }
	    	if (key == Input.KEY_LSHIFT || key == Input.KEY_RSHIFT) {
	            shift=true;
	        }
	    	
	    	//debut mvnt
	        if (key == Input.KEY_Q || key == Input.KEY_LEFT) {
	            dx = -60/zoom;
	        }
	        if (key == Input.KEY_D || key == Input.KEY_RIGHT) {
	            dx = 60/zoom;
	        }
	        if (key == Input.KEY_Z || key == Input.KEY_UP) {
	            dy = -60/zoom;
	        }
	        if (key == Input.KEY_S || key == Input.KEY_DOWN) {
	            dy = 60/zoom;
	        }
	        if(key== Input.KEY_R || key== Input.KEY_ADD){
				dzoom=1.1f;
			}
			if(key==Input.KEY_F || key== Input.KEY_SUBTRACT){
				dzoom=0.9f;
			}
			//Controle du temps
			if(key==Input.KEY_L){
				setGameSpeed(Global.gameSpeed*2f);
			}
			if(key==Input.KEY_K){
				setGameSpeed(Global.gameSpeed/2f);

			}
			
			//Controle de l'exposition lumineuse
			if(key==Input.KEY_PERIOD){
				Global.exposition*=1.2f;
			}
			if(key==Input.KEY_COMMA){
				Global.exposition/=1.2f;

			}
			
			//mode admin
			if(key==Input.KEY_F1){
				Global.adminMod=!Global.adminMod;

			}
			//mode plein ecran
			if(key==Input.KEY_F2){
				try {
					gConteneur.setFullscreen(!gConteneur.isFullscreen());
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//raccourcis changement carte
			if(ctrl){//ctrl est utilisé
				//TODO Action si ctrl est enclenché
				
				
			}
			
			

			
			
	    }
		
	public void mouseMotion(GameContainer gc){
		/*int mx=gc.getInput().getMouseX();
		int my=gc.getInput().getMouseY();
		//System.out.println(gc.getInput().mo);
		float w=gc.getWidth();
		float h=gc.getHeight();

		if (mx<20) {
            posX -= (20-mx)/zoom;
        }
        if (mx>w-20) {
            posX += (20-(w-mx))/zoom;
        }
        if (my<20) {
            posY -= (20-my)/zoom;
        }
        if (my>h-20) {
            posY += (20-(h-my))/zoom;
        }*/
       
		
	}
	 
	public void mouseWheelMoved(int change){
		//System.out.println(change);
		/*if(change>0){
			//zoom=1.2f*zoom;
			zoom=(float) (((double)change*1.1/120)*zoom);
		}else if(change<0){
			//zoom=0.8f*zoom;
			zoom=(float) (((double)-change*0.9/120)*zoom);

		}*/
		float dc=((float) change)/1200f;
		if(dc<-0.5){
			dc=-0.5f;
		}else if(dc>0.5){
			dc=0.5f;
		}
		zoom=(1f+dc)*zoom;

		
		short largeur = (short) gConteneur.getWidth();
		short hauteur = (short) gConteneur.getHeight();
		short largeur2 = (short) (largeur/2);//largeur/2
		short hauteur2 = (short) (hauteur/2);//hauteur/2
		
		posX=(int)(posX+(gConteneur.getInput().getMouseX()-largeur2)/(zoom*4));
		posY=(int)(posY+(gConteneur.getInput().getMouseY()-hauteur2)/(zoom*4));
		
		/*posX=gConteneur.getInput().getMouseX()*zoom-posX;
		posY=gConteneur.getInput().getMouseY()*zoom-posY;*/

		/*if(key== Input.KEY_R || key== Input.KEY_ADD){
			dzoom=1.1f;
		}
		if(key==Input.KEY_F || key== Input.KEY_SUBTRACT){
			dzoom=0.9f;
		}*/
	}
	public void mousePressed(int button, int x, int y) {
		//Click souris-----------
		if(button==Input.MOUSE_LEFT_BUTTON){
			/*screenPosX= (short) ( ( ((int)posX-posX2) *zoom) +screenCenterX);
			scpx-scx=(px-px2)*z
					(scpx-scx)/z+px2=px*/
			
			
			int largeur =  gConteneur.getWidth();
			int hauteur =  gConteneur.getHeight();
			int largeur2 =  (largeur/2);//largeur/2
			int hauteur2 =  (hauteur/2);//hauteur/2
			
			double worldX= (posX+(double)(((double)(x-largeur2))/zoom));
			double worldY= (posY+(double)(((double)(y-hauteur2))/zoom));
			//System.out.println("("+worldX+","+worldY+")");
			/*if(0<=worldY && worldY<Constantes.galaxieDiam){
				Systemes[((int)worldX)/Constantes.AL][((int)worldY)/Constantes.AL].clickOn(worldX,worldY);

			}*/
			System.out.println("x="+worldX+", y="+worldY);
			for(int i=0;i<Constantes.nbSystemes;i++){
				Systemes[i].clickOn(worldX,worldY);

			}
			
		}
		
		if(button==Input.MOUSE_MIDDLE_BUTTON){
			clicMolette=true;
		}
		

	}
	
	public void mouseReleased(int button, int x, int y) {
		if(button==Input.MOUSE_MIDDLE_BUTTON){
			clicMolette=false;
		}
	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		if(clicMolette==true){//deplacement avec clic molette
			posX+=(oldx-newx)*zoom;
			posY+=(oldy-newy)*zoom;
		}
	}
	
	public void setGameSpeed(double s){
		Global.gameSpeed=s;
		Global.UT=Global.gameSpeed*Constantes.timeFramS;
	}
	
	
	public static void main(String arg[]) throws SlickException{
		//new AppGameContainer(new Fenetre("jeu"), 1920, 1080, true).start();//debut en plein ecran
		new AppGameContainer(new Fenetre("jeu"), 1366, 768, false).start();//debut fenetre

		//new Fenetre("jeu");
	}

	
	
}


	