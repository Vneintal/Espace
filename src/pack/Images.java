package pack;

/*import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;*/
import org.newdawn.slick.Image;

public class Images {
	
	public static Image PIXEL;
	
	public static Image ETOILE;
	public static Image FLARE;
	

	public static Image PLANETE_GAZ0;
	public static Image PLANETE_SELENE0;
	public static Image PLANETE_MARS0;
	
	public static Image PLANETE_VENUS0;
	public static Image PLANETE_TERRA0;
	public static Image PLANETE_VIE0;
	
	public static Image PLANETE_OCEAN0;
	public static Image PLANETE_GLACE0;
	public static Image PLANETE_VIE1;
	
	public static int nbImagesCharged=0;
	public static final int nbImages=9;
	
	
	
	public static void charger(){
		try {
			
			FLARE=new Image("image/etoiles/flare.png");
			nbImagesCharged+=1;
			ETOILE=new Image("image/etoiles/etoile.png");
			nbImagesCharged+=1;
			
			PLANETE_GAZ0=new Image("image/planetes/gaz0.png");
			nbImagesCharged+=1;
			PLANETE_SELENE0=new Image("image/planetes/planete_selene0.png");
			nbImagesCharged+=1;
			PLANETE_MARS0=new Image("image/planetes/planete_mars0.png");
			nbImagesCharged+=1;
			PLANETE_VENUS0=new Image("image/planetes/planete_venus0.png");
			nbImagesCharged+=1;
			PLANETE_TERRA0=new Image("image/planetes/planete_terra0.png");
			nbImagesCharged+=1;
			PLANETE_VIE0=new Image("image/planetes/planete_hab1.png");
			nbImagesCharged+=1;
			PLANETE_VIE1=new Image("image/planetes/planete_hab2.png");
			nbImagesCharged+=1;
			PLANETE_OCEAN0=new Image("image/planetes/planete_ocean0.png");
			nbImagesCharged+=1;
			PLANETE_GLACE0=new Image("image/planetes/planete_glace0.png");
			nbImagesCharged+=1;
			
			PIXEL= new Image("image/pixel.png");
			PIXEL.setFilter(0);
			nbImagesCharged+=1;
			
		} catch (Exception e) {
			System.out.println("Certaines images sont manquantes");
			e.printStackTrace();
		}
	}
}
