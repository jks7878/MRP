package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.CommonData;
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
	CommonData cmd=new CommonData();
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//회원가입
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
	
	//예약저장
	public void insertRsv(Reservation r) throws SQLException {
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "insert into reservation values(res_no_seq.nextval,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, r.getRes_id());
			ps.setString(2, r.getName());
			ps.setString(3, r.getTitle());
			ps.setString(4, r.getRes_date());
			ps.setString(5, r.getRes_time());
			ps.setInt(6, r.getTicket());
			ps.setString(7, r.getTicket_sel());
			ps.setInt(8, r.getPrice());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//로그인 확인
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
	
	//회원가입 시 아이디 중복확인
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
	
	//예매 시 로그인 아이디로 회원정보 불러오기
	@Override
	public String[] selectLId(String id) { 
		String loginedIdinfo[]=new String[3];
		try {
			con = DriverManager.getConnection(url,user,pass);
			String sql="select * from member where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				loginedIdinfo[0]=rs.getString(3);
				loginedIdinfo[1]=rs.getString(4);
				loginedIdinfo[2]=rs.getString(5);
			}
			rs.close();
			ps.close();
		}catch (Exception e) {
				e.printStackTrace();
		}
		return loginedIdinfo;
	}
	
	//예약 변경시 해당 전시타이틀 가져오기
	@Override
	public String selectRsvT(int res_no) { 
		String title=null;
		try {
			con = DriverManager.getConnection(url,user,pass);
			String sql="select title from reservation where res_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, res_no);
			rs = ps.executeQuery();
			rs.next();
			title=rs.getString(1);
			if(rs.getString(1).equals("My Dear 피노키오 전")) {
				title="pino";
			}else if(rs.getString(1).equals("퀘이 형제 : 도미토리움으로의 초대전")) {
				title="brother";
			}else if(rs.getString(1).equals("퓰리처상 사진전")) {
				title="photo";
			}
			rs.close();
			ps.close();
		}catch (Exception e) {
				e.printStackTrace();
		}
		return title;
	}
	
	//전시날짜 매진시간체크
	@Override
	public int[] timeCheck(String exTitle, String date) {
		int[] tNum=new int[10];
		String[] time= {"10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00"};
		try {
			con = DriverManager.getConnection(url,user,pass);
			for(int i=0;i<time.length;i++) {
				String sql="select sum(ticket) from reservation where title = ? and res_date = ? and res_time = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, exTitle);
				ps.setString(2, date);
				ps.setString(3, time[i]);
				rs = ps.executeQuery();
				rs.next();
				tNum[i]=rs.getInt(1);
			}
			rs.close();
			ps.close();
		}catch (Exception e) {
				e.printStackTrace();
		}
		return tNum;
	}
	
	//예약내역 가져오기
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
	
	//예약변경
	@Override
	public boolean updateRsv(int res_no, String[] update) {
		try {
			con = DriverManager.getConnection(url,user,pass);
			String sql = "update reservation set res_date = ?, res_time = ? where res_no = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, update[0]);
			ps.setString(2, update[1]);
			ps.setInt(3, res_no);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//예약취소
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
			e.printStackTrace();
		}
		return false;
	}
	
}
