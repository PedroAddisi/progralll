package logic;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

public class Gui {

    private JFrame frame;
    private Master master;
    private JTextField ingresarPalabra;
    private JPanel panelDeResultados;
    private JLabel panelDeEstado;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {//hilo para ejecutar la interfaz gráfica
            public void run() {
                try {
                    Gui window = new Gui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Gui() {
        master = new Master("java"); // Palabra secreta por ahora
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        // Label de estado
        panelDeEstado = new JLabel("Intento: " + master.getIntentos());
        frame.add(panelDeEstado);

        // Input para la palabra
        ingresarPalabra = new JTextField("Ingresa una palabra");
        frame.add(ingresarPalabra);

        // Botón para enviar
        JButton submitButton = new JButton("Verificar");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Advinar();
            }
        });
        frame.add(submitButton);

        // Panel para mostrar resultados
        panelDeResultados = new JPanel();
        panelDeResultados.setLayout(new GridLayout(1, 5, 5, 5));
        frame.add(panelDeResultados);
    }

    private void Advinar() {
        String Palabra = ingresarPalabra.getText().trim();
        
        if (Palabra.isEmpty() || Palabra.equals("Ingresa una palabra")) {
            panelDeEstado.setText("¡Escribe una palabra!");
            return;
        }

        // El Master procesa y devuelve los colores
        int[] colors = master.controlarPalabra(Palabra);
        
        if (colors == null) {
            panelDeEstado.setText("La palabra debe tener la misma longitud");
            return;
        }

        // Mostrar resultado visualmente
        mostrarResultado(Palabra, colors);

        // Verificar si ganó
        if (master.Ganaste(Palabra)) {
            panelDeEstado.setText("¡¡GANASTE!! La palabra era: " + master.getPalabraRandom());
            ingresarPalabra.setEnabled(false);
        } else if (master.Perdiste()) {
            panelDeEstado.setText("¡PERDISTE! La palabra era: " + master.getPalabraRandom());
            ingresarPalabra.setEnabled(false);
        } else {
            panelDeEstado.setText("Intento: " + master.getIntentos());
        }

        ingresarPalabra.setText("");
    }

    private void panelDeResultados(String word, int[] colors) {
		mostrarResultado(word, colors);
	}

	private void mostrarResultado(String word, int[] colors) {
        panelDeResultados.removeAll();//limpia el panel de resultados para mostrar el nuevo intento
        
        for (int i = 0; i < word.length(); i++) {
            JLabel letterLabel = new JLabel(String.valueOf(word.charAt(i)).toUpperCase());//crea un JLabel para cada letra del intento
            
            // 0 = Verde, 1 = Amarillo, 2 = Gris
            if (colors[i] == 0) {
                letterLabel.setBackground(Color.GREEN);
            } else if (colors[i] == 1) {
                letterLabel.setBackground(Color.YELLOW);
            } else {
                letterLabel.setBackground(Color.GRAY);
            }
            
            letterLabel.setOpaque(true);
            letterLabel.setHorizontalAlignment(JLabel.CENTER);
            panelDeResultados.add(letterLabel);
        }
        
        panelDeResultados.revalidate();
        panelDeResultados.repaint();
    }
}