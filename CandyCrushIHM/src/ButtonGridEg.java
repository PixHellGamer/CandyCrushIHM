import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.print.DocFlavor;
import javax.swing.*;

    public class ButtonGridEg extends JPanel {
        Plateau jeu = new Plateau();
        int[][] board = jeu.getPlateau();
        private static final int ROWS = 12;
        private static final int COLS = 9;
        private static final int GAP = 5;
        private JLabel[][] buttonGrid = new JLabel[ROWS][COLS];


        public ButtonGridEg() {
            setLayout(new GridLayout(ROWS, COLS, GAP, GAP));

            MouseListener buttonListener = new MouseListener() {

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }

                int count = 0;
                JLabel btnPrev = new JLabel();
                @Override
                public void mouseClicked(MouseEvent evt) {
                    JLabel selectedBtn = (JLabel) evt.getSource();
                    for (int row = 0; row < buttonGrid.length; row++) {
                        for (int col = 0; col < buttonGrid[row].length; col++) {
                            if (buttonGrid[row][col] == selectedBtn) {
                                System.out.printf("Selected row and column: %d %d%n", row, col);
                            }
                        }
                    }
                    count++;
                    if(count == 1){
                        btnPrev = selectedBtn;
                    }
                    if(count == 2){
                        int sbLi = -1;
                        int sbCol = -1;
                        int pbLi = -1;
                        int pbCol = -1;
                        for (int row = 0; row < buttonGrid.length; row++) {
                            for (int col = 0; col < buttonGrid[row].length; col++) {
                                if (buttonGrid[row][col] == selectedBtn) {
                                    sbLi = row;
                                    sbCol = col;
                                }
                                if(buttonGrid[row][col] == btnPrev){
                                    pbLi=row;
                                    pbCol=col;
                                }
                            }
                        }
                        if(((sbLi == pbLi + 1 || sbLi == pbLi-1) && sbCol == pbCol) || ((sbCol == pbCol+1 || sbCol == pbCol -1) && sbLi == pbLi)) {
                            String temp = btnPrev.getText();
                            btnPrev.setText(selectedBtn.getText());
                            selectedBtn.setText(temp);
                            count = 0;
                        }
                        else{
                           count = 1;
                           btnPrev = selectedBtn;
                        }
                    }



                }
            };

            for (int row = 0; row < buttonGrid.length; row++) {
                for (int col = 0; col < buttonGrid[row].length; col++) {
                    String text = String.format("%d", board[row][col]);
                    buttonGrid[row][col] = new JLabel(text);
                    buttonGrid[row][col].addMouseListener(buttonListener);
                    add(buttonGrid[row][col]);
                }
            }
        }

        private static void createAndShowGui() {
            ButtonGridEg mainPanel = new ButtonGridEg();
            mainPanel.setSize(450,600);
            JFrame frame = new JFrame("ButtonGridEg");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.pack();
            frame.setLocationByPlatform(true);
            //frame.setSize(450,600);
            frame.setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGui();
                }
            });
        }

        /*
    class ClickListener implements MouseListener
    {
            public void mouseClicked(MouseEvent e){
                t = new Thread(new Traitement());
                t.start();
                scoreJoueur += 300;

            }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    class Traitement implements Runnable{
        public void run(){

            if(scoreJoueur <= goal){
                barreScore.setValue(scoreJoueur);
            }

            if(scoreJoueur >= goal){
                JFrame jf = new JFrame();
                JDialog jd = new JDialog(jf, "Gagné", true);
                jd.setLayout(new BorderLayout());

                JLabel win = new JLabel("Bien joué !");

                JButton menuButton = new JButton("Menu");
                JButton retryButton = new JButton("Réessayer");
                JButton nextLvlButton = new JButton("Prochain Niveau");

                JPanel boutton = new JPanel();
                boutton.setLayout(new GridLayout(1,3));
                boutton.add(menuButton);
                boutton.add(retryButton);
                boutton.add(nextLvlButton);

                jd.getContentPane().add(win, BorderLayout.CENTER);
                jd.getContentPane().add(boutton, BorderLayout.SOUTH);

                win.setHorizontalAlignment(JLabel.CENTER);
                win.setVerticalAlignment(JLabel.CENTER);
                jd.setSize(new Dimension(450, 200));
                jd.setVisible(true);
            }

        }

    }
    */
    }
