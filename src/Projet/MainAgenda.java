package Projet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

import Model.ListeDetail;
import Model.PlanteDetail;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainAgenda extends Application {
    private static final long serialVersionUID = 1L;
    public static Stage primary;
    public static BorderPane mainPane = new BorderPane();
    public static ListeDetail listeDetail=new ListeDetail();
    public static PlanteDetail planteDetail;
    public static int index=0;
    public BorderPane wholePane=new BorderPane();
    Calendar currentDate=new GregorianCalendar();
    //以此刻的年月日初始化右侧文本标题
    RightOftheWholePane log=new RightOftheWholePane(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH)+1,currentDate.get(Calendar.DAY_OF_MONTH));
    //以当前的年月设置左侧的部分
    TopOftheWholePane topOftheWholePane=new TopOftheWholePane(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH)+1,log,wholePane);
    BorderPane left=topOftheWholePane.setLeftOftheWholePane(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH)+1);


    public void start(Stage primaryStage) {
//        HashMap<String, PlanteDetail> map = null;
//        try
//        {
//            FileInputStream fis = new FileInputStream("hashmap.ser");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            map = (HashMap) ois.readObject();
//            ois.close();
//            fis.close();
//        }catch(IOException ioe)
//        {
//            ioe.printStackTrace();
//            return;
//        }catch(ClassNotFoundException c)
//        {
//            System.out.println("Class not found");
//            c.printStackTrace();
//            return;
//        }
//        System.out.println("Deserialized HashMap..");
//        // Display content using Iterator
//        Set set = map.entrySet();
//        Iterator iterator = set.iterator();
//        while(iterator.hasNext()) {
//            Map.Entry mentry = (Map.Entry)iterator.next();
//            System.out.print("key: "+ mentry.getKey() + " & Value: ");
//            System.out.println(mentry.getValue());
//        }
//        MainAgenda.listeDetail.setMap(map);

        primary = primaryStage;

        BottomOftheWholePane bottom=new BottomOftheWholePane(log);
        TopOftheWholePane top=new TopOftheWholePane(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH)+1,log,wholePane);

        top.setPadding(new Insets(5,5,5,5));
        left.setPadding(new Insets(5,5,5,5));
        bottom.setPadding(new Insets(5,5,5,5));
        log.setPadding(new Insets(5,5,5,5));
        wholePane.setTop(top);wholePane.setLeft(left);
        wholePane.setBottom(bottom);wholePane.setRight(log);
        wholePane.setStyle("-fx-background-color: #5DA38B");
        mainPane = wholePane;
        Scene scene = new Scene(wholePane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
