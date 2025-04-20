import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static int reiniciarJuego = -1;

    public static void main(String[] args) throws InterruptedException, LineUnavailableException {

        // JOptionPane.showMessageDialog(null, "¿Estás listo?" );

        JFrame ventana = new JFrame("juego");
        Juego jueguito = new Juego();
        ventana.add(jueguito);
        ventana.setSize(1300, 600);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (Juego.BSO1 != null) {
            Juego.BSO1.start();
        }

        while (true) {

            if (jueguito.juegoFinalizado) {
                reiniciarJuego = JOptionPane.showConfirmDialog(null, "Has Perdido," +
                        "Quieres jugar de nuevo?", "Has perdido", JOptionPane.YES_NO_OPTION);
                if (reiniciarJuego == 0) {
                    reiniciarValores();

                } else if (reiniciarJuego == 1) {
                    System.exit(0);
                }
            } else {
                jueguito.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException excepcion) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, excepcion);
                }
                if (Juego.pierdeVida==true){
                    Juego.pierdeVida=false;
                    Juego.vidas--;
                    Auto.saltando=false;
                    Obstaculo.x_inicial=1300;
                }
            }
        }
    }

    public static void reiniciarValores() {
        Juego.juegoFinalizado=false;
        Obstaculo.x_auxiliar=-2;
        Juego.puntos=0;
        Juego.nivel=0;
        Juego.vidas=3;
        reiniciarJuego=-1;
        Obstaculo.x_inicial=1300;


    }

}