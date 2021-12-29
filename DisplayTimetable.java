import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisplayTimetable extends JFrame {
    private String SID;
    private final String StdClass;
    private JPanel mainpanel;
    private JButton BACKButton;
    private JTable timetable;

    public DisplayTimetable(String SID,String StdClass) throws SQLException, InstantiationException, IllegalAccessException {
        super();
        this.StdClass = StdClass;
        this.SID = SID;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainpanel);
        this.pack();
        setSize(300, 400);


        DB conn = new DB();


        List<timetables> tt = new ArrayList<>();

        Statement st = conn.getConnection().createStatement();
        String sql = "SELECT * FROM " + StdClass + "";

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            String TID = rs.getString("TID");
            String Subject = rs.getString("Subject");
            String Day = rs.getString("Day");
            String session = rs.getString("Session");
            tt.add(new timetables(TID,Subject,Day,session));
        }
        rs.close();
        DisplayTimetable.StudentsTableModel studentsTableModel = new DisplayTimetable.StudentsTableModel(tt);
        timetable.setModel(studentsTableModel);
        timetable.setAutoCreateRowSorter(true);
        timetable.setAutoResizeMode(400);

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Students obj = new Students(SID,StdClass);
                obj.setVisible(true);
                dispose();
            }
        });
    }

    private static class StudentsTableModel extends AbstractTableModel {
        private final String[] COLUMNS = {"SID", "FirstName", "LastName", "ParentsName", "aclass", "RegisteredBy"};
        private List<timetables> rs;

        private StudentsTableModel(List<timetables> timetable) {
            this.rs = timetable;
        }

        @Override
        public int getRowCount() {
            return rs.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> rs.get(rowIndex).getTID();
                case 1 -> rs.get(rowIndex).getSubject();
                case 2 -> rs.get(rowIndex).getDay();
                case 3 -> rs.get(rowIndex).getSessionTime();
                default -> "-";
            };
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getValueAt(0, columnIndex) != null) {
                return getValueAt(0, columnIndex).getClass();
            } else {
                return Object.class;
            }
        }

    }
}
