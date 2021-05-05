package Projet;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class BottomOftheWholePane extends HBox
{
    public Button save;
    public Button delete;
    RightOftheWholePane log;//set
    public BottomOftheWholePane(RightOftheWholePane log) {
        this.log=log;
        Button btLogSave=new Button("Enregistrer");
        Button btLogDelete=new Button("Supprimer la note");


        btLogSave.setOnAction(e -> {
            try {
                File file=new File(log.getURL());
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(log.getTextArea());
                fileWriter.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Enregistrer des notes");
                alert.setContentText("Notes enregistrées avec succès");
                alert.showAndWait();
            }
            catch(Exception ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Enregistrer des notes");
                alert.setContentText("Échec de l'enregistrement de la note");
                alert.showAndWait();
            }
        });
        btLogDelete.setOnAction(e -> {
            log.setTextArea("");
            try {
                String s = log.getURL();
                File f = new File(s);
                f.delete();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Supprimer le note");
                alert.setContentText("Supprimer le note avec succès");
                alert.showAndWait();
            }
            catch(Exception ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("删除日志");
                alert.setContentText("删除日志失败˙");
                alert.showAndWait();
            }
        });

//        btLogRead.setOnAction(e -> {
//            try {
//                Scanner input = new Scanner(new File(log.getURL()));
//                String s = "";
//                while(input.hasNext())
//                    s += input.nextLine();
//                log.setTextArea(s);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Lire les notes");
//                alert.setContentText("Lire les notes avec succès");
//                alert.showAndWait();
//                input.close();
//            }
//            catch(Exception ex) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Lire les notes");
//                alert.setContentText("La note n'existe pas, n'a pas pu lire la note！");
//                alert.showAndWait();
//            }
//        });
        setAlignment(Pos.CENTER);
        setSpacing(10);
        getChildren().addAll(btLogSave,btLogDelete);
    }


}
