package sample;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private BorderPane borderPane;

    @FXML
    private GridPane gridPane;

    public void initializePane(){
        for(int i=0;i<gridPane.getRowConstraints().size();i++){
            for(int j=0;j<gridPane.getColumnConstraints().size();j++) {
                JFXButton button = new JFXButton();
                gridPane.add(button,i,j);

            }

        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridPane.hgapProperty().setValue(10);
        gridPane.vgapProperty().setValue(10);
        this.initializePane();


    }

}
