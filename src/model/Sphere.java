/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Abdo
 */
public class Sphere extends ThreeDShapes implements Serializable{
    private final double radius;
    public Sphere(String name, String type, double radius) {
        super(name, type);
        this.radius = radius;
    }

    /**
     *
     */
    public Sphere() {
        //super(null,null);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public double getSurfaceArea() {
        final double surfaceArea = 4 * Math.PI * Math.pow(radius, 2);
        return surfaceArea;
    }

    @Override
    public double getVolume() {
        final double volume = (4 * Math.PI * Math.pow(radius, 3)) / 3;
        
        return volume;
    }
    
}
