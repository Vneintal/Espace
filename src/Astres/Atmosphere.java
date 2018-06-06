package Astres;

import java.util.Map;
import java.util.TreeMap;

import enums.AtmComposants;

public class Atmosphere {
	
	protected int TSerre;//temperature due a l'effet de serre
	protected double P;//pression en bar
	protected Map<AtmComposants, Double> composition;
	protected double totalComp;//Somme des pressions des divers elements, utile pour ensuite avoir un pourcentage
	protected Astre astre;
	public Atmosphere(Astre a){
		composition= new TreeMap<AtmComposants, Double>();
		astre=a;
	}
	
	public int getTSerre(){
		return TSerre;
	}
	public double getPressure(){
		return P;
	}
	
	public Map<AtmComposants, Double> getComposition(){
		return composition;
	}
	
	/**
	 * @param element dont on veux connaitre le pourcentage dans l'atmosphere
	 * @return Pourcentage de l'element dans l'atmosphere
	 * */
	public float getPourcentElt(AtmComposants elt){
		return Math.round(1000f*composition.get(elt)/totalComp)/10f;
	}
	
	/**Calcul la somme des qauntite des elements dans l'atmosphere, necessaire pour calculer les pourcentages*/
	protected void setTotalComp(){
		double s=0;
		for(Map.Entry<AtmComposants,Double> elt : composition.entrySet()){
			s=s+elt.getValue();
		}
		totalComp=s;
	}
	
	public String showComposition(){
		String s="";
		boolean begin=false;
		for(Map.Entry<AtmComposants,Double> elt : composition.entrySet()){
			if(begin){
				s+=", ";
			}else{
				begin=true;
			}
			

			if(getPourcentElt(elt.getKey())!=0){
				s=s+elt.getKey()+": "+getPourcentElt(elt.getKey())+" %";
			}else{
				s=s+elt.getKey()+": traces";
			}
			
			
		}
		
		return s;
	}
	
	public String showPression(){
		String s="";
		float roundP=Math.round(100f*P)/100f;
		if(roundP==0){
			s+="négligeable";
		}else{
			s+=roundP+" bar";
		}
		
		return s;
	}
}
