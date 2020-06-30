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
import javafx.stage.Stage;

public class CommonServiceImpl implements CommonService{

	@Override
	public void WindowClose(ActionEvent event) {
		// TODO Auto-generated method stub
		Parent root = (Parent)event.getSource();
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
	}

	@Override
	public Parent showWindow(Stage s, String formPath) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		Map<String, TextField> txtFldMap = new HashMap<String, TextField>();
		for(String txtFldId : txtFldArr) {
			TextField txtFld = (TextField)root.lookup(txtFldId);
			txtFldMap.put(txtFldId, txtFld);
		}
		return txtFldMap;
	}

	@Override
	public boolean isEmpty(Map<String, TextField> txtFldMap, String[] txtFldArr, String[] list) {
		// TODO Auto-generated method stub
		CommonService comSrv = new CommonServiceImpl();
		int cnt = 0;
		
		for(String txtFldId : txtFldArr) {
			TextField txtFld = txtFldMap.get(txtFldId);
			if(txtFld.getText().isEmpty()) {				
				txtFld.requestFocus();
				comSrv.errorMsg(list[cnt]+"이(가) 비었습니다.");
				return true;
			}
			cnt++;
		}
		return false;
	}

	@Override
	public void errorMsg(String title, String header, String content) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);		
		alert.showAndWait();
	}

	@Override
	public void errorMsg(String header, String content) {
		// TODO Auto-generated method stub
		errorMsg("error", header, content);
	}

	@Override
	public void errorMsg(String content) {
		// TODO Auto-generated method stub
		errorMsg("error","error header",content);
	}

	public boolean cancelMsg(String content) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("메시지");
		alert.setHeaderText("알림");
		alert.setContentText(content);		

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    // ... user chose OK
			return true;
		} else {
			return false;
		    // ... user chose CANCEL or closed the dialog
		}

		//출처: https:studymake.tistory.com/583 [스터디메이크]
	}
	
}
