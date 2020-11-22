package DTO;

import java.time.LocalDate;
import java.time.YearMonth;

public class CommonData {
	
	private static LocalDate today=null;
	private static String loginedId=null;
	private static String exTitle=null;
	private static String exDate=null;
	private static String exTime=null;
	private static int ticket=0;
	private static String ticket_sel=null;
	private static int price=0;
	private static int res_no=0;
	
	public static LocalDate getToday() {
		return today;
	}
	public static void setToday(LocalDate today) {
		CommonData.today = today;
	}
	public YearMonth getYearmonth() {
		return YearMonth.of(today.getYear(), today.getMonthValue());
	}
	public String getLoginedId() {
		return loginedId;
	}
	public void setLoginedId(String loginedId) {
		CommonData.loginedId = loginedId;
	}
	public static String getExTitle() {
		return exTitle;
	}
	public static void setExTitle(String exTitle) {
		CommonData.exTitle = exTitle;
	}
	public static String getExDate() {
		return exDate;
	}
	public static void setExDate(String exDate) {
		CommonData.exDate = exDate;
	}
	public static String getExTime() {
		return exTime;
	}
	public static void setExTime(String exTime) {
		CommonData.exTime = exTime;
	}
	public static int getTicket() {
		return ticket;
	}
	public static void setTicket(int ticket) {
		CommonData.ticket = ticket;
	}
	public static String getTicket_sel() {
		return ticket_sel;
	}
	public static void setTicket_sel(String ticket_sel) {
		CommonData.ticket_sel = ticket_sel;
	}
	public static int getPrice() {
		return price;
	}
	public static void setPrice(int price) {
		CommonData.price = price;
	}
	public static int getRes_no() {
		return res_no;
	}
	public static void setRes_no(int res_no) {
		CommonData.res_no = res_no;
	}
}
