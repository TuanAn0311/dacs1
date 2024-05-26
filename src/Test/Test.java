package Test;

import java.security.SignatureException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import SignUp_SignIn.SignIn;



public class Test {
	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SignIn s = new SignIn();
			s.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	
	}
}
