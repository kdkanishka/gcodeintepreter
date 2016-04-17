/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imago.gcodedrawing;

import com.imago.gcode.domain.GCodeUtils;
import com.imago.gcode.domain.GCoordinate;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author kanishka
 */
public class GcodeSurface extends JPanel {

    private final int STEPS_PER_MM = 16;

    private List<String> gcodeList;
    private int posX = 0, posY = 0;

    public GcodeSurface(List<String> gcodeList) {
        this.gcodeList = gcodeList;
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //set background color
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        for (String gcode : gcodeList) {
//            System.out.println(gcode);
            //detect pen
            if (GCodeUtils.isPenDownCommand(gcode)) {
                if (GCodeUtils.isPenDown(gcode)) {
                    g2d.setPaint(Color.BLUE);
                } else {
                    g2d.setPaint(Color.WHITE);
                }
            }

            //detect Gcode coordinates
            if (GCodeUtils.isGcodePoint(gcode)) {
                try {
                    GCoordinate coordinate = GCodeUtils.getCoordinates(gcode);
                    memX = posX;
                    memY = posY;
                    drawBresenhamsLine(coordinate.getX(), coordinate.getY(), g2d);
                } catch (Exception e) {
//                    e.printStackTrace();
                }
            }

        }
    }

    int memX = 0, memY = 0;

    private void drawBresenhamsLine(double newX, double newY, Graphics2D g2d) {
        int x1 = (int) (newX * STEPS_PER_MM);
        int y1 = (int) (newY * STEPS_PER_MM);
        int x0 = posX;
        int y0 = posY;

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;

//        System.out.println("dx: " + dx + " dy: " + dy);
        long over = 0;

        if (dx > dy) {
            for (int i = 0; i < dx; i++) {
                stepX(g2d, sx);
                over += dy;
                if (over >= dx) {
                    over -= dx;
                    stepY(g2d, sy);
                }
                //stepper delay
            }
        } else {
            for (int i = 0; i < dy; i++) {
                stepY(g2d, sy);
                over += dx;
                if (over >= dy) {
                    over -= dy;
                    stepX(g2d, sx);
                }
                //stepper delay

            }
        }

        //line delay
        posX = x1;
        posY = y1;

    }

    private void stepX(Graphics2D g2d, int dir) {
//        System.out.println("STEPX X:" + (memX) + " Y:" + posY + " DIR:" + dir);
        g2d.drawLine(memX, memY, memX + dir, memY);
        memX += dir;
    }

    private void stepY(Graphics2D g2d, int dir) {
//        System.out.println("STEPY X:" + posX + " Y:" + (memY) + " DIR:" + dir);
        g2d.drawLine(memX, memY, memX, memY + dir);
        memY += dir;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    
    

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        repaint();
//    }

    public void setGcodeList(List<String> gcodeList) {
        this.gcodeList = gcodeList;
    }
}
