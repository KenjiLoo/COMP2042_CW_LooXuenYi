package gameMain.view.debugConsole;

/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2021  Loo Xuen Yi
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

import gameMain.view.gameBoard.GameplayScreen;
import gameMain.model.gameElements.ballProperties.Ball;
import gameMain.model.gameElements.wallProperties.Wall;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This method runs the Debug Console by pressing on 'Ctrl'+'alt'+'F1"
 * <p>
 * Refactored by:
 *
 * @author LooXuenYi
 */
public class DebugConsole extends JDialog implements WindowListener {

    private static final String TITLE = "Settings (Debug Console)";

    private final JFrame owner;
    private final RenderDebugConsole debugPanel;
    private final GameplayScreen gameBoard;
    private final Wall wall;

    /**
     * This is a constructor that runs the debug console when called
     *
     * @param owner
     * @param wall
     * @param gameBoard
     */
    public DebugConsole(JFrame owner, Wall wall, GameplayScreen gameBoard)
    {

        this.wall = wall;
        this.owner = owner;
        this.gameBoard = gameBoard;
        initialize();

        debugPanel = new RenderDebugConsole(wall);
        this.add(debugPanel, BorderLayout.CENTER);


        this.pack();
    }

    /**
     * This method includes the functional components of the debug console
     */
    private void initialize()
    {
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    /**
     * This method sets the default position where the debug console should appear
     */
    private void setLocation()
    {
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x, y);
    }

    /**
     * This method checks if the debug console window is opened
     *
     * @param windowEvent
     */
    @Override
    public void windowOpened(WindowEvent windowEvent) {}

    /**
     * This method checks if the debug console is closing
     *
     * @param windowEvent
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    /**
     * This method checks if the debug console is closing
     *
     * @param windowEvent
     */
    @Override
    public void windowClosed(WindowEvent windowEvent) {}

    /**
     * This method deals with animations and stop its animation thread and free any large buffers in the debug console
     *
     * @param windowEvent
     */
    @Override
    public void windowIconified(WindowEvent windowEvent) {}

    /**
     * This method can start the thread again and recreate the buffers in the debug console
     *
     * @param windowEvent
     */
    @Override
    public void windowDeiconified(WindowEvent windowEvent) {}

    /**
     * This method allows the rendering of the game after changes made in the debug console, and to become an active window
     *
     * @param windowEvent
     */
    @Override
    public void windowActivated(WindowEvent windowEvent)
    {
        setLocation();
        Ball b = wall.ball;
        debugPanel.setValues(b.getSpeedX(), b.getSpeedY());
    }

    /**
     * This method allows the debug console to debug console to become a background window
     *
     * @param windowEvent
     */
    @Override
    public void windowDeactivated(WindowEvent windowEvent) {}

}
