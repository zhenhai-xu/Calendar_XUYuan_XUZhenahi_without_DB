package Projet;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CalendarPane extends GridPane {
    RightOftheWholePane log;//set
    Calendar cc = Calendar.getInstance();
    int dayT = cc.get(Calendar.DAY_OF_MONTH);
    public CalendarPane(int year,int month,RightOftheWholePane log)
    {
        Label sun=new Label("SUN日");sun.setFont(Font.font("Times New Roman", FontWeight.BOLD,12));sun.setTextFill(Color.RED);
        Label mon=new Label("MON一");mon.setFont(Font.font("Times New Roman",FontWeight.BOLD,12));
        Label tue=new Label("TUE二");tue.setFont(Font.font("Times New Roman",FontWeight.BOLD,12));
        Label wed=new Label("WED三");wed.setFont(Font.font("Times New Roman",FontWeight.BOLD,12));
        Label thu=new Label("THU四");thu.setFont(Font.font("Times New Roman",FontWeight.BOLD,12));
        Label fri=new Label("FRI五");fri.setFont(Font.font("Times New Roman",FontWeight.BOLD,12));
        Label sat=new Label("SAT六");sat.setFont(Font.font("Times New Roman",FontWeight.BOLD,12));sat.setTextFill(Color.BLUE);
        this.add(sun, 0, 0);
        this.add(mon, 1, 0);
        this.add(tue, 2, 0);
        this.add(wed,3, 0);
        this.add(thu, 4, 0);
        this.add(fri,5, 0);
        this.add(sat, 6, 0);

        Calendar c=new GregorianCalendar(year, month-1,1);//从这个月1号开始，0表示1月份
        this.setGridLinesVisible(true);//显示网格线
        int hang=1;
        int totalDaysOfThisMonth=c.getActualMaximum(Calendar.DAY_OF_MONTH);//获取当前月的天数
        Button[] dates=new Button[40];
        for(int i=1;i<=totalDaysOfThisMonth;i++)
        {
            int day=i;
            int dayOfWeek=c.get(Calendar.DAY_OF_WEEK)-1;//星期日是一周的第一天，星期六是一周的第7天
            dates[i]=new Button(Integer.toString(i));
            dates[i].setStyle("-fx-background-color:null;-fx-background-insets: 0;  -fx-pref-width: 42px;-fx-pref-height: 30px;");
            Button date=dates[i];


            int k=i;

            dates[i].setOnAction(e->{
                date.setStyle(" -fx-pref-width: 42px;-fx-pref-height: 30px;-fx-background-color:pink");//选中的颜色
                //此时只能有一个按钮的颜色是粉色

                for(int j=1;j<=totalDaysOfThisMonth;j++)
                {
                    if(j!=k) {
                        if(j == dayT) {
                            dates[dayT].setStyle(" -fx-pref-width: 42px;-fx-pref-height: 30px;-fx-background-color:#d0ff7e");
                        }else{
                            dates[j].setStyle("-fx-background-color:null;-fx-background-insets: 0;  -fx-pref-width: 42px;-fx-pref-height: 30px;");
                            }
                        }
                    }
                log.setTitle(year + "/" + month + "/" + day );
                String s=String.format("%04d%02d%02d",year,month,day);
                s+=".txt";
                log.setURL(s);
                try {
                    Scanner input = new Scanner(new File(log.getURL()));
                    String ss = "";
                    while(input.hasNext())
                        ss += input.nextLine()+"\r\n";
                    log.setTextArea(ss);
                    input.close();
                }
                catch(Exception ex) {
                    String ss = "";
                    log.setTextArea(ss);
                }
            });//每选中一个日期就把右侧的文本的标题设为该日期
            this.add(dates[i], dayOfWeek, hang);
            if(c.get(Calendar.DAY_OF_WEEK)==7) hang++;
            c.add(Calendar.DAY_OF_MONTH,1);//日子数加一，变成下一天
        }
        dates[dayT].setStyle(" -fx-pref-width: 42px;-fx-pref-height: 30px;-fx-background-color:#d0ff7e");
    }
}
