/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giocodellavita;

import java.util.*;

/**
 *
 * @author diego
 */
public class Griglia {
    
    boolean grid[][];
    
    public Griglia(int n){
        grid = new boolean[n][n];
    }
    
    public void popolaGriglia(){
        Random r = new Random();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                grid[i][j]=r.nextBoolean();
            }
        }
    }
    
    public void popolaGriglia(int prob){
        Random r = new Random();
        int p;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                p=r.nextInt(100)+1;
                if(p<=prob){
                    grid[i][j]=true;
                } else {
                    grid[i][j]=false;
                }
            }
        }
    }
    
    public int getLenght(){
        return grid.length;
    }    
    
    public boolean getCell(int i, int j){
        return grid[i][j];
    }
    
    public void setCell(int i, int j, boolean val){
        grid[i][j]=val;
    }
    
    public void verificaAdiacienti(){
        boolean org[][] = new boolean[grid.length][grid.length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                org[i][j]=grid[i][j];
            }
        }
        int cont;
        for(int i=0;i<org.length;i++){
            for(int j=0;j<org[i].length;j++){
                cont = 0;
                for(int x = i-1;x<=i+1;x++){
                    for(int y = j-1;y<=j+1;y++){
                        if(x>=0 && x<=org.length-1 && y>=0 && y<=org.length-1){
                            if(org[x][y]){
                                cont++;
                            }
                        }
                    }
                }
                if(org[i][j]){
                    cont--;
                }
                if(org[i][j]==false && cont==3){
                    grid[i][j]=true;
                } else if(org[i][j] && (cont==2 || cont==3)){
                    grid[i][j]=true;
                } else {
                    grid[i][j]=false;
                }
            }
        }
    }
    
}