/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.SimpleLogger;

import java.time.LocalDateTime;

/**
 *
 * @author VTARMN013
 */
public class SimpleLogMessage {
    int msgId;
    public boolean isPopup;
    public MsgType type;
    public String text;
    public LocalDateTime timestamp;
}
