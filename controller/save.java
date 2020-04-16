package controller;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import view.*;

public class save implements ActionListener {
  gaufre maGaufre;
  niveau_graphique N;

  public save(gaufre g, niveau_graphique n) {
    maGaufre = g;
    N = n;
  }

public void actionPerformed(ActionEvent e){
	  //Creation du fichier de save
	  JButton save = new JButton();
	  JFileChooser fc = new JFileChooser();
	  fc.setDialogTitle("selectionnez un fichier");
	  fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	  if( fc.showSaveDialog(save) == JFileChooser.APPROVE_OPTION ) {
		  File f = fc.getSelectedFile();
		  try {
			FileWriter fw = new FileWriter(f);
			String gaufreS = N.get_nbcoup()+"/"+maGaufre.get_array().toString();
			fw.write(gaufreS);
			fw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	  }


  }
}
