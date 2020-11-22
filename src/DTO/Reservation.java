package DTO;

public class Reservation {
	private int res_no; //예약번호
	private String res_id; // 예약자 ID
	private String name; // 예약자 이름
	private String title; // 전시회 이름
	private String res_date; // 예약 날짜, 시작 일
	private String res_time; // 예약 시간대
	private int ticket; // 티켓 매수
	private String ticket_sel; // 티켓 구분
	private int price; // 총 가격
	
	public int getRes_no() {
		return res_no;
	}
	public void setRes_no(int res_no) {
		this.res_no = res_no;
	}
	public String getRes_id() {
		return res_id;
	}
	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRes_date() {
		return res_date;
	}
	public void setRes_date(String res_date) {
		this.res_date = res_date;
	}
	public String getRes_time() {
		return res_time;
	}
	public void setRes_time(String res_time) {
		this.res_time = res_time;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	public String getTicket_sel() {
		return ticket_sel;
	}
	public void setTicket_sel(String ticket_sel) {
		this.ticket_sel = ticket_sel;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
