package Astres;

import enums.AtmComposants;
import pack.Global;

public class AtmosphereGazeuse extends Atmosphere {

	public AtmosphereGazeuse(Astre a) {
		super(a);
		P=Double.NaN;
		TSerre=0;
		composition.put(AtmComposants.H2, 1.);
		composition.put(AtmComposants.He, Global.randExtreme(0, 1, 3));

		setTotalComp();
	}

	@Override
	public String showPression(){
		return "extrême";
	}
}
