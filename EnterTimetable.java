import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnterTimetable extends JFrame{
    private final String tid;
    private JPanel mainpanel;
    private JLabel tlabel;
    private JComboBox classcomboBox;
    private JTextField tidtextField;
    private JComboBox subjectcomboBox;
    private JComboBox sessioncomboBox;
    private JButton SUBMITTIMETABLEButton;
    private JComboBox daycomboBox;

    public EnterTimetable(String tid){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainpanel);
        this.pack();
        this.tid =tid;
        tidtextField.setText(tid);

        classcomboBox.addItem("P1");classcomboBox.addItem("P2");classcomboBox.addItem("P3");classcomboBox.addItem("P4");classcomboBox.addItem("P5");classcomboBox.addItem("P6");classcomboBox.addItem("P7");
        subjectcomboBox.addItem("ENGLISH");subjectcomboBox.addItem("MATH");subjectcomboBox.addItem("SST");subjectcomboBox.addItem("SCIENCE");
        sessioncomboBox.addItem("MORNING(7:00-9:00)");sessioncomboBox.addItem("AFTER BREAK(10:00-12:00)");sessioncomboBox.addItem("AFTER LUNCH(2:00-4:00)");
        daycomboBox.addItem("Mon");daycomboBox.addItem("Tue");daycomboBox.addItem("Wed");daycomboBox.addItem("Thur");daycomboBox.addItem("Fri");


        SUBMITTIMETABLEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DB conn = new DB();
                String timetableClass= (String) classcomboBox.getSelectedItem();
                String subject= (String) subjectcomboBox.getSelectedItem();
                String sessionTime= (String) sessioncomboBox.getSelectedItem();
                String day= (String) daycomboBox.getSelectedItem();


                String sql ="insert into "+timetableClass+"(TID,Subject,Day,Session) values (?,?,?,?)";
                PreparedStatement ptst = null;
                try {
                    ptst = conn.getConnection().prepareStatement(sql);
                    ptst.setString(1,tid);
                    ptst.setString(2,subject);
                    ptst.setString(3,day);
                    ptst.setString(4,sessionTime);
                    ptst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "timetable has been added successfully");
                    conn.getConnection().close();

                    Teacher obj =new Teacher(tid);
                    obj.setVisible(true);
                    obj.loginbutton.setEnabled(false);
                    dispose();

                } catch (SQLException | IllegalAccessException | InstantiationException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
