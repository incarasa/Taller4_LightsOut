package intefaz;

import com.formdev.flatlaf.FlatLightLaf;

public class MainLights {

	public static void main(String[] args) 
	{
		FlatLightLaf.install();
		InterfazPrincipal ventana1 = new InterfazPrincipal();
		ventana1.setVisible(true);
		

	}

}
