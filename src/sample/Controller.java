package sample;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.Array;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class Controller implements Initializable{

    @FXML
    private BorderPane borderPane;

    @FXML
    private GridPane gridPane;
    private int colSize;
    private int rowSize;

    public void initializePane(){
        Random r = new Random();
        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++) {
                JFXButton button = new JFXButton();
//                button.setStyle("-fx-background-color : rgb("+r.nextInt(255)+","+r.nextInt(255)+","+r.nextInt(255)+")");
//                button.getStyleClass().add("color4");
                gridPane.add(button,j,i);

            }

        }
        Button button=(Button)getNodeFromGridPane(gridPane,getRandom(),getRandom());
        button.setText("2");
        button=(Button)getNodeFromGridPane(gridPane,getRandom(),getRandom());
        button.setText("2");

    }
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
    public int getRandom(){
        return ThreadLocalRandom.current().nextInt(0,  rowSize);
    }
    public void moveLeft(){
        for(int row=0;row<rowSize;row++){
            for(int col=0;col<colSize;col++){
                Node node = getNodeFromGridPane(gridPane,col,row);
                Button nodeBtn =(Button)node;
                int colBack = col-1;
                if (colBack == -1){
                    colBack=colSize-1;
                }
//                System.out.println("column: "+colBack+" row: "+row);
                Button next =(Button) getNodeFromGridPane(gridPane,colBack,row);
               if (next!=null && next.getText()!=""){
                   System.out.println("hell");

               }else{
                   System.out.println("need space man");
               }
                gridPane.getChildren().remove(node);
                if (((Button) node).getText()!=""){
                    node.getStyleClass().add("color"+((Button) node).getText());
                }
                gridPane.add(node,colBack,row);


            }
        }

    }
    public void handleEvent(KeyEvent event){
        if(event.getCode().equals(KeyCode.LEFT)){
//            System.out.println("Left Arrow");
            moveLeft();
        }else if(event.getCode().equals(KeyCode.UP)){
            System.out.println("up Arrow");
        }else if(event.getCode().equals(KeyCode.RIGHT)){
            System.out.println("right Arrow");
        }else if (event.getCode().equals(KeyCode.DOWN)){
            System.out.println("down Arrow");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setColSize(gridPane.getColumnConstraints().size());
        setRowSize(gridPane.getRowConstraints().size());
        gridPane.hgapProperty().setValue(10);
        gridPane.vgapProperty().setValue(10);
        this.initializePane();
        gridPane.addEventHandler(KeyEvent.KEY_PRESSED, event -> handleEvent(event));



    }
    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }
}
