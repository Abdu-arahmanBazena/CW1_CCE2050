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
public class Circle extends TwoDShapes implements Serializable{
    
    private  double diameter=0.0;
    private final double pi = 3.14;
   

    public Circle(String name, String type , double diameter) {
        super(name, type);
        this.diameter= diameter;
        this.setArea(getArea());
        this.setPerimeter(getPerimeter());
    }
     public Circle() {
        
    }


    @Override
    public final double getArea() {
        final double r = diameter/ 2;
        final double tempArea = pi * ( r*r );
        return tempArea; 
    }

    @Override
    public final double getPerimeter() {
        final double tempPerimeter = pi * diameter;
        
        return tempPerimeter;
    }
    
}
