package controller;

import java.util.*;


public class Arbre {
  ArrayList<Arbre> suivant = new ArrayList<Arbre>();
  ArrayList<Integer> config;

  public Arbre(ArrayList<Integer> c) {
    config = c;
  }

  public Arbre(ArrayList<Integer> c, ArrayList<Arbre> b) {
	config = c;
	suivant = b;
  }

  public void afficherArbre(Arbre a, int niv, ArrayList<Integer> pere) {
	  if (a.get_suivant().isEmpty()) {
		  if (a.get_config().size() >= pere.size()) {
			  System.out.println("OHOH erreur ici pas normal : " + a.get_config() + " et " + pere);
		  }
		  return;
	  }
    for (int i = 0; i < a.get_suivant().size(); i++) {
		  if (a.get_config().size() >= pere.size() && niv != 0) {
			  System.out.println("OHOH erreur ici pas normal : " + a.get_config() + " et " + pere);
			  System.out.println("et niv = " + niv);
		  }
		  afficherArbre(a.get_suivant(i), niv + 1, a.get_config());
	  }
  }

  public void addSuivant(Arbre a) {
    suivant.add(a);
  }

  public void setSuivant(ArrayList<Arbre> a) {
    suivant = a;
  }

  public void setConfig(ArrayList<Integer> l) {
	config = l;
  }

  public Arbre get_suivant(int indice) {
    return suivant.get(indice);
  }

  public ArrayList<Arbre> get_suivant() {
    return suivant;
  }

  public boolean estVide() {
    return config.isEmpty();
  }

  public ArrayList<Integer> get_config() {
    return config;
  }

  public boolean est_config(ArrayList<Integer> list) {
    return list.equals(config);
  }

  public void afficher() {
    for (Integer i : config) {
      System.out.print(i + " ");
    }
  }

  public boolean egal(Arbre a) {
    return (a.get_config().equals(config));
  }
}
