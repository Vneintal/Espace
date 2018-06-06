package civ;

import java.io.Serializable;

import especes.Espece;

public class Pop implements Serializable, Cloneable{

	private Espece espece;
	private double pop;//Population totale de la pop
}
