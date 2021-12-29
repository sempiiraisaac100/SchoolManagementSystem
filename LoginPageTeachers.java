import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginPageTeachers extends JFrame {

    private JPanel loginPanel;
    private JLabel title;
    public JButton LOGINButton;
    private JTextField usernametextField;
    private JPasswordField passwordField1;
    private JButton REGISTERButton;
    private JButton BACKButton;
    public String TID;


    public LoginPageTeachers(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();

        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DB conn = new DB();
                String username = usernametextField.getText();
                String password = conn.MD5(String.valueOf(passwordField1.getPassword()));



                String sql ="SELECT TID,`UserName`,`Password` FROM `Teachers` WHERE `UserName`='"+username+"' && `Password`= '"+password+"'";
                Statement st = null;
                try {
                    st = conn.getConnection().createStatement();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
                ResultSet rs = null;
                try {
                    rs = st.executeQuery(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    if(rs.next()){
                        TID = rs.getString("TID");
                        Teacher obj =new Teacher(TID);
                        obj.setVisible(true);
                        dispose();
                        obj.loginbutton.setEnabled(false);
                    }else {
                        LoginPageTeachers obj =new LoginPageTeachers("failed to login");
                        obj.setVisible(true);
                        dispose();
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        REGISTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterFormTeacher obj =new RegisterFormTeacher();
                obj.setVisible(true);
                dispose();
                obj.regbutton.setEnabled(false);
            }
        });
        usernametextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changedUpdate(DocumentEvent e) {
                changed();
            }
            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (usernametextField.getText().equals("")){
                    LOGINButton.setEnabled(false);

                }
                else {
                    LOGINButton.setEnabled(true);
                }

            }
        });

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcome obj =new welcome("Back to welcome from Teachers");
                obj.setVisible(true);
                dispose();
            }
        });
    }

}
