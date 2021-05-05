package Control;

import Model.PlanteDetail;
import Projet.MainAgenda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;

public class AjouterNote {

    @FXML
    private Button retourBut;

    @FXML
    private Button ajouterBut;

    @FXML
    private TextField PHSuivi;

    @FXML
    private TextArea noteSuivi;

    @FXML
    private TextField hauteurSuivi;

    @FXML
    private DatePicker dateSuivi;

    @FXML
    private Hyperlink choisirPhoto;


    private File file;

    @FXML
    void choisirPhoto(ActionEvent event) {
        FileChooser fileChooser1 = new FileChooser();
        Stage cPhoto = new Stage();

        fileChooser1.setTitle("Open Resource File");
        File file = fileChooser1.showOpenDialog(cPhoto);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG图片","*.jpg"),
//                new FileChooser.ExtensionFilter("JPEG图片","*.jpeg"),
//                new FileChooser.ExtensionFilter("PNG图片","*.png"),
                new FileChooser.ExtensionFilter("所有文件","*.*")
        );
        this.file=file;
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Details.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = MainAgenda.primary;
//        Stage stage =new Stage();
//        //set what you want on your stage
//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Agenda Etudiant Jardinage - ajouter des mesures");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void ajouterNote(ActionEvent event) throws IOException {
        PlanteDetail planteDetail = MainAgenda.planteDetail;
        planteDetail.addIndex();
        dateSuivi.setValue(dateSuivi.getConverter().fromString(dateSuivi.getEditor().getText()));
        String date =dateSuivi.getValue().toString();
        planteDetail.addDateSuivi(date);

        if(hauteurSuivi.getText().length() != 0) {
            double hauteur = Double.valueOf(hauteurSuivi.getText());
            planteDetail.addHauteur(hauteur);
        }else{
            planteDetail.addHauteur(0.0);
        }

        if(PHSuivi.getText().length() != 0) {
        double ph=Double.valueOf(PHSuivi.getText());
        planteDetail.addPh(ph);
        } else{
            planteDetail.addPh(0.0);
        }

        String note=noteSuivi.getText();
        planteDetail.addNotes(note);

        if(this.file!=null){
            try {
                FileInputStream fis = new FileInputStream(this.file.getPath());
                DataInputStream dis = new DataInputStream(fis);
                FileOutputStream fos = new FileOutputStream("src/ImagePlante/"+planteDetail.getNom()+planteDetail.getIndex()+".jpg");
                DataOutputStream dos = new DataOutputStream(fos);
                byte[] bs = new byte[2048];
                int length=-1;
                while((length = dis.read(bs))!=-1){
                    dos.write(bs,0,length);
                }
                dis.close();
                dos.flush();
                fis.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (this.file != null) {
            try {
                //Image image = new Image("file:"+this.file.getPath());
              //planteDetail.addImagesList(image);
                planteDetail.addImagesList("file:"+this.file.getPath());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            File file = new File("src\\ImagePlante\\noPhoto.jpg");
            String string = file.toURI().toString();
            Image image = new Image(string);
            System.out.println("图片设置成功");
//            planteDetail.addImagesList(image);
            planteDetail.addImagesList(string);
        }





        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Details.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = MainAgenda.primary;
//        Stage stage =new Stage();
        //set what you want on your stage
//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Agenda Etudiant Jardinage - ajouter des mesures");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

}

