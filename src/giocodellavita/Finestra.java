/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giocodellavita;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author diego
 */
public class Finestra extends JFrame implements ActionListener, MouseListener {
    
    Griglia grid;
    Generatore gen;
    JLabel cells[][];
    JButton step;
    JButton start;
    JButton stop;
    JButton create;
    JTextField prob;
    JTextField delay;
    
    public Finestra(String title){
        super(title);
        gen = null;
        BorderLayout bl = new BorderLayout();
        GridLayout gl;
        this.setLayout(bl);
        JPanel mappa = new JPanel();
        JPanel control = new JPanel();
        grid = new Griglia(10);
        grid.popolaGriglia();
        cells = new JLabel[grid.getLenght()][grid.getLenght()];
        gl = new GridLayout(grid.getLenght(),grid.getLenght());
        mappa.setLayout(gl);
        for(int i=0;i<grid.getLenght();i++){
            for(int j=0;j<grid.getLenght();j++){
                cells[i][j] = new JLabel();
                cells[i][j].setOpaque(true);
                cells[i][j].addMouseListener(this);
                mappa.add(cells[i][j]);
                if(grid.getCell(i, j)){
                    cells[i][j].setBackground(Color.WHITE);
                } else {
                    cells[i][j].setBackground(Color.BLACK);
                }
            }
        }
        step = new JButton("Step");
        step.addActionListener(this);
        control.add(step);
        start = new JButton("Start");
        start.addActionListener(this);
        control.add(start);
        stop = new JButton("Stop");
        stop.addActionListener(this);
        control.add(stop);
        create = new JButton("Create");
        create.addActionListener(this);
        control.add(create);
        prob = new JTextField("Inserire probabilità");
        control.add(prob);
        delay = new JTextField("Inserire delay");
        control.add(delay);
        this.add(mappa, BorderLayout.CENTER);
        this.add(control, BorderLayout.NORTH);
    }
    
    public void aggiornaGriglia(){
        for(int i=0;i<grid.getLenght();i++){
            for(int j=0;j<grid.getLenght();j++){
                if(grid.getCell(i, j)){
                    cells[i][j].setBackground(Color.WHITE);
                } else {
                    cells[i][j].setBackground(Color.BLACK);
                }
            }
        }
    }
    
    public void aggiornaDaColore(){
        for(int i=0;i<grid.getLenght();i++){
            for(int j=0;j<grid.getLenght();j++){
                if(cells[i][j].getBackground() == Color.WHITE){
                    grid.setCell(i, j, true);
                } else {
                    grid.setCell(i, j, false);
                }
            }
        }
    }
    
    public int getDelay(){
        return Integer.parseInt(delay.getText());
    }
    
    public void setDelay(int n){
        delay.setText(Integer.toString(n));
    }
    
    public void dispose(){
        super.dispose();
        if(gen!=null){
            gen.interrupt();
            gen.setKeep(false);           
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() instanceof JButton){
            if(ae.getSource() == step){
                grid.verificaAdiacienti();
                this.aggiornaGriglia();
            }
            if(ae.getSource() == start && gen == null){
                gen = new Generatore(grid,this);
                gen.start();
            }
            if(ae.getSource() == stop && gen != null){
                gen.setKeep(false);
                gen = null;
            }
            if(ae.getSource() == create){
                try {
                    int p = Integer.parseInt(prob.getText());
                    if(p>=0 && p<=100){
                        grid.popolaGriglia(p);
                        this.aggiornaGriglia();
                    } else {
                        JOptionPane.showMessageDialog(null, "Inserire un numero tra 0 e 100");
                        prob.setText("0");
                    }
                } catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Insereire un numero");
                    prob.setText("0");
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getSource() instanceof JLabel){
            JLabel tmp = (JLabel) me.getSource();
            if(tmp.getBackground() == Color.WHITE){
                tmp.setBackground(Color.BLACK);
            } else {
                tmp.setBackground(Color.WHITE);
            }
            this.aggiornaDaColore();
        }
    }

    @Override
    public void mousePressed(MouseEvent me) { }

    @Override
    public void mouseReleased(MouseEvent me) { }

    @Override
    public void mouseEntered(MouseEvent me) { }

    @Override
    public void mouseExited(MouseEvent me) { }
    
}