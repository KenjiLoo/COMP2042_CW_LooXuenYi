package gameMain.controller.gameFrameController;

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
import gameMain.view.homeMenu.HomeMenu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;
import gameMain.view.instructionPage.InstructionPage;

/**
 * This class specifies the layout of the Game GUI
 *
 * Refactored by:
 * @author LooXuenYi
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    // Variables Declaration
    private static final String DEF_TITLE = "Brick Destroy";
    private GameplayScreen gameBoard;
    private HomeMenu homeMenu;
    private InstructionPage instructionPage;
    private boolean gaming;

    /**
     * This constructor conducts the formation of the game window layout
     */
    public GameFrame() throws IOException {
        super();
        gaming = false;
        this.setLayout(new BorderLayout());
        gameBoard = new GameplayScreen(this);
        homeMenu = new HomeMenu(this,new Dimension(511,511));

        this.add(homeMenu,BorderLayout.CENTER);

        this.setUndecorated(true);
    }

    /**
     * This method allows the program to load the home menu when started
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * This method renders the game board
     */
    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenu);
        this.add(gameBoard, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * This method is the controller method for the Intruction Page
     */
    public void enableInstructionPage(){
        this.dispose();
        this.remove(homeMenu);
        instructionPage = new InstructionPage(this,new Dimension(511,511));
        this.add(instructionPage,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * This method is the controller method for the Home menu
     */
    public void enableHomeMenu(){
        this.dispose();
        this.remove(instructionPage);
        this.add(homeMenu,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * This method sets the default location of where the window should appear
     */
    private void autoLocate()
    {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    /**
     * This method lets the game window the to be focused
     * @param windowEvent
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent)
    {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * This method checks if the window has lost focus
     *
     * @param windowEvent
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent)
    {
        if(gaming)
            gameBoard.onLostFocus();

    }

}
