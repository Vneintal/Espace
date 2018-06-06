package Astres;

import org.newdawn.slick.Graphics;

import enums.StarType;
import pack.Global;
import pack.Images;
import pack.Systeme;

public class Etoile extends Astre {

	//Etoile en orbite d'un systeme
	public Etoile(double dist,  Systeme syst) {
		super(dist, syst);
		setImage(Images.ETOILE);
	}
	
	//Etoile centrale d'un systeme
	public Etoile( Systeme syst) {
		super(0, syst);
		setImage(Images.ETOILE);

	}
	
	@Override
	public void genDens(){//TODO Temporaire, a changer, la valeur n'a aucun sens
		dens=1;
		if(syst.getStarType()==StarType.GR || syst.getStarType()==StarType.SGR ){
			dens=0.1;
		}if(syst.getStarType()==StarType.D ){
			dens=10;
		}if(syst.getStarType()==StarType.Q ){
			dens=100;
		}if(syst.getStarType()==StarType.X || syst.getStarType()==StarType.GX ){
			dens=10000;
		}
	}

	@Override
	protected void genMasse() {//TODO Temporaire, a changer, la valeur n'a aucun sens
		
		//sequence principal
		if(syst.getStarType()==StarType.M){
			masse=Global.rand(0.08e6, 0.4e6);
		}else if(syst.getStarType()==StarType.K){
			masse=Global.rand(0.5e6, 0.8e6);
		}else if(syst.getStarType()==StarType.G){
			masse=Global.rand(0.8e6, 1.2e6);
		}else if(syst.getStarType()==StarType.F){
			masse=Global.rand(0.2e6, 1.5e6);
		}else if(syst.getStarType()==StarType.A){
			masse=Global.rand(1.5e6, 3e6);
		}else if(syst.getStarType()==StarType.B){
			masse=Global.rand(2e6, 6e6);
		}else if(syst.getStarType()==StarType.O){
			masse=Global.rand(15e6, 90e6);
		}//Geantes
		else if(syst.getStarType()==StarType.GR){
			masse=Global.rand(0.3e6, 8e6);
		}else if(syst.getStarType()==StarType.SGR){
			masse=Global.rand(10e6, 80e6);
		}
		//Autres
		else if(syst.getStarType()==StarType.T){
			masse=Global.rand(0.01e6, 0.08e6);
		}else if(syst.getStarType()==StarType.D){
			masse=Global.rand(0.5e6, 0.7e6);
		}else if(syst.getStarType()==StarType.Q){
			masse=Global.rand(1.4e6, 3.2e6);
		}else if(syst.getStarType()==StarType.X){
			masse=Global.rand(3.2e6, 100e6);
		}else if(syst.getStarType()==StarType.GX){
			masse=Global.rand(1e12, 10e12);
		}
		
	}

	protected void genDiam(){
		//this.diam=2.*Math.cbrt((3*masse)/(4*Math.PI*dens));//(r=3/4 * pi*r^3)
		//sequence principal
				if(syst.getStarType()==StarType.M){
					this.diam=100;
				}else if(syst.getStarType()==StarType.K){
					this.diam=200;
				}else if(syst.getStarType()==StarType.G){
					this.diam=300;
				}else if(syst.getStarType()==StarType.F){
					this.diam=400;
				}else if(syst.getStarType()==StarType.A){
					this.diam=500;
				}else if(syst.getStarType()==StarType.B){
					this.diam=600;
				}else if(syst.getStarType()==StarType.O){
					this.diam=800;
				}//Geantes
				else if(syst.getStarType()==StarType.GR){
					this.diam=1000;
				}else if(syst.getStarType()==StarType.SGR){
					this.diam=10000;
				}
				//Autres
				else if(syst.getStarType()==StarType.T){
					this.diam=70;
				}else if(syst.getStarType()==StarType.D){
					this.diam=10;
				}else if(syst.getStarType()==StarType.Q){
					this.diam=2;
				}else if(syst.getStarType()==StarType.X){
					this.diam=2;
				}else if(syst.getStarType()==StarType.GX){
					this.diam=10000;
				}
	}

	@Override
	public void genAtmosphere(){
		atmos=null;//TODO gerer les atmospheres d'etoile?
	}
}
