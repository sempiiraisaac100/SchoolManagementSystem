import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class welcome extends JFrame {
    private JPanel welcomePanel;
    private JLabel title;
    private JButton STUDENTSButton;
    private JButton TEACHERSButton;

    public welcome(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(welcomePanel);
        this.pack();
        STUDENTSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPageStudents obj =new LoginPageStudents("Students Form");
                obj.setVisible(true);
                dispose();
                obj.LOGINButton.setEnabled(false);
            }
        });
        TEACHERSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPageTeachers obj =new LoginPageTeachers("Teacher Login");
                obj.setVisible(true);
                dispose();
                obj.LOGINButton.setEnabled(false);
            }
        });
    }
    public static void main(String [] args){

        JFrame frame = new welcome("Katikamu System");
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
