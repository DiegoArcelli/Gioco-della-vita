/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giocodellavita;

import java.util.*;

/**
 * <b>Classe che contiene la matrice di boolean che rappresenta la griglia con le varie cellule vive
 * o morte e i metodi per operare su di essa</b>
 * @author diego
 * @version 1.0
 */
public class Griglia {
    
    boolean grid[][];
    
    /**
     * Istanzia una matrice di booleani che rappresenterà la griglia
     * @param n Indica il numero di righe e di colonne della griglia che verrà generata
     */
    public Griglia(int n){
        grid = new boolean[n][n];
    }
    
    /**
     * Crea una matrice che viene inizializzata con valori booleani randomici
     */
    public void popolaGriglia(){
        Random r = new Random();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                grid[i][j]=r.nextBoolean();
            }
        }
    }
    
    /**
     * Crea una matrice che viene inizializzata con valori booleani randomici in base ad una certa probabilità
     * @param prob Rappresenta la probabilità che nella griglia vengano generate cellule vive
     */
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
    
    /**
     * Verifica il numero di cellule vive adiacienti ad ogni cellula e in base al loro numero stabilisce se
     * alla successiva generazione la cellula sarà viva o morta
     */
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
                        if(x>=0 && x<=org.length-1 && y>=0 && y<=org.length-1 && (x!=i || y!=j)){
                            if(org[x][y]){
                                cont++;
                            }
                        }
                    }
                }
                /*if(org[i][j]){
                    cont--;
                }*/
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