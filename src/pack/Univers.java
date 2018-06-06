package pack;

import static pack.Global.*;
import static pack.Constantes.*;


import enums.Ressource;
import enums.StarType;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Univers {
	
	
	
	public static void creatNewUniverse() {
		int i=0;
		while (i<Constantes.nbSystemes-1){//on repéte l'opertion jusqu'a ce que tout les systemes prevuent aient été crée
			int[] gpos=randGalactElliptiquePos(4,0.0002);//position aleatoire dans la galaxie
			StarType t=randStarType();
			boolean b=true;
			for(int j=0;j<i;j++){
				if(Global.Systemes[j].getSystPosXPm()==gpos[0] && Global.Systemes[j].getSystPosYPm()==gpos[1]){
					b=false;
				}
			}
			if(b){
				try {
					Global.Systemes[i]=(new Systeme(gpos[0],gpos[1],t));
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
		}
		try {
			Global.Systemes[i]=(new Systeme(0,0,StarType.GX));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}
	
	/**L'etoile est d'un type aleatoire*/
	public static StarType randStarType(){
		
		//Seqence principale: 97%
		double r=Math.random();
		if(r<0.20){
			return StarType.T;//Naine brune
		}else if(r<0.74){
			return StarType.M;
		}else if (r<0.86){
			return StarType.K;
		}else if (r<0.93){
			return StarType.G;
		}else if (r<0.96){
			return StarType.F;
		}else if (r<0.966){
			return StarType.A;
		}else if (r<0.968){
			return StarType.B;
		}else if (r<0.9680003){
			return StarType.O;
		}
		//Autre
		else if (r<0.99){//Naine Blanche
			return StarType.D;
		}else if (r<0.996){
			return StarType.GR;
		}else if (r<0.997){
			return StarType.SGR;
		}else if (r<0.999){
			return StarType.Q;
		}else if (r<1){
			return StarType.X;
		}
		
		
		
		return StarType.M;
		
		

	}
	
	/**Repartition eliptique*/
	public static int[] randGalactElliptiquePos(){
		double r=Math.random()*360;
		double d=Math.random()*Math.random()*Constantes.galaxieDiamPm;
		
		int[] res={(int) (d*Math.cos(r)),(int) (d*Math.sin(r))};//eliptique

		return res;
	}
	
	/**Repartition Spirale a n branche*/
	public static int[] randGalactElliptiquePos(int n,double spirCoef){
		double d=Math.pow(0.01+Math.random(),1.3)*Constantes.galaxieDiamPm;//distance au centre, 3 pour eviter une surpopulation au centre
		double fixAngle=2*Math.PI*((double)randint(0,n))/n+spirCoef*d;
		//double varAngle=randDensBras()*Constantes.galaxieDiamAL/(Math.pow(d, 1.3));//Ancien, bras plus fin
		double varAngle=randDensBras()*Constantes.galaxieDiamPm/(Math.pow(d, 1.1));//bras trés epais

		double r=fixAngle+varAngle;

		int[] res={(int) (d*Math.cos(r)),(int) (d*Math.sin(r))};//eliptique

		return res;//position de l'etoile
	}

	private static double randDensBras(){
		double i=Math.random();
		double j=Math.random();

		double res = i*(j-0.5);
		
		
		return res;
	}
	
}