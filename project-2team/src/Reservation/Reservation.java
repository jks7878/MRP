package Reservation;

public class Reservation {
	
	private String res_id;
	private String name;
	private String exhibit;
	private String date;
	private String time;
	private int ticket;
	private String ticket_sel;
	private int price;
	
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
	public String getExhibit() {
		return exhibit;
	}
	public void setExhibit(String exhibit) {
		this.exhibit = exhibit;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
