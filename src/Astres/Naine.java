package Astres;

import pack.Global;
import pack.Images;
import pack.Systeme;

public class Naine extends Tellurique {

	public Naine(double dist, Systeme syst) {
		super(dist, syst);
		// TODO Auto-generated constructor stub
		this.img=Images.PLANETE_SELENE0;
	}

	@Override
	protected void genMasse() {
		masse=Global.rand(0.0001, 0.01);
	}
	
	protected void genDiam(){
		this.diam=3;
	}
}
