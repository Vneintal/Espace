package pack;

import static pack.Global.Systemes;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;



public class Global {
	public static Systeme[] Systemes=new Systeme[Constantes.nbSystemes];	//Cases que contient le monde		
	
	public static boolean adminMod=true;
	public static double gameSpeed=1;
	public static double heure=0;
	public static double minute=0;
	public static double seconde=0;
	public static int lastSeconde=0;
	public static double UT=gameSpeed*Constantes.timeFramS;

	public static double posX;//pos de la camera
	public static double posY;//pos de la camera
	public static double zoom;//zoom de la camera

	public static double exposition=1;
	
	public static Object selectedThing=null;
	
	public static GameContainer gConteneur;
	
	/**a inclus et b exclus*/
	public static int randint(int a,int b){
		int nb=(int) (a+(b-a)*Math.random());
		if (nb>=b){
			nb=b-1;
		}
				
		return nb;
	}
	/**a et b inclus*/
	public static int randintInc(int a, int b){
		return randint(a,b+1);
	}
	
	/**a inclus, b exclus*/
	public static double rand(double a, double b){
		return a+(b-a)*Math.random();
	}
	
	/**les valeurs sont plus proche de a que de b*/
	public static double randExtreme(double a, double b,double coeff){

		return Math.pow(Math.random(),coeff)*(b-a)+a;
		
	}
	
	public static double mod(double k,int n){
	    if(k>=0){
	        return k%n;
	    }else{
	        while (k<0){
	            k+=n;
	        }
	        return k%n;
	    }
	}
	public static int mod(int k,int n){
	    if(k>=0){
	        return k%n;
	    }else{
	        while (k<0){
	            k+=n;
	        }
	        return k%n;
	    }
	}
	
	/**modulo la taille du monde*/
	public static double modWS(double d){
	    if(d>=0){
	        return d%((double)Constantes.galaxieDiamUB);
	    }else{
	        while (d<0){
	            d+=(double) Constantes.galaxieDiamUB;
	        }
	        return d%((double)Constantes.galaxieDiamUB);
	    }
	}
	
	/**modulo la taille du monde en cases*/
	public static int modWSC(int k){
	    if(k>=0){
	        return k%Constantes.nbSystemes;
	    }else{
	        while (k<0){
	            k+=Constantes.nbSystemes;
	        }
	        return k%Constantes.nbSystemes;
	    }
	}


	
}

