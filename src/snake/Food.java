/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Food extends Thread {

    Pa[][] panels;
private int delay;
    public Food(Pa[][] panels) {
        this(panels,0);
        this.panels = panels;
    }
 public Food(Pa[][] panels,int delay) {
        this.panels = panels;
        this.delay=delay;
    }
    @Override
    public void run() {
        try {
            int row = (int) (Math.random() * 50);
            int colum = (int) (Math.random() * 50);
            this.sleep(delay);
            panels[row][colum].setIsFoodHere(true);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        
    }
}
