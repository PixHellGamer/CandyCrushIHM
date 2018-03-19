import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Jeu {

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getObjectif() {
        return objectif;
    }

    public void setObjectif(int objectif) {
        this.objectif = objectif;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public ImageIcon[] getTabImageBonbon() {
        return tabImageBonbon;
    }

    public void setTabImageBonbon(ImageIcon[] tabImageBonbon) {
        this.tabImageBonbon = tabImageBonbon;
    }

    public JFrame getFenetre() {
        return fenetre;
    }

    public void setFenetre(JFrame fenetre) {
        this.fenetre = fenetre;
    }

    public JPanel getHeader() {
        return header;
    }

    public void setHeader(JPanel header) {
        this.header = header;
    }

    public JPanel getTableau() {
        return tableau;
    }

    public void setTableau(JPanel tableau) {
        this.tableau = tableau;
    }

    public boolean[][] getTabCacheBonbon() {
        return tabCacheBonbon;
    }

    public void setTabCacheBonbon(boolean[][] tabCacheBonbon) {
        this.tabCacheBonbon = tabCacheBonbon;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int[][] getTabBonbon() {
        return tabBonbon;
    }

    public void setTabBonbon(int[][] tabBonbon) {
        this.tabBonbon = tabBonbon;
    }

    public JLabel[][] getTabLabel() {
        return tabLabel;
    }

    public void setTabLabel(JLabel[][] tabLabel) {
        this.tabLabel = tabLabel;
    }

    private int niveau;
    private int objectif;
    private int temps;
    private Plateau plateau = new Plateau();
    private ImageIcon[] tabImageBonbon = new ImageIcon[6];
    private JFrame fenetre = new JFrame();
    private JPanel header = new JPanel();
    private JPanel tableau = new JPanel();
    private boolean[][] tabCacheBonbon = new boolean [plateau.getHauteur()][plateau.getLargeur()];
    private Random random = new Random();

    private int[][] tabBonbon = plateau.getPlateau();
    private JLabel[][] tabLabel = new JLabel[plateau.getHauteur()][plateau.getLargeur()];

    public Jeu(){

        this.score = 0;
        this.niveau = 1;
        this.objectif = 100;
        this.temps = 180;



        // Initialisation fenetre
        fenetre.setLayout(new BorderLayout());
        majTabLabel();
        initTabImageBonbon();
        initHeader();
        initPlateau();

        // -----PARTIE TEST----
        popBonbon();
        descendreBonbon();
        majPlateau();
        // --------------------

        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setTitle("Dancy Drush");

        fenetre.setSize(800, 850);
        fenetre.setLocationRelativeTo(null);

    }

    public void majTabLabel(){
        for(int i = 0; i < plateau.getHauteur(); i++)
            for(int j = 0; j < plateau.getLargeur(); j++){
                tabLabel[i][j] = new JLabel();
                tabLabel[i][j].setIcon(this.tabImageBonbon[tabBonbon[i][j]]);
            }
    }

    public void initTabImageBonbon(){
        tabImageBonbon[0] = new ImageIcon("Blue.png");
        tabImageBonbon[1] = new ImageIcon("Green.png");
        tabImageBonbon[2] = new ImageIcon("Orange.png");
        tabImageBonbon[3] = new ImageIcon("Purple.png");
        tabImageBonbon[4] = new ImageIcon("Red.png");
        tabImageBonbon[5] = new ImageIcon("Yellow.png");
        // tabImageBonbon[6] = new ImageIcon(path bonbon explo);
    }

    public void initHeader(){
        header.setLayout(new GridLayout(1, 4));
        header.setPreferredSize(new Dimension(800, 50));
        header.setBackground(Color.CYAN);
        JLabel score = new JLabel("Score : ".concat(Integer.toString(this.score)));
        JLabel niveau = new JLabel("Niveau : ".concat(Integer.toString(this.niveau)));
        JLabel objectif = new JLabel("Objectif : ".concat(Integer.toString(this.objectif)));
        JLabel temps = new JLabel("Temps restant : ".concat(Integer.toString(this.temps)));

        score.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                popBonbon();
            }
        });
        niveau.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                descendreBonbon();
            }
        });
        objectif.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                majPlateau();
            }
        });
        temps.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                popBonbon();
                descendreBonbon();
                majPlateau();
            }
        });

        header.add(score);
        header.add(niveau);
        header.add(objectif);
        header.add(temps);
        fenetre.getContentPane().add(header, BorderLayout.NORTH);
    }

    public void initPlateau(){
        tableau.setLayout(new GridLayout(plateau.getHauteur(), plateau.getLargeur()));
        tableau.setPreferredSize(new Dimension(800, 800));
        tableau.setBackground(Color.ORANGE);
        fenetre.getContentPane().add(tableau, BorderLayout.CENTER);

        for(int i = 0; i < plateau.getHauteur(); i++)
            for(int j = 0; j < plateau.getLargeur(); j++){
                tableau.add(tabLabel[i][j]);
            }
    }

    public void majPlateau(){
        for(int i = 0; i < plateau.getHauteur(); i++){
            for(int j = 0; j < plateau.getLargeur(); j++){
                if(tabBonbon[i][j] == -1)
                {
                    tabLabel[i][j].setText("Error");
                }
                else tabLabel[i][j].setIcon(this.tabImageBonbon[tabBonbon[i][j]]);
            }
        }
    }

    public void popBonbon(){

        int i = 0, j = 0, k = 0, l = 0, cpt = 0, serie = -1;

        for(i = 0; i < plateau.getHauteur(); i++){
            for(j = 0; j < plateau.getLargeur(); j++){
                tabCacheBonbon[i][j] = false;
            }
        }


        //Parcours en lignes
        for(i = 0; i < plateau.getHauteur(); i++){
            for(j = 0; j < plateau.getLargeur(); j++){
                if(tabBonbon[i][j] == serie){
                    cpt++;
                }
                else{
                    if(cpt >= 3) {
                        for (k = 1; k <= cpt; k++) {
                            tabCacheBonbon[i][j - k] = true;
                        }
                        cpt = 1;
                    }
                    else {
                        cpt = 1;
                        serie = tabBonbon[i][j];
                    }
                }
            }
            cpt = 0;
            serie = -1;
        }

        //Parcours en colonne
        for(j = 0; j < plateau.getLargeur(); j++){
            for(i = 0; i < plateau.getHauteur(); i++){
                if(tabBonbon[i][j] == serie){
                    cpt++;
                }
                else{
                    if(cpt >= 3) {
                        for (k = 1; k <= cpt; k++) {
                            tabCacheBonbon[i-k][j] = true;
                        }
                        cpt = 1;
                    }
                    else {
                        cpt = 1;
                        serie = tabBonbon[i][j];
                    }
                }
            }
            cpt = 0;
            serie = -1;
        }


        // Comparaison tabCacheBonbon et tabBonbon
        for(i = 0; i < plateau.getHauteur(); i++){
            for(j = 0; j < plateau.getLargeur(); j++){
                if(tabCacheBonbon[i][j]){
                    tabBonbon[i][j] = -1;
                }
            }
        }
    }

    public void descendreBonbon() {


        //Descente bonbons avec parcours de chaque colonne en commencant par le bas
        for(int j = 0; j < plateau.getLargeur(); j++){
            int differenciel = 1;
            boolean fini = false;
            int i = plateau.getHauteur() - 1;
            while(!fini) {
                if (tabBonbon[i][j] == -1) {
                    while (i - differenciel >= 0 && tabBonbon[i - differenciel][j] == -1) {
                        differenciel++;
                    }
                    if (i - differenciel >= 0) {
                        tabBonbon[i][j] = tabBonbon[i - differenciel][j];
                        tabBonbon[i - differenciel][j] = -1;
                    } else fini = true;
                }
                else if(i == 0) fini = true;
                i--;
            }
            for(int k = i; k >= 0; k--)
                tabBonbon[k][j] = -1;
        }


        //Remplacer les -1 par de nouveaux bonbons aléatoires

        for(int j = 0; j < plateau.getLargeur(); j++)
        {
            int i = 0;
            while(tabBonbon[i][j] == -1)
            {
                tabBonbon[i][j] = random.nextInt(5);
                i++;
            }
        }
    }



}


