package Service;

import java.io.IOException;
import java.util.Map;

import DAO.DBService;
import DAO.DBServiceImpl;
import Main.JoinController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JoinServiceImpl implements JoinService{
	
	DBService db=new DBServiceImpl();
	CommonService comSrv=new CommonServiceImpl();
	
	@Override
	public void joinForm() {
		Stage joinStage=new Stage();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../FXML/join.fxml"));
		Parent root=null;
		try {
			root=loader.load();
		}catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene=new Scene(root);
		JoinController joinCtrl=new JoinController();
		
		joinCtrl.setJoinParent(root);
		joinCtrl.setJoinForm(root);
		setting(root);
		
		joinStage.setTitle("회원가입");
		joinStage.setScene(scene);
		joinStage.show();
	}

	public void setting(Parent root) {
		Button join=(Button)root.lookup("#btnJoin");
		Button id=(Button)root.lookup("#btnId");
		join.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 16));
		id.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕.ttf"), 14));
	}
	
	//아이디 중복확인
	@Override
	public boolean compareID(Parent joinForm) {
		TextField txtId=(TextField)joinForm.lookup("#txtId");
		if(txtId.getText().equals("")) {
			comSrv.errorMsg("아이디를 입력해주세요");
			return false;
		}else if(db.selectMId(txtId.getText())) {
			comSrv.errorMsg("이미 사용중인 아이디 입니다");
			txtId.requestFocus();
			return false;
		}
		comSrv.errorMsg("사용가능한 아이디 입니다");
		return true;
	}
	
	//비밀번호/비밀번호재확인 일치확인
	@Override
	public boolean comparePW(Map<String, TextField> txtFldMap, String txtFldArr[]) {
		String pw=txtFldMap.get(txtFldArr[1]).getText();
		String cpw=txtFldMap.get(txtFldArr[2]).getText();
		if(!pw.contentEquals(cpw)) {
			comSrv.errorMsg("비밀번호가 일치하지 않습니다");
			txtFldMap.get(txtFldArr[1]).requestFocus();
			txtFldMap.get(txtFldArr[2]).clear();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean lengthCheck(Parent root) {
		PasswordField pw = (PasswordField) root.lookup("#txtPw");
		if (pw.getLength() < 8) {
			comSrv.errorMsg("알림", "비밀번호", "비밀번호는 8자 이상이어야 합니다");
			pw.requestFocus();
			return false;
		} else {
			return true;
		}
	}

}
