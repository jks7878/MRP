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


public class JoinController implements Initializable{
	
	private Parent joinForm;
	private CommonService comSrv;
	private JoinService joinSrv;
	
	static Parent joinParent; //회원가입폼
	static String joinId; //회원가입한 아이디
	
	boolean b;

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		comSrv = new CommonServiceImpl();
		joinSrv = new JoinServiceImpl();
	}
	
	public static Parent getJoinParent() {
		return joinParent;
	}

	public static void setJoinParent(Parent joinParent) {
		JoinController.joinParent = joinParent;
	}

	public static String getJoinId() {
		return joinId;
	}

	public static void setJoinId(String joinId) {
		JoinController.joinId = joinId;
	}

	public Parent getJoinForm() {
		return joinForm;
	}

	public void setJoinForm(Parent joinForm) {
		this.joinForm = joinForm;
	}

	//취소버튼
	public void CancelProc(ActionEvent e) {
		if(((CommonServiceImpl) comSrv).selectErrMsg("페이지에서 나갈 경우 입력된 데이터가 사라집니다 나가시겠습니까?")) {
			comSrv.closeWindow(e);
		}else {
			return;
		}		
	}
	
	//아이디 중복확인 버튼
	public void compareID(ActionEvent e) {
		b=joinSrv.compareID(getJoinParent());
	}
	
	//입력누락 확인
	private boolean isCheck(Map<String,TextField> txtFldMap, String[] txtFldArr) {
		if(comSrv.isEmpty(txtFldMap, txtFldArr)) {
			return false;
		}
		if(!b) {
			comSrv.errorMsg("아이디 중복확인을 해주세요");
			return false;
		}
		if(!joinSrv.lengthCheck(getJoinParent())){
	         return false;
	    }
		if(!joinSrv.comparePW(txtFldMap,txtFldArr)) {
			return false;
		}
		return true;
	}
	
	//회원가입버튼
	public void joinProc(ActionEvent e) throws SQLException {
		String []txtFldArr = {"#txtId","#txtPw","#txtCpw","#txtName","#txtTel","#txtEmail"};
		Map<String, TextField> txtFldMap = comSrv.getTextFieldInfo(getJoinParent(), txtFldArr);
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
  		
  		//회원가입 성공 시 로그인폼에 아이디 입력
		LoginController loginCtrl=new LoginController();
		Parent loginForm=loginCtrl.getLoginParent();
		TextField txtId=(TextField)loginForm.lookup("#txtId");
		TextField txtPw=(TextField)loginForm.lookup("#txtPw");
		txtId.setText(txtFldMap.get(txtFldArr[0]).getText());
		txtPw.requestFocus();
		
		comSrv.closeWindow(e);
	}
	
}













