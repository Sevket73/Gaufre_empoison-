package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

import view.niveau_graphique;

public class GestionIA implements ActionListener {
  niveau_graphique N;

  public GestionIA(niveau_graphique n) {
	N = n;
  }

  public void actionPerformed(ActionEvent e) {
	JComboBox<String> cb = (JComboBox<String>)e.getSource();
    String name = (String)cb.getSelectedItem();
    if (name == "IA aleatoire") N.IAAleaOn();
    else if (name == "IA difficile"){
      System.out.println("i m here =)");
      N.IACoupGagnantOn();
    }
  }
}
