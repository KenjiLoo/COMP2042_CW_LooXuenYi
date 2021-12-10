package gameMain.model.gameElements.brickProperties;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * This class specifies the properties of the brown cement brick seen in the gameplay
 *
 * Refactored by:
 * @author LooXuenYi
 */
public class CementBrick extends Brick {

    //Variable Declaration
    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;
    private BrickCrack crack;
    private Shape brickFace;

    /**
     * This constructor adds the point of the brick (location) and dimensions to the brick constructor specified in the abstract class "Brick"
     *
     * @param point
     * @param size
     */
    public CementBrick(Point point, Dimension size)
    {
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new BrickCrack(DEF_CRACK_DEPTH,DEF_STEPS, super.getBrickFace());
        brickFace = super.getBrickFace();
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * This method checks if it is broken or just make a crack everytime it is impacted
     *
     * @param point
     * @param dir
     * @return true
     */
    @Override
    public boolean setImpact(Point2D point, int dir)
    {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }

    /**
     * This method shows how the brick looks like
     *
     * @return brickFace
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * THis method draws a crack on the brick when there is an impact made
     */
    private void updateBrick()
    {
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    /**
     * This method restores the number of time needed to impact the brick to the maximum
     */
    public void repair()
    {
        super.repair();
        crack.reset();
        brickFace = super.getBrickFace();
    }
}
