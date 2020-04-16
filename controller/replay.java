package controller;

import java.awt.event.*;
import view.*;

public class replay implements ActionListener {
  gaufre maGaufre;
  niveau_graphique N;

  public replay(gaufre g, niveau_graphique n) {
    maGaufre = g;
    N = n;
  }

  public void actionPerformed(ActionEvent e){
    N.reinitialise();
    maGaufre.commencer();
    N.repaint();
  }
}
