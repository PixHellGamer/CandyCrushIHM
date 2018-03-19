import javax.swing.*;
import java.awt.*;

public class FenetreMenu extends JFrame {

    public FenetreMenu(){

        JPanel menuPanel = new JPanel();

        JButton buttonPlay = new JButton("Jouer");
        JButton buttonLevel = new JButton("Niveaux");
        JButton buttonQuit = new JButton("Quitter");

        menuPanel.setSize(new Dimension(600,800));
        menuPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        JLabel gamename = new JLabel("Candy Crush");

        buttonPanel.setLayout(new GridLayout(3,1));
        buttonPanel.add(buttonPlay);
        buttonPanel.add(buttonLevel);
        buttonPanel.add(buttonQuit);
        buttonPanel.setVisible(true);
        menuPanel.add(gamename, BorderLayout.NORTH);
        menuPanel.add(buttonPanel, BorderLayout.CENTER);

        menuPanel.setVisible(true);

        this.add(menuPanel);
        this.setVisible(true);
        this.setSize(600, 800);
    }

    public static void main(String[] args){
        FenetreMenu f = new FenetreMenu();

    }
}
