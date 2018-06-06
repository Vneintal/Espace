package pack;

import org.newdawn.slick.Color;

import enums.CarteMod;

public class Constantes {
	//donne technique
	public static /*final*/ int timeFramMs=20;
	public static /*final*/ double timeFramS=0.001*timeFramMs;
	
	
	//donnes du monde
	public static final int nbSystemes=40048;//largeur du monde;circonference
	public static final long Pm=100000;//un peta-metre, correspond a la taille d'un systeme
	public static final int galaxieDiamPm=(int) (20000);
	public static final int galaxieDiamUB=(int) (galaxieDiamPm*Pm);


	public static final double densFirstCell = 0.0001;
	
	public static final Color oceanColor= new Color(0,0,91);
	
	public static final int multVueCellSize= 10000;//seuil de zoom a partir duquel les cellules sont visible

	public static final float friction=1.1f;
	
	
	//Info de creation des systemes
	public static final double nbPlanetes = 1.5;//Nombre moyen de planetes par systemes
	public static final double nbAstres = 3;//nombre moyen de planetes naines et asteroides par systemes

	//Proba par types de planetes
	public static final double nbJupiter = 0.15;
	public static final double nbNeptune = 0.2;
	public static final double nbTerre = 0.4;
	public static final double nbMercure = 0.25;
	
	//Proba par types de planetes naines et asteroides
	public static final double nbNaine = 0.2;
	public static final double nbAsteroide = 0.8;





	
}
