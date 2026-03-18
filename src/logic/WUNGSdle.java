package logic;

import java.awt.EventQueue;

public class WUNGSdle {
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {//hilo para ejecutar la interfaz gráfica
            public void run() {
                try {
                    Gui window = new Gui();
                    window.inicio.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
