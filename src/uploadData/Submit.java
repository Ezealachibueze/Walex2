package uploadData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class Submit {

	private String fullname,email,address,city,origin,zipCode,phoneNumber;


     RetrieveConnection  request =	new RetrieveConnection();
	
	
	
	
	public Submit() {

	}
	
	public String calender() {
		String cd=null;
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
		LocalDateTime now=LocalDateTime.now();
		cd=dtf.format(now);
		System.out.println(dtf.format(now));
		return cd;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String sumitForm() {
		
		if(getFullname().trim().length()>0 || getEmail().trim().length()>0 ||getAddress().trim().length()>0 
	  ||getPhoneNumber().trim().length()>0 || getCity().trim().length()>0 || getOrigin().trim().length()>0) {
			
		FacesContext cont=FacesContext.getCurrentInstance();
		int a =Integer.parseInt(cont.getExternalContext().getRequestParameterMap().get("submit"));
		
		FacesContext conti=FacesContext.getCurrentInstance();
		int bb =Integer.parseInt(conti.getExternalContext().getRequestParameterMap().get("ttt"));
		System.out.println("total param"+bb);
		
		
		FacesContext contex=FacesContext.getCurrentInstance();
		int ite =Integer.parseInt(contex.getExternalContext().getRequestParameterMap().get("item"));
		System.out.println("total item"+ite);

		FacesContext name=FacesContext.getCurrentInstance();
		String tt =name.getExternalContext().getRequestParameterMap().get("name");
		System.out.println("total item"+tt);
		
		String baba=new StreamingCont().http();
		
		String ded=new Submit().calender();
		System.out.println("Date"+ded);
	
		
	request.submitAll(getFullname(),getEmail(),getAddress(),getPhoneNumber(),
				getCity(),getOrigin(),tt,baba,ite,bb,a,ded);
	
	notify(true,baba,1);
	http_invalidate();
	
		}else
			System.out.println("Error  !");
	 
		return null;
	}
	
	public String submitCartAll() {
		
		if(getFullname().trim().length()>0 || getEmail().trim().length()>0 
				||getAddress().trim().length()>0 ||getPhoneNumber().trim().length()>0
				|| getCity().trim().length()>0 || getOrigin().trim().length()>0) {
		String ded=new Submit().calender();
		String v= new 	StreamingCont().http();
		System.out.println(v);
		FacesContext conti=FacesContext.getCurrentInstance();
		int bb =Integer.valueOf(conti.getExternalContext().getRequestParameterMap().get("ttt"));
		request.check_cart_for_session_id(v,getFullname(),getEmail(),getAddress(),getPhoneNumber(), 
				getCity(),getOrigin(),bb, ded);
		notify(true,v,1);
		http_invalidate();
		
	
	}
		else
		System.out.println("Error !");
	
	return  null;
}
	
	public void http_invalidate() {
		
		FacesContext   fact=FacesContext.getCurrentInstance();
		HttpSession session= (HttpSession) fact.getExternalContext().getSession(false);
		session.invalidate();
	
	}
	
	public void notify(boolean status,String userSession,int updter) {
		String mainStatus=String.valueOf(status);
		new RetrieveConnection().notification(mainStatus,userSession,updter);
		
	}
	
}
