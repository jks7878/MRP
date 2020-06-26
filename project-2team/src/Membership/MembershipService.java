package Membership;

import java.util.List;

import javafx.scene.Parent;

public interface MembershipService {
	public boolean comparePW(String pw, String pwOk);
	public void AddComoboBox(Parent form, List<String> items);
	public boolean isComboBox(Parent membershipForm);
	public String getComboBoxString(Parent membershipForm);
	public boolean isChkBox(Parent membershipForm);
}
