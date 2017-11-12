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
public class Circle extends TwoDShapes{
    
    private final double diameter;
    private final double pie = 3.14;
   

    public Circle(String name, String type , double diameter) {
        super(name, type);
        this.diameter= diameter;
    }

    @Override
    public final double getArea() {
        final double r = diameter/ 2;
        final double tempArea = pie * ( r*r );
        return tempArea; 
    }

    @Override
    public final double getPerimeter() {
        final double tempPerimeter = pie * diameter;
        
        return tempPerimeter;
    }
    
}
