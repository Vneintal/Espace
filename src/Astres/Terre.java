package Astres;

import pack.Global;
import pack.Images;
import pack.Systeme;

public class Terre extends Tellurique {

	public Terre(double dist, Systeme syst) {
		super(dist, syst);
		// TODO Auto-generated constructor stub
		//this.img=Images.PLANETE_TERRA0;

	}

	@Override
	protected void genMasse() {
		masse=Global.rand(0.1, 10);
	}

	protected void genDiam(){
		this.diam=10;
	}
	
	
}
