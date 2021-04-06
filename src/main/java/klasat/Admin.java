package klasat;
import java.io.Serializable;
import java.sql.Connection;
import java.util.*;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import com.sun.faces.util.Util;




@ManagedBean
@RequestScoped 
public class Admin implements Serializable{
    
	ArrayList usersList ;
	Connection connection;
	private String username, password, confirmPassword,message;

	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	
		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		
	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message=message;
	    }

	    
	
	    //logimi i adminit ne menyre te thjeshte duke supozuar qe kemi vetem nje admin te sistemit
	    
	        public String login() {
	         
	            System.out.println("Entered Username is= " + username + ", password is= " + password);
	            if (username.equalsIgnoreCase("sosina") && password.equals("sosina123")) {
	                return "/punonjes/sistem_menaxhimi?faces-redirect=true";
	            } else {
	                return "/all/sistem_menaxhimi?faces-redirect=true";
	            }
	            
	        }
	        
    public String logout() {
    	 FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        return "/admin/login?faces-redirect=true";
    }
   
     
}

