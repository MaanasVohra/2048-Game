package Run.Main.game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	private JTextField textFieldUser;
	private JPasswordField passwordField;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 599, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setBounds(245, 49, 74, 30);
		frame.getContentPane().add(lblLogin);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(245, 141, 86, 20);
		frame.getContentPane().add(textFieldUser);
		textFieldUser.setColumns(10);

		JLabel lblUsername = new JLabel("User_Name");
		lblUsername.setBounds(135, 147, 86, 14);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(125, 207, 74, 14);
		frame.getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(245, 204, 86, 20);
		frame.getContentPane().add(passwordField);

		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Display disp = new Display();
				disp.setVisible(true);
			}
		});
		btnRegister.setBounds(231, 330, 113, 23);
		frame.getContentPane().add(btnRegister);

		JLabel lblNewUser = new JLabel("New User?");
		lblNewUser.setBounds(245, 305, 86, 14);
		frame.getContentPane().add(lblNewUser);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from RECORD where User=?;";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldUser.getText());
					ResultSet rs = pst.executeQuery();
					if (!rs.next()) {
						JOptionPane.showMessageDialog(null, "Invalid User Name");
					} else {
						String psswd = rs.getString(2);
						if (psswd.equals(passwordField.getText())) {
							frame.dispose();
							/// Open Game
							Game game = new Game();
							JFrame window = new JFrame("2048");
							window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							window.setResizable(true);
							window.getContentPane().add(game);
							window.pack();
							window.setLocationRelativeTo(null);
							window.setVisible(true);
							game.start();
						} else {
							JOptionPane.showMessageDialog(null, "Invalid Password");
						}
					}
					pst.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(242, 248, 89, 23);
		frame.getContentPane().add(btnLogin);
	}
}
