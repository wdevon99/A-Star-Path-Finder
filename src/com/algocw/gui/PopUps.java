package com.algocw.gui;

import javafx.scene.control.Alert;

/**
 *  @author devon wijesinghes
 *  This class is a custom  class made to use pop ups easyly
 */
public class PopUps {

    //this method genarates a  pop up box based on the parameters passed, the last parameter is the type of the alert box

    public static void popUp(String title,String headerText,String contentText,Alert.AlertType alertType){

        //creating alert box
        Alert alert = new Alert(alertType);
        //setting the title
        alert.setTitle(title);
        //setting the header text
        alert.setHeaderText(headerText);
        //setting the content
        alert.setContentText(contentText);
        //setting the height
        alert.setHeight(650);
        //setting it to be visible/to show
        alert.showAndWait();
    }

}
