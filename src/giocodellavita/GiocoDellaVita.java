/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giocodellavita;

import javax.swing.JFrame;

/**
 * <b>Applicazione che implementa l'automa di Conway</b>
 * @author diego
 * @version 1.0
 */
public class GiocoDellaVita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Finestra f = new Finestra("Gioco della vita");
        f.setSize(800,800);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
}