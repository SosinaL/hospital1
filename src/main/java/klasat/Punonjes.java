package klasat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.xml.crypto.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;



@ManagedBean
@RequestScoped 
public class Punonjes {
	
	ArrayList punonjesList;
	
 String emer, mbiemer, pozicion, message;
 Date data_fillimi,dateSelected; 
	private int punonjes_id,paga_ore,paga,ore_pune;
	
	 
	public String getEmer() {
		return emer;
	}
	public void setEmer(String emer) {
		this.emer = emer;
	}
	public int getPunonjes_id() {
		return punonjes_id;
	}
	public void setPunonjes_id(int punonjes_id) {
		this.punonjes_id = punonjes_id;
	}
	public void setMbiemer(String mbiemer) {
		this.mbiemer= mbiemer;
	}
	public String getMbiemer() {
		return mbiemer;
	}
	public void setPozicion(String pozicion) {
		this.pozicion =pozicion;
	}
	public String getPozicion() {
		return pozicion;
	}
	public void setDate(Date date) {
		this.data_fillimi =date;
	}
	public Date getDate() {
		return  data_fillimi;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message)
	{
		this.message=message;
	}
	public void setPaga_ore(int paga_ore) {
		this.paga_ore = paga_ore;
	}
	public int getPaga_ore() {
		return paga_ore;
	}
	public void setPaga(int paga) {
		this.paga = paga;
	}//paga e punonjesit do te logaritet si shumezim i diteve te punes * pagen per ore
	public int getPaga() {
		return day*paga_ore;
	}
	public void setOre_pune(int ore_pune) {
		this.ore_pune = ore_pune;
	}
	public int getOre_pune() {
		return ore_pune;
	}
	public Date getdateSelected() {
        return dateSelected;
    }
 
    public void setdateSelected(Date dateSelected) {
        this.dateSelected = dateSelected;
    }
   
	
    
// variabli  day ruan diten e cila i perket dates qe ka vendosur perdoruesi
   // LocalDate currentDate =LocalDate.parse(dateSelected);
    int day = data_fillimi.getMonth();
	public ArrayList <?>punonjesList()
	{
		punonjesList = PunonjesDb.punonjesList();
		
		return punonjesList;
	}
	
	//fshirja  e punonjesve
    public void delete(int punonjes_id){
    	
        PunonjesDb.delete(punonjes_id);
    }
    
    
    // shtim punonjesi i ri
    public String savePunonjes() {
    	
    	PunonjesDb.save(this);
    	message = "Success! Punonjes i ri u shtua";
    	return "sistem_menaxhimi";
    }
    
    //afishimi i te dhenave per 1 punonjes

	public String info() {
    	Punonjes u = PunonjesDb.punonjesInfo();

    	this.emer = u.getEmer();
		this.mbiemer = u.getMbiemer();
		this.pozicion = u.getPozicion();
		this.data_fillimi = u.getDate();
		this.paga_ore = u.getPaga_ore();
		this.paga= u.getPaga();

		return "afisho_punonjes.xhtml";
    }
      
    
    
	 
    
    // editimi i punonjesit
    
    
    public String editPunonjes(int punonjes_id) {
    	boolean done = PunonjesDb.editPunonjes(this, punonjes_id);
        if ( done ) {
        	   message = "Punonjesi u editu!";
        	   return "/punonjes/sistem_menaxhimi?faces-redirect=true";
        }else {
            message = "Editimi nuk mund te behet, provo serish";
            return "/punonjes/edit_punonjes?faces-redirect=true";
        }
    }
	public void checkPunonjes()
	{
		
	
    
  	if(day==30)
  	{
  		PunonjesDb.delete(punonjes_id);
  	}
  	
  	if(day!=30)
  	{
  		message="Nuk mund ta fshini punonjesin";
  		
  	}
	}

} 