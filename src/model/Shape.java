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
public abstract class Shape {
    private final String ShapeName;
    private final String ShapeType;
    
    public Shape(String name, String type)
    {
        this.ShapeName = name;
        this.ShapeType = type;
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
