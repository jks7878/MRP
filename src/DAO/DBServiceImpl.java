package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.Member;
import DTO.Reservation;
import DTO.TableRowDataModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
			con = DriverManager.getConnection(url,user,pass);
			String sql="select * from member where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
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

	@Override
	public ObservableList<TableRowDataModel> getReservation(String res_id) {
		ObservableList<TableRowDataModel> list = FXCollections.observableArrayList();		
		try {
			con = DriverManager.getConnection(url,user,pass);
			String sql = "select * from reservation where res_id = ? order by res_date";
			ps = con.prepareStatement(sql);
			ps.setString(1, res_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				TableRowDataModel rsv = new TableRowDataModel
				(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
				rs.getString(6),rs.getInt(7),rs.getString(8),rs.getInt(9));
//				System.out.println("번호 : " + rsv.getRes_no());
//				System.out.println("아이디 : " + rsv.getRes_id());
//				System.out.println("이름 : " + rsv.getName());
//				System.out.println("전시회명 : " + rsv.getExhibit());
				list.add(rsv);
			}
			rs.close();
			ps.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteRsv(int res_no) {
		try {
			con = DriverManager.getConnection(url,user,pass);
			String sql = "delete from reservation where res_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, res_no);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
