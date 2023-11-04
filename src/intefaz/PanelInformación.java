package intefaz;

import java.awt.GridLayout;

import javax.swing.*;

public class PanelInformación extends JPanel
{
	private InterfazPrincipal ventanaPrincipal;
	
	private JLabel labNumeroJugadas;
	private JLabel labNombreJugador;
	
	public PanelInformación(InterfazPrincipal pPrincipal)
	{
		ventanaPrincipal = pPrincipal; //ahora el panel conoce la ventana principal
		
		setLayout(new GridLayout(1,4));
		JLabel labJugadas = new JLabel("Jugadas:");
		labNumeroJugadas = new JLabel("0");
		JLabel labJugador = new JLabel("Jugador:");
		labNombreJugador = new JLabel("");
		
		//añadir al panel las etiquetas
		add(labJugadas);
		add(labNumeroJugadas);
		add(labJugador);
		add(labNombreJugador);
	}
	

	public String getNumeroJugadas()
	{
		return (labNumeroJugadas.getText());
	}
	public void actualizarJugadas(String numeroJugadas)
	{
		labNumeroJugadas.setText(numeroJugadas);
	}
	
	public void setNombreJugador(String nombre)
	{
		labNombreJugador.setText(nombre);
	}
}
