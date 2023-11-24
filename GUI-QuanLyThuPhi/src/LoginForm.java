import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LoginForm {
    // Create a simple login form with two text fields and a button.
    // The button should be disabled until both text fields are filled.
    // When the button is pressed, show a dialog with the text "Welcome!".
    // Use the following names and IDs for the components:
    // - Username text field: usernameTextField
    // - Password text field: passwordTextField
    // - Login button: loginButton

    private JPanel mainPanel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JButton loginButton;

    public LoginForm() {
        loginButton.setEnabled(false);
        loginButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Welcome!");
        });
        usernameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                check();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                check();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                check();
            }
            private void check() {
                loginButton.setEnabled(!usernameTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty());
            }
        });
        passwordTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                check();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                check();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                check();
            }
            private void check() {
                loginButton.setEnabled(!usernameTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty());
            }
        });

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
