package Service;

import java.util.Arrays;

import DAO.DataBaseService;
import DAO.DatabaseServiceImpl;
import Membership.MembershipService;
import Membership.MembershipServiceImpl;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginServiceImpl implements LoginService{
	
	@Override
	public void LoginProc(Parent root) {
		// TODO Auto-generated method stub
		CommonService comSrv = new CommonServiceImpl();
		TextField idTxt = (TextField)root.lookup("#idTxt");
		TextField pwTxt = (TextField)root.lookup("#pwTxt");

//		DataBaseService db = new DatabaseServiceImpl();
//		boolean login = db.select(idTxt.getText(), pwTxt.getText());		
//		if(login) {
//			comSrv.errorMsg("확인","로그인 되었습니다");
//			System.out.println("ID : " + idTxt.getText());
//			System.out.println("PW : " + pwTxt.getText());
//		}else {
//			comSrv.errorMsg("아이디 혹은 비밀번호가 틀렸습니다");
//		}
		if(idTxt.getText().equals("test") && pwTxt.getText().equals("1234")) {	
			System.out.println("임시 로그인 성공");
			Stage s = (Stage)root.getScene().getWindow();
			s.close();
		}else {
			System.out.println("test " + "1234");
		}				
	}
	
	@Override
	public Parent openMembershipForm() {
		// TODO Auto-generated method stub
		CommonService comSrv = new CommonServiceImpl();
		Stage membershipForm = new Stage();
		Parent form = comSrv.showWindow(membershipForm, "../FXML/membership.fxml");
		String items[] = {"20대 미만", "20대", "30대", "40대", "50대", "60대","70대 이상"};
		MembershipService memberSrv = new MembershipServiceImpl();
		memberSrv.AddComoboBox(form, Arrays.asList(items));
		
		return form;	
	}

}












