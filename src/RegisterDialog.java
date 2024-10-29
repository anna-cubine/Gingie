import javax.swing.*;
import java.awt.*;

public class RegisterDialog extends JDialog {
	public RegisterDialog(JFrame parent) {
		super(parent, "Register", true);
		setLayout(new GridLayout(4, 2));
		setSize(300, 200);
		
		add(new JLabel("Username:"));
		JTextField usernameField = new JTextField(20);
		add(usernameField);
		
		add(new JLabel("Password:"));
		JPasswordField passwordField = new JPasswordField(20);
		add(passwordField);
		
		add(new JLabel("Email:"));
		JTextField emailField = new JTextField(20);
		add(emailField);
		
		JButton registerButton = new JButton("Create Account");
		JButton backButton = new JButton("< Back");
		
		add(backButton);
		add(registerButton);
		
		backButton.addActionListener(e -> dispose());
		registerButton.addActionListener(e -> dispose());
		setLocationRelativeTo(parent);
	}
}