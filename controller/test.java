package controller;

import java.awt.event.*;
import view.*;

public class test implements ActionListener {
  niveau_graphique N;

  public test(niveau_graphique n) {
    N = n;
  }

  public void actionPerformed(ActionEvent e){
    N.IAOn();
  }
}
