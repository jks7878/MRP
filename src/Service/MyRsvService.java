package Service;

import DTO.TableRowDataModel;
import javafx.collections.ObservableList;

public interface MyRsvService {
	public ObservableList<TableRowDataModel> listMyReservation(String res_id);
	public void changeRsv(int res_no);
	public boolean cancleRsv(int res_no);
	public void openLastRsvForm();
}
