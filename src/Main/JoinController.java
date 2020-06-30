package Main;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import DAO.DBService;
import DAO.DBServiceImpl;
import DTO.Member;
import Service.CommonService;
import Service.CommonServiceImpl;
import Service.JoinService;
import Service.JoinServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class JoinController extends Controller implements Initializable{
	
	private Parent root;
	private CommonService comSrv;
	private JoinService joinSrv;
	
	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		comSrv = new CommonServiceImpl();
		joinSrv = new JoinServiceImpl();
	}
	
	public void CancelProc(ActionEvent e) {
		if(((CommonServiceImpl) comSrv).selectErrMsg("페이지에서 나갈 경우 입력된 데이터가 사라집니다 나가시겠습니까?")) {
			comSrv.closeWindow(e);
		}else {
			return;
		}		
	}
	
	private boolean isCheck(Map<String,TextField> txtFldMap, String[] txtFldArr) {
		if(comSrv.isEmpty(txtFldMap, txtFldArr)) {
			return false;
		}
		if(!joinSrv.compareID(root)) {
			return false;
		}
		if(!joinSrv.comparePW(txtFldMap,txtFldArr)) {
			return false;
		}
		return true;
	}
	
	public void joinProc() throws SQLException {
		String []txtFldArr = {"#txtId","#txtPw","#txtCpw","#txtName","#txtTel","#txtEmail"};
		Map<String, TextField> txtFldMap = comSrv.getTextFieldInfo(root, txtFldArr);
		if(!isCheck(txtFldMap, txtFldArr)) {
			return;
		}
		
		Member m = new Member();
		m.setId(txtFldMap.get(txtFldArr[0]).getText());
		m.setPw(txtFldMap.get(txtFldArr[1]).getText());
		m.setName(txtFldMap.get(txtFldArr[3]).getText());
		m.setTel(txtFldMap.get(txtFldArr[4]).getText());
		m.setEmail(txtFldMap.get(txtFldArr[5]).getText());
		
		DBService db = new DBServiceImpl();
		db.insert(m);
		comSrv.errorMsg("메시지", "완료", "회원가입이 완료되었습니다");
		Stage s = (Stage)root.getScene().getWindow();
		s.close();
	}
	
}













