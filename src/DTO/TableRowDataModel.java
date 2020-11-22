package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableRowDataModel {
	private SimpleIntegerProperty res_no; // 예약 번호
	private SimpleStringProperty res_id; // 예약자 ID
	private SimpleStringProperty name; // 예약자 이름
	private SimpleStringProperty exhibit; // 전시회 이름
	private SimpleStringProperty res_date; // 예약 날짜
	private SimpleStringProperty res_time; // 예약 시간대
	private SimpleIntegerProperty ticket; // 티켓 매수
	private SimpleStringProperty ticket_sel; // 티켓 구분
	private SimpleIntegerProperty price; // 총 가격

	public TableRowDataModel(int res_no, String res_id, String name, String exhibit, String res_date, String res_time,
			int ticket, String ticket_sel, int price) {
		this.res_no = new SimpleIntegerProperty(res_no);
		this.res_id = new SimpleStringProperty(res_id);
		this.name = new SimpleStringProperty(name);
		this.exhibit = new SimpleStringProperty(exhibit);
		this.res_date = new SimpleStringProperty(res_date);
		this.res_time = new SimpleStringProperty(res_time);
		this.ticket = new SimpleIntegerProperty(ticket);
		this.ticket_sel = new SimpleStringProperty(ticket_sel);
		this.price = new SimpleIntegerProperty(price);
	}

	public SimpleIntegerProperty getRes_no() {
		return res_no;
	}

	public void setRes_no(SimpleIntegerProperty res_no) {
		this.res_no = res_no;
	}

	public SimpleStringProperty getRes_id() {
		return res_id;
	}

	public void setRes_id(SimpleStringProperty res_id) {
		this.res_id = res_id;
	}

	public SimpleStringProperty getName() {
		return name;
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public SimpleStringProperty getExhibit() {
		return exhibit;
	}

	public void setExhibit(SimpleStringProperty exhibit) {
		this.exhibit = exhibit;
	}

	public SimpleStringProperty getRes_date() {
		return res_date;
	}

	public void setRes_date(SimpleStringProperty res_date) {
		this.res_date = res_date;
	}

	public SimpleStringProperty getRes_time() {
		return res_time;
	}

	public void setRes_time(SimpleStringProperty res_time) {
		this.res_time = res_time;
	}

	public SimpleIntegerProperty getTicket() {
		return ticket;
	}

	public void setTicket(SimpleIntegerProperty ticket) {
		this.ticket = ticket;
	}

	public SimpleStringProperty getTicket_sel() {
		return ticket_sel;
	}

	public void setTicket_sel(SimpleStringProperty ticket_sel) {
		this.ticket_sel = ticket_sel;
	}

	public SimpleIntegerProperty getPrice() {
		return price;
	}

	public void setPrice(SimpleIntegerProperty price) {
		this.price = price;
	}
}
