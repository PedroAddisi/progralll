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
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Gui {
	JFrame inicio;
    JFrame frame;
    private Logica logica;
    private JTextField ingresarPalabra;
    private JPanel panelDeResultados;
    private JLabel panelDeEstado;
    public Gui() {
    	String palabraSecreta = Logica.obtenerPalabraRandom();
        logica = new Logica(palabraSecreta);
        initialize();
        pantallainicio();
    }
    private void pantallainicio() {
    	inicio = new JFrame();
    	inicio.getContentPane().setForeground(new Color(255, 255, 255));
    	inicio.getContentPane().setBackground(new Color(255, 255, 255));
        inicio.setBounds(100, 100, 500, 400);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel lblNewLabel = new JLabel("Wungsdle");
        lblNewLabel.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"ES", "EN", "FR", "IT"}));
        comboBox.setToolTipText("");
        
        JButton btnNewButton = new JButton("Jugar");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	 @Override
        	public void mouseClicked(MouseEvent e) {
        		 inicio.setVisible(false);
				frame.setVisible(true);
        	}
        });
        inicio.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNewLabel, btnNewButton, comboBox}));
        GroupLayout groupLayout = new GroupLayout(inicio.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(103)
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(183)
        					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(231, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
        			.addGap(88)
        			.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        			.addGap(154))
        );
        inicio.getContentPane().setLayout(groupLayout);
    	
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