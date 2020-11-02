package uploadData;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.*;

@ManagedBean
@SessionScoped
public class Cart {
	
	int a,price;
	String name,photo,itemCo;
	
	
	public String inCart() throws SQLException {
		FacesContext context=FacesContext.getCurrentInstance();
		a=Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("cat"));
		
		Connection con=new DatabaseCon().getConnection();
		PreparedStatement pst=con.prepareStatement("select * from walex where id="+a);
		ResultSet rs=pst.executeQuery();
	
		while(rs.next()){
			name=rs.getString(5);
			price=rs.getInt(3);
			itemCo=rs.getString(2);
		}
		
		String baba=new StreamingCont().http();
		new RetrieveConnection().insert(a,price,name,baba,itemCo);
		new RetrieveConnection().count(baba);
		new RetrieveConnection().totalAmount(baba);
		
		return null;
	}

}
