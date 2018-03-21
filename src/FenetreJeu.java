import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Thread.*;


public class FenetreJeu extends JFrame{

    Jeu jeu = new Jeu();
    JPanel header = new JPanel();
    JPanel gameGrid = new JPanel();
    JPanel scorePanel = new JPanel();
    int scoreJoueur = 0;
    private Thread t;
    JProgressBar barreScore;
    //Timer, Niveau et objectif seront récupérés dans un fichier niveau
    int time = jeu.getTemps();
    int lvl = jeu.getNiveau();
    int goal = jeu.getObjectif();

    public FenetreJeu(){


        JPanel gamePanel = new JPanel();

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setSize(600, 800);


        JLabel temps = new JLabel("Temps: " + time);
        JLabel niveau = new JLabel(""+lvl);
        JLabel objectif = new JLabel("Objectif : " + goal);

        header.setLayout(new GridLayout(1, 3));
        header.add(temps);
        header.add(niveau);
        header.add(objectif);

        //gameGrid.setLayout(new GridLayout(12,9));
        ButtonGridEg btngrid = new ButtonGridEg();

        barreScore = new JProgressBar();
        barreScore.setPreferredSize(new Dimension(450,30));
        barreScore.setMinimum(0);
        barreScore.setMaximum(goal);
        barreScore.setValue(scoreJoueur);
        barreScore.setStringPainted(true);

        scorePanel.add(barreScore);

        gamePanel.add(header, BorderLayout.NORTH);
        gamePanel.add(btngrid, BorderLayout.CENTER);
        gamePanel.add(scorePanel, BorderLayout.SOUTH);
        gamePanel.setVisible(true);

        this.add(gamePanel);
        this.setVisible(true);
        this.setSize(600, 800);

    }

    public static void main(String[] args){
        FenetreJeu f = new FenetreJeu();

    }

}
