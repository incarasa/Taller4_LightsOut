package intefaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelConfiguraci�n extends JPanel implements ActionListener
{
	private JComboBox boxTama�o;
	private InterfazPrincipal ventanaPrincipal;
	
	//botones
	private JRadioButton btnFacil;
	private JRadioButton btnMedio;
	private JRadioButton btnDificil;
	
	//atributos de informaci�n
	private int tama�o = 3; //3 o 5 para (3x3) o (5x5). Se dan valores por defecto.
	private int dificultad = 1; //luces que ser�n desordenadas. Se dan valores por defecto.
	
	public PanelConfiguraci�n(InterfazPrincipal pPrincipal) 
	{
		ventanaPrincipal = pPrincipal; //ahora el panel conoce la ventana principal
		
		setLayout(new GridLayout(1,6)); 
		JLabel labTama�oJLabel = new JLabel("Tama�o:");
		JLabel labDificultadJLabel = new JLabel("       Dificultad:");
		
		//Creaci�n del ComboBox
		String[] dificultades = {"3x3" , "5x5"};
		boxTama�o = new JComboBox(dificultades);
		boxTama�o.addActionListener(this); //a�ade action listener a boxTama�o
		
		//Creacion Botones
		btnFacil = new JRadioButton("Facil");
		btnMedio = new JRadioButton("Medio");
		btnDificil = new JRadioButton("Dificil");
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(btnFacil);
		group.add(btnMedio);
		group.add(btnDificil);
		
		//action-listeners botones
		btnFacil.addActionListener(this);
		btnMedio.addActionListener(this);
		btnDificil.addActionListener(this);
		
		//A�ade los elementos al panel
		add(labTama�oJLabel);
		add(boxTama�o);
		add(labDificultadJLabel);
		add(btnFacil);
		add(btnMedio);
		add(btnDificil);
		
		//Se activa el facil por defecto
		btnFacil.setSelected(true);
	}
	
	/*
	 * Por ahora este panel bota 0 si 3x3 o 1 si 5x5
	 * NUEVO: cambia la dificultad a 1, 3 o 5 dependiendo de lo seleccionado.
	 * NUEVO: cambia el tama�o a 3 o a 5 dependiendo de la opcion seleccionada
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== boxTama�o)
		{
			System.out.println(boxTama�o.getSelectedIndex()); //imprime el indice del action listen
			int indice = boxTama�o.getSelectedIndex(); //dependiendo de la posicion en la lista
			if(indice == 0)
			{
				tama�o = 3;
			}
			/*
			 * Puede abrirse este else con else if cuantas veces sea necesario
			 * dependiendo de las opciones de la lista que se le agreguen al juego.
			 */
			else 
			{
				tama�o = 5;
			}
			
		}
		else if(e.getSource()== btnFacil)
		{
			dificultad = 1;
			System.out.println("F�cil");
		}
		else if(e.getSource()== btnMedio)
		{
			dificultad = 3;
			System.out.println("Medio");
		}
		else if(e.getSource()== btnDificil)
		{
			dificultad = 5;
			System.out.println("Dificil");
		}
		
	}
	/**
	 * Estas funciones son GETTERS que devuelven la infomaci�n importante de este panel
	 * 
	 */
	public int darTama�o() {
		return tama�o;
	}

	public int darDificultad() {
		return dificultad;
	}
	
}
