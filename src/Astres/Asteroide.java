package Astres;

import pack.Global;
import pack.Images;
import pack.Systeme;

public class Asteroide extends Tellurique {

	public Asteroide(double dist, Systeme syst) {
		super(dist, syst);
		// TODO Auto-generated constructor stub
		this.img=Images.PLANETE_SELENE0;

	}
	
	@Override
	protected void genMasse() {
		masse=Global.rand(0.000001, 0.0001);
	}

	protected void genDiam(){
		this.diam=1;
	}
	
}
