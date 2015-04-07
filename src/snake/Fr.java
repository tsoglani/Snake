/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class Fr extends JFrame {

    Rectangle[] limits = new Rectangle[4];
    private int level = 1;
    private int lengthOfSnake = 1;
    private JPanel centralPanel = new JPanel();
    private Pa[][] panels = new Pa[50][50];
    private Thr thread;
    private ArrayList<String> snake = new ArrayList<String>();
    private boolean gameIsOver = false;

    public Pa[][] getPanels() {
        return panels;
    }

    public void setPanels(Pa[][] panels) {
        this.panels = panels;
    }

    public ArrayList getSnake() {
        return snake;
    }

    public void setSnake(ArrayList snake) {
        this.snake = snake;
    }

    public int getLengthOfSnake() {
        return lengthOfSnake;
    }

    public void setLengthOfSnake(int lengthOfSnake) {
        this.lengthOfSnake = lengthOfSnake;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isGameIsOver() {
        return gameIsOver;
    }

    public void setGameIsOver(boolean gameIsOver) {
        this.gameIsOver = gameIsOver;
    }

    public Fr() {


        this.setSize(800, 600);
        centralPanel.setLayout(new GridLayout(50, 50));
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                panels[i][j] = new Pa(i, j);
                centralPanel.add(panels[i][j]);

            }
        }
        for (int i = 0; i < 4; i++) {
            limits[i] = new Rectangle();
        }
        new Food(panels).start();
        thread = new Thr(this);
        thread.start();

        panels[20][20].setIsSnakeHere(true);
        snake.add(20 + "-" + 20);
        this.addKeyListener(key);
        this.add(centralPanel);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setFocusable(true);
        this.setVisible(true);
    }
    KeyListener key = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                thread.setGoto("right");
            }
            if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                thread.setGoto("left");
            }
            if (ke.getKeyCode() == KeyEvent.VK_UP) {
                thread.setGoto("up");
            }
            if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                thread.setGoto("down");
            }

        }

        @Override
        public void keyReleased(KeyEvent ke) {
        }
    };

    @Override
    public void paint(Graphics gg) {
        super.paint(gg);
        Graphics2D g = (Graphics2D) gg;
//        g.setColor(Color.black);
//        limits[0].setBounds(0, 0, this.getWidth(), 65);
//        limits[1].setBounds(0, 0, 25, this.getHeight());
//        limits[2].setBounds(0, this.getHeight()-35, this.getWidth(), this.getHeight());
       
//        g.fill(limits[0]);
//        g.fill(limits[1]);
//        g.fill(limits[2]);
//   g.drawLine(0, 65, this.getWidth(), 65);
//   g.drawLine(25,0, 25, this.getHeight());
//    g.drawLine(0, this.getHeight()-35,this.getWidth(),this.getHeight()-35);
//     g.drawLine(this.getWidth()-25, 0,this.getWidth()-25,this.getHeight()-35);
        g.drawString("Score = "+thread.getScore(), 60, 60);
        
        if (gameIsOver) {
            this.removeAll();
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            Font f = new Font("", Font.CENTER_BASELINE, 45);
            g.setFont(f);
            g.setBackground(Color.yellow);
            g.setColor(Color.orange);
            g.drawString("Game Is Over", 300, 250);

        }
       
    }
}
