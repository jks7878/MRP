package Service;

import java.util.Map;

import DAO.DBService;
import DAO.DBServiceImpl;
import DTO.TableRowDataModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MyRsvServiceImpl implements MyRsvService {
	DBService db = new DBServiceImpl();
	CommonService comSrv = new CommonServiceImpl();
	
	@Override
	public ObservableList<TableRowDataModel> listMyReservation(String res_id) {
		DBService db = new DBServiceImpl();
		ObservableList<TableRowDataModel> list = db.getReservation(res_id);
		if(list == null) {
			return null;
		}	
		return list;
	}

	@Override
	public void changeRsv(int res_no) {
		System.out.println("예약 번호 : " + res_no);
	}

	@Override
	public boolean cancleRsv(int res_no) {
		if(comSrv.selectErrMsg("예약을 취소할 경우 기록된 예약 정보가 모두 사라집니다\n정말 취소하시겠습니까?")){
			if(db.deleteRsv(res_no)) {
				comSrv.errorMsg("예약이 취소되었습니다");
				return true;
			}		
		}
		return false;
	}

	@Override
	public void openLastRsvForm() {

		
	}	
}
