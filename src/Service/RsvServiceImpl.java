package Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import DAO.DBService;
import DAO.DBServiceImpl;
import DTO.CommonData;
import Main.RsvController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RsvServiceImpl implements RsvService {
	
	CommonService comSrv=new CommonServiceImpl();
	RsvController rsvCtrl=new RsvController();
	private DBService dbSrv=new DBServiceImpl();
	private CommonData cmd=new CommonData();
	private static Stage rsvStage;
	
	private ImageView exImg;
	private Button btnNext;
	private Button btnCalPrev,btnCalNext; //이전달 다음달
	private Label lblTitle,lblSelCal,lblSelDate,lblSelTime,lblTime,sun,mon,tue,wed,thu,fri,sat; //전시제목,선택날짜,선택시간,회차선택,일월화수목금토
	private static ToggleButton tb1,tb2,tb3,tb4,tb5,tb6,tb7,tb8,tb9,tb10; //시간선택
	private static ToggleButton tlist[]= {tb1,tb2,tb3,tb4,tb5,tb6,tb7,tb8,tb9,tb10};
	private static ToggleGroup gTime; //시간그룹
	private static ToggleButton day1,day2,day3,day4,day5,day6,day7,day8,day9,day10,
								day11,day12,day13,day14,day15,day16,day17,day18,day19,day20,
								day21,day22,day23,day24,day25,day26,day27,day28,day29,day30,
								day31,day32,day33,day34,day35,day38,day39,day40,day41,day42; //날짜선택
	private static ToggleButton dayList[]= {day1,day2,day3,day4,day5,day6,day7,day8,day9,day10,
											day11,day12,day13,day14,day15,day16,day17,day18,day19,day20,
											day21,day22,day23,day24,day25,day26,day27,day28,day29,day30,
											day31,day32,day33,day34,day35,day38,day39,day40,day41,day42}; //날짜그룹
	private static ToggleGroup gDate; //날짜그룹
	
	private static Map<String, String> DayOfWeek = new HashMap<>();
	private static YearMonth currentYM;
	static int no;
	
	@Override
	public void rsvForm() {
		rsvStage=new Stage();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../FXML/rsv.fxml"));
		Parent root=null;
		try {
			root=loader.load();
		}catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene=new Scene(root);
		RsvController rsvCtrl=loader.getController();
		
		rsvCtrl.setRsvParent(root);
		rsvCtrl.setRsvForm(root);
		setting(root);
		
		rsvStage.setTitle("전시 예매");
		rsvStage.setScene(scene);
		rsvStage.show();
	}
	
	public void set(Parent rsvForm) {
		exImg=(ImageView)rsvForm.lookup("#exhibition");
		lblTitle=(Label)rsvForm.lookup("#lblTitle");
		lblTime=(Label)rsvForm.lookup("#lblTime");
		lblSelDate=(Label)rsvForm.lookup("#lblSelDate");
		lblSelTime=(Label)rsvForm.lookup("#lblSelTime");
		lblSelCal=(Label)rsvForm.lookup("#lblSelCal");
		
		btnCalPrev=(Button)rsvForm.lookup("#btnCalPrev");
		btnCalNext=(Button)rsvForm.lookup("#btnCalNext");
		btnNext=(Button)rsvForm.lookup("#btnNext");
	}
	
	public void setting(Parent rsvForm) {
		set(rsvForm);
		String exTitle=cmd.getExTitle();
		exImg.setImage(new Image(getClass().getResourceAsStream("../img/exhibition/"+exTitle+".jpg")));
		
		if(exTitle.equals("pino")) {
			lblTitle.setText("My Dear 피노키오 전");
			no = 1;
		}else if(exTitle.equals("brother")) {
			lblTitle.setText("퀘이 형제 : 도미토리움으로의 초대전");
			no = 2;
		}else if(exTitle.equals("photo")) {
			lblTitle.setText("퓰리처상 사진전");
			no = 3; 
		}
		
		lblTitle.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"),26));
		lblTime.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 20));
		lblSelDate.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 18));
		lblSelTime.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 18));
		lblSelCal.setFont(Font.loadFont(getClass().getResourceAsStream("../font/FuturaPTMedium.otf"), 30));
		btnCalPrev.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕.ttf"), 25));
		btnCalNext.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕.ttf"), 25));
		btnNext.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 20));
		if(cmd.getRes_no()!=0) {
			btnNext.setText("예약변경");
		}
		
		String lblFldArr[]= {"#sun","#mon","#tue","#wed","#thu","#fri","#sat"};
		Map<String, Label> lblFldMap=comSrv.getLabelInfo(rsvForm, lblFldArr);
		for(int i=0;i<lblFldArr.length;i++) {
			lblFldMap.get(lblFldArr[i]).setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕.ttf"), 16));
		}
		String[] engDay = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
		String[] korDay = {"일", "월", "화", "수", "목", "금", "토"};
		for(int i = 0; i < engDay.length; i++) {
			DayOfWeek.put(engDay[i], korDay[i]); 
		}
		
		gTime=new ToggleGroup();
		for(ToggleButton t:tlist) {
			for(int i=1;i<=10;i++) {
				t=(ToggleButton)rsvForm.lookup("#tb"+i);
				t.setToggleGroup(gTime);
				t.setUserData(10+(i-1));
			}
		}
		gDate=new ToggleGroup();
		for(ToggleButton t:dayList) {
			for(int i=1;i<=35;i++) {
				t=(ToggleButton)rsvForm.lookup("#day"+i);
				t.setToggleGroup(gDate);
			}
		}
		setDate(rsvForm, cmd.getYearmonth());
	}
	
	//달력 '<' 버튼
	@Override
	public void calPrevBtn(Parent rsvForm) {
		calSelNull(rsvForm);
		setDate(rsvForm, currentYM.minusMonths(1)); //한달 뺀 달력을 로드
		LocalDate firstDay = LocalDate.of(currentYM.getYear(), currentYM.getMonthValue(), 1);
		setToday(rsvForm, firstDay);
	}

	//달력 '>' 버튼
	@Override
	public void calNextBtn(Parent rsvForm) {
		calSelNull(rsvForm);
		setDate(rsvForm, currentYM.plusMonths(1)); //한달 뺀 달력을 로드		
		LocalDate firstDay = LocalDate.of(currentYM.getYear(), currentYM.getMonthValue(), 1);
		setToday(rsvForm, firstDay);
	}
	
	//달력 < > 눌렀을 때 버튼 초기화
	public void calSelNull(Parent rsvForm) {
		set(rsvForm);
		gTime.selectToggle(null);
		gDate.selectToggle(null);
		lblSelDate.setText("");
		lblSelTime.setText("");
	}
	
	public void setToday(Parent rsvForm, LocalDate date) {
		set(rsvForm);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM");
		lblSelCal.setText(date.format(dtf));
	}
	
	public void setDate(Parent rsvForm, YearMonth ym) {
		LocalDate calendarDate = LocalDate.of(ym.getYear(), ym.getMonthValue(), 1); //해당 년월의 1일을 가져온다.
		int row = checkRow(ym);
		setToday(rsvForm, calendarDate);
		while(!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) { //일요일이 아닐때까지 하루씩 빼간다.
			calendarDate = calendarDate.minusDays(1); //하루씩 감소
		}
		
		LocalDate e1[] = {LocalDate.of(2020, 6, 26), LocalDate.of(2020, 10, 4)};
		LocalDate e2[] = {LocalDate.of(2020, 6, 27), LocalDate.of(2020, 10, 4)};
		LocalDate e3[] = {LocalDate.of(2020, 7, 01), LocalDate.of(2020, 10, 18)};
		
		int i = 1;
		LocalDate reservableDate, today;
		today = cmd.getToday();
		reservableDate = today.plusDays(14);
		for(ToggleButton t : dayList) {
			t=(ToggleButton)rsvForm.lookup("#day"+i);
			t.setVisible(true);
			t.setDisable(false);
			if(row < 6) {
				if(i > 35) {
					t.setVisible(false);
				}
			}
			if(calendarDate.isBefore(today) || calendarDate.isEqual(today) || calendarDate.isAfter(reservableDate)) {
				t.setDisable(true);
			}
			if(no == 1) {
				if(calendarDate.isBefore(e1[0]) || calendarDate.isAfter(e1[1])) {
					t.setDisable(true);
				}
			}else if(no == 2) {
				if(calendarDate.isBefore(e2[0]) || calendarDate.isAfter(e2[1])) {
					t.setDisable(true);
				}
			}else if(no == 3) {
				if(calendarDate.isBefore(e3[0]) || calendarDate.isAfter(e3[1])) {
					t.setDisable(true);
				}
			}
			t.setText(String.valueOf(calendarDate.getDayOfMonth()));
			t.setUserData(calendarDate + " (" + DayOfWeek.get(calendarDate.getDayOfWeek().toString()) + ")");
			calendarDate = calendarDate.plusDays(1);
			i++;
		}
		currentYM = ym;
	}
	
	// 필요한 행수를 계산해서 리턴
	public int checkRow(YearMonth ym) {
		int cnt = 1;
		int standard;
		int m;
		LocalDate calendarDate = LocalDate.of(ym.getYear(), ym.getMonthValue(), 1);
		standard = calendarDate.getMonthValue();
		while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) {
			calendarDate = calendarDate.minusDays(1);
		}
		m = calendarDate.getMonth().getValue();
		while (true) {
			calendarDate = calendarDate.plusDays(1);
			if (m == standard) {
				if (cnt < 6) {
					return 5;
				}
				if (cnt == 6) {
					if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
						return 6;
					}
					return 5;
				}
				if (cnt == 7) {
					if (m == 2) {
						return 5;
					}
					return 6;
				}
			}
			m = calendarDate.getMonth().getValue();
			cnt++;
		}
	}
	
	//날짜 선택
	@Override
	public void selDate(Parent rsvForm, ActionEvent e) {
		set(rsvForm);
		lblSelTime.setText("");
		for(ToggleButton t:tlist) {
			for(int i=0;i<10;i++) {
				t=(ToggleButton)rsvForm.lookup("#tb"+(i+1));
				t.setDisable(false);
			}
		}
		if(gDate.getSelectedToggle()==null) {
			gTime.selectToggle(null);
			lblSelDate.setText("");
			return;
		}
		lblSelDate.setText(gDate.getSelectedToggle().getUserData().toString());
		gTime.selectToggle(null);
		
		//티켓매진
		int[] tNum=dbSrv.timeCheck(lblTitle.getText(), gDate.getSelectedToggle().getUserData().toString());
		for(ToggleButton t:tlist) {
			for(int i=0;i<10;i++) {
				t=(ToggleButton)rsvForm.lookup("#tb"+(i+1));
				if(tNum[i]>=5) {
					t.setDisable(true);
				}
			}
		}
	}
	
	//시간 선택
	@Override
	public void selTime(Parent rsvForm,ActionEvent e) {
		set(rsvForm);
		if(gDate.getSelectedToggle()==null) {
			gTime.selectToggle(null);
			comSrv.errorMsg("날짜를 선택해주세요");
			return;
		}else {
			if(gTime.getSelectedToggle()==null) {
				gTime.selectToggle(null);
				lblSelTime.setText("");
				return;
			}
			lblSelTime.setText(gTime.getSelectedToggle().getUserData().toString()+":00");
			return;
		}
	}
	
	//입력 누락 확인
	@Override
	public boolean isSelected(Parent rsvForm) {
		set(rsvForm);
		if(gDate.getSelectedToggle()==null) { //날짜 누락
			comSrv.errorMsg("날짜를 선택해주세요");
			return false;
		}else if(gTime.getSelectedToggle()==null) { //시간 누락
			comSrv.errorMsg("시간대를 선택해주세요");
			return false;
		}else {
			cmd.setExDate(lblSelDate.getText());
			cmd.setExTime(lblSelTime.getText());
			return true;
		}
	}
	
	//신규예매/예약변경
	@Override
	public void rsvNext(ActionEvent e) {
		if(cmd.getRes_no()!=0) {
			if(comSrv.selectErrMsg("예약 변경하시겠습니까?")) {
				String[] update= {cmd.getExDate(),cmd.getExTime()};
				DBService dbSrv=new DBServiceImpl();
				if(dbSrv.updateRsv(cmd.getRes_no(), update)) {
					comSrv.errorMsg("예약 변경이 완료되었습니다");
					clear();
					comSrv.closeWindow(e);
				}else {
					comSrv.errorMsg("예약 변경이 불가합니다.");
					return;
				}
			}
		}else {
			Rsv2Service rsv2Srv=new Rsv2ServiceImpl();
			rsv2Srv.rsv2Form(rsvStage);
		}
	}
	
	//예매저장값 초기화
	public void clear() {
		cmd.setRes_no(0);
		cmd.setExDate(null);
		cmd.setExTime(null);
	}
	
}

