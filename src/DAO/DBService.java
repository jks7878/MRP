package DAO;

import java.sql.SQLException;
import java.util.List;

import DTO.CommonData;
import DTO.Member;
import DTO.Reservation;
import DTO.TableRowDataModel;
import javafx.collections.ObservableList;

public interface DBService {
	public void insert(Member m) throws SQLException;
	public void insertRsv(Reservation r) throws SQLException;
	public boolean select(String id, String pw);
	public boolean selectMId(String id);
	public String[] selectLId(String id);
	public String selectRsvT(int res_no);
	public int[] timeCheck(String exTitle, String date);
	public ObservableList<TableRowDataModel> getReservation(String id);
	public boolean updateRsv(int res_no, String[] update);
	public boolean deleteRsv(int res_no);
}
