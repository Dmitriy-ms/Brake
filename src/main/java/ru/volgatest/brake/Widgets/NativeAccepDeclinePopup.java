/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets;

//import UI.MainController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 *
 * @author VTARMN013
 */
public class NativeAccepDeclinePopup {
    public static boolean show(String msg,String okButtonName, String noButtonName){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //alert.getDialogPane().getStylesheets().add(MainController.CSSPATH);
        alert.setTitle("Select");
        alert.setHeaderText(msg);
        ButtonType ok = new ButtonType(okButtonName==null?"Ok":okButtonName);
        ButtonType no = new ButtonType(noButtonName==null?"Decline":noButtonName);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ok,no);
        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null ||option.get() == no) {
            return false;
        } else return true;
    }
}
