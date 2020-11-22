package Service;

import java.io.IOException;

import DAO.DBService;
import DAO.DBServiceImpl;
import DTO.CommonData;
import DTO.TableRowDataModel;
import Main.MyRsvController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MyRsvServiceImpl implements MyRsvService {
	
	DBService db = new DBServiceImpl();
	CommonService comSrv = new CommonServiceImpl();
	private CommonData cmd=new CommonData();
	
	@Override
	public void myRsvForm() {
		Stage myRsvStage=new Stage();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../FXML/myrsv.fxml"));
		Parent root=null;
		try {
			root=loader.load();
		}catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene=new Scene(root);
		setting(root);
		
		myRsvStage.setTitle("나의 예약");
		myRsvStage.setScene(scene);
		myRsvStage.show();
	}
	
	public void setting(Parent root) {
		Label lblMyRsv=(Label)root.lookup("#lblMyRsv");
		lblMyRsv.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"),26));
	}
	
	//예약내역
	@Override
	public ObservableList<TableRowDataModel> listMyReservation(String res_id) {
		ObservableList<TableRowDataModel> list = db.getReservation(res_id);
		if(list == null) {
			return null;
		}	
		return list;
	}
	
	//예약변경
	@Override
	public boolean changeRsv(int res_no) {
		if(res_no==0) {
			comSrv.errorMsg("변경할 예약을 선택해주세요");
			return false;
		}
		if(comSrv.selectErrMsg("예약 변경은 날짜/시간만 변경 가능합니다.\n변경하시겠습니까?")) {
			cmd.setRes_no(res_no);
			cmd.setExTitle(db.selectRsvT(res_no));
			RsvService rsvSrv=new RsvServiceImpl();
			rsvSrv.rsvForm();
			return true;
		}
		return false;
	}
	
	//예약취소
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
}
