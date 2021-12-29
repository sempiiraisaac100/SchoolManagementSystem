import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    public Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException {
        String dbURL = "jdbc:mysql://localhost:3306/katikamudb";
        String username = "marv";
        String password = "@marvin256";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch( ClassNotFoundException e ) {
            e.printStackTrace();
        }


        Connection con = DriverManager.getConnection( dbURL, username, password );

        if ( con != null ) {
            return con;
        }
        else return null;
    }


    public  String MD5(String password) {

            String passwordToHash = password;
            String generatedPassword = null;

            try
            {
                // Create MessageDigest instance for MD5
                MessageDigest md = MessageDigest.getInstance("MD5");

                // Add password bytes to digest
                md.update(passwordToHash.getBytes());

                // Get the hash's bytes
                byte[] bytes = md.digest();

                // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }

                // Get complete hashed password in hex format
                generatedPassword = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return generatedPassword;
        }
    }
//cellVectorHolder.addElement(list);
//        for(Iterator iterator1 = cellVectorHolder.iterator();iterator1.hasNext();) {
//        List list1 = (List) iterator.next();
//        String sid = list1.get(0).toString();
//        String subject = list1.get(1).toString();
//        int marks = Integer.parseInt(list1.get(2).toString());
//        String tid = list1.get(3).toString();
//
//        String sql = "INSERT INTO Marks (SID,Subject,Marks,TID) VALUES (?, ?, ?,?)";
//        PreparedStatement ps = conn.getConnection().prepareStatement(sql);
//        ps.setString(1, sid);
//        ps.setString(2, subject);
//        ps.setInt(3, marks);
//        ps.setString(4, tid);
//        ps.executeUpdate();
//        System.out.println("Values Inserted Successfully");