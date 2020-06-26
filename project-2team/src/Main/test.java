package Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
	public static void main(String[] args) {
		String start = "2020-06-26";
		String end = "2020-07-11";
		try {
			SimpleDateFormat form = new SimpleDateFormat("yyyy-mm-dd");
			Date begindate = form.parse(start);
			Date enddate = form.parse(end);
			
			long diff = enddate.getTime() - begindate.getTime();
			long diffdays = diff/(24*60*60*1000);
			System.out.println("날짜차이 : " + diffdays);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
