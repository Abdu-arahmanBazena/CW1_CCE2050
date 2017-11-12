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
public class Triangle extends TwoDShapes{
    private final double hypotenuse;
    private final double oppsite;
    private final double adjacent;
  
    
    public Triangle(String name, String type, double hypotenuse , double oppsite , double adjacent ) {
        super(name, type);
        this.hypotenuse = hypotenuse;
        this.oppsite = oppsite;
        this.adjacent = adjacent;
    }
    //calculate the area of the triangle 
    @Override
    public final double getArea()
    {
        final double tempArea;
        tempArea = (1/2) *(getAdjacent() * getOppsite());
        return tempArea;
    };
    
    //calculate the area of the Perimeter 
     @Override
    public final double getPerimeter()
    {
        final double tempPerimeter; 
        tempPerimeter = getHypotenuse() + getOppsite() + getAdjacent();
        
        return tempPerimeter;
    };
    
    
    //setters and getters 
    /**
     * @return the hypotenuse
     */
    public double getHypotenuse() {
        return hypotenuse;
    }

    /**
     * @return the oppsite
     */
    public double getOppsite() {
        return oppsite;
    }

    /**
     * @return the adjacent
     */
    public double getAdjacent() {
        return adjacent;
    }
}
