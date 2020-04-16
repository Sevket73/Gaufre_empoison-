package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.Thread;
import controller.*;

@SuppressWarnings("serial")
public class niveau_graphique extends JComponent{
  gaufre maGaufre;
  Image uneCase;
  int nb_coup;
  int width, height;
  Graphics2D drawable;
  int tailleCasex;
  int tailleCasey;
  boolean IAAlea;
  boolean IAJoue;
  boolean IACoupGagnant;
  IArandom monIa;
  JLabel monLabel;
  IAArbre monArbre;
  Component c1, c2;
  int gagnant;

  public niveau_graphique(gaufre g, JLabel monLabel, Component comp1, Component comp2){
    nb_coup = 0;
    this.monLabel = monLabel;
    chargerImage();
    IAAlea = false;
    IAJoue = false;
    IACoupGagnant = false;
    this.maGaufre = g;
    c1 = comp1;
    c2 = comp2;
    gagnant = -1;
  }

  void chargerImage() {
	InputStream IScase = ClassLoader.getSystemClassLoader().getResourceAsStream("Case.png");
	try {
	  uneCase = ImageIO.read(IScase);
	 }
	catch (Exception e) {
	  System.out.println("probleme avec l'image");
	  System.exit(1);
	 }
  }

  public int get_tailleCasex(){
    return width / this.maGaufre.get_nbcolonne();
  }

  public int get_nbcoup() {
      return this.nb_coup;
  }

  public void set_nbcoup(int n)  {
      nb_coup = n;
  }

  public int get_tailleCasey() {
    return height / this.maGaufre.get_nbligne();
  }

  public void set_gaufre(gaufre g){
    this.maGaufre = g;
  }

  public void affiche_niveau(){
    tailleCasex = get_tailleCasex();
    tailleCasey = get_tailleCasey();
    for (int i = 0; i < maGaufre.get_nbligne(); i++){
      for(int j = 0; j < maGaufre.get_nbcolonne(); j++){
        if (maGaufre.get_value(i,j) != 0){
          drawable.drawImage(uneCase, j * tailleCasex, i * tailleCasey, tailleCasex, tailleCasey, null);
        }
      }
    }
  }

  public void incre(){
    nb_coup++;
  }

  public void IAOn(){
    IAJoue = !IAJoue;
    monArbre = new IAArbre(maGaufre);
    monIa = new IArandom();
  }

  public void IAAleaOn() {
	IAAlea = !IAAlea;
	IACoupGagnant = false;
  }

  public void IACoupGagnantOn() {
	IACoupGagnant = !IACoupGagnant;
	IAAlea = false;
  }

  public void reinitialise(){
    maGaufre.commencer();
    monArbre = new IAArbre(maGaufre);
    gagnant = -1;
    nb_coup = 0;
  }

  public void victoire(int fin){
    if(gagnant == -1 && fin == 1) gagnant = ((nb_coup + 1) % 2 )+ 1;
    else if(gagnant == -1) gagnant = ((nb_coup) % 2) + 1;
    if (gagnant == 1) monLabel.setText("Victoire du joueur 1");
    else if (!IAJoue) monLabel.setText("Victoire du joueur 2");
    else monLabel.setText("Victoire de l'ordinateur");
  }

  public void joueIA() {
  System.out.println(IACoupGagnant);
	if (IAAlea) {
    System.out.println("Alea");
	  maGaufre = monIa.joue_coup(maGaufre.get_nbligne() - 1, maGaufre.get_nbcolonne() - 1, maGaufre);
	  repaint();
	  incre();
	}
	else if (IACoupGagnant){
    System.out.println("IACG");
	  maGaufre = monArbre.joueCoupGagnant(maGaufre.get_array(), 0);
	  repaint();
	  incre();
	 }
  }

  @Override
  public void paintComponent(Graphics g) {
    this.drawable = (Graphics2D) g;
    this.width = getSize().width;
    this.height = getSize().height;
    c1.setBounds(c1.getX(), c1.getY(), width/4, c1.getHeight());
    c2.setBounds(c2.getX(), c2.getY(), width/4, c2.getHeight());
    affiche_niveau();
    System.out.println(nb_coup);
    if (nb_coup % 2 == 0 ) monLabel.setText("Au tour du joueur 1");
    else if (!IAJoue) monLabel.setText("Au tour du joueur 2");
    else monLabel.setText("Au tour de l'ordinateur");
    if (maGaufre.gagner() != -1) {
      victoire(maGaufre.gagner());
    }
    if (IAJoue && nb_coup % 2 != 0) joueIA();
	  else if (IAJoue) {
      try{ Thread.sleep(1000);}
       catch (Exception e){}
    }
  }
}
