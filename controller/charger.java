package controller;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import view.*;

public class charger implements ActionListener {
  gaufre maGaufre;
  niveau_graphique N;

  public charger(gaufre g, niveau_graphique n) {
    maGaufre = g;
    N = n;
  }

public void actionPerformed(ActionEvent e){
	  //Recuperation du fichier
	  JButton load = new JButton();
	  JFileChooser fc = new JFileChooser();
	  fc.setDialogTitle("selectionnez un fichier");
	  fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	  if( fc.showOpenDialog(load) == JFileChooser.APPROVE_OPTION ) {
		  File f = fc.getSelectedFile();
		  try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String frS = br.readLine();
			String[] frSS = frS.split("/");
			frSS[1] = frSS[1].replace("[", "");
			frSS[1] = frSS[1].replace("]", "");
			frSS[1] = frSS[1].replace(" ", "");
			String[] arrayS = frSS[1].split(",");
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (String i : arrayS) {
				arr.add(Integer.parseInt(i));
			}
			N.set_gaufre(maGaufre.AlToGaufre(arr));
      N.set_nbcoup(Integer.parseInt(frSS[0]));
			N.repaint();
			fr.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	  }
  }
}
