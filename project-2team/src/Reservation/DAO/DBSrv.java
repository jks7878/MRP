package Reservation.DAO;

import java.sql.SQLException;
import java.util.List;

import Reservation.Reservation;

public interface DBSrv {
	
	public void insert(Reservation r) throws SQLException;
	public List<String> selectR(String id);
	
}
