package Control;

import Model.PlanteDetail;
import Projet.MainAgenda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModifierNote implements Initializable {
    @FXML
    private Button retourBut;

    @FXML
    private TextField PHSuivi;

    @FXML
    private TextArea noteSuivi;

    @FXML
    private TextField hauteurSuivi;

    @FXML
    private Button Modifier;

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
    void Modifier(ActionEvent event) throws IOException {
        int i =MainAgenda.index;
        PlanteDetail planteDetail = MainAgenda.planteDetail;

        dateSuivi.setValue(dateSuivi.getConverter().fromString(dateSuivi.getEditor().getText()));

        String date =dateSuivi.getValue().toString();
        planteDetail.setDateSuivi(i,date);

        double hauteur =Double.valueOf(hauteurSuivi.getText());
        planteDetail.setHauteur(i,hauteur);

        double ph=Double.valueOf(PHSuivi.getText());
        planteDetail.setPh(i,ph);

        String note=noteSuivi.getText();
        planteDetail.setNotes(i,note);

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
                System.out.println("文件保存完成!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (this.file != null) {
            try {
//                Image image = new Image("file:"+this.file.getPath());
//                planteDetail.setImagesList(i,image);

                planteDetail.setImagesList(i,"file:"+this.file.getPath());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            File file = new File("src/ImagePlante/noPhoto.jpg");
            String string = file.toURI().toString();
            Image image = new Image(string);
            System.out.println("图片设置成功");
            planteDetail.setImagesList(i,string);

//            planteDetail.setImagesList(i,image);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int i = MainAgenda.index;
        PlanteDetail p =MainAgenda.planteDetail;
        if(p.getDateSuivi().get(i) != null) {
            String datetest = "" + p.getDateSuivi().get(i);
            dateSuivi.setValue(LocalDate.parse(datetest));
        }
        hauteurSuivi.setText(p.getHauteur().get(i).toString());
        PHSuivi.setText(p.getPh().get(i).toString());
        noteSuivi.setText(p.getNotes().get(i));
    }
}
