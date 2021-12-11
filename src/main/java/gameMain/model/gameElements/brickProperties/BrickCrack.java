package gameMain.model.gameElements.brickProperties;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.*;

/**
 * This class specifies the properties of a brick's crack
 *
 * Refactored by:
 * @author LooXuenYi
 */
public class BrickCrack {

    //Variable Declaration
    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;
    private Random rnd;
    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;
    private GeneralPath crack;
    private int crackDepth;
    private int steps;
    private final Shape brickFace;

    /**
     * This constructor defines the behavior of the cracks
     *
     * @param crackDepth
     * @param steps
     */
    public BrickCrack(int crackDepth, int steps, Shape brickFace)
    {
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
        this.brickFace = brickFace;
    }

    /**
     * This method is used to generate the appearance of the cracks on the bricks
     *
     * @return crack
     */
    public GeneralPath draw()
    {
        return crack;
    }

    /**
     * This method makes all cracks disappear
     */
    public void reset(){
        crack.reset();
    }

    /**
     * This method specifies the rules on how the cracks should be created upon impact
     *
     * @param point
     * @param direction
     */
    protected void makeCrack(Point2D point, int direction)
    {
        assert false;
        Rectangle bounds = brickFace.getBounds();
        Point impact = new Point((int)point.getX(),(int)point.getY());
        Point start = new Point();
        Point end = new Point();

        switch(direction){
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);
                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);
                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;
        }
    }

    /**
     * This method specifies the rules on how the cracks should be created upon impact
     *
     * @param start
     * @param end
     */
    protected void makeCrack(Point start, Point end)
    {
        GeneralPath path = new GeneralPath();

        path.moveTo(start.x,start.y);

        double w = (end.x - start.x) / (double)steps;
        double h = (end.y - start.y) / (double)steps;

        int bound = crackDepth;
        int jump  = bound * 5;

        double x,y;

        for(int i = 1; i < steps; i++){
            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if(inMiddle(i,CRACK_SECTIONS,steps))
                y += jumps(jump,JUMP_PROBABILITY);

            path.lineTo(x,y);
        }

        path.lineTo(end.x,end.y);
        crack.append(path,true);
    }

    private int randomInBounds(int bound)
    {
        int n = (bound * 2) + 1;
        return Brick.getRnd().nextInt(n) - bound;
    }

    private boolean inMiddle(int i,int steps,int divisions)
    {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }

    private int jumps(int bound,double probability)
    {
        if(Brick.getRnd().nextDouble() > BrickCrack.JUMP_PROBABILITY)
            return randomInBounds(bound);
        return  0;
    }

    /**
     * This method allows cracks to be made, returning the location of the cracks
     *
     * @param from
     * @param to
     * @param direction
     * @return out
     */
    private Point makeRandomPoint(Point from,Point to, int direction)
    {
        Point out = new Point();
        int pos;

        switch(direction){
            case HORIZONTAL:
                pos = Brick.getRnd().nextInt(to.x - from.x) + from.x;
                out.setLocation(pos,to.y);
                break;
            case VERTICAL:
                pos = Brick.getRnd().nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x,pos);
                break;
            default:
                throw new IllegalStateException("Unexpected value: There is an error in cracking" + direction);
        }

        return out;
    }

}
