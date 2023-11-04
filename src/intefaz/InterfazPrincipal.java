package intefaz;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;

import javax.swing.*;

import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

public class InterfazPrincipal extends JFrame
{
	private PanelConfiguración panelConfiguración = new PanelConfiguración(this);
	private PanelJuego panelJuego = new PanelJuego(this);
	private PanelOpciones panelOpciones = new PanelOpciones(this);
	private PanelInformación panelInformación = new PanelInformación(this);
	private String nombreJugador;
	private Top10 top10;
	private File archivoTop10;
	
	public InterfazPrincipal()
	{
		setTitle("Lights Out Game");
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		
		add(panelConfiguración, BorderLayout.NORTH);
		add(panelJuego , BorderLayout.WEST);
		add(panelOpciones , BorderLayout.CENTER);
		add(panelInformación , BorderLayout.SOUTH);
		
		//Iniciar el top 10
		top10 = new Top10();
		
		//Crear el file
		archivoTop10 = new File("C:\\Users\\incar\\eclipse-workspace\\Taller4_LightsOut_esqueleto\\data\\top10.csv");
		
		//Se le pide el nombre al jugador
				nombreJugador = JOptionPane.showInputDialog(this, "Introduzca su nombre",
						"Iniciar juego " , JOptionPane.QUESTION_MESSAGE);
				
				//se pone el nombre en el panelInfo
				panelInformación.setNombreJugador(nombreJugador);
		
	}
	/**
	 * Estas funciones se encargan de averiguar cuando mide el panel de Juego para enviarselo
	 * al pintor
	 * @alto
	 * @ancho
	 */
	public int darAltoJuego()
	{
		return panelJuego.getHeight();
	}
	public int darAnchoJuego()
	{
		return panelJuego.getWidth();
	}
	
	public PanelJuego getPanelJuego()
	{
		return panelJuego;
	}
	
	//funcion para iniciar un nuevo juego
	public void iniciarNuevoJuego()
	{
		/* se obtiene el tamaño y la dificultad para enviar al pintor
		 * al igual que el alto y ancho del tablero.
		 */
		int tamaño = panelConfiguración.darTamaño();
		int dificultad = panelConfiguración.darDificultad();
		int altoJuego = darAltoJuego();
		int anchoJuego = darAnchoJuego();
		
		//se inicia un tablero nuevo
		Tablero tablero = new Tablero(tamaño);
		
		
		
		//
		//se le envía a el Panel pintor de Juego pero antes se desordena
		tablero.desordenar(dificultad);
		panelJuego.setTablero(tablero);
		
		//se envía otra info importante
		panelJuego.setTamaño(tamaño);
		panelJuego.setDificultad(dificultad);
		panelJuego.setHeightTablero(altoJuego);
		panelJuego.setWidthTablero(anchoJuego);
		
		//Se quita has ganado
		panelJuego.cambiarEsGanador(false);
		
		//Se pone las jugadas en 0
		panelInformación.actualizarJugadas("0");
		
		//se refresca
		panelJuego.repintar();
	}
	/**
	 * Esta funcion hace una jugada y mira si la persona ganó el juego o no
	 * @param fila
	 * @param columna
	 * @param tablero
	 */
	public void Jugar(int fila, int columna , Tablero tablero)
	{
		tablero.jugar(fila, columna);
		panelJuego.repintar();
		
		//sumar una jugada al panel de la informacion
		int jugadas = Integer.parseInt(panelInformación.getNumeroJugadas());
		jugadas ++;
		panelInformación.actualizarJugadas(String.valueOf(jugadas));
		
		//comprobar si con la jugada el jugador ganó
		boolean gano = tablero.tableroIluminado();
		if(gano)
		{
			//calcular el puntaje
			int puntaje = tablero.calcularPuntaje();
			
			//revisar y actualizar el top10
			if(top10.esTop10(puntaje)) 
			{
				//actualizar top10
				top10.agregarRegistro(nombreJugador, puntaje);
				try {
					top10.salvarRecords(archivoTop10);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			
			
			
			panelJuego.cambiarEsGanador(gano); //cambia a true el parametro para imprimir que gano.
			JOptionPane.showMessageDialog(this, "Has ganado el juego. Felicitaciones!!! "+
					"Tu puntaje es: " + Integer.toString(puntaje) + " puntos.",
					"Ganador", JOptionPane.INFORMATION_MESSAGE);

		}
	}
	
	//METODO PARA REINICIAR
	public void reiniciar() 
	{
		panelInformación.actualizarJugadas("0");
		Tablero tablero = panelJuego.getTablero();
		tablero.reiniciar();
		panelJuego.repintar();
	}
	
	//METODO PARA MOSTRAR EL TOP 10
	public void mostrarTop10()
	{
		
		//Cargar info
		top10.cargarRecords(archivoTop10);
		
		//pasar 
		
		InterfazSecundaria ventanaTop10 = new InterfazSecundaria(top10);
		ventanaTop10.setVisible(true);
		
		
	}
	

	//METODO PARA CAMBIAR EL NOMBRE DEL JUGADOR
	public void cambiarNombre()
	{
		//Se le pide el nombre al jugador
		nombreJugador = JOptionPane.showInputDialog(this, "Introduzca su nombre",
						"Cambiar nombre " , JOptionPane.QUESTION_MESSAGE);
				
		//se pone el nombre en el panelInfo
		panelInformación.setNombreJugador(nombreJugador);
	}
	

	

}
