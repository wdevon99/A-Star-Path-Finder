package com.algocw.gui;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainScreen extends Application{
    //tjis variable will store the main pane in which all other components will be set to
    private static  BorderPane mainBorderPane;
    //this variable will store the main grid pane object
    private static GridPane mainGridPane;


    @Override
    public void start(Stage primaryStage) throws Exception {

        //setting the title
        primaryStage.setTitle("Enjoy your game");

        //instantiating BorderPane
        mainBorderPane= new BorderPane();
        //setting the style class
        mainBorderPane.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());

        //creating the components
        Label lblHeading= new Label("Path Finder - Devon Wijeshinge");
        //setting the main 20x20 grid pane
        mainGridPane=getAnGrid(20,20);

        mainBorderPane.setTop(lblHeading);
        mainBorderPane.setCenter(mainGridPane);




        //creating the scene
        Scene scene = new Scene(mainBorderPane, 602,700);
        primaryStage.setScene(scene);
        primaryStage.show();


    }



    //this method will return a grid pane @prams = rows :umber of rows of the grid | columns :number of columns of the grid
    private GridPane getAnGrid(int rows ,int columns){

        GridPane grid;

        //instantiating a grid pane
        grid = new GridPane();

        //creating the cols
        for(int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(30);
            grid.getColumnConstraints().add(column);
        }

        //creating the rows
        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(30);
            grid.getRowConstraints().add(row);
        }

        //setting the styles for the grid
        grid.getStyleClass().getClass().;

        return grid;
    }

}
