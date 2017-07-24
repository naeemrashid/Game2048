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
import java.util.ArrayList;
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
    private Node[][] list = new Node[16][2];
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
//        Button button=(Button)getNodeFromGridPane(gridPane,getRandom(),getRandom());
//        button.setText("2");
//        button=(Button)getNodeFromGridPane(gridPane,getRandom(),getRandom());
//        button.setText("2");
        Button button=(Button)getNodeFromGridPane(gridPane,2,2);
        button.setText("2");
        button=(Button)getNodeFromGridPane(gridPane,3,2);
        button.setText("2");

    }
    public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
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
        int index=0;
        for(int col=1;col<colSize;col++){
            for(int row=0;row<rowSize;row++){
                Node node = getNodeFromGridPane(gridPane,col,row);
                int colBack = col-1;
                Node next = getNodeFromGridPane(gridPane,colBack,row);
                node.getStyleClass().clear();
                node.getStyleClass().add("button");
                if (((Button) node).getText()!=""){
                    list[index][0]=node;
                    list[index][1]=next;
                    index++;
                }
            }
            move();
        }

    }
    public void move(){
        for (int index=0;index<list.length;index++){
            if (list[index][0]==null){
                break;
            }else {
                Node node=list[index][0];
                Node next=list[index][1];
//                next.getStyleClass().removeAll("color2");
                if (((Button)next).getText()==""){
                    next.getStyleClass().add("color"+((Button) node).getText());
                    ((Button)next).setText(((Button) node).getText());
                    ((Button) node).setText("");
                }else if(((Button)next).getText().equals(((Button)node).getText())) {
                    int num=Integer.parseInt(((Button) node).getText());
                    ((Button)next).setText(""+num*2);
                    ((Button) node).setText("");
                    node.getStyleClass().clear();
                    node.getStyleClass().add("button");
                }
            }
        }
    }
    public void handleEvent(KeyEvent event){
        if(event.getCode().equals(KeyCode.LEFT)){
//            System.out.println("Left Arrow");
            moveLeft();
//            testing();
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
