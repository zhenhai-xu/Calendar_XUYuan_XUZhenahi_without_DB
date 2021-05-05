package Control;

import Model.PlanteDetail;
import Projet.BottomOftheWholePane;
import Projet.TopOftheWholePane;
import Projet.MainAgenda;
import Projet.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.*;

public class PlanteListePane implements Initializable {

    @FXML
    private Button modifierBut1;

    @FXML
    private Button suprimerBut1;

    @FXML
    private Button detaileBut1;

    @FXML
    private Pane listePlante;

    @FXML
    private AnchorPane listePane;
    @FXML
    private Button ajouterPlante;
    @FXML
    private Pane panee;

    AjouterUnePlante childcontroller;

    double paneY=0;


    //把列表里的植物展示到界面
    public void afficherUnePlante(PlanteDetail planteDetail){
        AnchorPane panePlante = new AnchorPane();
        panePlante.setId(planteDetail.getNom());//id 要修改的！！
        panePlante.prefHeight(180.0);
        panePlante.prefWidth(797);
        panePlante.minHeight(0);
        panePlante.minWidth(0);
        panePlante.setStyle("-fx-background-color: #5DA38B");
        panePlante.setLayoutX(1.0);
        panePlante.setLayoutY(paneY);


        Button button1 = new Button("Detaile");
        Button button2 = new Button("Modifier");
        Button button3 = new Button("Supprimer");

        button1.setId("detaileBut1");
        button2.setId("modifierBut1");
        button3.setId("suprimerBut1");
        button1.setLayoutX(710);
        button1.setLayoutY(50);
        button2.setLayoutX(686);
        button2.setLayoutY(90);
        button3.setLayoutX(676);
        button3.setLayoutY(130);
        button1.setPrefWidth(65.0);
        button2.setPrefWidth(90.0);
        button3.setPrefWidth(100.0);


        button1.setStyle("-fx-background-color: #E4FFA8");
        button2.setStyle("-fx-background-color: #E4FFA8");
        button3.setStyle("-fx-background-color: #E4FFA8");

        button1.setOnAction(e -> {

            MainAgenda.planteDetail =planteDetail;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/details.fxml"));
            Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Stage stage = MainAgenda.primary;
//        Stage stage =new Stage();
            //set what you want on your stage

            stage.setTitle("Agenda Etudiant Jardinage - details de plante");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        });
        button2.setOnAction(e -> {
            MainAgenda.planteDetail =planteDetail;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/modifierPlante.fxml"));
            Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Stage stage = MainAgenda.primary;
//        Stage stage =new Stage();
            //set what you want on your stage

            stage.setTitle("Agenda Etudiant Jardinage - details de plante");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        });

        //删除的时候重载页面就好了 待改
        button3.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("警告");
            alert.headerTextProperty().set("您真的要删除吗？");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                MainAgenda.planteDetail=null;
                MainAgenda.listeDetail.remove(planteDetail.getNom());
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/planteListe.fxml"));
                Parent root1 = null;
                try {
                    root1 = (Parent) fxmlLoader.load();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Stage stage = MainAgenda.primary;
//        Stage stage =new Stage();
                //set what you want on your stage


                stage.setTitle("Agenda Etudiant Jardinage - ajouter une plante");
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                stage.show();
            }
        });

        Label label1 = new Label("date de plantation : " + planteDetail.getDate().get("datePlantation"));
        Label label2 = new Label("date de rempotage : " + planteDetail.getDate().get("dateRempotage"));
        Label label3 = new Label("date de arronsage : " + planteDetail.getDate().get("dateArronsage"));
        Label label4 = new Label("date de entretien : " + planteDetail.getDate().get("dateEntretien"));
        Label label5 = new Label("nom de plante : " + planteDetail.getNom());

        label1.setLayoutX(180);
        label1.setLayoutY(40);
        label2.setLayoutX(180);
        label2.setLayoutY(70);
        label3.setLayoutX(180);
        label3.setLayoutY(100);
        label4.setLayoutX(180);
        label4.setLayoutY(130);
        label5.setLayoutX(180);
        label5.setLayoutY(10);

        ImageView imageView = new ImageView();
        imageView.setLayoutX(15);
        imageView.setLayoutY(10);
        if(planteDetail.getImage()!=null) {
            Image image= new Image(planteDetail.getImage());
            imageView.setImage(image);

//            imageView.setImage(planteDetail.getImage());
            System.out.println("图片设置成功");
            imageView.setFitWidth(130);
            imageView.setFitHeight(150);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(false);

        }else {
            File file = new File("src\\ImagePlante\\noPhoto.jpg");
            String string = file.toURI().toString();
            Image image = new Image(string);
            imageView.setImage(image);
            System.out.println("图片设置成功");
            imageView.setFitWidth(130);
            imageView.setFitHeight(150);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(false);
            planteDetail.setImage(string);
//            planteDetail.setImage(image);
        }

        this.paneY+=180;
        listePane.setPrefHeight(listePane.getPrefHeight()+180);
        panePlante.getChildren().addAll(button1,button2,button3,label1,label2,label3,label4,label5,imageView);
        listePane.getChildren().add(panePlante);

    }

    @FXML
    void retour(ActionEvent event) {
        Stage stage = MainAgenda.primary;
//        Stage stage =new Stage();
        BorderPane wholePane=new BorderPane();
        Calendar currentDate=new GregorianCalendar();
        //以此刻的年月日初始化右侧文本标题
        RightOftheWholePane log=new RightOftheWholePane(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH)+1,currentDate.get(Calendar.DAY_OF_MONTH));
        //以当前的年月设置左侧的部分
        TopOftheWholePane topOftheWholePane=new TopOftheWholePane(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH)+1,log,wholePane);
        BorderPane left=topOftheWholePane.setLeftOftheWholePane(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH)+1);
        BottomOftheWholePane bottom=new BottomOftheWholePane(log);
        TopOftheWholePane top=new TopOftheWholePane(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH)+1,log,wholePane);
        //调整块于块之间的间距
        top.setPadding(new Insets(5,5,5,5));
        left.setPadding(new Insets(5,5,5,5));
        bottom.setPadding(new Insets(5,5,5,5));
        log.setPadding(new Insets(5,5,5,5));
        wholePane.setTop(top);wholePane.setLeft(left);
        wholePane.setBottom(bottom);wholePane.setRight(log);
        wholePane.setStyle("-fx-background-color: #7FFFAA");
        Scene scene = new Scene(wholePane);
        stage.setScene(scene);
        stage.setTitle("Agenda Etudiant Jardinage");
        stage.setResizable(false);
        stage.show();
    }



    @FXML
    //切换界面到添加植物
    private void ajouterUnePlante(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/ajouterUnePlante.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = MainAgenda.primary;
//        Stage stage =new Stage();
        //set what you want on your stage


        stage.setTitle("Agenda Etudiant Jardinage - ajouter une plante");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }




    @Override
    //初始化窗口，如果加载界面之后 还要做点什么的话，界面只能加载静态的，就我们画好的界面。
    // 动态的加载，比如加载植物列表，因为植物列表(保存在一个地方）
    public void initialize(URL location, ResourceBundle resources) {


        Set keys = MainAgenda.listeDetail.keySet();
        System.out.print(keys.toString()+"zheshi keys");
        for (Object key : keys) {
            afficherUnePlante((PlanteDetail) MainAgenda.listeDetail.get(key));
        }
        System.out.println("\n----------------------");

    }
}
