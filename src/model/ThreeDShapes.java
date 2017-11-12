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
public abstract class ThreeDShapes extends Shape{
    
    public ThreeDShapes(String name, String type) {
        super(name, type);
    }
    
    public abstract double getSurfaceArea();
    
    public abstract double getVolume();
    
}
