package Astres;

import pack.Global;
import pack.Images;
import pack.Systeme;

public class Neptune extends Gazeuses {

	public Neptune(double dist, Systeme syst) {
		super(dist, syst);
		// TODO Auto-generated constructor stub
		this.img=Images.PLANETE_GAZ0;

	}

	@Override
	protected void genMasse() {
		masse=Global.rand(10, 50);
	}
	
	protected void genDiam(){
		this.diam=20;
	}
}
