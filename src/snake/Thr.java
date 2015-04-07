/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Thr extends Thread {
private  int score;
    int posX = 0;
    int posY = 0;
    private int sleepOfSnake = 200;
    private String goTo = "left";
    private Pa[][] panels;
    private Fr frame;
    private String goToBackup = "left";

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

   

    public Thr(Fr frame) {
        this.frame = frame;
        this.panels = frame.getPanels();
    }

    public String getGoto() {
        return goTo;
    }

    public int getSleepOfSnake() {
        return sleepOfSnake;
    }

    public void setSleepOfSnake(int sleepOfSnake) {
        this.sleepOfSnake = sleepOfSnake;
    }

    public void setGoto(String Goto) {
        this.goTo = Goto;
    }

    public void run() {
        while (true) {
            try {

                int newPosX = 0;
                int newPosY = 0;



                try {
                    this.sleep(this.sleepOfSnake);
                   
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }


                /// stin arxi kanw ola ta panels aspra
                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        panels[i][j].setIsSnakeHere(false);
                    }
                }

                // diatrexw tin lista mou me tin for
                for (int i = 0; i < frame.getSnake().size(); i++) {

                    String pos = (String) frame.getSnake().get(i);
                    String[] positions = pos.split("-");
                    // pernw to kathe stoixeio to xwrize me - kai apothikeyw ta apotelesmata
                    // sto posX kai posX ... to newPosX kai newPosY einai to apotelesma
                    // tis metakinisis tou prwtou .. to prwto to vazw otan vrw ena food 

                    posX = Integer.parseInt(positions[0]);
                    posY = Integer.parseInt(positions[1]);
                    newPosX = posX;
                    newPosY = posY;


                    if (i != frame.getSnake().size() - 1) {
                        // pernw ola ta kommatia tou fidiou ektos apo to kefali
                        // kai lew sto panel oti ekei exw ena fidi
                        panels[posX][posY].setIsSnakeHere(true);

                    } else {
                        // pernw to prwto twra kai tou rythmizw tin metakinisi tou analoga me tin peristasi
                        // alla epidi an metakinithei efoson ta alla dn metakinounte tha dimiourgithei 
                        // ena kaino panel anamesa tous gia ayto emfanizw kai tis palias thesis [posX][posY]



                        /// ama exw pathsei to panw na min mporw na patisw to katw
                        if (goToBackup.equals("right") && this.goTo.endsWith("left")) {
                            this.goTo = goToBackup;
                        }
                        if (goToBackup.equals("left") && this.goTo.endsWith("right")) {
                            this.goTo = goToBackup;
                        }

                        if (goToBackup.equals("up") && this.goTo.endsWith("down")) {
                            this.goTo = goToBackup;
                        }
                        if (goToBackup.equals("down") && this.goTo.endsWith("up")) {
                            this.goTo = goToBackup;
                        }



                        if (goTo.equals("left")) {
                            newPosY = posY - 1;
                            if (newPosY < 0) {

                                newPosY = 49;
                            }
                            panels[posX][posY].setIsSnakeHere(true);



                        } else if (goTo.equals("right")) {
                            newPosY = posY + 1;
                            if (newPosY >= 50) {

                                newPosY = 0;
                            }
                            panels[posX][posY].setIsSnakeHere(true);



                        } else if (goTo.equals("up")) {
                            newPosX = posX - 1;
                            if (newPosX < 0) {
                                newPosX = 49;
                            }
                            panels[posX][posY].setIsSnakeHere(true);

                        } else if (goTo.equals("down")) {
                            newPosX = posX + 1;
                            if (newPosX >= 50) {
                                newPosX = 0;
                            }
                            panels[posX][posY].setIsSnakeHere(true);

                        }
                        if (panels[newPosX][newPosY].getBackground() == Color.black) {
                            frame.setGameIsOver(true);
                            frame.repaint();
                            this.stop();
                            break;
                        }
                        panels[newPosX][newPosY].setIsSnakeHere(true);
                        goToBackup = this.goTo;
                    }
                }

                // diagrafw to teleytaio stoixeio apo tin lista tou fidiou
                frame.getSnake().remove(0);
                // tou pernaw san head (sto telos tis listas) tis nees sintetagmenes

                frame.getSnake().add(newPosX + "-" + newPosY);

                // kanw elengxo an sto idio panel yparxei to fidi(backround==black)

                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {

                        if (panels[i][j].isIsFoodHere() && panels[i][j].isIsSnakeHere()) {
                            // an yparxei megalwnw to megethos tou fidiou
                            frame.setLengthOfSnake(frame.getLengthOfSnake() + 1);
                            // oti pernaei teleytaio stin arraylist paei sto telos
                            //
                            //ara edw  vazw kainourio head sto fidi 
                            score+=20;
                            frame.getSnake().add(i + "-" + j);
                            panels[i][j].setIsFoodHere(false);
                            // kai tou lew oti dn exei edw fagito
                            new Food(panels, 1000).start();
// ayksanw tin taxythta 
                            if (frame.getLengthOfSnake() % 3 == 0 && sleepOfSnake > 50) {
                                this.sleepOfSnake -= 25;
                            }
                        }

                    }
                }

                frame.repaint();
            } catch (ArrayIndexOutOfBoundsException ee) {
                System.out.println(" game over ");
                frame.setGameIsOver(true);
                frame.repaint();
                this.stop();
                break;
            } catch (IndexOutOfBoundsException ee) {
                ee.printStackTrace();
                continue;
            } catch (ConcurrentModificationException ex) {
                ex.printStackTrace();
                continue;
            }

        }

    }
}
