/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imago.gcode.domain;

/**
 *
 * @author kanishka
 */
public class GCodeUtils {

    final static String PEN_DOWN = "M300 S30";
    final static String PEN_UP = "M300 S50";

    private GCodeUtils() {

    }

    public static boolean isPenDownCommand(String gcode) {
        if (gcode.startsWith("M300")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPenDown(String gcode) {
        if (gcode.startsWith(PEN_DOWN)) {
            return true;
        } else if (gcode.startsWith(PEN_UP)) {
            return false;
        }
        return false;
    }

    public static boolean isGcodePoint(String gcode) {
        if (gcode.startsWith("G1")) {
            return true;
        } else {
            return false;
        }
    }

    public static GCoordinate getCoordinates(String gcode) {
        double x, y;
        x = Double.parseDouble(gcode.substring(gcode.indexOf("X")+1, gcode.indexOf("Y")).trim());
        y = Double.parseDouble(gcode.substring(gcode.indexOf("Y")+1, gcode.indexOf("F")).trim());
        
        return new GCoordinate(x, y);
    }
}
