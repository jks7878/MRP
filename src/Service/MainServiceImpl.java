package Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainServiceImpl implements MainService{

	@Override
	public void setFont(Parent root) {
		Font font1=Font.loadFont(getClass().getResourceAsStream("../font/FuturaPTLight.otf"), 20);
		Font font2=Font.loadFont(getClass().getResourceAsStream("../font/AppleSDGothicNeoSB.ttf"), 16);
		Font font3=Font.loadFont(getClass().getResourceAsStream("../font/AppleSDGothicNeoB.ttf"), 20);
		Label today=(Label)root.lookup("#today");
		Label day1=(Label)root.lookup("#day1");
		Label day2=(Label)root.lookup("#day2");
		Label day3=(Label)root.lookup("#day3");
		Label title1=(Label)root.lookup("#title1");
		Label title2=(Label)root.lookup("#title2");
		Label title3=(Label)root.lookup("#title3");
		today.setFont(font1);
		day1.setFont(font2);
		day2.setFont(font2);
		day3.setFont(font2);
		title1.setFont(font3);
		title2.setFont(font3);
		title3.setFont(font3);
	}

	@Override
	public void setToday(Parent root) {
		Label today=(Label)root.lookup("#today");
		Calendar td=Calendar.getInstance();
		if(td.get(Calendar.MONTH)+1<10) {
			today.setText(td.get(Calendar.YEAR)+".0"+(td.get(Calendar.MONTH)+1)+"."+td.get(Calendar.DAY_OF_MONTH));
		}else {
			today.setText(td.get(Calendar.YEAR)+"."+(td.get(Calendar.MONTH)+1)+"."+td.get(Calendar.DAY_OF_MONTH));
		}
	}

	@Override
	public void setDday(Parent root) {
		//전시db의 시작날짜 가져오기
		Label day1=(Label)root.lookup("#day1");
		Label day2=(Label)root.lookup("#day2");
		Label day3=(Label)root.lookup("#day3");
		Label day[]= {day1,day2,day3};
		
		Calendar td=Calendar.getInstance();
		Calendar dd1=Calendar.getInstance();
		Calendar dd2=Calendar.getInstance();
		Calendar dd3=Calendar.getInstance();
		
		td.set(td.get(Calendar.YEAR),td.get(Calendar.MONTH)+1,td.get(Calendar.DAY_OF_MONTH));
		dd1.set(2020,06,26);
		dd2.set(2020,06,27);
		dd3.set(2020,07,01);
		Calendar date[]= {dd1,dd2,dd3};
		
		List<Long> dday=new ArrayList<Long>();
		for(Calendar c:date) {
			long diff=c.getTime().getTime()-td.getTime().getTime();
			long diffday=diff/(1000*60*60*24)-1;
			dday.add(diffday);
		}
		for(int i=0;i<dday.size();i++) {
			if(dday.get(i)>0) {
				day[i].setText("D - "+dday.get(i));
			}else {
				day[i].setText("진행중");
			}
		}
	}
	
	@Override
	public Parent loginForm() {
		CommonService comSrv = new CommonServiceImpl();
		Stage loginForm = new Stage();
		Parent form = comSrv.showWindow(loginForm, "../FXML/login.fxml");
		return form;
	}

	@Override
	public void selExhibition(MouseEvent e) {
		ImageView ex=(ImageView)e.getSource();
		String id=ex.getId();
		String exTitle="";
		if(id.equals("exhibition1")) {
			exTitle="pino";
		}else if(id.equals("exhibition2")) {
			exTitle="brother";
		}else if(id.equals("exhibition3")) {
			exTitle="photo";
		}
		
		ExhibitionService exSrv=new ExhibitionServiceImpl();
		exSrv.exhibitionForm(exTitle);
	}

	@Override
	public Parent myRsvForm() {
		CommonService comSrv = new CommonServiceImpl();
		Stage myRsvForm = new Stage();
		Parent form=comSrv.showWindow(myRsvForm, "../FXML/myrsv.fxml");
		return form;
	}
	
}
