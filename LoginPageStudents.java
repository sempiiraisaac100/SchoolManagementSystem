import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginPageStudents extends JFrame {

    private JPanel loginPanel;
    public JButton LOGINButton;
    private JLabel ttile;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton BACKButton;
    public String SID;
    public String Stdclass;


    public LoginPageStudents(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();

        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DB conn = new DB();
                String username = userNameField.getText();
                String password = conn.MD5(String.valueOf(passwordField.getPassword()));

                String sql ="SELECT SID,`UserName`,`Password`,Class FROM `Students` WHERE `UserName`='"+username+"' && `Password`= '"+password+"'";
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
                            SID = rs.getString("SID");
                            Stdclass = rs.getString("Class");
                            Students obj =new Students(SID,Stdclass);
                            obj.setVisible(true);
                            dispose();
                        }else{
                        JOptionPane.showMessageDialog(new JFrame(),"Wrong Username Or  Password,TRY AGAIN");
                    }
                    } catch (SQLException exception) {
                    exception.printStackTrace();
                }
                }
        });
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcome obj =new welcome("Back to welcome from Students");
                obj.setVisible(true);
                dispose();
            }
        });
        userNameField.getDocument().addDocumentListener(new DocumentListener() {
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
                if (userNameField.getText().equals("")){
                    LOGINButton.setEnabled(false);
                }
                else {
                    LOGINButton.setEnabled(true);
                }

            }
        });
    }

}
