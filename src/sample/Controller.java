package sample;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Controller implements Initializable{

    @FXML
    private BorderPane borderPane;

    @FXML
    private GridPane gridPane;
    @FXML
    private HBox box;
    @FXML
    private JFXButton restart;
    @FXML
    private Label score;

    private int colSize;
    private int rowSize;
    private static int scoreNum=0;
    private Node[][] list = new Node[16][2];
    private ArrayList<String> smileys = new ArrayList<>();
    private StackPane stackPane;
    private boolean dialogOpened = false;

    public void initializePane(){
//        Image image = new Image("resources/images/001-smile.png");
//        button.setGraphic(new ImageView(image));
//        button.setAlignment(Pos.TOP_LEFT);
        Random r = new Random();
        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++) {
                JFXButton button = new JFXButton();

                gridPane.add(button,j,i);

            }

        }
        Button button=(Button)getNodeFromGridPane(gridPane,getRandom(),getRandom());
        button.setText("2");
        button=(Button)getNodeFromGridPane(gridPane,getRandom(),getRandom());
        button.setText("2");
        color();
    }
    public void rollDice(){
        if (!checkSpace()){
            return;
        }
        while (true){
            Node node=getNodeFromGridPane(gridPane,getRandom(),getRandom());
            if (((Button)node).getText()==""){
                ((Button)node).setText("2");
                break;
            }
        }
    }

    public  boolean checkSpace(){
        for (Node node:gridPane.getChildren()) {
            if(((Button)node).getText()==""){
                return true;
            }
        }
        return false;

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
                if (((Button) node).getText()!=""){
                    list[index][0]=node;
                    list[index][1]=next;
                    index++;
                }
            }

        }
        move();

    }
    public void moveRight(){
        int index=0;
        for(int col=colSize-2;col>=0;col--){
            for(int row=0;row<rowSize;row++){
                Node node = getNodeFromGridPane(gridPane,col,row);
                int colBack = col+1;
                Node next = getNodeFromGridPane(gridPane,colBack,row);
                if (((Button) node).getText()!=""){
                    list[index][0]=node;
                    list[index][1]=next;
                    index++;
                }
            }

        }
        move();

    }
    public void moveUp(){
        int index=0;
        for(int row=1;row<rowSize;row++){
            for(int col=0;col<colSize;col++){
                Node node = getNodeFromGridPane(gridPane,col,row);
                int rowBack = row-1;
                Node next = getNodeFromGridPane(gridPane,col,rowBack);
                if (((Button) node).getText()!=""){
                    list[index][0]=node;
                    list[index][1]=next;
                    index++;
                }
            }

        }
        move();

    }
    public void moveDown(){
        int index=0;
        for(int row=rowSize-2;row>=0;row--){
            for(int col=0;col<colSize;col++){
                Node node = getNodeFromGridPane(gridPane,col,row);
                int rowBack = row+1;
                Node next = getNodeFromGridPane(gridPane,col,rowBack);
                if (((Button) node).getText()!=""){
                    list[index][0]=node;
                    list[index][1]=next;
                    index++;
                }
            }

        }
        move();

    }

    public void color(){
        for (Node node:gridPane.getChildren()){
            node.getStyleClass().clear();
            node.getStyleClass().add("button");
            if (((Button)node).getText()!="") {
                node.getStyleClass().add("color" + ((Button) node).getText());
            }

        }
    }
    public void move(){
        for (int index=0;index<list.length;index++){
            if (list[index][0]==null){
                break;
            }else {
                Node node=list[index][0];
                Node next=list[index][1];
                if (((Button)next).getText()==""){
                    ((Button)next).setText(((Button) node).getText());
                    ((Button) node).setText("");
                }else if(((Button)next).getText().equals(((Button)node).getText())) {
                    int num=Integer.parseInt(((Button) node).getText());
                    ((Button)next).setText(""+num*2);
                    scoreNum+=num;
                    score.setText(" Score: "+scoreNum);
                    ((Button) node).setText("");
                }
            }
        }
        color();
    }

    public void handleEvent(KeyEvent event){
        if(scoreNum==2048 || gameOver()){
            if(!dialogOpened) {
                showDialog("You Win !!", "Congratulation you have won the Game. would you like\n" +
                        "to play again or exit the Game.", stackPane);
                dialogOpened = true;
            }
            return;
        }
        if(event.getCode().equals(KeyCode.LEFT)){
            rollDice();
            moveLeft();
        }else if(event.getCode().equals(KeyCode.UP)){
            rollDice();
            moveUp();
        }else if(event.getCode().equals(KeyCode.RIGHT)){
            rollDice();
            moveRight();
        }else if (event.getCode().equals(KeyCode.DOWN)){
            rollDice();
            moveDown();
        }
    }
    public void handleEvent(MouseEvent event){
        start();
    }

    public void start(){
        gridPane.getChildren().remove(0,gridPane.getChildren().size());
        initializePane();
        scoreNum=0;
        score.setText(" Score: "+scoreNum);

    }


    /*
    * */


public void showDialog(String heading,String body,StackPane stackPane){
    JFXButton restart = new JFXButton("Restart");
    restart.setId("normal");
    JFXButton cancel = new JFXButton("Cancel");
    cancel.setId("normal");

    JFXDialogLayout content = new JFXDialogLayout();
    content.setHeading(new Text(heading));
    content.setBody(new Text(body));
    content.setActions(restart,cancel);

    JFXDialog dialog= new JFXDialog(stackPane,content, JFXDialog.DialogTransition.CENTER);
    dialog.show();
    cancel.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            dialog.close();
            System.exit(0);

        }
    });
    restart.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            dialog.close();
            dialogOpened=false;
            start();
        }
    });
}
public boolean gameOver(){
    if(!checkSpace()) {
        if (!dialogOpened){
            showDialog("Game Over","Would you like to try again !!!",stackPane);
            dialogOpened=true;
        }

        return true;
    }
    return false;
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        restart.setId("normal");
        score.setId("score");
        box.setSpacing(200);
        setColSize(gridPane.getColumnConstraints().size());
        setRowSize(gridPane.getRowConstraints().size());
        gridPane.hgapProperty().setValue(10);
        gridPane.vgapProperty().setValue(10);
        stackPane = new StackPane();
        stackPane.getChildren().add(gridPane);
        borderPane.setCenter(stackPane);
        start();
        restart.addEventHandler(MouseEvent.MOUSE_CLICKED ,event -> handleEvent(event));
        gridPane.addEventHandler(KeyEvent.KEY_PRESSED, event -> handleEvent(event));

    }
    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }
}
