package Main;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import DAO.DataBaseService;
import DAO.DatabaseServiceImpl;
import Membership.MembershipService;
import Membership.MembershipServiceImpl;
import Service.CommonService;
import Service.CommonServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class MembershipController extends Controller implements Initializable{
	private Parent root;
	private CommonService comSrv;
	private MembershipService membershipServ;
	
	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		comSrv = new CommonServiceImpl();
		membershipServ = new MembershipServiceImpl();
	}
	
	public void CancelProc(ActionEvent event) {
		if(((CommonServiceImpl) comSrv).cancelMsg("페이지에서 나갈 경우 입력된 데이터가 사라집니다 나가시겠습니까?")) {
			comSrv.WindowClose(event);
		}else {
			return;
		}		
	}
	
	private boolean isCheck(Map<String,TextField> txtFldMap, String[] txtFldArr, String[] list) {
		if(comSrv.isEmpty(txtFldMap, txtFldArr, list)) {
			return false;
		}
		
		String pw = txtFldMap.get(txtFldArr[2]).getText();
		String pwOk = txtFldMap.get(txtFldArr[3]).getText();
		
		if(!membershipServ.comparePW(pw, pwOk)) {
			comSrv.errorMsg("패스워드가 일치하지 않습니다");
			txtFldMap.get(txtFldArr[3]).requestFocus();
			return false;
		}

		return true;
	}
	
	public void MembershipProc() throws SQLException {
		String []txtFldArr = {"#txtName","#txtID","#txtPw","#txtPwOk"};
		String []list = {"이름","아이디","패스워드","패스워드 확인"};
		Map<String, TextField> txtFldMap = comSrv.getTextFieldInfo(root, txtFldArr);
		
		if(!isCheck(txtFldMap, txtFldArr, list)) {
			return;
		}
		if (comSrv.isEmpty(txtFldMap, txtFldArr, list)) {
			return;
		}
		String pw = txtFldMap.get(txtFldArr[2]).getText();
		String pwOk= txtFldMap.get(txtFldArr[3]).getText();
		
		if(!membershipServ.comparePW(pw,pwOk)) {
			return;
		}
		
		Member member = new Member();
		
		member.setName(txtFldMap.get(txtFldArr[0]).getText());
		member.setId(txtFldMap.get(txtFldArr[1]).getText());
		member.setPw(txtFldMap.get(txtFldArr[2]).getText());
		member.setAge(membershipServ.getComboBoxString(root));
		
		DataBaseService db = new DatabaseServiceImpl();
		db.insert(member);
		comSrv.errorMsg("메시지", "완료", "회원가입이 완료되었습니다");
		Stage s = (Stage)root.getScene().getWindow();
		s.close();
	}		
}













