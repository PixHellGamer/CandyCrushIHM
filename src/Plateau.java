import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Plateau {

    private int hauteur = 12;
    private int largeur = 9;
    private int[][] plateau;
    private Random random = new Random();

    public Plateau(){


    plateau = new int[hauteur][largeur];
        // Génération aléatoire des bonbons
        for(int i = 0; i < hauteur; i++){
            for(int j = 0; j < largeur; j++){
                plateau[i][j] = random.nextInt(6);
            }
        }
    }

    public int getHauteur(){
        return this.hauteur;
    }

    public int getLargeur(){
        return this.largeur;
    }

    public int[][] getPlateau(){
        return this.plateau;
    }
}
