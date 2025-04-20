import javax.swing.*;
import java.awt.*;

public class Fondo {

    //objeto de la clase juego
    Juego jueguito;
    //variables tamaño del fondo
    int anchoFondo = 1300;
    int altoFondo = 600;
    //coordenadas iniciales para mover el fondo
    int x1 = 1300;
    int y1 = 0;
    //coordenadas auxiliares que mueven otro fondo
    int x2 = 0;
    int y2 = 0;


    public Fondo(Juego jueguito) {
        this.jueguito = jueguito;

    }

    public void paint(Graphics2D g) {
        ImageIcon fondo = new ImageIcon(getClass().getResource("/multimedia/fondo.png"));
        g.drawImage(fondo.getImage(), x1, y1, anchoFondo, altoFondo, null);
        g.drawImage(fondo.getImage(), x2, y2, anchoFondo, altoFondo, null);
    }

    public void mover(){
        x1-=5;
        x2-=5;
        if (x1==0 && x2==-1300){
            x1=1300;
            x2=0;
        }
    }
}