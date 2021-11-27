package elements.ballProperties;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * This abstract class defines the properties of a ball
 *
 * Refactored by:
 * @author LooXuenYi
 */
abstract public class Ball {

    private Shape ballFace;

    public Point2D center;

    public Point2D up;
    public Point2D down;
    public Point2D left;
    public Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * This constructor defines the pattern of the ball in terms of how it moves, which position it should be after every action, and the size of the ball
     *
     * @param center
     * @param radiusA
     * @param radiusB
     * @param inner
     * @param border
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){

        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());

        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * This abstract method allows the constructor to specify the dimensions of the ball
     *
     * @param center
     * @param radiusA
     * @param radiusB
     * @return
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * This method specifies how the ball should move
     */
    public void move(){

        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * This method sets the speed of the ball during gameplay
     *
     * @param x
     * @param y
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * This method specifies the speed of the ball while moving horizontally
     *
     * @param s
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * This method specifies the speed of the ball while moving vertically
     *
     * @param s
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * This method allows the ball to move left-wards
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * This method allows the ball to move down-wards
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * Gets the color of the border of the ball
     *
     * @return
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * Gets the color of the ball
     *
     * @return
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * Gets the default position of the ball
     *
     * @return
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * Gets the face of the ball
     * @return
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * This method specifies how the ball changes position
     *
     * @param p
     */
    public void moveTo(Point p){

        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * This method shoots the position of the ball
     *
     * @param width
     * @param height
     */
    private void setPoints(double width,double height){

        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * This method returns the current speed of the ball on the x axis
     *
     * @return
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * This method returns the current speed of the ball on the y axis
     *
     * @return
     */
    public int getSpeedY(){
        return speedY;
    }


}
