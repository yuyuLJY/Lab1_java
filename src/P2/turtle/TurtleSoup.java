/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;

import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
   public static void drawSquare(Turtle turtle, int sideLength) {
    	try {
    		turtle.draw();
    		turtle.forward(sideLength);
    		turtle.turn(90);
    		turtle.forward(sideLength);
    		turtle.turn(90);
    		turtle.forward(sideLength);
    		turtle.turn(90);
    		turtle.forward(sideLength);
    		turtle.turn(90);
    	}catch(Exception e) {
    		throw new RuntimeException("implement me!");
    	}   
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	try {
    		double angle = ((sides-2)*180)/(double)sides;
    		//第一次出错的原因，整数除以整数，任然为整数，7边形应该为128.57，错误的算出128.0
    		//java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
    		//df.format(angle);
    		return angle;
    	}catch(Exception e){
    		throw new RuntimeException("implement me!");
    	}
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	try {
    		double sides_ =(360.0/(180-angle));
    		int sides =(int)(Math.ceil(sides_));//先变成四舍五入好的小数，再从小数变成整数
    		return sides;
    	}catch(Exception e){
    		throw new RuntimeException("implement me!");
    	}
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	try {
    		double angle = calculateRegularPolygonAngle(sides) ;//找出转弯的角度
    		turtle.draw();
    		turtle.forward(sideLength);
    		double re_angle = 180-angle;
    		for(int i=1;i<=sides-1;i++) {
    			turtle.turn(re_angle);
        		turtle.forward(sideLength);
    		}
    	}catch(Exception e){
    		throw new RuntimeException("implement me!");
    	}
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	double degrees=0;
    	try {
    		int re_targetX = -currentX+targetX;//得到相对的X坐标
    		int re_targetY = -currentY+targetY;//得到相对的Y坐标
    		double a=Math.atan2(re_targetX,re_targetY);//得到的是弧度
    		double b = (a*180/Math.PI);//角度
    		degrees = b -currentHeading;
    		if(degrees<0) {
    			degrees += 360;
    		}
    		return degrees;
    	}catch(Exception e){
    		throw new RuntimeException("implement me!");
    	}
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
    	try {
    		double currentHeading=0;//起始方向为0
    		List<Double> a= new ArrayList<Double>();//大小写很重要
    		a.add(calculateHeadingToPoint(currentHeading,
					xCoords.get(0),yCoords.get(0),xCoords.get(1),yCoords.get(1)));//先添加第一个元素
    		for(int i=0; i<xCoords.size()-2; i++) {
    			//传入参数，当前位置+下一个位置
    			a.add(calculateHeadingToPoint(a.get(i),
    					xCoords.get(i+1),yCoords.get(i+1),xCoords.get(i+2),yCoords.get(i+2)));
    		}
    		return a;
    	}catch(Exception e){
    		throw new RuntimeException("implement me!");
    	}
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	try {
    		int sideLength=40;
    		turtle.draw();
    		turtle.color(PenColor.RED);
    		turtle.forward(sideLength);
    		for(int i=1;i<=2;i++) {
    			turtle.turn(120);
        		turtle.forward(sideLength);
    		}
    		turtle.turn(60);
    		turtle.forward(40);
    		turtle.turn(120);
    		turtle.forward(40);
    		turtle.color(PenColor.GREEN);
    		turtle.forward(40);
    		turtle.turn(240);
    		turtle.forward(40);
    		turtle.turn(240);
    		turtle.forward(40);
    		turtle.turn(120);
    		turtle.forward(40);
    		turtle.turn(120);
    		turtle.forward(40);
    	}catch(Exception e){
    		throw new RuntimeException("implement me!");
    	}
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        //drawSquare(turtle, 40);
        //drawRegularPolygon(turtle,6,40);
        drawRegularPolygon(turtle,8,40);
        //drawPersonalArt(turtle);
        // draw the window
        turtle.draw();
    }

}