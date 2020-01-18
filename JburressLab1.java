/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jburresslab1;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author John
 */
public class JburressLab1 extends Application implements ChangeListener<String>{
    
     private Label aLabel;
     private ImageView imageView;
    
    @Override
    public void start(Stage primaryStage) {
        
        imageView = new ImageView(new Image(this.getClass().getResourceAsStream("/IMG/logo.png")));
        
        BorderPane root = new BorderPane();
        
        root.setCenter(imageView);
      
        root.setTop(buildMenuBar());
        aLabel = new Label("John Burress");
        ToolBar toolBar = new ToolBar(aLabel);
        root.setBottom(toolBar);
        Scene scene = new Scene(root);
        ArrayList<String> aList = new ArrayList<>();
        aList.add("First Album");
        aList.add("Cindy");
        aList.add("Fred");
        aList.add("Kate");
        aList.add("Keith");
        aList.add("Matt");
        aList.add("Rickey");

        ListView<String> list = new ListView<>();
        root.setLeft(list);
        list.setItems(FXCollections.observableArrayList(aList));
        list.getSelectionModel().selectedItemProperty().addListener(this);
        
        list.setPrefWidth(computeStringWidth("First Album") * 2);
        
        primaryStage.setTitle("John Burress Lab1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void setStatus(String aString)
    {
        aLabel.setText(aString);
    }
    
    private void onAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("John Burress, CSCD 370 Lab 1 Winter 2020");
        alert.showAndWait();
    }
    
    public MenuBar buildMenuBar()
    {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("_File");
        MenuItem quitMenuItem = new MenuItem("_Quit");
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q,KeyCombination.CONTROL_DOWN));
        quitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().add(quitMenuItem);
        
        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);
        
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        
        return menuBar;
    }
    
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
    {
       String path;
       
       if(!newValue.equalsIgnoreCase("first album"))//is a member of band
       {
           path = "/IMG/" + newValue.toLowerCase() + ".png";
           if(path.contains("cindy"))
               setStatus("Cindy Wilson (Percussion since 1976)");
           else if(path.contains("fred"))
               setStatus("Fred Schneider (Vocals, Cowbell,since 1976)");
           else if(path.contains("kate"))
               setStatus("Kate Pierson (Organ since 1976)");
           else if(path.contains("keith"))
               setStatus("Keith Strickland (Drums, Guitar, since 1976)");
           else if(path.contains("matt"))
               setStatus("Matt Flynn (Touring, Drums, prior to 2004)");
           else if(path.contains("rickey"))
               setStatus("Ricky Wilson, deceased (Bass, 1976-1985)");
           else
               setStatus("Not Found");
       }
       else//is first album
       {
           path = "/IMG/logo.png";
           setStatus("First Album, 1979");
       }
       imageView.setImage((new Image(this.getClass().getResourceAsStream(path))));
       
    }
    
    private double computeStringWidth(String s)
    {
        final Text text = new Text(s);
        return text.getLayoutBounds().getWidth();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }

    
    
}
