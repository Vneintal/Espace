package Astres;

import java.util.Map;
import java.util.Map.Entry;

import org.newdawn.slick.Image;

import enums.AtmComposants;
import pack.Global;
import pack.Images;

public class AtmosphereTellurique extends Atmosphere {

	public AtmosphereTellurique(Astre a) {
		super(a);
		if(a.masse<0.05){//Planete trop petites pour avoir une vrai atmosphpere
			P=0;
		}else{
			P=Global.randExtreme(0, 100*(a.getMasse()-0.05), 6);

		}
		double r=Math.random();//Probabilité pour le composant principal de l'atmosphere
		if(r<0.4){//Composé avant tout d'azote
			composition.put(AtmComposants.N2, 1.);
			composition.put(AtmComposants.CO2, Global.randExtreme(0, 1, 5));
		}else if(r<0.95){//Composé avant tout de CO2
			composition.put(AtmComposants.CO2, 1.);
			composition.put(AtmComposants.N2, Global.randExtreme(0, 1, 5));
		}else{//Composé d'hydrogene (rare)
			composition.put(AtmComposants.H2, 1.);
			composition.put(AtmComposants.CO2, Global.randExtreme(0, 1, 3));
			composition.put(AtmComposants.N2, Global.randExtreme(0, 1, 3));
		}
			
		genTSerre();//setTotalComp(); est inclus
		if(P>10){
			a.setImage(Images.PLANETE_VENUS0);
		}else if(P>0.005){
			if(a.getTemperature()<100 && a.getTemperature()>0){
				a.setImage(Images.PLANETE_TERRA0);
			}else{
				a.setImage(Images.PLANETE_MARS0);
			}
			
		}
		
	}
	/**Lorsqu'il y a de la vie utilisant la photosynthese*/
	public void usePhotosynthese(){
		composition.put(AtmComposants.O2, Global.randExtreme(0.01,1,3));
		composition.put(AtmComposants.CO2, composition.get(AtmComposants.CO2)/10);
		
		genTSerre();


	}

	public void genTSerre(){
		setTotalComp();//Au cas ou
		if(P>0){
			TSerre=(int) (0.05*P*(30+getPourcentElt(AtmComposants.CO2)));
		}
	}
}
