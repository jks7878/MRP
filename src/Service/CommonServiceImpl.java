package Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import Main.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CommonServiceImpl implements CommonService{

	@Override
	public void closeWindow(ActionEvent e) {
		Parent root = (Parent)e.getSource();
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
	}

	@Override
	public Parent showWindow(Stage s, String formPath) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
		Parent root = null;
		
		try {
			root = loader.load();
			s.setScene(new Scene(root));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Controller ctrler = loader.getController();		
		ctrler.setRoot(root);
		
		s.show();
		return root;
	}

	@Override
	public Map<String, TextField> getTextFieldInfo(Parent root, String[] txtFldArr) {
		Map<String, TextField> txtFldMap = new HashMap<String, TextField>();
		for(String txtFldId : txtFldArr) {
			TextField txtFld = (TextField)root.lookup(txtFldId);
			txtFldMap.put(txtFldId, txtFld);
		}
		return txtFldMap;
	}
	
	@Override
	public Map<String, Label> getLabelInfo(Parent root, String[] lblFldArr) {
		Map<String, Label> lblFldMap = new HashMap<String, Label>();
		for(String lblFldId : lblFldArr) {
			Label lblFld = (Label)root.lookup(lblFldId);
			lblFldMap.put(lblFldId, lblFld);
		}
		return lblFldMap;
	}

	@Override
	public boolean isEmpty(Map<String, TextField> txtFldMap, String[] txtFldArr) {
		for(String txtFldId : txtFldArr) {
			TextField txtFld = txtFldMap.get(txtFldId);
			if(txtFld.getText().isEmpty()) {				
				if(txtFldId.equals("#txtId")) {
					errorMsg("아이디를 입력해주세요");
				}else if(txtFldId.equals("#txtPw")) {
					errorMsg("비밀번호를 입력해주세요");
				}else if(txtFldId.equals("#txtCpw")) {
					errorMsg("비밀번호 재확인을 입력해주세요");
				}else if(txtFldId.equals("#txtName")) {
					errorMsg("이름을 입력해주세요");
				}else if(txtFldId.equals("#txtTel")) {
					errorMsg("전화번호를 입력해주세요");
				}else if(txtFldId.equals("#txtEmail")) {
					errorMsg("이메일을 입력해주세요");
				}
				txtFld.requestFocus();
				return true;
			}
		}
		return false;
	}

	@Override
	public void errorMsg(String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);		
		alert.showAndWait();
	}

	@Override
	public void errorMsg(String header, String content) {
		errorMsg("error", header, content);
	}

	@Override
	public void errorMsg(String content) {
		errorMsg("error","error header",content);
	}

	public boolean selectErrMsg(String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("메시지");
		alert.setHeaderText("알림");
		alert.setContentText(content);		

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    // ... user chose OK
			return true;
		} else {
			// ... user chose CANCEL or closed the dialog
			return false;
		}
		//출처: https:studymake.tistory.com/583 [스터디메이크]
	}
	
}
