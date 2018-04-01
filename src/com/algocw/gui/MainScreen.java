package com.algocw.gui;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainScreen extends Application{
    //tjis variable will store the main pane in which all other components will be set to
    private static  BorderPane mainBorderPane;
    //this variable will store the main grid pane object
    private static GridPane mainGridPane;
    //this variable will store the formHbox object
    private static VBox formPaneVBox;


    @Override
    public void start(Stage primaryStage) throws Exception {
        //setting the title
        primaryStage.setTitle("Devon Wijesinghe - CW");
        //getting the main scene
        Scene scene= getMainScene();
        //linking the main style sheet
        scene.getStylesheets().add("mainStyle.css");
        //setting the resizability as false
        primaryStage.setResizable(false);
        //seting the scene and showing it
        primaryStage.setScene(scene);

        primaryStage.show();

        resetGridColorFill(true);


    }


    /**
     *
     * @return : The main scene that will be set to the primaryStage
     */
    public Scene getMainScene(){

        //instantiating BorderPane
        mainBorderPane= new BorderPane();
        //setting the style class

        //creating the components
        Label lblHeading= new Label("Path Finder - Devon Wijesinghe");
        //setting the main 20x20 grid pane
        mainGridPane=getAnGrid(21,21);

        //setting style class
        lblHeading.getStyleClass().add("mainHeading");
        mainGridPane.getStyleClass().add("grid");

        //setting the form pane
        formPaneVBox=getFormPane();

        //aligning to center
        BorderPane.setAlignment(lblHeading, Pos.CENTER);
        BorderPane.setAlignment(mainGridPane, Pos.CENTER);
        BorderPane.setAlignment(formPaneVBox,Pos.CENTER);


        //adding the x axis numbers
        for(int i=1; i<21 ; i++){
            Label label= new Label(" x"+i);
            label.setTextFill(Color.BLACK);
            mainGridPane.add(label,i,0);

        }
        //adding the y axis numbers
        for(int i=1; i<21 ; i++){
            Label label= new Label(" y"+i);
            label.setTextFill(Color.BLACK);
            mainGridPane.add(label,0,i);

        }



        //adding the components to the main Border Pane
        mainBorderPane.setTop(getFormPane());
        mainBorderPane.setCenter(mainGridPane);
        mainBorderPane.setBottom(lblHeading);

        //creating the scene
        Scene scene = new Scene(mainBorderPane, 672,900);

        return scene;

    }

    /**
     *
     * This method created the form required to take coordinates of the start and end point and also the heuristic type
     * @return : a HBox containing the form
     */

    public VBox getFormPane(){

        VBox formPaneVBox = new VBox(30);
        HBox firstrow = new HBox(5);
        HBox secondRow = new HBox(5);
        HBox thirdRow = new HBox(5);

        //creating the form components
        //== row 1 ==
        Label lblSX=new Label("Start X :");
        TextField tfStartX = new TextField();
        Label lblSY=new Label("Start X :");
        TextField tfStartY = new TextField();
        Label lblEX=new Label("End X :");
        TextField tfEndX = new TextField();
        Label lblEY=new Label("End Y :");
        TextField tfEndY = new TextField();
        //== row 2 ==

        RadioButton rbManhattan= new RadioButton("Manhattan");
        RadioButton rbEuclidean= new RadioButton("Euclidean");
        RadioButton rbChebyshev= new RadioButton("Chebyshev");

        //== row 3 ==
        Button runBtn= new Button("RUN");

        //setting Style classes
        tfStartX.getStyleClass().add("textField");
        tfStartY.getStyleClass().add("textField");
        tfEndX.getStyleClass().add("textField");
        tfEndY.getStyleClass().add("textField");
        formPaneVBox.getStyleClass().add("formPane");


        //setting alignment to center
        formPaneVBox.setAlignment(Pos.CENTER);
        firstrow.setAlignment(Pos.CENTER);
        secondRow.setAlignment(Pos.CENTER);
        thirdRow.setAlignment(Pos.CENTER);

        //=======================================================
        //======  On click actions ======

        runBtn.setOnAction(e -> {
            resetGridColorFill(false);
            colorAnCell(1,3,Color.YELLOW);
            colorAnCell(1,4,Color.RED);
            colorAnCell(1,5,Color.RED);
            colorAnCell(1,6,Color.RED);
            colorAnCell(2,7,Color.RED);
            colorAnCell(2,8,Color.RED);
            colorAnCell(2,8,Color.RED);
            colorAnCell(3,8,Color.RED);
            colorAnCell(4,8,Color.RED);
            colorAnCell(5,8,Color.RED);
            colorAnCell(6,8,Color.RED);
            colorAnCell(7,8,Color.RED);
            colorAnCell(8,8,Color.RED);
            colorAnCell(9,9,Color.RED);
            colorAnCell(10,9,Color.RED);
            colorAnCell(11,9,Color.RED);
            colorAnCell(12,9,Color.RED);
            colorAnCell(13,9,Color.BLUE);


        });

        //=======================================================
        //adding components to HBox
        // --- first row
        firstrow.getChildren().add(lblSX);
        firstrow.getChildren().add(tfStartX);
        firstrow.getChildren().add(lblSY);
        firstrow.getChildren().add(tfStartY);
        firstrow.getChildren().add(lblEX);
        firstrow.getChildren().add(tfEndX);
        firstrow.getChildren().add(lblEY);
        firstrow.getChildren().add(tfEndY);
        // --- Second row
        secondRow.getChildren().add(rbManhattan);
        secondRow.getChildren().add(rbEuclidean);
        secondRow.getChildren().add(rbChebyshev);
        // --- third row
        thirdRow.getChildren().add(runBtn);


        //adding HBoxes to VBox
        formPaneVBox.getChildren().add(firstrow);
        formPaneVBox.getChildren().add(secondRow);
        formPaneVBox.getChildren().add(thirdRow);


        return formPaneVBox;

    }



    /**
     * This method will color the grid by populating grid with rectangles
     * @param isColored : to make the grid coloured or black and white - True for colored
     */
    public static void resetGridColorFill(boolean isColored){


        for(int i=1;i<21;i++){
            for (int j=1;j<21;j++){

                int val=GridPopulator.matrixArray[i-1][j-1];

                //adding rectangles to the grid pane
                Rectangle rectangle= new Rectangle(0,0,29,29);


                switch (val){
                    case 1:

                        if(isColored){
                            rectangle.setFill(Color.rgb(36, 221, 36));
                        }else{
                            //lightest shade of grey (weight = 2)
                            rectangle.setFill(Color.rgb(240, 240, 240));
                        }

                        break;
                    case 2:
                        if(isColored){
                            rectangle.setFill(Color.rgb(0, 181, 0));
                        }else {
                            //middle shade of grey (weight = 3)
                            rectangle.setFill(Color.rgb(224, 224, 224));
                        }
                        break;
                    case 3:
                        if(isColored){
                            rectangle.setFill(Color.rgb(198, 198, 198));
                        }else {
                            //darkest shade of greay (weight = 4);
                            rectangle.setFill(Color.rgb(208, 208, 208));
                        }
                        break;
                    case 4:
                        if(isColored){
                            rectangle.setFill(Color.rgb(0, 57, 222));
                        }else {
                            //black
                            rectangle.setFill(Color.rgb(0, 0, 0));
                        }
                        break;
                    default:
                        if(isColored){
                            rectangle.setFill(Color.rgb(107, 181, 0));
                        }else{
                            //white shade of grey (weight = 1)
                            rectangle.setFill(Color.rgb(255, 255, 255));
                            break;
                        }

                }
                mainGridPane.add(rectangle,j,i);

            }
        }

    }

    /**
     *
     * This method will color a single cell in the grid
     *
     * @param x : X axis coordinate
     * @param y : y axis coordinate
     * @param color : the color of the fill
     */
    public static void colorAnCell(int x,int y ,Color color){

        //creating a new circle and aligning to center
        Circle circle = new Circle(12);

        GridPane.setHalignment(circle, HPos.CENTER);
        //setting the color
        circle.setFill(color);
        //adding to the grid pain at the correct position - (x,y)
        mainGridPane.add(circle,x,y);
    }


    /**
     * this method will return a grid pane
     * @param rows :number of rows of the grid
     * @param columns :number of columns of the grid
     * @return : return a rows x columns grid pane
     */
    public static GridPane getAnGrid(int rows ,int columns){

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

        return grid;
    }





}
