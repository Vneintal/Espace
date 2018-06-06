package Astres;

import pack.Global;
import pack.Systeme;

public abstract class Gazeuses extends Astre {

	public Gazeuses(double dist, Systeme syst) {
		super(dist, syst);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void genDens(){
		dens=Global.rand(0.5,1.5);
	}

	@Override
	public void genAtmosphere(){
		atmos=new AtmosphereGazeuse(this);
	}
	

}
