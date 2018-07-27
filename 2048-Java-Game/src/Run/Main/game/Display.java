package Run.Main.game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Display extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display frame = new Display();
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
	Connection connection = null;
	private JTextField textFieldUser;
	private JPasswordField passwordField;
	private JTextField textField;
	private JLabel lblMobileNumber;
	private JLabel lblPassword;
	private JLabel lblUserName;
	private JButton btnRegister;

	public Display() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(187, 39, 86, 20);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(187, 82, 86, 20);
		contentPane.add(passwordField);

		textField = new JTextField();
		textField.setBounds(187, 118, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblMobileNumber = new JLabel("Mobile Number");
		lblMobileNumber.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 11));
		lblMobileNumber.setBounds(45, 124, 107, 14);
		contentPane.add(lblMobileNumber);

		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 11));
		lblPassword.setBounds(45, 85, 86, 14);
		contentPane.add(lblPassword);

		lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		lblUserName.setBounds(45, 42, 86, 14);
		contentPane.add(lblUserName);

		btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into RECORD values (?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldUser.getText());
					pst.setString(2, passwordField.getText());
					pst.setString(3, textField.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "Registered.");
					pst.close();
					contentPane.setVisible(false);
					Login lg = new Login();
					lg.frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnRegister.setBounds(157, 185, 116, 23);
		contentPane.add(btnRegister);
	}

}