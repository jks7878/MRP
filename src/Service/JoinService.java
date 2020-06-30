package Service;

import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.control.TextField;

public interface JoinService {
	public void setFont(Parent root);
	public boolean compareID(Parent joinForm);
	public boolean comparePW(Map<String, TextField> txtFldMap, String txtFldArr[]);
}
