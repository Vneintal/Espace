package astar;

public class Noeud {
	Noeud pere;//Utilise pour l'algo
	boolean v;//valeur du noeud, indique si on peut traverser 
	int cout;//cout du noeud, corespond au carré de la distance parcouru
	int h;//heuristique du noeud, carrée de la distance du chemin
	int x;//posx
	int y;//posy
	
	public Noeud(int x, int y,boolean valeur){
		this.x=x;
		this.y=y;
		this.v=valeur;
		this.cout=1;
		this.pere=null;
		this.h=0;
	}
	public Noeud(int x, int y,boolean valeur,Noeud pere){
		this.x=x;
		this.y=y;
		this.v=valeur;
		this.cout=1;
		this.pere=pere;
		this.h=0;
	}

	
	
	public int getHeuristique(){
		return h;
	}
	
	public Noeud getPere(){
		return pere;
	}
	
	public boolean equals(Object o){
		if(o instanceof Noeud){
			Noeud n=(Noeud) o;
			if(n.getX()==this.x && n.getY()==this.y){
				return true;
			}
			return false;
		}else{
			return false;
		}
		
	}
	
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public boolean getvaleur(){
		return v;
	}
	public int getCout(){
		return cout;
	}
	public void setCout(int cout){
		this.cout=cout;
	}
	public void setHeuristique(int heuristique){
		this.h=heuristique;
	}
	
}
