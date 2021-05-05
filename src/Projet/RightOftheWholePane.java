package Projet;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class RightOftheWholePane extends VBox{
    public Label title;
    public TextArea logArea;
    public String url;

    //构造方法，以给定年月日设置右边的文本区域
    public RightOftheWholePane(int year,int month,int day)
    {
        title=new Label();
        logArea=new TextArea();
        logArea.setWrapText(true);
        title.setAlignment(Pos.CENTER);
        logArea.setPrefColumnCount(9);
        logArea.setPrefRowCount(9);

        String s=String.format("%04d%02d%02d",year,month,day);
        this.url=s+".txt";
        setTitle(day + "/" + month + "/" + year);
        title.setStyle("-fx-pref-width: 350px;-fx-pref-height: 20px;-fx-background-color:pink");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD,15));
        title.setTextFill(Color.BLUE);
        setAlignment(Pos.CENTER);
        ScrollPane scrollPane=new ScrollPane(logArea);//支持滚动

        AnchorPane plantPane = new AnchorPane();
        Label titre = new Label("Consultez votre liste de plantes");
        titre.setFont(Font.font ("Berlin Sans FB", 20));
        titre.setLayoutX(50);
        titre.setLayoutY(40);
        Button consulter =new Button("Liste de Plante");
        consulter.setLayoutX(120);
        consulter.setLayoutY(145);


        //<Label layoutX="80.0" layoutY="43.0" prefHeight="37.0" prefWidth="422.0" text="Consultez votre liste de plantes">
        //         <font>
        //            <Font name="Berlin Sans FB" size="33.0" />
        //         </font>
        //      </Label>
//                <Button layoutX="132.0" layoutY="145.0" mnemonicParsing="false" text="Liste de plante " />
//      <Button layoutX="320.0" layoutY="145.0" mnemonicParsing="false" text="Ajouter Une Plante" />
        plantPane.getChildren().addAll(consulter,titre);
        plantPane.setLayoutY(80);
        getChildren().addAll(title,logArea,plantPane);
        consulter.setOnAction(event -> {
            Parent root = null;
            try {
                Stage primary =MainAgenda.primary;
                root = FXMLLoader.load(getClass().getResource("../View/planteListe.fxml"));
                primary.setTitle("Agenda Etudiant Jardinage - Liste de plantes");
                primary.setScene(new Scene(root,800,600));
                primary.centerOnScreen();
                primary.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    //set
    public void setTitle(String s) {title.setText(s);}
    public void setTextArea(String s) {logArea.setText(s);}
    public void setURL(String s) {this.url=s;}
    //get
    public String getTitle() {return title.getText();}
    public String getTextArea() {return logArea.getText();}
    public String getURL() {return this.url;}
}
