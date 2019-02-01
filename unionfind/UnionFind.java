import java.util.ArrayList;
import java.util.Collections;

public class UnionFind {
  private int[] parents;
  private int[] size;

  public UnionFind(int initSize) {
    parents = new int[++initSize];
    size = new int[initSize];
  }

  int find(int x) {
      if(parents[++x] == 0){
          parents[x] = x;
      }

      if(parents[x] != x){
        parents[x] = findHelper(parents[x]);
      }
      return parents[x];
  }
  
//  int find(int x){
//      if(parents[++x] == 0){
//          parents[x] = x;
//      } 
//
//      int tmp;
//      while(parents[x] != x){
//          tmp = x;
//          x = parents[x];
//          parents[tmp] = parents[parents[x]];
//      }
//      return x;
//  }

  int findHelper(int x){
      if(parents[x] != x){
        parents[x] = findHelper(parents[x]);
      }
      return parents[x];
  }

  int union(int x, int y) {
    int xr = find(x);
    int yr = find(y);
    if(size[xr] > size[yr]){
        parents[yr] = xr;
        size[xr] += size[yr];
        return xr;
    } else {
        parents[xr] = yr;
        size[yr] += size[xr];
        return yr;
    }
  }
}
