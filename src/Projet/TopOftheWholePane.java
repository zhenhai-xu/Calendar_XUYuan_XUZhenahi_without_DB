package Projet;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class TopOftheWholePane extends HBox {
    public int year,month;
    public TextField yearText = new TextField();
    public TextField monthText = new TextField();
    RightOftheWholePane log;//set
    public BorderPane wholePane;

    public Button btLastYear = new Button("l'annee deriere");
    public Button btNextYear = new Button("l'lannee prochain");
    public Button btLastMonth = new Button("le mois deriere");
    public Button btNextMonth = new Button("le mois prochain");

    public TopOftheWholePane(int year,int month,RightOftheWholePane log,BorderPane wholePane)
    {
        //设置年月并初始化
        this.wholePane=wholePane;
        this.log=log;
        this.year=year;this.month=month;
        this.yearText.setText(Integer.toString(year));
        this.monthText.setText(Integer.toString(month));
        yearText.setAlignment(Pos.CENTER);//年份的文本域设置为居中
        monthText.setAlignment(Pos.CENTER);//月份的文本域设置为居中
        yearText.setEditable(true);
        monthText.setEditable(true);

        this.setAlignment(Pos.CENTER);//HBox对齐方式为居中
        this.setSpacing(5);//两个节点之间的间距
        yearText.setOnAction(e->{
            int newYear=Integer.parseInt(yearText.getText()),newMonth=Integer.parseInt(monthText.getText());
            setCalendarBaseOnGivenDate(newYear,newMonth);
        });
        monthText.setOnAction(e->{
            int newYear=Integer.parseInt(yearText.getText()),newMonth=Integer.parseInt(monthText.getText());
            setCalendarBaseOnGivenDate(newYear,newMonth);
        });
        btLastYear.setOnAction(e->{
            this.year--;
            yearText.setText(String.valueOf(this.year));
            log.setTextArea("");
            wholePane.setLeft(setLeftOftheWholePane(this.year,this.month));
        });
        btNextYear.setOnAction(e->{
            this.year++;
            yearText.setText(String.valueOf(this.year));
            log.setTextArea("");
            wholePane.setLeft(setLeftOftheWholePane(this.year,this.month));
        });
        btLastMonth.setOnAction(e->{
            if(this.month==1)
            {
                this.month=12;this.year--;
            }
            else
                this.month--;
            yearText.setText(String.valueOf(this.year));
            monthText.setText(String.valueOf(this.month));
            log.setTextArea("");
            wholePane.setLeft(setLeftOftheWholePane(this.year,this.month));
        });
        btNextMonth.setOnAction(e->{
            if(this.month==12)
            {
                this.month=1;this.year++;
            }
            else
                this.month++;
            yearText.setText(String.valueOf(this.year));
            monthText.setText(String.valueOf(this.month));
            log.setTextArea("");
            wholePane.setLeft(setLeftOftheWholePane(this.year,this.month));
        });
        yearText.setPrefColumnCount(6);
        yearText.setPrefHeight(5);
        monthText.setPrefColumnCount(6);
        monthText.setPrefHeight(5);
        this.getChildren().addAll(btLastYear,yearText,btNextYear,btLastMonth,monthText,btNextMonth);
    }
    public void setCalendarBaseOnGivenDate(int year,int month)
    {
        if(year>0 && month>0 && month<=12)
        {
            this.year=year;this.month=month;
            wholePane.setLeft(setLeftOftheWholePane(this.year,this.month));
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("设置日期");
            alert.setContentText("设置日期失败，请输入合法的日期");
            alert.showAndWait();
        }
    }

    //以指定日期设置左侧,方法返回组件
    public BorderPane setLeftOftheWholePane(int year, int month)
    {
        BorderPane leftOftheWholePane=new BorderPane();
        ClockPane clock=new ClockPane();//以当前时间建立一个时钟
        EventHandler<ActionEvent> eventHandler = e -> {
            clock.setCurrentTime();
        };
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(1000),eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        CalendarPane calender=new CalendarPane(year,month,log);//以给定的年月建立日历 main
        leftOftheWholePane.setCenter(calender);
        leftOftheWholePane.setBottom(clock);
        return leftOftheWholePane;
    }

}
