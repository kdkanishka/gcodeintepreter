/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imago.gcodedrawing;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author kanishka
 */
public class PointsEx extends JFrame {

    public PointsEx() throws IOException {

        initUI();
    }

    private List<String> readGcodeFile(String filePath) throws FileNotFoundException, IOException {
        List<String> gcodeLines = new ArrayList<>();
        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null;) {
                gcodeLines.add(line);
            }
        }
        return gcodeLines;
    }

    private void initUI() throws IOException {

        List<String> gcodeLines = readGcodeFile("/home/kanishka/Desktop/drawing1.gcode");

        final GcodeSurface surface = new GcodeSurface(gcodeLines);
        add(surface);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

            }
        });

        setTitle("Points");
        setSize(512, 512);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

//                PointsEx ex;
//                try {
//                    ex = new PointsEx();
//                    ex.setVisible(true);
//                } catch (IOException ex1) {
//                    ex1.printStackTrace();
//                }
                GcodeViewer gcodeViewer=new GcodeViewer();
                gcodeViewer.setLocationRelativeTo(null);
                gcodeViewer.setVisible(true);
            }
        });
    }
}
