package com.algocw.gui;
import com.algocw.algo.AStar;
import com.algocw.algo.Node;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
 
public class MainScreen extends Application{
    //tjis variable will store the main pane in which all other components will be set to
    private static  BorderPane mainBorderPane;
    //this variable will store the main grid pane object
    private static GridPane mainGridPane;
    //this variable will store the formHbox object
    private static VBox formPaneVBox;

    //this variable will state true for colored , false for black and white
    private static boolean isColored=true;

    //variables to store the start and end coordinates
    private static int startX=0;
    private static int startY=0;
    private static int endX=19;
    private static int endY=0;
    //this click count variable is to keep track of the nuber of clicks on the grid pane
    private int clickCount=0;

    //text fields that are used to take the coordinates
    private TextField tfStartX;
    private TextField tfStartY;
    private TextField tfEndX;
    private TextField tfEndY;

    private final ToggleGroup toggleGroup = new ToggleGroup();
    //these radio buttons will store the type of formulae to be used
    private RadioButton rbManhattan;
    private RadioButton rbEuclidean;
    private RadioButton rbChebyshev;
    //this string will store the selected formulae method from the radio buttons
    private static String selectedMethod="Manhattan";

    //creating a new AStar object to use the AStar pathfinder
    private AStar as= new AStar();


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
        //setting the scene and showing it
        primaryStage.setScene(scene);
        primaryStage.show();

        //setting up the grid for the 1st time
        resetGridColorFill(true);
        //initializing the path finder
        initializePathFinder();
    }

    /**
     *
     * @return : The main scene that will be set to the primaryStage
     */
    public Scene getMainScene(){


        // ----- heading -----
        HBox heading = new HBox(5);
        heading.setAlignment(Pos.CENTER);
        Label lblHeading = new Label("A* Path Finder by Devon Wijesinghe");
        heading.getChildren().add(lblHeading);
        lblHeading.getStyleClass().add("mainHeading");
        heading.getStyleClass().add("heading");
        // ----- heading end -----


        //instantiating BorderPane
        mainBorderPane= new BorderPane();
        //setting the style class

        //creating the components

        //setting the main 20x20 grid pane
        mainGridPane=getAnGrid(20,20);

        //On mouse click event handle to get the grid coordinated when clicked
        mainGridPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            //getting the source node
            javafx.scene.Node source = (javafx.scene.Node) e.getTarget();
            //calling the setStartAndEndPointsFromClick method
            setStartAndEndPointsFromClick(source);;

        });


        //setting style class
        mainGridPane.getStyleClass().add("grid");

        //setting the form pane
        formPaneVBox=getFormPane();

        //aligning to center
        BorderPane.setAlignment(mainGridPane, Pos.CENTER);
        BorderPane.setAlignment(formPaneVBox,Pos.CENTER);


        //adding the components to the main Border Pane
        mainBorderPane.setTop(heading);
        mainBorderPane.setCenter(mainGridPane);
        mainBorderPane.setBottom(getFormPane());

        //creating the scene
        Scene scene = new Scene(mainBorderPane, 630,930);

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

        //creating the form component

        //== row 1 ==
        Label lblSX=new Label("Start X :");
        tfStartX = new TextField("0");
        Label lblSY=new Label("Start Y :");
        tfStartY = new TextField("0");
        Label lblSpace=new Label("                  ");
        Label lblEX=new Label("End X :");
        tfEndX = new TextField("0");
        Label lblEY=new Label("End Y :");
        tfEndY = new TextField("19");

        //== row 2 ==
        rbManhattan= new RadioButton("Manhattan");
        rbManhattan.setSelected(true);
        rbEuclidean= new RadioButton("Euclidean");
        rbChebyshev= new RadioButton("Chebyshev");

        //setting the toggle group
        rbManhattan.setToggleGroup(toggleGroup);
        rbEuclidean.setToggleGroup(toggleGroup);
        rbChebyshev.setToggleGroup(toggleGroup);

        //== row 3 ==
        Button runBtn= new Button("RUN");
        Button colorBtn= new Button("Color ON/OFF");

        //setting Style classes
        tfStartX.getStyleClass().add("textField");
        tfStartY.getStyleClass().add("textField");
        tfEndX.getStyleClass().add("textField");
        tfEndY.getStyleClass().add("textField");
        runBtn.getStyleClass().add("btnRun");
        colorBtn.getStyleClass().add("btnColor");
        lblSX.getStyleClass().add("lbl");
        formPaneVBox.getStyleClass().add("formPane");


        //setting alignment to center
        formPaneVBox.setAlignment(Pos.CENTER);
        firstrow.setAlignment(Pos.CENTER);
        secondRow.setAlignment(Pos.CENTER);
        thirdRow.setAlignment(Pos.CENTER);


        //======  On click and on change actions ======

        //main run button login
        runBtn.setOnAction(e -> {
           //the onRunButtonPressed method is called to which run the algorithm and draws the path
            onRunButtonPressed();
        });

        //changing between black and white and colored
        colorBtn.setOnAction(e->{
            isColored=!isColored;
            resetGridColorFill(isColored);

        });

        //=======================================================

        //adding components to HBox

        // --- first row
        firstrow.getChildren().addAll(lblSX,tfStartX,lblSY,tfStartY,lblSpace,lblEX,tfEndX,lblEY,tfEndY);

        // --- Second row
        secondRow.getChildren().addAll(rbManhattan,rbEuclidean,rbChebyshev);

        // --- third row
        thirdRow.getChildren().addAll(runBtn,colorBtn);

        //adding HBoxes to VBox
        formPaneVBox.getChildren().addAll(firstrow,secondRow,thirdRow);

        return formPaneVBox;

    }

    /**
     * This method will initialize the AStar path finder
     */
    public void initializePathFinder(){
        //Creating a new AStar object
        as= new AStar();
        //resetting the grid
        resetGridColorFill(isIsColored());
        //populating the matrix
        as.populateNodeMatrix();

    }

    /**
     * This method is the method that is called when the RUN button is pressed, this method will run the algorithm and draws the path
     */
    public void onRunButtonPressed(){

        long startTime = System.nanoTime();

        //initializing the path finder
        initializePathFinder();
        //setting the values retrieved from the radio button to the variables
        setTheRadioValue();
        //set and end start points in get
        setStartAndEndPoints();
        //populating the matrix again with new values
        as.populateNodeMatrix();
        //getting the start node from the matrix
        Node startNode = as.nodeMatrix[startX][startY];

        // call the Main method that will run to find the path from the start point to the end point
        as.findPath(startNode);

        //========== FOR TESTING ==========
        as.printArray(as.nodeMatrix);
        System.out.println("--------------");
        System.out.println(as.visitedArraylist);
        System.out.println("--------------");
        System.out.println(as.finalPathArrayList);
        //========== FOR TESTING ==========

        //Coloring the visited nodes in one colordd
        //drawPath(as.visitedArraylist,Color.CYAN);

        if(isIsColored()){
            //Coloring the final path in YELLOW
            drawPath(as.finalPathArrayList,Color.CYAN);
        }else {
            //Coloring the final path in YELLOW
            drawPath(as.finalPathArrayList,Color.CYAN);
        }



        //the time taken in milli seconds
        long estimatedTime = (System.nanoTime() - startTime )/1000000;

        PopUps.popUp("PATH FOUND" , "Path found succesfully :) \nDetails are shown below","Selected Metric : "+ selectedMethod +"\n\nTime taken : "+ estimatedTime + "ms"  + "\nG Cost : "+as.nodeMatrix[endX][endY].getgCost() , Alert.AlertType.INFORMATION);


    }



    /**
     * This method will color the grid by populating grid with rectangles
     * @param isColored : to make the grid coloured or black and white - True for colored
     */
    public static void resetGridColorFill(boolean isColored){

        mainGridPane.getChildren().clear();

        //looping through the 2d array matrix
        for(int i=0;i<20;i++){
            for (int j=0;j<20;j++){

                int val=GridPopulator.matrixArray[i][j];

                //adding rectangles to the grid pane
                Rectangle rectangle= new Rectangle(0,0,29,29);


                //this switch will set the cell color based on the value
                switch (val){
                    case 2:

                        if(isColored){
                            rectangle.setFill(Color.rgb(36, 221, 36,0.2));
                        }else{
                            //lightest shade of grey (weight = 2)
                            rectangle.setFill(Color.rgb(240, 240, 240,0.2));
                        }

                        break;
                    case 3:
                        if(isColored){
                            rectangle.setFill(Color.rgb(0, 181, 0,0.2));
                        }else {
                            //middle shade of grey (weight = 3)
                            rectangle.setFill(Color.rgb(224, 224, 224,0.2));
                        }
                        break;
                    case 4:
                        if(isColored){
                            rectangle.setFill(Color.rgb(198, 198, 198,0.2));
                        }else {
                            //darkest shade of greay (weight = 4);
                            rectangle.setFill(Color.rgb(208, 208, 208,0.2));
                        }
                        break;
                    case 5:
                        if(isColored){
                            rectangle.setFill(Color.rgb(0, 57, 222,0.2));
                        }else {
                            //black
                            rectangle.setFill(Color.rgb(0, 0, 0,0.2));
                        }
                        break;
                    default:
                        if(isColored){
                            rectangle.setFill(Color.rgb(107, 181, 0,0.5));
                        }else{
                            //white shade of grey (weight = 1)
                            rectangle.setFill(Color.rgb(255, 255, 255,0.5));
                            break;
                        }

                }
                mainGridPane.add(rectangle,j,i);

            }
        }

    }


    /**
     * this method will mark the start and end points in the GUI grid
     */
    public  void setStartAndEndPoints(){
        //getting the tf values
        startX= Integer.parseInt(tfStartX.getText());
        startY= Integer.parseInt(tfStartY.getText());
        endX= Integer.parseInt(tfEndX.getText());
        endY= Integer.parseInt(tfEndY.getText());

        //setting the color of the START point as red
        colorAnCell(startX,startY,Color.GREEN);
        //setting the color of the END point as green
        colorAnCell(endX,endY,Color.RED);

    }

    /**
     * This method will set the start and end points in the grid by clicking
     * @param source : the target node source
     */
    private void setStartAndEndPointsFromClick(javafx.scene.Node source) {

        //incrementing the count
        clickCount ++;

        //getting the col and row index of the node
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);

        //getting the node object of the corresponding index
        Node selectedNode = as.nodeMatrix[colIndex][rowIndex];

        //checking if node is blocked
        if(selectedNode.isBlocked()){
            //if blocked , a alert will be show and the method will stop with the return
            PopUps.popUp("Warning","This node is blocked","You can not go to this node as it is blocked , click on a different cell . Sorry :)", Alert.AlertType.WARNING);
            clickCount--;
            return;
        }

        //checking for the first click
        if(clickCount==1){
            //setting up the start point
            tfStartX.setText(String .valueOf(colIndex));
            tfStartY.setText(String .valueOf(rowIndex));
            //coloring the start Node GREEN
            colorAnCell(colIndex,rowIndex,Color.GREEN);
        }
        //checking for the second click
        else if (clickCount==2){
            //setting up the end point
            tfEndX.setText(String.valueOf(colIndex));
            tfEndY.setText(String .valueOf(rowIndex));
            //coloring the end Node RED
            colorAnCell(colIndex,rowIndex,Color.RED);
        }
        //if more than 2 click recorded
        else {
            clickCount=0;
            //this will reset the grid
            resetGridColorFill(isIsColored());
        }

    }


    /**
     * This method will draw a path using a 2d array and colorAnCell() method
     * @param finalPathArrayList : list of node containing the final path
     */
    public void drawPath(ArrayList<Node> finalPathArrayList ,Color pathColor){
        for (Node node : finalPathArrayList){
            //X coordinate
            int x=node.getxCoordinate();
            //Y coordinate
            int y=node.getyCoordinate();

            if( !(x ==startX && y==startY) && !(x ==endX && y==endY)){
                //coloring a cell with coordinates x,t
                colorAnCell(x,y,pathColor);
            }

        }

    }

    /**
     * This method will set the radio button values and set the selected radio value
     */
    public void setTheRadioValue(){
        //setting user data
        rbManhattan.setUserData("Manhattan");
        rbEuclidean.setUserData("Euclidean");
        rbChebyshev.setUserData("Chebyshev");

        //getting the selected radio button
        RadioButton selectedRadioButton =
                (RadioButton) toggleGroup.getSelectedToggle();

        //setting the value of the selected radio button to the variable
        selectedMethod = String.valueOf(selectedRadioButton.getUserData());
        System.out.println(selectedMethod);

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
        //creating a new rectrangle and aligning to center
        Rectangle rectrangle = new Rectangle(20,20);
        //setting alignment to center
        GridPane.setHalignment(rectrangle, HPos.CENTER);
        //setting the color
        rectrangle.setFill(color);
        //adding to the grid pain at the correct position - (x,y)
        mainGridPane.add(rectrangle,x,y);
    }

    /**
     * this method will return a grid pane
     * @param rows :number of rows of the grid
     * @param columns :number of columns of the grid
     * @return : return a rows x columns grid pane
     */
    public static GridPane getAnGrid(int rows ,int columns){
        //Declaring and instantiating a grid pane
        GridPane grid;
        grid = new GridPane();

        //creating the columns
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



    //Getters and setters

    public static boolean isIsColored() {
        return isColored;
    }

    public static int getEndX() {
        return endX;
    }

    public static int getEndY() {
        return endY;
    }

    public static String getSelectedMethod() {
        return selectedMethod;
    }
}

