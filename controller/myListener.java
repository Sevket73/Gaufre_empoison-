package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import view.*;

public class myListener extends MouseAdapter {
  gaufre maGaufre;
  niveau_graphique N;

	public myListener(gaufre g, niveau_graphique n) {
		maGaufre = g;
    N = n;
	}

	@Override
	public void mousePressed(MouseEvent e) {
    int l = e.getY() / N.get_tailleCasey();
		int c = e.getX() / N.get_tailleCasex();
    if(maGaufre.get_value(l,c) != 0){
      maGaufre.joue(l, c);
      N.set_gaufre(maGaufre);
      N.incre();
      N.repaint();
    }
	}
}
