/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class Pa extends JPanel {

    private int row;
    private int colum;
    private boolean isSnakeHere = false;
    private boolean isFoodHere = false;

    public Pa() {
        this(0, 0);
    }

    public Pa(int row, int colum) {
        this.row = row;
        this.setFocusable(false);
        this.colum = colum;
        this.setBorder(BorderFactory.createLineBorder(Color.yellow));
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColum() {
        return colum;
    }

    public void setColum(int colum) {
        this.colum = colum;
    }

    public boolean isIsFoodHere() {
        return isFoodHere;
    }

    public void setIsFoodHere(boolean isFoodHere) {
        this.isFoodHere = isFoodHere;
    }

    public boolean isIsSnakeHere() {
        return isSnakeHere;
    }

    public void setIsSnakeHere(boolean isSnakeHere) {
        this.isSnakeHere = isSnakeHere;
    }

    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        if (isSnakeHere) {
            this.setBackground(Color.black);
        } else if (isFoodHere) {
            g.drawOval(0, 0, 10, 10);
            g.fillOval(3, 3, 5, 5);
        } else {
            this.setBackground(Color.white);
        }
        
        
    }
}
