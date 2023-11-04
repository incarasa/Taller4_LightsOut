package intefaz;

import java.util.*;
import javax.swing.*;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Top10;

public class InterfazSecundaria extends JFrame
{
	private JList ListaPrincipal;
	private DefaultListModel<String> modeloLista;
	
	public InterfazSecundaria(Top10 top10) //se le pasa el top 10 con la info cargada
	{
		setTitle("Top 10");
		setSize(500,500);
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);

		
		modeloLista = new DefaultListModel<>();
		JList<String> ListaPrincipal = new JList<>(modeloLista);
		
		add(new JScrollPane(ListaPrincipal));
		
		//se crea una lista con el top10
		Collection<RegistroTop10> coleccion = top10.darRegistros();
		List<RegistroTop10> lista = new ArrayList<>(coleccion);
		
		//se itera para meter al JList cada top10
		for(int i = 0; i<lista.size(); i++)
		{
			RegistroTop10 registro = lista.get(i);
			String nombre = registro.darNombre();
			String puntos = Integer.toString(registro.darPuntos());
			modeloLista.addElement(Integer.toString(i + 1) +"        " +nombre +"        " +puntos);
		}
		//modeloLista.addElement("Item 1");
		//modeloLista.addElement("Item 2");
		//modeloLista.addElement("Item 2");
		
	}
	
	public void añadirElemento(String elemento)
	{
		modeloLista.addElement(elemento);
	}
}
