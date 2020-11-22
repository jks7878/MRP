package Service;

import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.control.TextField;

public interface JoinService {
	public void joinForm();
	public boolean compareID(Parent joinForm);
	public boolean comparePW(Map<String, TextField> txtFldMap, String txtFldArr[]);
	public boolean lengthCheck(Parent root);
}
