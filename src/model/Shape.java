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
public abstract class Shape implements Serializable{
    private  String ShapeName;
    private  String ShapeType;
    
    public Shape(String Name, String Type)
    {
        this.ShapeName = Name;
        this.ShapeType = Type;
    }
     public Shape()
    {
        
    }

    /**
     * @return the ShapeName
     */
    public String getShapeName() {
        return ShapeName;
    }

    /**
     * @return the ShapeType
     */
    public String getShapeType() {
        return ShapeType;
    }
}
