package main.elements.wallProperties;

/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2021 Loo Xuen Yi
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import main.elements.ballProperties.Ball;
import main.elements.ballProperties.RubberBall;
import main.elements.brickProperties.Brick;
import main.elements.brickProperties.BrickCrack;
import main.elements.brickProperties.CementBrick;
import main.elements.brickProperties.ClayBrick;
import main.elements.brickProperties.SteelBrick;
import main.elements.playerProperties.Player;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This class specifies the properties of the wall, that activates when the game is played. The wall contains all components that are in the gameplay
 *
 * Refactored by:
 * @author LooXuenYi
 */
public class Wall {

    //Variable Declaration
    private static final int LEVELS_COUNT = 4;
    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private Random rnd;
    private Rectangle area;
    public Brick[] bricks;
    public Ball ball;
    public Player player;
    private Brick[][] levels;
    private int level;
    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    /**
     * This constructor includes all necessary properties of the wall to be called when used
     *
     * @param drawArea
     * @param brickCount
     * @param lineCount
     * @param brickDimensionRatio
     * @param ballPos
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos)
    {
        this.startPoint = new Point(ballPos);

        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPos);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;
    }

    /**
     * This method specifies the properties of the game components when it's on the first level
     *
     * @param drawArea
     * @param brickCnt
     * @param lineCnt
     * @param brickSizeRatio
     * @param type
     * @return tmp
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type)
    {
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;
    }

    /**
     * This method specifies the properties of the game components when it's in the chess board level (Level 2 and above)
     *
     * @param drawArea
     * @param brickCnt
     * @param lineCnt
     * @param brickSizeRatio
     * @param typeA
     * @param typeB
     * @return tmp
     */
    //duplicated with the above
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB)
    {
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * This method renders the rubber ball
     *
     * @param ballPos
     */
    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }

    /**
     * This method specifies which type of level are in each level
     *
     * @param drawArea
     * @param brickCount
     * @param lineCount
     * @param brickDimensionRatio
     * @return tmp
     */
    private Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio)
    {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        return tmp;
    }

    /**
     * This method allows the ball and player to move within the wall
     */
    public void move()
    {
        player.move();
        ball.move();
    }

    /**
     * This method allows the ball to make an impact
     */
    public void findImpacts()
    {
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            brickCount--;
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * This method allows the ball to make a bounce whenever it hits the sides of the brick
     */
    private boolean impactWall()
    {
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.down, BrickCrack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.up, BrickCrack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.right, BrickCrack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.left, BrickCrack.LEFT);
            }
        }
        return false;
    }

    /**
     * This method allows the ball to make a bounce whenever it hits the sides of the wall
     *
     * @return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())))
     */
    private boolean impactBorder()
    {
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * This method returns the brick count
     *
     * @return brickCount
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     * This method returns the count of balls left during a gameplay
     * @return ballCount
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * This method checks if the ball falls out of the frame
     * @return ballLost
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * This method resets the position of the ball whenever needed
     */
    public void ballReset()
    {
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    /**
     * This method resets the components of the wall whenever needed
     */
    public void wallReset()
    {
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    /**
     * This method sets the number of balls to zero when it's used up
     *
     * @return ballCount == 0
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * This method sets the brick count to zero when there are no more bricks
     *
     * @return brickCount == 0
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * This method specifies what happens when the game proceeds to the next level
     */
    public void nextLevel()
    {
        if (level<3) {
            bricks = levels[level++];
        }

        this.brickCount = bricks.length;
    }

    /**
     * This method checks if there are more levels to go
     *
     * @return level < levels.length
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * This method sets the ball's speed on the x axis
     *
     * @param s
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    /**
     * This method sets the ball's speed on the y axis
     *
     * @param s
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     * This method resets the ball count to 3 whenever needed
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * This method renders the specifies the properties of the brick within the walls
     *
     * @param point
     * @param size
     * @param type
     * @return out
     */
    private Brick makeBrick(Point point, Dimension size, int type)
    {
        Brick out;
        switch(type){
            case CLAY:
                out = new ClayBrick(point, size);
                break;
            case STEEL:
                out = new SteelBrick(point, size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            default:
                throw new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return out;
    }

}
