
package chess;

import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Handler {

    Player white;
    Player black;
    Board board;

    int clickTimed = 0;
    boolean clickPlayLocal = false;
    boolean clickPlayLan = false;
    boolean clickPlay = false;
    boolean clickOptions = false;
    private int width = 1200;
    private int height = 800;
    private ImageIcon sBg, sPB, sOB, sBB, sNB, sTB, sLOB, sLAB, sGc;
    JFrame frame = new JFrame("Chess");
    Drawing drawing = new Drawing();



    Scanner s = new Scanner(System.in);

    public Handler() {
        white = new Player(true);
        black = new Player(false);
        board = new Board(white, black);
    }

    public void run() { //change to tick / render
        board.display();
        board.initFrame();
        Music.volume = Music.Volume.LOW;
        if (clickTimed != 0) { //SPEED CHESS MUSIC
            Music.MII.stop();
            Music.SONIC.play();
        } else
            Music.MII.play();
    }

    public void close() { //close ports for networking

    }

    public void menu(){
        frame.setSize(width, height);
        frame.getContentPane().add(drawing);
        frame.addMouseListener(new MouseListen());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void menuDisplay(){
        if(!frame.isShowing())
        frame.setVisible(true);
    }

    class MouseListen extends MouseAdapter {
        private int fx = -1, fy = -1;// mouse coordinate values
        private int dx = -1, dy = -1;
        public int cx, cy, px, py, rx, ry;

        public void mouseClicked(MouseEvent e) {
            cx = e.getX();
            cy = e.getY();
            System.out.println("hey");

        }

        public void mousePressed(MouseEvent e) {
            px = e.getX();
            py = e.getY();
        }

        public void mouseReleased(MouseEvent e) {
            rx = e.getX();
            ry = e.getY();
            System.out.println(rx + ", " + ry);
            if (!clickPlay && !clickOptions && 55 < rx && rx < 305 && 475 < ry && ry < 575) {   //clicked on play
                clickPlay = true;
                clickOptions = false;
                System.out.println("u clicked play");
            }

            else if(clickPlay && !clickOptions && 55 < rx && rx < 305 && 475 < ry && ry < 575 && clickTimed == 0) { //clicked on NORMAL
                clickTimed = 1;
                System.out.println("time mode = 1");
            }

            else if (clickPlay && !clickOptions && 55 < rx && rx < 305 && 575 < ry && ry < 675 && clickTimed == 0) { //click on TIMED
                clickTimed = 2;
                System.out.println("time mode = 2");
            }

            else if (clickPlay && !clickOptions && clickTimed != 0 && 55 < rx && rx < 305 && 475 < ry && ry < 575){
                clickPlayLocal = true;
                System.out.println("play mode = local");
            }

            else if (clickPlay && !clickOptions && clickTimed != 0 && 55 < rx && rx < 305 && 575 < ry && ry < 675){
                clickPlayLan = true;
                System.out.println("play mode = lan");
                String C = JOptionPane.showInputDialog("Please enter server computer name");
            }

            else if(!clickPlay && !clickOptions && 55 < rx && rx < 305 && 575 < ry && ry < 675 ){  // clicked on options
                clickOptions = true;
                clickPlay = false;
                System.out.println("u clicked options");
            }

            else if((clickPlay || (!clickPlay && clickOptions)) && 18 < rx && rx < 168 && 730 <= ry && ry < 795){  // clicked on back
                if(clickOptions)
                    clickOptions = false;
                if(clickTimed == 0)
                    clickPlay = false;
                else if(clickTimed != 0)
                    clickTimed = 0;
                System.out.println("u clicked back");
            }

        }
    }


    class Drawing extends JComponent {
        public Drawing() {
            repaint();
        }

        public void paint(Graphics g) {
            String localDir = System.getProperty("user.dir");
            ImageIcon bg = new ImageIcon(localDir + "\\bg.jpg");
            ImageIcon gc = new ImageIcon(localDir + "\\GIGACHESS.png");
            ImageIcon playB = new ImageIcon(localDir + "\\playB.png");
            ImageIcon optionsB = new ImageIcon(localDir + "\\optionsB.png");
            ImageIcon backB = new ImageIcon(localDir + "\\backB.png");
            ImageIcon normalB = new ImageIcon(localDir + "\\normalB.png");
            ImageIcon timedB = new ImageIcon(localDir + "\\timedB.png");
            ImageIcon localB = new ImageIcon(localDir + "\\localB.png");
            ImageIcon lanB = new ImageIcon(localDir + "\\lanB.png");



            Image background = bg.getImage();//background
            Image newBg = background.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
            sBg = new ImageIcon(newBg);

            Image gigaChess = gc.getImage();//title
            Image newGc = gigaChess.getScaledInstance(700, 300, Image.SCALE_SMOOTH);
            sGc = new ImageIcon(newGc);


            Image playButton = playB.getImage();//play button
            Image newPB = playButton.getScaledInstance(250, 100, Image.SCALE_SMOOTH);
            sPB = new ImageIcon(newPB);

            Image optionsButton = optionsB.getImage();//options button
            Image newOB = optionsButton.getScaledInstance(250, 100, Image.SCALE_SMOOTH);
            sOB = new ImageIcon(newOB);

            Image normalButton = normalB.getImage();//normal button
            Image newNB = normalButton.getScaledInstance(250, 100, Image.SCALE_SMOOTH);
            sNB = new ImageIcon(newNB);

            Image timedButton = timedB.getImage();//timed button
            Image newTB = timedButton.getScaledInstance(250, 100, Image.SCALE_SMOOTH);
            sTB = new ImageIcon(newTB);

            Image localButton = localB.getImage();//local button
            Image newLOB = localButton.getScaledInstance(250, 100, Image.SCALE_SMOOTH);
            sLOB = new ImageIcon(newLOB);

            Image lanButton = lanB.getImage();//lan button
            Image newLAB = lanButton.getScaledInstance(250, 100, Image.SCALE_SMOOTH);
            sLAB = new ImageIcon(newLAB);

            Image backButton = backB.getImage();//back button
            Image newBB = backButton.getScaledInstance(150, 60, Image.SCALE_SMOOTH);
            sBB = new ImageIcon(newBB);



            sBg.paintIcon(this, g, 0,0);
            sGc.paintIcon(this, g, 0,0);

            if(!clickPlay && !clickOptions) {  // displaying play and options
                sPB.paintIcon(this, g, 55, 450);
                sOB.paintIcon(this, g, 55, 550);
            }

            if(clickPlay && !clickOptions && clickTimed == 0){  //displaying normal and timed
                sNB.paintIcon(this, g, 55, 450);
                sTB.paintIcon(this, g, 55, 550);
            }

            if(clickPlay && !clickOptions && clickTimed != 0){
                sLOB.paintIcon(this, g, 55, 450);
                sLAB.paintIcon(this, g, 55, 550);
            }

            if(clickPlay || (!clickPlay && clickOptions)){ //displaying back
                sBB.paintIcon(this, g, 10, 700);
            }

            //sPB.paintIcon(this, g,width/24, height/2);

            //System.out.println(localDir);
        }
    }
}


