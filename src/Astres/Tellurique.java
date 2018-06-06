package Astres;

import pack.Global;
import pack.Images;
import pack.Systeme;

public abstract class Tellurique extends Astre {

	public boolean vie=false;
	public boolean vieTerrestre=false;
	public boolean photosythese=false;
	public boolean civ=false;

	//public boolean photosythese=false;

	
	public Tellurique(double dist,  Systeme syst) {
		super(dist, syst);
		if(atmos.getPressure()<0.01){
			this.img=Images.PLANETE_SELENE0;
		}else if(atmos.getPressure()>20){
			this.img=Images.PLANETE_VENUS0;
		}else if(253>getTemperature() || getTemperature()>373 || getMasse()<=0.05 || Math.random()<0.1){//random pr planete hab en zone habitable
			this.img=Images.PLANETE_MARS0;
		}else if(Math.random()<0.1){//monde ocean
			dens=dens/2;
			this.img=Images.PLANETE_OCEAN0;
		}else{//monde habitable
			this.img=Images.PLANETE_TERRA0;
			if(Math.random()<0.9 && syst.getAge()>0.5){//si la vie est apparue
				vie=true;
			
				if(Math.random()<0.1*(syst.getAge()-1)){//Les chances de photosythese augmentes
					((AtmosphereTellurique) atmos).usePhotosynthese();
					if(253>getTemperature() || getTemperature()>353){//Si la photosyntese a ete trop efficase
						this.img=Images.PLANETE_GLACE0;
						//vie=false;
					}else{
						photosythese=true;
						if(Math.random()<0.2*(syst.getAge()-2)){//Si la vie terrestre est apparue
							this.img=Images.PLANETE_VIE0;
							vieTerrestre=true;
			
						}
					}
				}else{
					if(Math.random()<0.2*(syst.getAge()-2)){//Si la vie terrestre est apparue, mais avec une autre photosythese
						this.img=Images.PLANETE_VIE1;
						vieTerrestre=true;
					}
					
				}
			}
			
		}
		
		
		if(vieTerrestre && Math.random()<0.01*(syst.getAge()-4)){//civilisation
			civ=true;
		}
	}
	
	@Override
	public void genDens(){
		if(TBase>173){
			dens=Global.rand(3, 6);
		}else{
			dens=Global.rand(1.5, 4);
		}
	}
	
	@Override
	public void genAtmosphere(){
		atmos=new AtmosphereTellurique(this);
	}
}
