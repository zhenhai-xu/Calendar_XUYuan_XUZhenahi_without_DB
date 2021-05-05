package Control;

import Model.PlanteDetail;
import Projet.MainAgenda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Details implements Initializable {

    @FXML
    private Label labelEnt;

    @FXML
    private AnchorPane graphePane;

    @FXML
    private Label labelCou;

    @FXML
    private Button retourBut;

    @FXML
    private Label labelRec;

    @FXML
    private Button ajoutSuiviBut;

    @FXML
    private Label labelNom;

    @FXML
    private Label labelRem;

    @FXML
    private AnchorPane recordPane;

    @FXML
    private Label labelPlan;

    @FXML
    private ImageView imageView;

    @FXML
    private Label labelArr;

    @FXML
    private AnchorPane detailsPane;
    private double paneY;

    public void setImageView(PlanteDetail p) {
        ImageView imageView = new ImageView();
        imageView.setLayoutX(30);
        imageView.setLayoutY(40);
        imageView.setFitWidth(140);
        imageView.setFitHeight(160);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(false);

        Image image = new Image(p.getImage());
        imageView.setImage(image);
//        imageView.setImage(p.getImage());

        detailsPane.getChildren().add(imageView);
    }

    @FXML
    void retourBut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/planteListe.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = MainAgenda.primary;
//        Stage stage =new Stage();

        stage.setTitle("Agenda Etudiant Jardinage - ajouter une plante");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void ajoutSuiviBut(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/ajouterNote.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = MainAgenda.primary;
//        Stage stage =new Stage();
        //set what you want on your stage

        stage.setTitle("Agenda Etudiant Jardinage - ajouter une note");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

    public void  afficherPlanteSuivi(PlanteDetail planteDetail,int i){
        AnchorPane panePlante = new AnchorPane();
        panePlante.setId(planteDetail.getNom());
        panePlante.prefHeight(180.0);
        panePlante.prefWidth(799);
        panePlante.minHeight(0);
        panePlante.minWidth(0);
        panePlante.setStyle("-fx-background-color: #E4FFA8");
        panePlante.setLayoutX(1.0);
        panePlante.setLayoutY(paneY);

        Button button2 = new Button("Modifier");
        Button button3 = new Button("Supprimer");


        button2.setId("modifierBut1");
        button3.setId("suprimerBut1");

        button2.setLayoutX(410);
        button2.setLayoutY(80);
        button3.setLayoutX(400);
        button3.setLayoutY(120);
        button2.setPrefWidth(90.0);
        button3.setPrefWidth(100.0);



        Label label1 = new Label("date : " + planteDetail.getDateSuivi().get(i));
        Label label2 = new Label("hauteur : " + planteDetail.getHauteur().get(i));
        Label label3 = new Label("PH : " + planteDetail.getPh().get(i));
        Label label5 = new Label("note : " + planteDetail.getNotes().get(i));

        label1.setLayoutX(180);
        label1.setLayoutY(40);
        label2.setLayoutX(180);
        label2.setLayoutY(70);
        label3.setLayoutX(180);
        label3.setLayoutY(100);
        label5.setLayoutX(180);
        label5.setLayoutY(130);

        ImageView imageView = new ImageView();
        imageView.setLayoutX(15);
        imageView.setLayoutY(15);
        button2.setStyle("-fx-background-color: #5DA38B");
        button3.setStyle("-fx-background-color: #5DA38B");
        button2.setOnAction(e -> {
            MainAgenda.index=i;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/modifierNote.fxml"));
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

        //删除的时候重载页面就好了
        button3.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("警告");
            alert.headerTextProperty().set("您真的要删除吗？");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                MainAgenda.planteDetail.getPh().remove(i);
                MainAgenda.planteDetail.getDateSuivi().remove(i);
                MainAgenda.planteDetail.getHauteur().remove(i);
                MainAgenda.planteDetail.getNotes().remove(i);
                MainAgenda.planteDetail.getImagesList().remove(i);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Details.fxml"));
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

        if(planteDetail.getImagesList().get(i)!=null){
            Image image = new Image(planteDetail.getImagesList().get(i));
            imageView.setImage(image);
//            imageView.setImage(planteDetail.getImagesList().get(i));
            imageView.setFitWidth(130);
            imageView.setFitHeight(140);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(false);

        }else {
            File file = new File("src/ImagePlante/noPhoto.jpg");
            String string = file.toURI().toString();
            Image image = new Image(string);
            imageView.setImage(image);
            System.out.println("图片设置成功");
            imageView.setFitWidth(130);
            imageView.setFitHeight(140);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(false);

        }
        this.paneY+=150;
        recordPane.setPrefHeight(recordPane.getPrefHeight()+150);

        panePlante.getChildren().addAll(button2,button3,label1,label2,label3,label5,imageView);

        recordPane.getChildren().add(panePlante);

    }
    public void afficherGrapheHauteur(){

        NumberAxis xAxis = new NumberAxis(1, 15, 3);
        xAxis.setLabel("Nombre de mesures");
        NumberAxis yAxis = new NumberAxis(0, 200, 40);
        yAxis.setLabel("Hauteur");
        LineChart linechart = new LineChart(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        series.setName("Hauteur de la plante");

        for(int i = 0 ; i <MainAgenda.planteDetail.getHauteur().size();i++ ) {
            series.getData().add(new XYChart.Data(i+1,MainAgenda.planteDetail.getHauteur().get(i)));
        }

        linechart.getData().add(series);
        linechart.setPrefWidth(500);
        linechart.setPrefHeight(250);
        linechart.setLayoutX(0);
        linechart.setLayoutY(0);
        graphePane.getChildren().add(linechart);

    }
    public void afficherGraphePH(){

        NumberAxis xAxis = new NumberAxis(1, 15, 3);
        xAxis.setLabel("Nombre de mesures");
        NumberAxis yAxis = new NumberAxis(-2, 15, 2);
        yAxis.setLabel("PH ");
        LineChart linechart = new LineChart(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        series.setName("changement de PH");

        for(int i = 0 ; i <MainAgenda.planteDetail.getPh().size();i++ ) {
            series.getData().add(new XYChart.Data(i+1,MainAgenda.planteDetail.getPh().get(i)));
        }

        linechart.getData().add(series);
        linechart.setPrefWidth(500);
        linechart.setPrefHeight(250);
        linechart.setLayoutX(0);
        linechart.setLayoutY(270);
        graphePane.getChildren().add(linechart);

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PlanteDetail planteDetail = MainAgenda.planteDetail;
        labelNom.setText(planteDetail.getNom());
        labelPlan.setText("date de plantation : "+planteDetail.getDate().get("datePlantation"));
        labelRem.setText("date de rempotage : "+planteDetail.getDate().get("dateRempotage"));
        labelArr.setText("date de arronsage : "+planteDetail.getDate().get("dateArronsage"));
        labelEnt.setText("date de entretien : "+planteDetail.getDate().get("dateEntretien"));
        labelCou.setText("date de coupe : "+planteDetail.getDate().get("dateCoupe"));
        labelRec.setText("date de recolte : "+planteDetail.getDate().get("dateRecolte"));
        setImageView(planteDetail);

        for(int i = 0; i<planteDetail.getHauteur().size();i++){
            afficherPlanteSuivi(planteDetail,i);
        }
        afficherGrapheHauteur();
        afficherGraphePH();
    }
}
