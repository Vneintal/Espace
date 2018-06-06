package astar;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Vector;

import pack.Constantes;
import pack.Global;

public class AStar {

	
	/**L'arrivé et le depart doivent etre atteignable*/
	public static Vector<Noeud> chercheChemin(int xDepart, int yDepart, int xArrive, int yArrive, boolean[][] graph){
		
		//variables
		Noeud depart=new Noeud(xDepart,yDepart,true);
		Noeud arrive=new Noeud(xArrive,yArrive,true);
		
		Noeud tmp;//noud utilisé dans la boucle principale
		Noeud tmpv;//voisin de tmp
		
		Vector<Noeud> open=new Vector<Noeud>();
		Vector<Noeud> close=new Vector<Noeud>();
		
		//init
		open.add(depart);
		
		int cpt=0;
		//debut
		while (open.isEmpty()==false){
			cpt+=1;
			//System.out.println(cpt);
			if(cpt>500){//TODO Mieux definir la valeur du compteur?(meilleur valeur? valeur parametrable?)
				return null;
			}
			
			tmp=depile(open);
			if(tmp.equals(arrive)){//si objectif atteint
				//System.out.println("**********************************Objectif atteint*******************************************");
				return reconstituerChemin(tmp);
			}else{
				//pour tout les voisins
				for(int x0=tmp.getX()-1;x0<=tmp.getX()+1;x0++){
					for(int y=tmp.getY()-1;y<=tmp.getY()+1;y++){
						int x=Global.mod(x0, Constantes.nbSystemes);//La valeur ne doit pas subire d'effets de bord
						
						if(!(x==tmp.getX() && y==tmp.getY())  &&//Si la case est voisine mais differente
							(0<=x && x<Constantes.nbSystemes)  &&//Si la position x est valide (on considere que le graph est centré sur le point de depart)//TODO centrer le graph dans cette fonction
							(0<=y && y<Constantes.nbSystemes)&&//Si la position y est valide;
							(  graph[x][y]  ==  true  )       ){//Si le noeud est accessible;
						
									tmpv=new Noeud(x,y,graph[x][y],tmp);
									int ido=indiceDansListe(tmpv, open);
									int idc=indiceDansListe(tmpv, close);
	
									boolean b1=false;
									boolean b2=false;
									
									if(ido!=-1){
										if(open.get(ido).getCout()<tmp.getCout()+1){
											b1=true;
										}
									}
									if(idc!=-1){
										if(close.get(idc).getCout()<tmp.getCout()+1){
											b2=true;
										}
									}
									
									if(!(b1 || b2)){
										//if(tmp.getX()==tmpv.getX() || tmp.getY()==tmpv.getY()){
											tmpv.setCout(tmp.cout+1);//ligne droite//TODO eventuellement ameliorer le cout des chemins ici
										/*}else{
											tmpv.setCout(tmp.cout+2);//diagonale, cout au carré
										}*/
											
										tmpv.setHeuristique(tmpv.getCout()+distancep2(tmpv,arrive));
										open.add(tmpv);
										/*Global.worldCases[tmpv.getX()][tmpv.getY()].setR((short) 255);
										Global.worldCases[tmpv.getX()][tmpv.getY()].setV((short) 255);
										Global.worldCases[tmpv.getX()][tmpv.getY()].setB((short) 0);*/
									}
									
									
									//System.out.println(x+" "+y);
														
						}
					}
				}
				close.add(tmp);
				/*Global.worldCases[tmp.getX()][tmp.getY()].setR((short) 255);
				Global.worldCases[tmp.getX()][tmp.getY()].setV((short) 0);
				Global.worldCases[tmp.getX()][tmp.getY()].setB((short) 255);*/

				

			}
		}
		return null;//erreur
	}
	
	/**n1<n2 renvoie 1, n1=n2 renvoie 0, n1>n2 renvoie -1*/
	static int compare(Noeud n1,Noeud n2){
		if(n1.getHeuristique()<n2.getHeuristique()){
			return  -1;
		}else if(n1.getHeuristique()==n2.getHeuristique()){
			return  0;
		}else{
			return 1;
		}	
	}
	
	
	static Noeud depile(Vector<Noeud> v){
		int id=0;//indice de l'element a depiler
		for (int i=0;i<v.size();i++){
			
			if(compare(v.get(id),v.get(i))==1){
				id=i;
			}
		}	
		return v.remove(id);	
	}
	
	/**Le dernier noeud est celui de depart*/
	static Vector<Noeud> reconstituerChemin(Noeud n){
		Vector<Noeud> v=new Vector<Noeud>();
		
		v.add(n);
		while(v.lastElement().getPere()!=null){
			v.addElement(v.lastElement().getPere());
		}
		
		return v;
	}
	
	static int indiceDansListe(Noeud n, Vector<Noeud> v){
		for(int i=0;i<v.size();i++){
			if(n.equals(v.get(i))){
				return i;
			}
		}
		return -1;//correspond a une erreur
	}
	
	/**La distance est au carree*/
	static int distancep2(Noeud n1, Noeud n2){
		int x=n1.getX()-n2.getX();
		int y=n1.getY()-n2.getY();
		
		int d1=x*x+y*y;
		
		x=(Constantes.nbSystemes+n1.getX())-n2.getX();
		int d2=x*x+y*y;
		
		x=n1.getX()-(Constantes.nbSystemes+n2.getX());
		int d3=x*x+y*y;

		
		/*if(x>Constantes.worldSizeC/2){
			System.out.println("cas a:"+x);
			x=x-Constantes.worldSizeC/2;
		}
		if(x<-Constantes.worldSizeC/2){
			System.out.println("cas b:"+x);
			x=x+Constantes.worldSizeC/2;
		}*/
		
		
		return Math.min(d1,Math.min(d2, d3));
	}
	
}
