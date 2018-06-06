package pack;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import Astres.Astre;
import Astres.Tellurique;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

public class HUD {

	private static double sZoom;
	private static double sPosX;
	private static double sPosY;
	
	public static void afficher(GameContainer gc,Graphics g){

		float w=gc.getWidth();
		float h=gc.getHeight();
		
		int mouseX=gc.getInput().getMouseX();
		int mouseY=gc.getInput().getMouseY();
		
		g.setColor(Color.yellow);
		
		InFenDebug.afficher(g,gc);
				
		//g.fillRect(w-200, h-70, 100, 3);
		
		affObjProp(gc,g);
		
		//nbcell
		g.drawString("Il y a "+Global.Systemes.length+" Systemes", 50, 30);//Pop
		
		//Affichage des astres interesents
		int nbTerra=0;
		for(Systeme s : Global.Systemes){
			for(int i=0 ; i<s.getNbAstres();i++){
				if(s.getAstre(i) instanceof Tellurique){
					Tellurique a=(Tellurique) s.getAstre(i);
					//if(a.getMasse() >= 0.01){
					if(a.civ){
					//if(a.img==Images.PLANETE_VIE0){
					//if(a.getTemperature()>273-20 && a.getTemperature()<273+100 && a.getMasse() >= 0.01){
						nbTerra+=1;
					}
				}
			}
		}
		g.drawString("Il y a "+nbTerra+" civilisations", 170, h-50);//Pop

		//echelle
		g.drawString(Float.toString((float) (9.6/(sZoom*Constantes.Pm))), w-170, h-50);//Pop

		g.drawString(Global.heure+"h "+Global.minute+" min "+((int)Global.seconde)+" s", 50,50);
		g.drawString(Double.toString(Global.gameSpeed)+"x", 50, 70);
	}



	
	public static void setScreenProp(double posX, double posY, double zoom) {
		sPosX=posX;sPosY=posY;sZoom=zoom;
	}
	
	/**Affiche les propriétés d'un objet (systeme, vaisseaux ou planete)*/
	private static void affObjProp(GameContainer gc,Graphics g){
		float w=gc.getWidth();
		float h=gc.getHeight();
		
		g.fillRect(w-200, h-70, 100, 3);
		
		/**Si rien n'est selectionné*/
		if(Global.selectedThing==null){
			g.drawString("Aucun Objet selectionnée", w-400, 50);

		/**Affichage de systeme, infos sur l'etoile*/
		}else if(Global.selectedThing instanceof Systeme){
			Systeme obj=(Systeme)Global.selectedThing;
			g.drawRoundRect(w-400, 50, w-50, 150,10);
			g.drawString("Objet Selectionnée: "+obj.toString(), w-390, 50);
			g.drawString("Type: "+obj.getStarType(), w-390, 70);
			//g.drawString("position: "+obj.getSystPosXPm()/9.4+","+obj.getSystPosYPm()/9.4, w-390, 90);
			g.drawString("astres: "+obj.getNbAstres(), w-390, 90);
			g.drawString("lum: "+obj.getLum(), w-390, 110);
			g.drawString("age: "+obj.getAge(), w-390, 130);


			/**Affichage du astre (planete, lune, asteroide)*/
		}else if(Global.selectedThing instanceof Astre){
			Astre obj=(Astre)Global.selectedThing;
			g.drawRoundRect(w-400, 50, w-50, 150,10);
			g.drawString("Objet Selectionnée: "+obj.toString(), w-390, 50);
			g.drawString("Type: "+obj.getClass(), w-390, 70);
			g.drawString("distance: "+obj.getDist()+" Gm", w-390, 90);
			g.drawString("masse: "+obj.getMasse()+" kg", w-390, 110);
			g.drawString("diamètre: "+obj.getDiam()+" Mm", w-390, 130);
			g.drawString("densité: "+obj.getDens(), w-390, 150);
			g.drawString("temperature: "+(obj.getTemperature()-273)+" °C", w-390, 170);

			
			if(obj.hasAtmopshere()){
				g.drawString("Atmosphere: ", w-390, 210);
				g.drawString("Pression: "+obj.getAtmosphere().showPression(), w-390, 230);
				g.drawString("Composition: "+obj.getAtmosphere().showComposition(), w-390, 250);
			}


		}
	}
}
