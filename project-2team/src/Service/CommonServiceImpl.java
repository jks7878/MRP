package Service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
		Parent root = (Parent)event.getSource();
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
	}

	@Override
	public Parent showWindow(Stage s, String formPath, String imgPath) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
		Parent root = null;
		
		try {
			root = loader.load();
			s.setScene(new Scene(root));
			s.getScene().getStylesheets().add(getClass().getResource(imgPath).toExternalForm());
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
	public boolean isEmpty(Map<String, TextField> txtFldMap, String[] txtFldArr, String[] list) {
		CommonService comSrv = new CommonServiceImpl();
		int cnt = 0;
		
		for(String txtFldId : txtFldArr) {
			TextField txtFld = txtFldMap.get(txtFldId);
			if(txtFld.getText().isEmpty()) {				
				txtFld.requestFocus();
				comSrv.errorMsg(list[cnt]+"�씠(媛�) 鍮꾩뿀�뒿�땲�떎.");
				return true;
			}
			cnt++;
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

	public boolean cancelMsg(String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("硫붿떆吏�");
		alert.setHeaderText("�븣由�");
		alert.setContentText(content);		

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    // ... user chose OK
			return true;
		} else {
			return false;
		    // ... user chose CANCEL or closed the dialog
		}

		//異쒖쿂: https:studymake.tistory.com/583 [�뒪�꽣�뵒硫붿씠�겕]
	}

	@Override
	public int[] calender() {
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		int day=cal.get(Calendar.DAY_OF_MONTH);
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		int min=cal.get(Calendar.MINUTE);
		int callist[]= {year,month,day,hour,min};
		return callist;
	}
	
}
