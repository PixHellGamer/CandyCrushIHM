import java.awt.*;

public class Bonbon {

    private Image image;
    private int numero;
    private Image[] tabImage;


    public void setImage(Image image){
        this.image = image;
    }

    public void setNumero(int num){
        this.numero = num;
    }

    public Bonbon(int num){
        this.image = tabImage[num];
    }

}
