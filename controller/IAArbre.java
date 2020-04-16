package controller;

import java.util.*;

public class IAArbre {
  gaufre g;
  Hashtable <ArrayList<Integer>, Hashtable<Boolean, Boolean>> maHash;

  public IAArbre(gaufre t) {
    g = t;
    maHash = new Hashtable <ArrayList<Integer>, Hashtable<Boolean, Boolean>>();
  }

  public void afficherArbre(Arbre a) {
	a.afficherArbre(a, 0, a.get_config());
  }

  ArrayList<Integer> simulationDeCoup(ArrayList<Integer> ml, int val) {
    ArrayList<Integer> list = copie(ml);
    Iterator<Integer> monI = list.iterator();
    while(monI.hasNext()) {
      int i = monI.next();
      if( i % val == 0) monI.remove();
    }
    return list;
  }
  
  boolean appartientHash(int pos, ArrayList<Integer> a) {
	if (maHash.containsKey(a)) {
	  if (maHash.get(a).containsKey(pos % 2 == 0)) return true;
	}
	return false;
  }
  
  void ajouteHash (int pos, ArrayList<Integer> a, boolean b) {
	if (maHash.containsKey(a)) {
	  maHash.get(a).put(pos % 2 == 0, b);
	}
	Hashtable <Boolean, Boolean> sec = new Hashtable <Boolean, Boolean>();
	sec.put(pos % 2 == 0, b);
	maHash.put(a, sec);
  }

  public gaufre joueCoupGagnant(ArrayList<Integer> coucou, int niv) {
	ArrayList <ArrayList<Integer>> ma = new ArrayList <ArrayList<Integer>>();
	ArrayList <ArrayList<Integer>> f = new ArrayList <ArrayList<Integer>>();
    for(Integer i : coucou) {
	  ArrayList <Integer> idk = simulationDeCoup(coucou, i);
	  if (filsGagnant(niv + 1, idk)) ma.add(idk);
	  else f.add(idk);
    }
    Random monRandom = new Random();
    int val = 0;
    if (ma.size() == 0) ma = f;
    if (ma.size() >= 0) val = monRandom.nextInt(ma.size());
    return g.AlToGaufre(ma.get(val));
  }
  
  boolean filsGagnant(int pos, ArrayList<Integer> a) {
    if (a.size() == 1) return pos % 2 != 0;
    else if (a.size() == 0) return pos % 2 == 0;
    else if (appartientHash(pos, a)) return maHash.get(a).get(pos % 2 == 0);
    boolean edfCestDeLaMerde;
    for (Integer i : a) {
      ArrayList<Integer> idk = simulationDeCoup(a, i);
      if (appartientHash(pos, idk)) edfCestDeLaMerde = maHash.get(idk).get(pos % 2 == 0);
      else edfCestDeLaMerde = filsGagnant(pos + 1, idk);
      if (pos % 2 != 0 && !edfCestDeLaMerde) {
    	if (appartientHash(pos, idk)) ajouteHash(pos, idk, false);
    	return false;
      }
      else if (pos % 2 == 0 && edfCestDeLaMerde) {
    	if (appartientHash(pos, idk)) ajouteHash(pos, idk, true);
    	return true;
      }
      ajouteHash(pos, idk, edfCestDeLaMerde);
    }
    return pos % 2 != 0;
  }
  
  ArrayList<Integer> copie( ArrayList<Integer> liste) {
    ArrayList <Integer> temp = new ArrayList <Integer>();
    for (Integer i : liste) temp.add(i);
    return temp;
  }
}
