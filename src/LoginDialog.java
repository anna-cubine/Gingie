import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog {
	public LoginDialog(JFrame parent) {
		super(parent, "Login", true);
		setLayout(new GridLayout(3, 2));
		setSize(300, 150);
		
		add(new JLabel("Username:"));
		JTextField usernameField = new JTextField(20);
		add(usernameField);
		
		add(new JLabel("Password:"));
		JPasswordField passwordField = new JPasswordField(20);
		add(passwordField);
		
		JButton loginButton = new JButton("Login");
		add(new JLabel("New user?"));
		add(loginButton);
		
		loginButton.addActionListener(e -> dispose());
		setLocationRelativeTo(parent);
	}
}