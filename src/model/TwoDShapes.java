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
public abstract class TwoDShapes extends Shape{
    
    public TwoDShapes(String name, String type) {
        super(name, type);
    }
    
    public abstract double getArea();
    
    public abstract double getPerimeter();
}
