/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giocodellavita;

import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class Generatore extends Thread {
    
    Griglia grid;
    Finestra fin;
    boolean keep;

    public Generatore(Griglia grid, Finestra fin) {
        this.grid = grid;
        this.fin = fin;
        keep=false;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }

    public void run(){
        keep=true;
        while(keep){
            try {
                grid.verificaAdiacienti();
                fin.aggiornaGriglia();
                sleep(fin.getDelay());
            } catch (InterruptedException ex) {
                System.out.println("Interruzione avvenuta");
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Insereire un numero");
                fin.setDelay(0);
            }
        }
    }
    
}