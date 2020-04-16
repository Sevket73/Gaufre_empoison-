package controller;

import java.util.*;
import java.lang.Math;

public class gaufre {
  int nbligne;
  int nbcolonne;
  int Tab[][];
  ArrayList<Integer> temp;

  public gaufre(int nbligne, int nbcolonne) {
    this.nbligne = nbligne;
    this.nbcolonne = nbcolonne;
    commencer();
  }

  public gaufre AlToGaufre(ArrayList <Integer> i) {
    temp = i;
    Tab = new int[nbligne][nbcolonne];
    for (Integer val : temp) {
      Tab[get_ligne_array(val)][get_colonne_array(val)] = val;
    }
    return this;
  }

  public void commencer() {
    temp = new ArrayList<>();
    Tab = new int[nbligne][nbcolonne];
    for (int i = 0; i < nbligne; i++) {
      for(int j = 0; j < nbcolonne; j++) {
        Tab[i][j] = set_value(i,j);
        temp.add((Integer)Tab[i][j]);
      }
    }
  }


  int set_value(int x, int y) {
	  return (int)(Math.pow(3,x)*Math.pow(2,y));
  }

  public int[][] getTab() {
	  return Tab;
  }

  public int get_colonne_array (int pos) {
    int c = 0;
    while(pos % 2 == 0) {
      c ++;
      pos = (int) pos / 2;
    }
    return c;
  }

  public int get_ligne_array(int pos) {
    int c = 0;
    while(pos % 3 == 0) {
      c ++;
      pos = (int) pos / 3;
    }
    return c;
  }

  public int get_nbligne() {
    return this.nbligne;
  }

  public ArrayList<Integer> get_array() {
    return temp;
  }

  public int get_nbcolonne() {
    return this.nbcolonne;
  }

  public int get_value(int i, int j) {
    return Tab[i][j];
  }

  void supprimer(int posx, int posy) {
    int val = get_value(posx, posy);
    for(int i = 0; i < nbligne; i++) {
      for (int j = 0; j < nbcolonne; j++) {
        if (Tab[i][j] != 0) {
          if(Tab[i][j] % val == 0) {
            temp.remove((Integer)Tab[i][j]);
            Tab[i][j] = 0;
          }
        }
      }
    }
  }


  public int gagner() {
  if (Tab[0][0] == 0) return 0;
	if (Tab[0][1] == 0 && Tab[1][0] == 0) return 1;
  return -1;
  }

  public void joue(int posx, int posy) {
    supprimer(posx,posy);
  }
}
