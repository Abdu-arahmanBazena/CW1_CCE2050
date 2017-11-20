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
public class Rectangle extends TwoDShapes implements Serializable{
    private final double width;
    private final double length;
   
   
    
    public Rectangle(String name, String type, double length, double width) {
        super(name, type);
        this.length = length;
        this.width = width;
         this.setArea(getArea());
        this.setPerimeter(getPerimeter());
       
    }

    public Rectangle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public final double getArea()
    {
        final double tempArea;
        tempArea = getLength() * getWidth();
        return tempArea;
    };
     @Override
    public final double getPerimeter()
    {
        final double tempPerimeter;
        tempPerimeter = (getWidth() * 2) + (getLength() * 2);
        
        return tempPerimeter;
    };
    
    
    //getters 
    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the length
     */
    public double getLength() {
        return length;
    }
    
}
