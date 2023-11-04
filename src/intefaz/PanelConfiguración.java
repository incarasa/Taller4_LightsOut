package intefaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelConfiguración extends JPanel implements ActionListener
{
	private JComboBox boxTamaño;
	private InterfazPrincipal ventanaPrincipal;
	
	//botones
	private JRadioButton btnFacil;
	private JRadioButton btnMedio;
	private JRadioButton btnDificil;
	
	//atributos de información
	private int tamaño = 3; //3 o 5 para (3x3) o (5x5). Se dan valores por defecto.
	private int dificultad = 1; //luces que serán desordenadas. Se dan valores por defecto.
	
	public PanelConfiguración(InterfazPrincipal pPrincipal) 
	{
		ventanaPrincipal = pPrincipal; //ahora el panel conoce la ventana principal
		
		setLayout(new GridLayout(1,6)); 
		JLabel labTamañoJLabel = new JLabel("Tamaño:");
		JLabel labDificultadJLabel = new JLabel("       Dificultad:");
		
		//Creación del ComboBox
		String[] dificultades = {"3x3" , "5x5"};
		boxTamaño = new JComboBox(dificultades);
		boxTamaño.addActionListener(this); //añade action listener a boxTamaño
		
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
		
		//Añade los elementos al panel
		add(labTamañoJLabel);
		add(boxTamaño);
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
	 * NUEVO: cambia el tamaño a 3 o a 5 dependiendo de la opcion seleccionada
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== boxTamaño)
		{
			System.out.println(boxTamaño.getSelectedIndex()); //imprime el indice del action listen
			int indice = boxTamaño.getSelectedIndex(); //dependiendo de la posicion en la lista
			if(indice == 0)
			{
				tamaño = 3;
			}
			/*
			 * Puede abrirse este else con else if cuantas veces sea necesario
			 * dependiendo de las opciones de la lista que se le agreguen al juego.
			 */
			else 
			{
				tamaño = 5;
			}
			
		}
		else if(e.getSource()== btnFacil)
		{
			dificultad = 1;
			System.out.println("Fácil");
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
	 * Estas funciones son GETTERS que devuelven la infomación importante de este panel
	 * 
	 */
	public int darTamaño() {
		return tamaño;
	}

	public int darDificultad() {
		return dificultad;
	}
	
}
