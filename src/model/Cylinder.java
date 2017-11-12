/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Abdo
 */
public class Cylinder extends ThreeDShapes{
    
    private final double cylinderRadius;
    private final double cylinderHeight;
    public Cylinder(String name, String type,double cylinderRadius, double cylinderHeight) {
        super(name, type);
        this.cylinderRadius=cylinderRadius;
        this.cylinderHeight =cylinderHeight; 
        
    }

    @Override
    public double getSurfaceArea() {
        final double durfaceArea = ((2*22*cylinderRadius)/7)*(cylinderRadius+cylinderHeight);
        return durfaceArea;
    }

    @Override
    public double getVolume() {
        final double cylinderVolume = ((22*cylinderRadius*cylinderRadius*cylinderHeight)/7);
        return cylinderVolume;
    }
    
}
