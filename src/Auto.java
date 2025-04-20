import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Auto {
    //objeto clase juego
    Juego jueguito;
    //variables que nos ayudan a saber si el auto salta o no
    static boolean saltando = false;
    boolean sube = false;
    boolean baja = false;
    //variables que delimitan el area del objeto
    Area llantaDelantera, llantaTrasera, carroceria, tractor;
    //variables de tamaño del personaje;
    int anchoPersonaje = 130;
    int altoPersonaje = 90;
    //coordenadas iniciales de personaje;
    static int x_inicial = 50;
    static int y_inicial = 150;
    //coordenadas manipular el personaje;
    int x_auxiliar = 0;
    int y_auxiliar = 0;


    public Auto(Juego jueguito) {
        this.jueguito = jueguito;
    }

    public void paint(Graphics2D g) {
        ImageIcon auto = new ImageIcon(getClass().getResource("/multimedia/avion.png"));
        g.drawImage(auto.getImage(), x_inicial, y_inicial, anchoPersonaje, altoPersonaje, null);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            saltando = true;

        }
    }

    public Area getBounds(){
        Rectangle forma1=new Rectangle(x_inicial, y_inicial, 95, 62);
        carroceria = new Area(forma1);

        Ellipse2D forma2 = new Ellipse2D.Double(x_inicial,y_inicial+28,48,48);
        llantaTrasera= new Area(forma2);
        Ellipse2D forma3 = new Ellipse2D.Double(x_inicial+73,y_inicial+29,30,38);

        llantaDelantera = new Area(forma3);
        tractor=carroceria;

        tractor.add(llantaTrasera);
        tractor.add(llantaDelantera);

        return tractor;

    }


    public void mover() {
        //si el personaje no sale de la pantalla
        if (x_inicial + x_auxiliar > 0 && x_inicial + x_auxiliar < jueguito.getWidth() - anchoPersonaje) {
            x_inicial += x_auxiliar;
        }
        if (saltando) {
            if (y_inicial == 270) { //si el auto esta en el suelo
                sube = true;
                y_auxiliar = -5;
                baja = false;
            }
            if (y_inicial == 110) { //si el auto llego al limite del salto
                baja = true;
                y_auxiliar = 5;
                sube = false;
            }

            if (sube) {
                y_inicial += y_auxiliar;
            }

            if (baja) {
                y_inicial += y_auxiliar;
                if (y_inicial == 270) {
                    saltando = false;
                }
            }
        }
    }
}
