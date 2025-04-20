import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;

public class Juego extends JPanel {

    URL direccionSonidoSalto, direccionSonidoChoque, direccionBSO1;
   static Clip sonidoChoque, sonidoSalto, BSO1;

    // Objetos de las clases Auto, Obstaculo y Fondo
    Auto auto = new Auto(this);
    Obstaculo obstaculo = new Obstaculo(this);
    Fondo fondo = new Fondo(this);

    // Variables para el juego
    static boolean juegoFinalizado = false;
    static boolean pierdeVida = false;
    static int vidas = 3;
    static int puntos = 0;
    static int nivel = 1;

    public Juego() {
        try {
            // Cargar sonido de choque
            direccionSonidoChoque = getClass().getResource("/multimedia/choque.wav");
            AudioInputStream audioChoque = AudioSystem.getAudioInputStream(direccionSonidoChoque);
            sonidoChoque = AudioSystem.getClip();
            sonidoChoque.open(audioChoque);

            // Cargar sonido de salto
            direccionSonidoSalto = getClass().getResource("/multimedia/salto.wav");
            AudioInputStream audioSalto = AudioSystem.getAudioInputStream(direccionSonidoSalto);
            sonidoSalto = AudioSystem.getClip();
            sonidoSalto.open(audioSalto);

            //Cargar bso1
            direccionBSO1 = getClass().getResource("/multimedia/BSO1.wav");
            AudioInputStream audioBSO1 = AudioSystem.getAudioInputStream(direccionBSO1);
            BSO1 = AudioSystem.getClip();
            BSO1.open(audioBSO1);



        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                auto.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                auto.keyReleased(e);
            }
        });

        setFocusable(true);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        dibujar(g2);
        dibujarPuntaje(g2);
    }

    public void dibujar(Graphics2D g) {
        fondo.paint(g);
        auto.paint(g);
        obstaculo.paint(g);
        mover();
    }

    public void mover(){
        obstaculo.mover();
        auto.mover();
        fondo.mover();

    }

    public void dibujarPuntaje(Graphics2D g) {

        Font score = new Font("Arial", Font.BOLD, 30);
        g.setFont(score);
        g.setColor(Color.darkGray);
        g.drawString("Puntaje: " + puntos, 1100, 30);
        g.drawString("Vidas: " + vidas, 20, 30);
        g.drawString("Nivel: " + nivel, 570, 30);

        if (juegoFinalizado) {
            g.setColor(Color.red);
            g.drawString("¡¡¡Has Perdido!!!", ((float) getBounds().getCenterX() / 2) + 170, 70);
        }
    }

    public void finJuego(){
        juegoFinalizado=true;

    }

}
