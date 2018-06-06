package pack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class InFenDebug {

	private static String msg="";
	
	public static void afficher(Graphics g,GameContainer gc) {

		float w=gc.getWidth();
		float h=gc.getHeight();

		//g.drawRoundRect(0,h-200, w/2,h,5);
		g.drawString(msg, 0, h-190);
		
	}
	
	public static void println(String ms){
		msg+="\n"+ms;
		//System.out.println(msg);
		if(msg.length()>100){
			msg=msg.substring(50, msg.length()-1);
		}
	}
	
}
