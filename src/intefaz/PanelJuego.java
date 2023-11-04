package intefaz;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import uniandes.dpoo.taller4.modelo.*;

public class PanelJuego extends JPanel implements MouseListener
{
	private InterfazPrincipal ventanaPrincipal;
	
	//atributos que necesita para dibujar el tablero
	private Tablero tablero = null; //muy importante lo envía el mundo del problema.
	private int widthTablero;
	private int heightTablero;
	private int tamaño; //3 o 5 por ahora (3x3) (5x5)
	private int dificultad;//moviemientos para el que desordena
	private boolean ganador = false;
	private boolean tutorial = true;
	
	
	
	public PanelJuego(InterfazPrincipal pPrincipal)
	{
		ventanaPrincipal = pPrincipal; //ahora el panel conoce la ventana principal
		
		setPreferredSize(new Dimension(500,20));
		setBackground(Color.CYAN);
		setOpaque(true);
		this.addMouseListener(this);

		
	}
	
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		
		g2d.setStroke(new BasicStroke(4));
		if(tutorial)
		{
			g2d.setPaint(Color.BLACK);
			g2d.setFont(new Font("Helvetica", Font.BOLD , 15));
			g2d.drawString("Lights out: Enciende todas la luces!!!", 100,150);
			g2d.drawString("Selecciona las opciones arriba y haz click en nuevo juego", 50,250);
		}
		
		/**
		 * A partir de aqui lo que pasa es que el pintor empieza a recorrer el tablero
		 * si encuentra un valor que es Encendido (true) pinta de amarillo si no pinta de negro.
		 * Esto de acuerdo tambien con el tamaño del tablero.
		 */
		if (tablero != null)
			
		{
			tutorial = false;
			/**
			 * REVISAR LA LOGICA HASTA INT YYYY
			 */
			boolean[][] tableroArreglo = tablero.darTablero(); //extraigo el tablero
			
			//calculo el alto y ancho de los cuadros
			int altoCuadros = calcularAltoCuadros(heightTablero, tamaño);
			int anchoCuadros = calcularAnchoCuadros(widthTablero, tamaño);
			
			///REVISARRRRRR
			int y = 0; //variable que me da la posicion y para pintar
			for(int fila = 0; fila < tamaño ; fila++) 
			{
				int x = 0; //variable que me da la pos de x para pintar
				for(int columna = 0; columna < tamaño; columna ++)
				{
					if(tableroArreglo[fila][columna])
					{
						g2d.setPaint(Color.YELLOW);
						g2d.fillRect(x, y, anchoCuadros, altoCuadros);
						g2d.setPaint(Color.WHITE);
						g2d.drawRect(x, y, anchoCuadros, altoCuadros);
						
					}
					else
					{
						g2d.setPaint(Color.BLACK);
						g2d.fillRect(x, y, anchoCuadros, altoCuadros);
						g2d.setPaint(Color.WHITE);
						g2d.drawRect(x, y, anchoCuadros, altoCuadros);
					}
					x = x+anchoCuadros;
				}
				y = y + altoCuadros;
			}
			
			if(ganador)
			{
				g2d.setPaint(Color.RED);
				g2d.setFont(new Font("Ink Free", Font.BOLD , 35));
				g2d.drawString("HAS GANADOOOOO!!", 50,250);
			}
			//g2d.setPaint(Color.BLACK);
			//g2d.fillRect(0, 0, 200, 100);
			//g2d.fillRect(200, 0, 200, 100);
			//g2d.drawLine(0, 0, 500, 500);
			//g2d.setPaint(Color.WHITE);
			//g2d.drawRect(0, 0, 200, 100);
			//g2d.drawRect(200, 0, 200, 100);
		}
		
	}
	
	
	public void repintar() //esta funcion vuelve a pintar
	{
		repaint();
	}
	
	/**
	 * Funciones para definir el alto y ancho de los cuadros
	 * lo que hacen es tomar el tamaño del tablero y dividirlo entre
	 * el numero de casillas (3x3) o (5x5)
	 */
	
	public int calcularAltoCuadros(int altoTab , int tamaño)
	{
		int altoCuadros = altoTab / tamaño;
		return altoCuadros;
	}
	public int calcularAnchoCuadros(int anchoTab , int tamaño)
	{
		int anchoCuadros = anchoTab / tamaño;
		return anchoCuadros;
	}
	
	
	/**
	 * SETTERS de atributos importantes para pintar.
	 * 
	 */
	public void setTablero(Tablero tab)
	{
		this.tablero = tab;
	}
	
	public Tablero getTablero()
	{
		return this.tablero;
	}

	public void setWidthTablero(int widthTablero) {
		this.widthTablero = widthTablero;
	}

	public void setHeightTablero(int heightTablero) {
		this.heightTablero = heightTablero;
	}

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}
	public void cambiarEsGanador(boolean esGanador) {
		this.ganador = esGanador;
	}

	
	//MOUSE LISTENER METHODS
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int click_x = e.getX();
		int click_y = e.getY();
		int[] casilla = convertirCoordenadasACasilla(click_x, click_y);
		int fila = casilla[0];
		int columna = casilla[1];
		ventanaPrincipal.Jugar(fila, columna, tablero);
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//convertir a Casilla
	private int[] convertirCoordenadasACasilla(int x, int y)
	{
	int ladoTablero = tamaño;
	int altoPanelTablero = heightTablero;
	int anchoPanelTablero = widthTablero;
	int altoCasilla = altoPanelTablero / ladoTablero;
	int anchoCasilla = anchoPanelTablero / ladoTablero;
	int fila = (int) (y / altoCasilla);
	int columna = (int) (x / anchoCasilla);
	return new int[] { fila, columna };
	}
}
