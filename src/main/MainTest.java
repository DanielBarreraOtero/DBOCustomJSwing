package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import componentes.cJTextField.CJTextField;

import java.awt.Color;
import java.awt.FlowLayout;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class MainTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JFormattedTextField formattedTextField;
	private JLabel lblTest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainTest frame = new MainTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		CJTextField CJT = null;
		try {
			CJT = new CJTextField("Mi caja", CJTextField.NUMERICO_CEROS);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		CJT.setHorizontalAlignment(JTextField.RIGHT);
		CJT.setBackground(Color.WHITE);
		
		//TODO FALTA CAMBIAR LA HORIENTAZION DEL TEXTFIELD CUANDO EL TIPO SEA NUMERICO_CEROS
//		CJT.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//		CJT.setColumns(50);
		
		contentPane.add(CJT);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblTest = new JLabel("Test Formato");
		contentPane.add(lblTest);
		
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("#,##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		formattedTextField = new JFormattedTextField(mask);
		formattedTextField.setColumns(10);
		
		contentPane.add(formattedTextField);
	}
	
}
