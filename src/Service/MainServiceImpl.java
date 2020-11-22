package Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DTO.CommonData;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainServiceImpl implements MainService{
	
	CommonService comSrv=new CommonServiceImpl();
	private CommonData cmd=new CommonData();
	
	private Label today,day1,day2,day3,title1,title2,title3,lblId;
	private ImageView log;
	private DatePicker dp;
	
	public void set(Parent root) {
		today=(Label)root.lookup("#today");
		day1=(Label)root.lookup("#day1");
		day2=(Label)root.lookup("#day2");
		day3=(Label)root.lookup("#day3");
		title1=(Label)root.lookup("#title1");
		title2=(Label)root.lookup("#title2");
		title3=(Label)root.lookup("#title3");
		lblId=(Label)root.lookup("#lblId");
		log=(ImageView)root.lookup("#btnLogin");
		dp = (DatePicker)root.lookup("#minical");
	}

	@Override
	public void setFont(Parent root) {
		set(root);
		Font font1=Font.loadFont(getClass().getResourceAsStream("../font/FuturaPTLight.otf"), 20);
		Font font2=Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 16);
		Font font3=Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕ExtraBold.ttf"), 20);
		today.setFont(font1);
		day1.setFont(font2);
		day2.setFont(font2);
		day3.setFont(font2);
		title1.setFont(font3);
		title2.setFont(font3);
		title3.setFont(font3);
		lblId.setFont(font2);
	}
	
	//오늘날짜
	@Override
	public void setToday(Parent root) {
		set(root);
		cmd.setToday(LocalDate.now());
		Calendar td=Calendar.getInstance();
		today.setText(cmd.getToday().toString());
		
	}
	
	//오늘날짜 변경
	@Override
	public void modToday(Parent root) {
		set(root);
		boolean flag = false;
		dp.setVisible(true);
		dp.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER){
				if(dp.getValue() == null) {
					dp.setVisible(false);
					return;
				}
				cmd.setToday(dp.getValue());
				today.setText(cmd.getToday().toString());
			}
			dp.setVisible(false);
			setDday(root);
		});
		root.setOnMousePressed(e -> {
			dp.setVisible(false);
		});
	}
	
	//디데이
	@Override
	public void setDday(Parent root) {
		set(root);
		Label day[]= {day1,day2,day3};
		
		LocalDate ed1[] = {LocalDate.of(2020, 6, 26), LocalDate.of(2020, 10, 4)};
		LocalDate ed2[] = {LocalDate.of(2020, 6, 27), LocalDate.of(2020, 10, 4)};
		LocalDate ed3[] = {LocalDate.of(2020, 7, 01), LocalDate.of(2020, 10, 18)};
		List<LocalDate[]> dates = new ArrayList<LocalDate[]>();
		dates.add(ed1);
		dates.add(ed2);
		dates.add(ed3);
		long gap;
		
		List<Long> dday=new ArrayList<Long>();
		for(LocalDate[] d : dates) {
			gap = ChronoUnit.DAYS.between(cmd.getToday(), d[0]);
			dday.add(gap);
		}
		int i = 0;
		for(LocalDate[] d : dates) {
			if(cmd.getToday().isBefore(d[0])) {
				day[i].setText("D - "+dday.get(i));
			}else if(cmd.getToday().isAfter(d[1])) {
				day[i].setText("마감");
			}
			else {
				day[i].setText("진행중");
			}
			i++;
		}
	}
	
	//로그아웃
	@Override
	public void logout(Parent root) {
		set(root);
		if(comSrv.selectErrMsg("로그아웃 하시겠습니까?")) {
			cmd.setLoginedId(null);
			lblId.setText("");
			log.setImage(new Image(getClass().getResourceAsStream("../img/main/btn_login.png")));
			return;
		}
	}
	
	//선택한 전시정보
	@Override
	public void selExhibition(MouseEvent e) {
		ImageView ex=(ImageView)e.getSource();
		String id=ex.getId();
		if(id.equals("exhibition1")) {
			cmd.setExTitle("pino");
		}else if(id.equals("exhibition2")) {
			cmd.setExTitle("brother");
		}else if(id.equals("exhibition3")) {
			cmd.setExTitle("photo");
		}
		ExhibitionService exSrv=new ExhibitionServiceImpl();
		exSrv.exhibitionForm();
	}
	
	//예약내역
	@Override
	public Parent myRsvForm() {
		Stage myRsvForm = new Stage();
		Parent form=comSrv.showWindow(myRsvForm, "../FXML/myrsv.fxml");
		return form;
	}
	
}
