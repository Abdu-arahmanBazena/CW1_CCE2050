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
public abstract class TwoDShapes extends Shape implements Serializable{
    private double area;
    private double Perimeter;
    public TwoDShapes(String name, String type) {
        super(name, type);
    }
    public TwoDShapes()
    {
    }
    
    
    public  double getArea()
    {
        return area;
    }
    
    public  double getPerimeter()
    {
        return Perimeter;
    }

    /**
     * @param area the area to set
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * @param Perimeter the Perimeter to set
     */
    public void setPerimeter(double Perimeter) {
        this.Perimeter = Perimeter;
    }
}
