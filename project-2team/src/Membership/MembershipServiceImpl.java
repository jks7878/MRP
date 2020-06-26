package Membership;

import java.util.List;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

public class MembershipServiceImpl implements MembershipService{
	@Override
	public boolean comparePW(String pw, String pwOk) {
		// TODO Auto-generated method stub
		return pw.contentEquals(pwOk);
	}

	@Override
	public void AddComoboBox(Parent form, List<String> items) {
		// TODO Auto-generated method stub
		ComboBox<String> cmbAge = (ComboBox<String>) form.lookup("#cmbAge");
		
		if(cmbAge != null) {
			for(String item : items) {
				cmbAge.getItems().add(item);
			}
		}
	}

	@Override
	public boolean isComboBox(Parent membershipForm) {
		// TODO Auto-generated method stub
		ComboBox<String> cmbAge = (ComboBox<String>)membershipForm.lookup("#cmbAge");
		if(cmbAge == null) {
			return false;
		}else if(cmbAge.getValue() == null) {
			return false;
		}
		return true;
	}

	@Override
	public String getComboBoxString(Parent membershipForm) {
		// TODO Auto-generated method stub
		ComboBox<String> cmbAge = (ComboBox<String>)membershipForm.lookup("#cmbAge");
		
		if(cmbAge == null) {
			return "";
		}
		return cmbAge.getValue().toString();
	}
	
	@Override
	public boolean isChkBox(Parent membershipForm) {
		// TODO Auto-generated method stub
		if(!((CheckBox)membershipForm.lookup("#chkMusic")).isSelected() && 
				!((CheckBox)membershipForm.lookup("#chkSport")).isSelected() &&
				!((CheckBox)membershipForm.lookup("#chkMovie")).isSelected()) {
			return false;
		}
		return true;
	}

}
