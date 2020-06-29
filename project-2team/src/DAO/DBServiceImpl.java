package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.Member;

public class DBServiceImpl implements DBService {
	
	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	final static String user = "system";
	final static String pass = "oracle";
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Member m) throws SQLException {
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "insert into member values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, m.getId());
			ps.setString(2, m.getPw());
			ps.setString(3, m.getName());
			ps.setString(4, m.getTel());
			ps.setString(5, m.getEmail());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean select(String id, String pw) {
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "select * from member where id=? and pw=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs = ps.executeQuery();		
			if(rs.next() == false) {
				return false;
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean selectMId(String id) {
		try {
			con=DriverManager.getConnection(url,user,pass);
			String sql="select * from member where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			rs.close();
			ps.close();
		}catch (Exception e) {
				e.printStackTrace();
		}
		return false;
	}

}
