package gameMain.model.gameElements.brickProperties;

import gameMain.model.gameElements.ballProperties.Ball;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This abstract class specifies the properties that all brick types should have
 *
 */
abstract public class Brick  {

    //Variable Declaration
    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;
    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;
    private static Random rnd;
    private String name;
    Shape brickFace;
    private Color border;
    private Color inner;
    private int fullStrength;
    private int strength;
    private boolean broken;

    /**
     * This constructor defines the properties of bricks created that are listed as parameters
     *
     * @param name
     * @param pos
     * @param size
     * @param border
     * @param inner
     * @param strength
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength)
    {
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;
    }

    /**
     * This method passes the properties of the brick's display
     *
     * @param pos
     * @param size
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * This method specifies what happens to the brick upon impact
     *
     * @param point
     * @param dir
     * @return broken
     */
    public  boolean setImpact(Point2D point , int dir)
    {
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     *This method is used as a part of the brick rendering
     */
    public abstract Shape getBrick();

    /**
     * This method returns the color of the border
     *
     * @return border
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * This method returns the body color of the brick
     *
     * @return inner
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * This method is used in checking if there is an impact made on the brick
     *
     * @param b
     * @return out
     */
    public final int findImpact(Ball b)
    {
        if(broken)
            return 0;
        int out  = 0;

        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    /**
     * This method is used in returning "broken" when the brick is broken
     *
     * @return broken
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * This method repairs bricks so that cracks disappears and restore its number of impacts needed to break the brick
     */
    public void repair()
    {
        broken = false;
        strength = fullStrength;
    }

    /**
     * This method reduces the number of times needed to hit the brick until it breaks.
     * It is used everytime there is an impact made
     */
    public void impact()
    {
        strength--;
        broken = (strength == 0);
    }

    /**
     * This method get a random number
     *
     * @return rnd
     */
    public static Random getRnd() {
        return rnd;
    }

    /**
     * This method returns the properties of the brick's display
     *
     * @return brickFace
     */
    public Shape getBrickFace(){
        return brickFace;
    }

}





