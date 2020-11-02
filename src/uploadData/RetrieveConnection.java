 package uploadData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

public class RetrieveConnection {
	
private DatabaseCon data;
DatabaseCon db=new DatabaseCon();

	public byte[] newImage(int id) {
		Connection con=null;
		byte[] bb=null;
		try {
			 con=new DatabaseCon().getConnection();
			PreparedStatement pst=con.prepareStatement("select * from walex where id="+id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				bb=rs.getBytes(4);
			}	
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bb;
		
	}
	
	
	public List<StreamingCont>  list() {
	
		
		List<StreamingCont> lis=new ArrayList<StreamingCont>();
		
		Connection con=null;
			
		try {
			 con=db.getConnection();
		
			PreparedStatement pst=con.prepareStatement("select * from walex limit 4");
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
			
			StreamingCont cont=new StreamingCont();
			cont.setId(rs.getString(1));
			cont.setItemComment(rs.getString(2));
			cont.setPrice(rs.getString(3));
			cont.setName(rs.getString(5));
			lis.add( cont);
			}	
		}catch(Exception e) {
			System.out.println(e);
		}
		
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lis;
	}


	
	
	public List<StreamingCont>  readers(String f){
		
		List<StreamingCont> lis=new ArrayList<StreamingCont>();
		
		Connection con=null;
		
		try {
			 con=db.getConnection();
		
			PreparedStatement pst=con.prepareStatement("select * from  "+createTable()+" where userId  like '%"+f+"%'  order by id desc ");
		    ResultSet	ers=pst.executeQuery();
			while (ers.next()) {
			StreamingCont  pack=new StreamingCont();
			pack.setId(ers.getString(1)); //id
			pack.setName(ers.getString(2)); //name
			pack.setPrice(ers.getString(3)); //price
			pack.setUserauto_gen(ers.getString(4)); //userid
			pack.setPhotos(ers.getString(6)); //photo
			pack.setItemComment(ers.getString(7));
			
			lis.add(pack);
			
			}
	}catch (Exception e) {
		System.out.println(e);
	}
	
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			return lis;}
	
	
	public void count (String f){
		Connection con=null;
			
		try {
			 con=db.getConnection();
		
			PreparedStatement pst=con.prepareStatement("select sum(items)  as alias from "+createTable()+" where userId  like '%"+f+"%'");
		    ResultSet ers=pst.executeQuery();
			while (ers.next()) {
			StreamingCont  pack=new StreamingCont();
			pack.setItems(ers.getString("alias")); //items
		
			}
		
	}catch (Exception e) {
		System.out.println(e);
	}
		
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	public void totalAmount (String x){
		Connection con=null;
		
		try {
			 con=db.getConnection();
		
			PreparedStatement pst=con.prepareStatement("select sum(price)  as total from "+createTable()+" where userId  like '%"+x+"%'");
		    ResultSet ers=pst.executeQuery();
			while (ers.next()) {
			StreamingCont  pack=new StreamingCont();
			pack.setTotalAmount(ers.getString("total")); //totalamount 
			}
		
	}catch (Exception e) {
		System.out.println(e);
	}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	
	}
	public String createTable() {
		String c,b;
		b= new StreamingCont().http();
		c="T".concat(b.concat("Z"));
		return c;
	}
	
	
	public void insert(int a, int priceId, String nameId, String baba, String baba2) {
	
		Connection	con=null;
		
		String create_table="create table if not exists "+createTable()+"(id  serial,name varchar(100),price int,userId varchar(100),items int,photo int,itemComm varchar(100));";
		
		try {
		con=new DatabaseCon().getConnection();
		Statement stmt=con.createStatement();
		stmt.execute(create_table);
		System.out.println("table created");
	      PreparedStatement	pst=con.prepareStatement("insert into "+createTable()+" (name,price,UserId,items,photo,itemComm) values(?,?,?,?,?,?)");
	
		pst.setString(1, nameId);
		pst.setInt(2, priceId);
		pst.setString(3, baba);
		pst.setInt(4, 1);
		pst.setInt(5, a);
		pst.setString(6, baba2);
		pst.executeUpdate();
		
	}catch(Exception e) {
	System.out.println(e);
	}
	finally {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		System.out.println(createTable());
		
	}
	

	public List<StreamingCont>  list5() {
		List<StreamingCont> lis=new ArrayList<StreamingCont>();
		
		Connection con=null;
			
		try {
			 con=db.getConnection();
		
			PreparedStatement pst=con.prepareStatement("select * from walex limit 10");
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
			
			StreamingCont cont=new StreamingCont();
		 	cont.setId(rs.getString(1));
		 	cont.setItemComment(rs.getString(2));
        	cont.setPrice(rs.getString(3));
			cont.setName(rs.getString(5));
			lis.add( cont);
			}	
		}catch(Exception e) {
			System.out.println(e);
		}
		
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lis;
	}
	
	
	
	
	public void drop(int sd) {
		Connection con=null;
	try {
		con=db.getConnection();
	   PreparedStatement	pst=con.prepareStatement("delete from "+createTable()+" where photo="+sd);
		pst.execute();
		System.out.println(sd);
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}


	public void submitAll(String fullname, String email, String address, String string2, String city, 
			String origin,String string, String baba, int ite, int bb, int a, String ded) {
	
		Connection con=null;
		try {
			
			con=db.getConnection();
			PreparedStatement pst=con.prepareStatement("insert into walexProduct"
					+ " (Fullname,Email,addresses,PhoneNumber,city,state_of_origin,NameOfClothe,"
					+ "userId,Number_of_items,totalAmount,Pictures,Current_Time_Stamp)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1, fullname);
			pst.setString(2, email);
			pst.setString(3, address);
			pst.setString(4, string2);
			pst.setString(5, city);
			pst.setString(6, origin);
			pst.setString(7, string);
			pst.setString(8, baba);
			pst.setInt(9, ite);
			pst.setInt(10, bb);
			pst.setInt(11,a);
			pst.setString(12, ded);
			pst.executeUpdate();
		
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
	
	
	

	private void Send_in(String id,String bab,String string, String string2,  int string4, int integer,
			String fullname, String email,String  address,String PhoneNumber,String city, String origin, int bv, String dates) {
		Connection con=null;
		try {
			
			con=db.getConnection();
			PreparedStatement pst=con.prepareStatement("insert into walexProduct"
					+ " (Fullname,Email,addresses,PhoneNumber,city,state_of_origin,NameOfClothe,"
					+ "userId,Number_of_items,totalAmount,Pictures,Current_Time_Stamp)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1, fullname);
			pst.setString(2, email);
			pst.setString(3, address);
			pst.setString(4, PhoneNumber);
			pst.setString(5, city);
			pst.setString(6, origin);
			pst.setString(7, string);
			pst.setString(8, bab);
			pst.setInt(9, string4);
			pst.setInt(10, bv);
			pst.setInt(11,integer);
			pst.setString(12, dates);
			
			
			pst.executeUpdate();
			delete(id);
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	public void check_cart_for_session_id(String ded, String fullname, String getEmail,String  Address,
			String PhoneNumber,String City, String Origin,int bv , String dc) {
		Connection con=null;
		try {
			
			con=db.getConnection();
			PreparedStatement pst=con.prepareStatement("select * from  "+createTable()+" where  userId like '%"+ded+"%'");
			ResultSet  rs=pst.executeQuery();
			
			while (rs.next()) {
				
				Send_in(rs.getString(1),ded,rs.getString(2), rs.getString(4),Integer.valueOf(rs.getString(5))
			,Integer.valueOf(rs.getString(6)),  fullname,  getEmail,  Address, PhoneNumber, City,  Origin,bv ,dc);
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		
	}



	
	private void delete(String id) {
		Connection con1=null;
		try {
			
			con1=db.getConnection();
			PreparedStatement pst=con1.prepareStatement("delete from  "+createTable()+" where  id="+id);
			pst.execute();
		
		}catch (Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				con1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	
	

	public byte[] photo(int id) {
		Connection con=null;
		byte[] bb=null;
		try {
			 con=new DatabaseCon().getConnection();
			PreparedStatement pst=con.prepareStatement("select * from walex where id="+id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				bb=rs.getBytes(4);
			}	
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bb;
		
	}


	public void cartDelete(String bb) {
		Connection con1=null;
		try {
			
			con1=db.getConnection();
			PreparedStatement pst=con1.prepareStatement("delete from   "+createTable()+" where  userId like '%"+bb+"%'");
			pst.execute();
		
		}catch (Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				con1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}


	public void notification(String mainStatus, String userSession, int updter) {
		Connection con=null;
		try {
			con=new DatabaseCon().getConnection();
		      PreparedStatement	pst=con.prepareStatement("insert into walex_notification(status1,user_session_id,updater) values(?,?,?)");
		
			pst.setString(1, mainStatus);
			pst.setString(2, userSession);
			pst.setInt(3, updter);
	
			pst.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
	
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}


	public byte[] limit(int women) {
		Connection con=null;
		byte[] bb=null;
		try {
			 con=new DatabaseCon().getConnection();
			PreparedStatement pst=con.prepareStatement("select * from walex where id="+women);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				bb=rs.getBytes(4);
			}	
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return bb;
	}
	}






