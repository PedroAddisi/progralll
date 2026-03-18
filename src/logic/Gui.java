package logic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
public class Gui {
	JFrame inicio;
    JFrame frame;
    private Logica logica;
    private JTextField ingresarPalabra;
    private JPanel panelDeResultados;
    private JLabel panelDeEstado;
    private JTextField txtIngresarNombre;
    public Gui() {
    	String palabraSecreta = Logica.obtenerPalabraRandom();
        logica = new Logica(palabraSecreta);
       // initialize();
        pantallainicio();
    }
    private void pantallainicio() {
    	inicio = new JFrame();
    	inicio.getContentPane().setForeground(new Color(255, 255, 255));
    	inicio.getContentPane().setBackground(new Color(255, 255, 255));
    	inicio.getContentPane().setLayout(null);
    	
    	JLabel label = new JLabel("");
    	label.setBounds(0, 0, 161, 120);
    	inicio.getContentPane().add(label);
    	
    	JLabel lblNewLabel_1 = new JLabel("Wungsdle");
    	lblNewLabel_1.setBounds(0, 0, 394, 120);
    	lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    	inicio.getContentPane().add(lblNewLabel_1);
    	
    	JComboBox comboBox_1 = new JComboBox();
    	comboBox_1.setBounds(404, 0, 79, 50);
    	comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"ES", "EN", "FR", "IT"}));
    	inicio.getContentPane().add(comboBox_1);
    	
    	JLabel label_1 = new JLabel("");
    	label_1.setBounds(0, 120, 161, 120);
    	inicio.getContentPane().add(label_1);
    	
    	JButton btnNewButton_1 = new JButton("Jugar");
    	btnNewButton_1.setBounds(0, 120, 483, 120);
    	btnNewButton_1.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			 inicio.setVisible(false);
 				frame.setVisible(true);
    		}
    	});
    	inicio.getContentPane().add(btnNewButton_1);
    	
    	JLabel label_2 = new JLabel("");
    	label_2.setBounds(322, 120, 161, 120);
    	inicio.getContentPane().add(label_2);
    	
    	JComboBox comboBox_2 = new JComboBox();
    	comboBox_2.setBounds(0, 310, 139, 50);
    	comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"normal( 5 Letras)", "dificil(8 Letras)", "pesadilla(10 Letras)"}));
    	inicio.getContentPane().add(comboBox_2);
    	
    	txtIngresarNombre = new JTextField();
    	txtIngresarNombre.setBounds(140, 240, 343, 120);
    	txtIngresarNombre.setText("Ingresar nombre");
    	inicio.getContentPane().add(txtIngresarNombre);
    	txtIngresarNombre.setColumns(10);
    	
    	JLabel label_3 = new JLabel("");
    	label_3.setBounds(322, 240, 161, 120);
    	inicio.getContentPane().add(label_3);
        inicio.setBounds(100, 100, 500, 400);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel lblNewLabel = new JLabel("Wungsdle");
        lblNewLabel.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"ES", "EN", "FR", "IT"}));
        comboBox.setToolTipText("");
    }
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(4, 1, 10, 10));

        // Label de estado
        panelDeEstado = new JLabel("Intento: " + logica.getIntentos());
        panelDeEstado.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.getContentPane().add(panelDeEstado);

        // Input para la palabra
        ingresarPalabra = new JTextField("Ingresa una palabra");
        ingresarPalabra.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.getContentPane().add(ingresarPalabra);

        // Botón para enviar
        JButton submitButton = new JButton("Verificar");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.addActionListener(new ActionListener() {//acción al hacer clic en el botón
            @Override
            public void actionPerformed(ActionEvent e) {
                Advinar();
            }
        });
        frame.getContentPane().add(submitButton);

        // Panel para mostrar resultados
        panelDeResultados = new JPanel();
        panelDeResultados.setLayout(new GridLayout(1, 5, 5, 5));
        frame.getContentPane().add(panelDeResultados);
    }

    private void Advinar() {
        String Palabra = ingresarPalabra.getText().trim();
        
        if (Palabra.isEmpty() || Palabra.equals("Ingresa una palabra")) {
            panelDeEstado.setText("¡Escribi una palabra ");
            return;
        }

        // El Logica procesa y devuelve los colores
        int[] colors = logica.controlarPalabra(Palabra);
        
        if (colors == null) {
            panelDeEstado.setText("La palabra debe tener la misma longitud");
            return;
        }

        // Mostrar resultado visualmente
        mostrarResultado(Palabra, colors);

        // Verificar si ganó
        if (logica.Ganaste(Palabra)) {
            panelDeEstado.setText("GANASTE La palabra era: " + logica.getPalabraRandom());
            ingresarPalabra.setEnabled(false);
        } else if (logica.Perdiste()) {
            panelDeEstado.setText("¡PERDISTE! La palabra era: " + logica.getPalabraRandom());
            ingresarPalabra.setEnabled(false);
        } else {
            panelDeEstado.setText("Intento: " + logica.getIntentos());
        }

        ingresarPalabra.setText("");
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