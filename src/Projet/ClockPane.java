package Projet;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Calendar;
import java.util.GregorianCalendar;

class ClockPane extends Pane
{
    private int hour,minute,second;
    //默认构造方法，设置现在的时间
    public ClockPane()
    {
        setCurrentTime();
    }
    //带参数的构造方法
    public ClockPane(int hour,int minute,int second)
    {
        this.hour=hour;this.minute=minute;this.second=second;
    }
    //get
    public int getHour() {return hour;}
    public int getMinute() {return minute;}
    public int getSecond() {return second;}
    //set新的时间之后重新显示时钟
    public void setHour(int h) {this.hour=h;paintClock();}
    public void setMinute(int m) {this.minute=m;paintClock();}
    public void setSecond(int s) {this.second=s;paintClock();}
    //
    public void setCurrentTime()
    {
        Calendar calendar=new GregorianCalendar();//公历
        this.hour=calendar.get(Calendar.HOUR_OF_DAY);
        this.minute=calendar.get(Calendar.MINUTE);
        this.second=calendar.get(Calendar.SECOND);
        paintClock();
    }
    //显示时钟
    public void paintClock()
    {
        getChildren().clear();
        double clockRadius=250*0.8*0.4;
        double centerX = 150 / 2.0 + 80;
        double centerY = 300 / 2.0 - 65;
        //59个圆点	，计算圆点的（x,y,r）
        for(int i = 0; i < 60; i++) {
            double x = centerX + Math.sin((i / 60.0) * 2 * Math.PI) * clockRadius;
            double y = centerY + Math.cos((i / 60.0) * 2 * Math.PI) * clockRadius;
            double radius= i%15==0?5:(i%5==0?3:1);//大圆点，中圆点，小圆点
            Circle c = new Circle(x,y,radius);
            if(i%5!=0 && i%15!=0) c.setFill(Color.BLACK);
            else c.setFill(Color.ORANGE);//颜色填充

            getChildren().add(c);//把外圈的圆点加入到栈组件中
        }
        //秒针
        double slength = clockRadius * 0.8;
        double secondX = centerX + slength * Math.sin((second / 60.0) * 2 * Math.PI);
        double secondY = centerY - slength * Math.cos((second / 60.0) * 2 * Math.PI);
        Line sLine = new Line(centerX,centerY,secondX,secondY);
        sLine.setStrokeWidth(1);
        sLine.setStroke(Color.RED);
        //分针
        double mlength = clockRadius * 0.65;
        double minuteX = centerX + mlength * Math.sin((Math.PI / 60) * 2 * minute);
        double minuteY = centerY - mlength * Math.cos((Math.PI / 60) * 2 * minute);
        Line mLine = new Line(centerX,centerY,minuteX,minuteY);
        mLine.setStrokeWidth(2);
        mLine.setStroke(Color.BLUE);
        //时针
        double hlength = clockRadius * 0.5;
        double hourX = centerX + hlength * Math.sin((Math.PI / 12) * 2 * (hour % 12 + minute / 60.0));
        double hourY = centerY - hlength * Math.cos((Math.PI / 12) * 2 * (hour % 12 + minute / 60.0));
        Line hLine = new Line(centerX,centerY,hourX,hourY);
        hLine.setStrokeWidth(4);
        hLine.setStroke(Color.ORANGE);
        setStyle("-fx-background-color:gray");
        getChildren().addAll(sLine,mLine,hLine);//把指针加入到栈组件中
    }
}
