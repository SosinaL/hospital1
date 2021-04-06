package klasat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDb {

   

  public static Admin login(String username, String password) {

        try (Connection con = Database.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from admin where username = ? and password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Admin a = new Admin();
                a.setUsername(username);
                a.setPassword(password);
               return a;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

  
    
}
	
	