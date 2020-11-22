package Service;

import java.util.Map;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public interface CommonService {
	public void closeWindow(ActionEvent e);
	public Parent showWindow(Stage s,String formPath);
	public void errorMsg(String title, String header, String content);
	public void errorMsg(String header, String content);
	public void errorMsg(String content);
	public Map<String, TextField> getTextFieldInfo(Parent root, String[] txtFldArr);
	public Map<String, Label> getLabelInfo(Parent root, String[] lblFldArr);
	public boolean isEmpty(Map<String, TextField> txtFldMap, String[] txtFldArr);
	public boolean selectErrMsg(String content);
}
