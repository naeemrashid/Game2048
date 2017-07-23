package sample;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.Array;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private BorderPane borderPane;

    @FXML
    private GridPane gridPane;

    public void initializePane(){
//        Random r = new Random();
        for(int i=0;i<gridPane.getRowConstraints().size();i++){
            for(int j=0;j<gridPane.getColumnConstraints().size();j++) {
                JFXButton button = new JFXButton();
//                button.setStyle("-fx-background-color : rgb("+r.nextInt(255)+","+r.nextInt(255)+","+r.nextInt(255)+")");
                button.getStyleClass().add("color8");
                gridPane.add(button,i,j);

            }

        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridPane.hgapProperty().setValue(10);
        gridPane.vgapProperty().setValue(10);
        this.initializePane();
        gridPane.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode().equals(KeyCode.LEFT)){
                System.out.println("Left Arrow");
            }else if(event.getCode().equals(KeyCode.UP)){
                System.out.println("up Arrow");
            }else if(event.getCode().equals(KeyCode.RIGHT)){
                System.out.println("right Arrow");
            }else if (event.getCode().equals(KeyCode.DOWN)){
                System.out.println("down Arrow");
            }
        });


    }

}
