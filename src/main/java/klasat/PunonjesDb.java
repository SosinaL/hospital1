package klasat;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;
import java.time.LocalDate;
public class PunonjesDb {
	 //funx i cili kthen listen me punonjes
	 public static ArrayList<Punonjes> punonjesList() {
	        try (Connection con = Database.getConnection()) {
	            Statement stmt=con.createStatement();  
	            ResultSet rs=stmt.executeQuery("select * from punonjes"); 
	            ArrayList<Punonjes> punonjes = new ArrayList<Punonjes>();
	            while(rs.next()) {
	                Punonjes a = new Punonjes();
	                
	                a.setEmer(rs.getString("emer"));
	                a.setMbiemer(rs.getString("mbiemer"));
	                a.setPozicion(rs.getString("pozicion"));
	                a.setPaga_ore(rs.getInt("paga_ore"));
	                a.setDate(rs.getDate("date_fillimi"));
	                a.setPaga(rs.getInt("paga"));
	              
	                punonjes.add(a);
	            }
	           
	            return punonjes;
	        } catch (Exception ex) {
	            System.out.println("PunonjesDb> punonjesList() : " + ex.getMessage());
	            return null;
	        }
	    }
	
    // fshirja e nje punonjesi\
    public static void delete(int punonjes_id){
        try{
        	Connection conn = Database.getConnection();  
            PreparedStatement stmt = conn.prepareStatement("delete from punonjes where punonjes_id =  and date_fillimi "+punonjes_id);  
            stmt.executeUpdate();  
            System.out.println("Punonjes deleted successfully");
        }catch(Exception e){
        	System.out.println("PunonjesDb->delete() : " + e.getMessage());
        }
    }
    
    // Used to save actor record
    public static void save(Punonjes a){
    
        int result = 0;
        try{
        	Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("insert into punonjes( emer,mbiemer,pozicion,paga_ore,data_fillimi) values(?,?,?,?,?)");
            
            stmt.setString(1, a.getEmer());
            stmt.setString(2, a.getMbiemer());
            stmt.setString(3, a.getPozicion());
            stmt.setInt(4, a.getPaga_ore());
            stmt.setDate(5,(Date) a.getDate());
            
            
            
            result = stmt.executeUpdate();
            String message;
			//kontrolli qe i bejme nqs pozicioni qe ka vendosur perdoruesi ka page me te larte se infermiere
            // kam supozuar qe paga max per ore qe ka sanitari nuk duhet te jete me e madhe se 456 leke
            if((a.getPozicion()=="sanitar") &&(a.getPaga_ore()>456))
            	
            
            	 if((a.getPozicion()=="infermier") &&(a.getPaga_ore()>700))
            		 
            message="Ju lutem vendosni te dhenat e sakta";
            
      
            System.out.println("Punonjesi u ruajt me sukses!");
            conn.close();
            

        }catch(Exception e){
        	System.out.println("PunonjesDb->save() : " + e.getMessage());
        }
    }
    
    // Used to fetch record to update
    public static Punonjes edit(int punonjes_id){
        Punonjes a= null;
        try{
        	Connection conn = Database.getConnection();
            Statement stmt=conn.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from punonjes where punonjes_id = "+(punonjes_id));
            rs.next();
            a = new Punonjes();
            a.setPunonjes_id(rs.getInt("punonjes_id"));
            a.setEmer(rs.getString("emer"));
            a.setMbiemer(rs.getString("mbiemer"));
            a.setPozicion(rs.getString("data_fillimi"));
           
            a.setPaga_ore(rs.getInt("paga_ore"));
            a.setPaga(rs.getInt("paga"));
            System.out.println("Punonjes data updated!");
            conn.close();
            return a;
        }catch(Exception e){
        	System.out.println("PunonjesDb->edit() : " + e.getMessage());
        	return null;
        }       
    }

    public static boolean editPunonjes(Punonjes a, int punonjes_id) {
        try (Connection con = Database.getConnection()) {
            PreparedStatement ps = con.prepareStatement("update punonjes set emer=?, mbiemer=?, pozicion=?,paga_ore=?,paga=?,data_fillimi=? where punonjes_id=?");
            ps.setString(1, a.getEmer());
            ps.setString(2, a.getMbiemer());
            
            ps.setString(3, a.getPozicion());
            ps.setDate(4, (Date) a.getDate());
            
            ps.setInt(5,a.getPaga_ore() );
            ps.setInt(6,a.getPaga() );
           
            System.out.println("Punonjesi u editua!");
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception ex) {
            System.out.println("PunonjesDb->editPunonjes() : " + ex.getMessage());
            return false;
        }
    }
    public static Punonjes punonjesInfo() {
		 try (Connection con = Database.getConnection()) {
	            PreparedStatement stmt=con.prepareStatement("select * from punonjes where emer=?");  
	            stmt.setString(1, Util.getEmer());
	            ResultSet rs;
	            rs=stmt.executeQuery();
	            rs.next();
	             rs.getString("emer");
	             rs.getString("mbiemer");
	             rs.getString("pozicion");
	             rs.getString("date");
	           rs.getString("paga_ore");
	             rs.getString("paga");
	            stmt.executeUpdate();
	        } catch (Exception ex) {
	            System.out.println("Punonjes-> punonjesInfo() : " + ex.getMessage());
	           
	        }
		return null;
	 }
    
  

   
	
		
		
	    

}
