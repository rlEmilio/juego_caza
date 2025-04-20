import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Obstaculo {

    //objeto clase juego
    Juego jueguito;
    //variables que delimitan el area del obstaculo
    Area cabeza, cuerpo, vaca;
    //variables tamaño del personaje
    int anchoObstaculo = 80;
    int altoObstaculo = 110;
    //coordenadas iniciales donde se ubica el objeto
    static int x_inicial = 1300;
    int y_inicial = 270;
    //coordenadas para manipular el objeto
    static int x_auxiliar = -5;


    public Obstaculo(Juego jueguito) {
        this.jueguito = jueguito;
    }

    public void mover() {
        if (x_inicial <= -100) {
            jueguito.puntos++;
            x_inicial = 1300;
            int aleatorio = (int) (Math.random() * 2) + 1;
            if (aleatorio == 1) {
                y_inicial = 270;
            } else {
                y_inicial = 190;
            }


            if (jueguito.puntos == 3 || jueguito.puntos == 6 || jueguito.puntos == 9 || jueguito.puntos == 12) {
                x_auxiliar += -2;
                jueguito.nivel++;
            }
        } else {
            if (colision()) {
                if (jueguito.vidas == 0) {
                    jueguito.finJuego();
                } else {
                    Juego.pierdeVida = true;
                }
                if (Juego.sonidoChoque != null) {
                    Juego.sonidoChoque.setFramePosition(0); // ← esto reinicia el audio al inicio
                    Juego.sonidoChoque.start();
                }
            } else {
                x_inicial += x_auxiliar;
            }

        }
    }

    public Area getBounds() {
        Ellipse2D forma1 = new Ellipse2D.Double(x_inicial, y_inicial, 40, 40);
        Rectangle forma2 = new Rectangle(x_inicial + 12, y_inicial + 16, 50, 53);

        cabeza = new Area(forma1);
        cuerpo = new Area(forma2);

        vaca = cabeza;
        vaca.add(cuerpo);

        return vaca;
    }

    public boolean colision() {
        Area areaA = new Area(jueguito.auto.getBounds());
        areaA.intersect(getBounds());

        return !areaA.isEmpty();
    }

    public void paint(Graphics2D g) {
        ImageIcon obstaculo = new ImageIcon(getClass().getResource("/multimedia/misil.png"));
        g.drawImage(obstaculo.getImage(), x_inicial, y_inicial, anchoObstaculo, altoObstaculo, null);

    }
}
