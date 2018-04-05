package com.algocw.gui;
import com.algocw.algo.PathFinder;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    //this variable will state true for colored , false for black and white
    private static boolean isColored=true;

    //variables to store the start and end coordinates
    private static int startX=1;
    private static int startY=1;
    private static int endX=3;
    private static int endY=3;

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
        mainGridPane=getAnGrid(20,20);

        //setting style class
        lblHeading.getStyleClass().add("mainHeading");
        mainGridPane.getStyleClass().add("grid");

        //setting the form pane
        formPaneVBox=getFormPane();

        //aligning to center
        BorderPane.setAlignment(lblHeading, Pos.CENTER);
        BorderPane.setAlignment(mainGridPane, Pos.CENTER);
        BorderPane.setAlignment(formPaneVBox,Pos.CENTER);


        //adding the components to the main Border Pane
        mainBorderPane.setTop(getFormPane());
        mainBorderPane.setCenter(mainGridPane);
        mainBorderPane.setBottom(lblHeading);

        //creating the scene
        Scene scene = new Scene(mainBorderPane, 630,900);

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
        tfStartX = new TextField("1");
        Label lblSY=new Label("Start Y :");
        tfStartY = new TextField("1");
        Label lblSpace=new Label("                  ");
        Label lblEX=new Label("End X :");
        tfEndX = new TextField("1");
        Label lblEY=new Label("End Y :");
        tfEndY = new TextField("1");

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
        formPaneVBox.getStyleClass().add("formPane");


        //setting alignment to center
        formPaneVBox.setAlignment(Pos.CENTER);
        firstrow.setAlignment(Pos.CENTER);
        secondRow.setAlignment(Pos.CENTER);
        thirdRow.setAlignment(Pos.CENTER);

        //=======================================================
        //======  On click and on change actions ======

        //main rum button login
        runBtn.setOnAction(e -> {

            //set and end start points in get
            setStartAndEndPoints();

            //TODO call the method in PathFinderClass to get the array of coorinates that will form the path (Passing the start and end point)

            setTheRadioValue();

            PathFinder pf=new PathFinder();
            pf.setArray();
            int [][] coordinatesArray = pf.getFinalCoordinatesArray();
            drawPath(coordinatesArray);

        });

        //changing bettween black and white and colored
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
     * This method will color the grid by populating grid with rectangles
     * @param isColored : to make the grid coloured or black and white - True for colored
     */
    public static void resetGridColorFill(boolean isColored){


        //looping through the 2d array matrix
        for(int i=0;i<20;i++){
            for (int j=0;j<20;j++){

                int val=GridPopulator.matrixArray[i][j];

                //adding rectangles to the grid pane
                Rectangle rectangle= new Rectangle(0,0,29,29);


                //this switch will set the cell color based on the value
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
     * this method will mark the start and end points in the GUI grid
     */
    public  void setStartAndEndPoints(){
        //resetting the grid
        resetGridColorFill(isColored);

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
     * This method will draw a path using a 2d array and colorAnCell() method
     *
     * @param coordinatesArray : A 2D array which has the coordintates of the path cells
     */
    public void drawPath(int [][] coordinatesArray ){

        //int [][] coordinatesArray = new int[][] {{1,1},{1,2},{1,3},{1,4},{1,5},{2,6},{2,7}};
        for (int i =0 ; i<coordinatesArray.length;i++){

            //X coordinate
            System.out.print(coordinatesArray[i][0]+",") ;
            int x=coordinatesArray[i][0];

            //Y coordinate
            System.out.print(coordinatesArray[i][1]);
            int y=coordinatesArray[i][1];


            System.out.println("----");

            colorAnCell(x,y,Color.YELLOW);

        }

    }


    /**
     * This method will set the radio button values and set the selected radio value
     */
    public void setTheRadioValue(){
        //setting user data
        rbManhattan.setUserData("manhattan");
        rbEuclidean.setUserData("euclidean");
        rbChebyshev.setUserData("chebyshev");

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


    public TextField getTfStartX() {
        return tfStartX;
    }

    public void setTfStartX(TextField tfStartX) {
        this.tfStartX = tfStartX;
    }

    public TextField getTfStartY() {
        return tfStartY;
    }

    public void setTfStartY(TextField tfStartY) {
        this.tfStartY = tfStartY;
    }

    public TextField getTfEndX() {
        return tfEndX;
    }

    public void setTfEndX(TextField tfEndX) {
        this.tfEndX = tfEndX;
    }

    public TextField getTfEndY() {
        return tfEndY;
    }

    public void setTfEndY(TextField tfEndY) {
        this.tfEndY = tfEndY;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public RadioButton getRbManhattan() {
        return rbManhattan;
    }

    public void setRbManhattan(RadioButton rbManhattan) {
        this.rbManhattan = rbManhattan;
    }

    public RadioButton getRbEuclidean() {
        return rbEuclidean;
    }

    public void setRbEuclidean(RadioButton rbEuclidean) {
        this.rbEuclidean = rbEuclidean;
    }

    public RadioButton getRbChebyshev() {
        return rbChebyshev;
    }

    public void setRbChebyshev(RadioButton rbChebyshev) {
        this.rbChebyshev = rbChebyshev;
    }

    public static BorderPane getMainBorderPane() {
        return mainBorderPane;
    }

    public static void setMainBorderPane(BorderPane mainBorderPane) {
        MainScreen.mainBorderPane = mainBorderPane;
    }

    public static GridPane getMainGridPane() {
        return mainGridPane;
    }

    public static void setMainGridPane(GridPane mainGridPane) {
        MainScreen.mainGridPane = mainGridPane;
    }

    public static VBox getFormPaneVBox() {
        return formPaneVBox;
    }

    public static void setFormPaneVBox(VBox formPaneVBox) {
        MainScreen.formPaneVBox = formPaneVBox;
    }

    public static boolean isIsColored() {
        return isColored;
    }

    public static void setIsColored(boolean isColored) {
        MainScreen.isColored = isColored;
    }

    public static int getStartY() {
        return startY;
    }

    public static void setStartY(int startY) {
        MainScreen.startY = startY;
    }

    public static int getEndX() {
        return endX;
    }

    public static void setEndX(int endX) {
        MainScreen.endX = endX;
    }

    public static int getEndY() {
        return endY;
    }

    public static void setEndY(int endY) {
        MainScreen.endY = endY;
    }

    public static String getSelectedMethod() {
        return selectedMethod;
    }

    public static void setSelectedMethod(String selectedMethod) {
        MainScreen.selectedMethod = selectedMethod;
    }

    public static int getStartX() {
        return startX;
    }

    public static void setStartX(int startX) {
        MainScreen.startX = startX;
    }
}
