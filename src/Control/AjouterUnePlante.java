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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class AjouterUnePlante implements Initializable {

    @FXML
    private Hyperlink choirsirPhoto;

    @FXML
    private TextField nomDePlante;

    @FXML
    private AnchorPane ajoutePLPane;
    @FXML
    private TextArea noteText;
    @FXML
    private Button modifierBut;
    @FXML
    private Button retourBut;
    @FXML
    private DatePicker dateDeRem;
    @FXML
    private DatePicker dateDePla;
    @FXML
    private DatePicker dateDeArr;
    @FXML
    private DatePicker dateDeEnt;
    @FXML
    private DatePicker dateDeCou;
    @FXML
    private DatePicker dateDeRec;
    @FXML
    private DatePicker dateDeMes;
    @FXML
    private TextField PH;
    @FXML
    private TextField hauteur;

    private File file;


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

    @FXML
    //添加植物的窗口里的返回键的事件
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
        //添加植物到窗口里，添加键的事件
    void AjouterBut(ActionEvent event) throws IOException {
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

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.file != null) {
            try {
                //Image image = new Image("file:"+this.file.getPath());
                //plante.setImage(image);

                plante.setImage("file:"+this.file.getPath());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        MainAgenda.listeDetail.put(plante.getNom(),plante);

//        try
//        {
//            FileOutputStream fos =
//                    new FileOutputStream("hashmap.ser");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(MainAgenda.listeDetail);
//            oos.close();
//            fos.close();
//            System.out.printf("Serialized HashMap data is saved in hashmap.ser");
//        }catch(IOException ioe)
//        {
//            ioe.printStackTrace();
//        }


        if(hauteur.getText().length() != 0) {
            plante.addHauteur(Double.valueOf(hauteur.getText()));
        }else {
            plante.addHauteur(0.0);
        }
        if(PH.getText().length() != 0) {
        plante.addPh(Double.valueOf(PH.getText()));
        } else{
            plante.addPh(0.0);
        }
        plante.addImagesList(plante.getImage());

        dateDeMes.setValue(dateDeMes.getConverter()
                .fromString(dateDeMes.getEditor().getText()));
        if(dateDeMes.getValue() != null) {
            plante.addDateSuivi(dateDeMes.getValue().toString());

        }else{
            plante.addDateSuivi(LocalDate.now().toString());
        }
            plante.addNotes(plante.getNote());

        Set keys = plante.getDate().keySet();
        String dateEnreg[] = new String[3];
        for (Object key : keys) {
            String s = ((String) plante.getDate().get(key));
            int i = 0;
            if(s != null){
            for (String retval: s.split("-")){
                dateEnreg[i] = retval;
                System.out.println(retval+" fenge de dongxi");
                i++;
            }
            int year = Integer.valueOf(dateEnreg[0]);
            int month = Integer.valueOf(dateEnreg[1]);
            int day = Integer.valueOf(dateEnreg[2]);
            String data=String.format("%04d%02d%02d",year,month,day);
            data+=".txt";
            try {
                File file=new File(data);
                FileWriter fileWriter = new FileWriter(file,true);
                fileWriter.write(plante.getNom()+" "+key.toString()+"\r\n");
                fileWriter.close();

            }
            catch(Exception ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Enregistrer des notes");
                alert.setContentText("Échec de l'enregistrement de la note");
                alert.showAndWait();
            }}
        }


        System.out.println("添加成功");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/planteListe.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = MainAgenda.primary;
//        Stage stage =new Stage();
        //set what you want on your stage

        stage.setTitle("Agenda Etudiant Jardinage - Liste des plantes");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }
    @Override
    //初始化窗口，如果加载界面之后 还要做点什么的话
    public void initialize(URL location, ResourceBundle resources) {


    }
}