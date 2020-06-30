package DTO;

public class CommonData {
	private static String loginedId = "test";
	private static String today = null;
	
	public String getLoginedId() {
		return loginedId;
	}
	public void setLoginedId(String loginedId) {
		this.loginedId = loginedId;
	}
	public static String getToday() {
		return today;
	}
	public static void setToday(String today) {
		CommonData.today = today;
	}
}
