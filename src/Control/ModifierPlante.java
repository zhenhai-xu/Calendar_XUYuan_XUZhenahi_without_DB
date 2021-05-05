package Control;

import Model.PlanteDetail;
import Projet.MainAgenda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ModifierPlante implements Initializable {

    @FXML
    private Button modifierBut;

    @FXML
    private DatePicker dateDePla;

    @FXML
    private TextArea noteText;

    @FXML
    private AnchorPane ajoutePLPane;

    @FXML
    private Button retourBut;

    @FXML
    private DatePicker dateDeArr;

    @FXML
    private Hyperlink choirsirPhoto;

    @FXML
    private DatePicker dateDeRec;

    @FXML
    private DatePicker dateDeEnt;

    @FXML
    private DatePicker dateDeRem;

    @FXML
    private DatePicker dateDeCou;

    @FXML
    private TextField nomDePlante;
    @FXML
    private File file;

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
    void modifierBut(ActionEvent event) throws IOException {
        String nom = nomDePlante.getText();

        String note = noteText.getText();

        Map date = new HashMap<String ,String>();

        dateDePla.setValue(dateDePla.getConverter()
                .fromString(dateDePla.getEditor().getText()));
        dateDeRem.setValue(dateDeRem.getConverter()
                .fromString(dateDeRem.getEditor().getText()));
        dateDeArr.setValue(dateDeArr.getConverter()
                .fromString(dateDeArr.getEditor().getText()));
        dateDeEnt.setValue(dateDeEnt.getConverter()
                .fromString(dateDeEnt.getEditor().getText()));
        dateDeCou.setValue(dateDeCou.getConverter()
                .fromString(dateDeCou.getEditor().getText()));
        dateDeRec.setValue(dateDeRec.getConverter()
                .fromString(dateDeRec.getEditor().getText()));

        if(dateDePla.getValue()!=null) {
            String datePla = dateDePla.getValue().toString();
            date.put("datePlantation", datePla);
        }else {
            date.put("datePlantation", null);
        }

        if(dateDeRem.getValue()!=null) {
            String dateRem = dateDeRem.getValue().toString();
            date.put("dateRempotage", dateRem);
        }else {
            date.put("dateRempotage", null);
        }

        if(dateDeArr.getValue()!=null) {
            String dateArr = dateDeArr.getValue().toString();
            date.put("dateArronsage", dateArr);
        }else {
            date.put("dateArronsage", null);
        }

        if(dateDeEnt.getValue()!=null) {
            String dateEnt = dateDeEnt.getValue().toString();
            date.put("dateEntretien", dateEnt);
        }else {
            date.put("dateEntretien", null);
        }

        if(dateDeCou.getValue()!=null) {
            String dateCou = dateDeCou.getValue().toString();
            date.put("dateCoupe", dateCou);
        }else {
            date.put("dateCoupe", null);
        }

        if(dateDeRec.getValue()!=null) {
            String dateRec = dateDeRec.getValue().toString();
            date.put("dateRecolte", dateRec);}
        else {
            date.put("dateRecolte", null);
        }



        PlanteDetail plante = new PlanteDetail(nom, date, note);

        if(this.file!=null){
            try {
                FileInputStream fis = new FileInputStream(this.file.getPath());
                DataInputStream dis = new DataInputStream(fis);
                FileOutputStream fos = new FileOutputStream("src/ImagePlante/"+nom+".jpg");
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
                String s ="file:src/ImagePlante/"+nom+".jpg";
//                Image image = new Image("file:"+this.file.getPath());
                Image image = new Image(s);
                plante.setImage(s);

//                plante.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            plante.setImage(MainAgenda.planteDetail.getImage());
        }

        MainAgenda.listeDetail.remove(MainAgenda.planteDetail.getNom());
        MainAgenda.listeDetail.put(plante.getNom(),plante);

        System.out.println("添加成功");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/planteListe.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = MainAgenda.primary;
//        Stage stage =new Stage();
        //set what you want on your stage


        stage.setTitle("Agenda Etudiant Jardinage - ajouter une plante");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
        //在添加植物中添加照片
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(MainAgenda.planteDetail.getDate().get("datePla")!=null) {
            String datetest = "" + MainAgenda.planteDetail.getDate().get("datePla");
            dateDePla.setValue(LocalDate.parse(datetest));
        }
        if(MainAgenda.planteDetail.getDate().get("dateRem")!=null) {
            String datetest = "" + MainAgenda.planteDetail.getDate().get("dateRem");
            dateDeRem.setValue(LocalDate.parse(datetest));
        }
        if(MainAgenda.planteDetail.getDate().get("dateArr")!=null) {
            String datetest = "" + MainAgenda.planteDetail.getDate().get("dateArr");
            dateDeArr.setValue(LocalDate.parse(datetest));
        }
        if(MainAgenda.planteDetail.getDate().get("dateEnt")!=null) {
            String datetest = "" + MainAgenda.planteDetail.getDate().get("dateEnt");
            dateDeEnt.setValue(LocalDate.parse(datetest));
        }
        if(MainAgenda.planteDetail.getDate().get("dateCou")!=null) {
            String datetest = "" + MainAgenda.planteDetail.getDate().get("dateCou");
            dateDeCou.setValue(LocalDate.parse(datetest));
        }
        if(MainAgenda.planteDetail.getDate().get("dateRec")!=null) {
            String datetest = "" + MainAgenda.planteDetail.getDate().get("dateRec");
            dateDeRec.setValue(LocalDate.parse(datetest));
        }
        if(MainAgenda.planteDetail.getNom()!=null) {
        String nom = MainAgenda.planteDetail.getNom();
        nomDePlante.setText(nom);
        }
    }
}

