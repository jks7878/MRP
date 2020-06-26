package Reservation.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Reservation.Reservation;

public class DBSrvImpl implements DBSrv {

	final static String DRIVER="oracle.jdbc.driver.OracleDriver";
	final static String URL="jdbc:oracle:thin:@localhost:1521:XE";
	final static String USER="system";
	final static String PASS="oracle";
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	static {
		try {
			Class.forName(DRIVER);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Reservation r) throws SQLException {
		try {
			con=DriverManager.getConnection(URL,USER,PASS);
			String sql="insert into reservation values (?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, r.getRes_id());
			ps.setString(2, r.getName());
			ps.setString(3, r.getExhibit());
			ps.setString(4, r.getDate());
			ps.setString(5, r.getTime());
			ps.setInt(6, r.getTicket());
			ps.setString(7, r.getTicket_sel());
			ps.setInt(8, r.getPrice());
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> selectR(String id) {
		List<String> info=new ArrayList<String>();
		try {
			con=DriverManager.getConnection(URL,USER,PASS);
			String sql="select name,tel,email from member where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			for(int i=1; i<4; i++) {
				String s=rs.getString(i);
				info.add(s);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

}
