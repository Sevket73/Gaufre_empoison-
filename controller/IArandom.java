package controller;

import java.util.*;

public class IArandom{
  Random moncoup;
  
  public IArandom() {
	moncoup = new Random();
  }

  public gaufre joue_coup(int xmax, int ymax, gaufre maGaufre){
    int x = xmax;
    int y = ymax;
    while (maGaufre.get_value(x,y) == 0 ){
      x = moncoup.nextInt(xmax);
      y = moncoup.nextInt(ymax);
    }
    maGaufre.joue(x,y);
    return maGaufre;
  }
}
