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
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            y_auxiliar = -5;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            y_auxiliar = 5;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x_auxiliar = 5;
        }

        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x_auxiliar = -5;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            y_auxiliar = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
            x_auxiliar = 0;
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
        int nuevaY = y_inicial + y_auxiliar;
        int nuevaX = x_inicial + x_auxiliar;
        // No permitir que suba por encima del margen superior (40 píxeles)
        // Ni que baje por debajo del fondo (alto ventana - alto personaje)
        if (nuevaY >= 40 && nuevaY <= (jueguito.getHeight() - altoPersonaje)) {
            y_inicial = nuevaY;
        }
        if (nuevaX >= 5 && nuevaX <= (jueguito.getWidth() - anchoPersonaje)) {
            x_inicial = nuevaX;
        }
    }
}
