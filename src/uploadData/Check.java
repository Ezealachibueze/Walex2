package uploadData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class Check {
	
	
	Map<String,Object>sessionMap=FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	  
	public void directImages(String cc) {
		
	        try {
	        	Connection con=new DatabaseCon().getConnection();
	        	PreparedStatement pst=con.prepareStatement("select * from walex where id="+cc);
	        	ResultSet rs=pst.executeQuery();
	         while(rs.next()) {
	        	StreamingCont stm=new StreamingCont();
	        	stm.setId(rs.getString(1));
	        	stm.setItemComment(rs.getString(2));
	        	stm.setPrice(rs.getString(3));    
	        	stm.setName(rs.getString(5));
	        	 	
	        	sessionMap.put("direct",stm);
	        	
	         }   }catch(Exception e) {
	        	System.out.println(e);
	        }        
	}
	
	public byte[] dir(int ccb) {
		Connection con=null;
	    byte[]arr=null;
	    try {	   
		 con=new DatabaseCon().getConnection();
			PreparedStatement pst=con.prepareStatement("select * from walex where id="+ccb);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				arr=rs.getBytes(4);
			}}catch(Exception e) {
				System.out.println(e);
			}
		return arr;
	}

	
	
}
