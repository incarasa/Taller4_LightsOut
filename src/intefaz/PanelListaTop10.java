package intefaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class PanelListaTop10 extends JPanel
{
	public PanelListaTop10()
	{
		setLayout(new GridLayout(1,3));
		setBackground(Color.BLACK);
		setOpaque(true);
		setPreferredSize(new Dimension(300,300));
		JLabel texto = new JLabel("HOLALLALAL");
		add(texto);
	}
}
