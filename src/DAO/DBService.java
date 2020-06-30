package DAO;

import java.sql.SQLException;

import DTO.Member;
import DTO.TableRowDataModel;
import javafx.collections.ObservableList;

public interface DBService {
	public void insert(Member m) throws SQLException;
	public boolean select(String id, String pw);
	public boolean selectMId(String id);
	public ObservableList<TableRowDataModel> getReservation(String id);
	public boolean deleteRsv(int res_no);
}
