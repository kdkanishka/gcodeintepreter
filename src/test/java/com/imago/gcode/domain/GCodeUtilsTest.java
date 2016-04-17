/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imago.gcode.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kanishka
 */
public class GCodeUtilsTest {

    public GCodeUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCoordinates method, of class GCodeUtils.
     */
    @Test
    public void testGetCoordinates() {
        System.out.println("getCoordinates");
        String gcode = "G1 X7.32 Y33.05 F3500.00";
        GCoordinate expResult = new GCoordinate(7.32, 33.05);
        GCoordinate result = GCodeUtils.getCoordinates(gcode);
        assertEquals(expResult.getX(), result.getX(), 0);
        assertEquals(expResult.getY(), result.getY(), 0);
    }

}
