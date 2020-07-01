package Service;

import java.util.Map;

import DAO.DBService;
import DAO.DBServiceImpl;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class JoinServiceImpl implements JoinService{

	@Override
	public void setFont(Parent root) {
		Button join=(Button)root.lookup("#btnJoin");
		Button id=(Button)root.lookup("#btnId");
		join.setFont(Font.loadFont(getClass().getResourceAsStream("../font/AppleSDGothicNeoR.ttf"), 16));
		id.setFont(Font.loadFont(getClass().getResourceAsStream("../font/AppleSDGothicNeoR.ttf"), 16));
	}
	
	@Override
	public boolean compareID(Parent joinForm) {
		TextField txtId=(TextField)joinForm.lookup("#txtId");
		DBService db=new DBServiceImpl();
		CommonService comSrv=new CommonServiceImpl();
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
	
	@Override
	public boolean comparePW(Map<String, TextField> txtFldMap, String txtFldArr[]) {
		String pw=txtFldMap.get(txtFldArr[1]).getText();
		String cpw=txtFldMap.get(txtFldArr[2]).getText();
		if(!pw.contentEquals(cpw)) {
			CommonService comSrv=new CommonServiceImpl();
			comSrv.errorMsg("비밀번호가 일치하지 않습니다");
			txtFldMap.get(txtFldArr[1]).requestFocus();
			txtFldMap.get(txtFldArr[2]).clear();
			return false;
		}
		return true;
	}

}
