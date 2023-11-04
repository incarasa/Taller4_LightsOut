package intefaz;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import uniandes.dpoo.taller4.modelo.Tablero;

public class PanelOpciones extends JPanel implements ActionListener
{
	private InterfazPrincipal ventanaPrincipal;
	
	private JButton btnNuevo;
	private JButton btnReiniciar;
	private JButton btnTop10;
	private JButton btnCambiarJugador;
	
	public PanelOpciones(InterfazPrincipal pPrincipal)
	{
		ventanaPrincipal = pPrincipal; //ahora el panel conoce la ventana principal
		
		setLayout(new GridLayout(10,1));
		
		btnNuevo = new JButton("Nuevo");
		btnReiniciar = new JButton("Reiniciar");
		btnTop10 = new JButton("Top10");
		btnCambiarJugador = new JButton("Cambiar Jugador");
		
		//action-listener
		btnNuevo.addActionListener(this);
		btnTop10.addActionListener(this);
		btnReiniciar.addActionListener(this);
		btnCambiarJugador.addActionListener(this);
		
		//se añaden los elementos al panel
		add(new JLabel(""));
		add(new JLabel(""));
		add(btnNuevo);
		add(new JLabel(""));
		add(btnReiniciar);
		add(new JLabel(""));
		add(btnTop10);
		add(new JLabel(""));
		add(btnCambiarJugador);
		add(new JLabel(""));
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNuevo) 
		{
			System.out.println("nuevoooo");
			ventanaPrincipal.darAltoJuego();
			ventanaPrincipal.iniciarNuevoJuego();
			
		}
		else if (e.getSource()==btnReiniciar)
		{
			ventanaPrincipal.reiniciar();
		}
		else if (e.getSource()==btnTop10)
		{
			ventanaPrincipal.mostrarTop10();
		}
		else if(e.getSource()==btnCambiarJugador)
		{
			ventanaPrincipal.cambiarNombre();
		}
		
	}
}
