package Astres;

import pack.Global;
import pack.Images;
import pack.Systeme;

public class Mercure extends Tellurique {

	public Mercure(double dist, Systeme syst) {
		super(dist, syst);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void genMasse() {
		masse=Global.rand(0.01, 0.1);
	}
	
	protected void genDiam(){
		this.diam=5;
	}
	
	
}
