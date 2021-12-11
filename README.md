#COMP2042 SM Coursework - BRICK DESTROYER

#REFACTORING ACTIVITIES:
1) Grouped Classes that have an immediate similarity into relevant packages. 	
   - The classes `Ball` and `Rubber Ball` are in the Package `ballProperties`.
   - The classes `Brick`, `CementBrick`, `ClayBrick`, `SteelBrick` are put into the package `brickProperties`.
   - The classes `Player` and `Wall` are put into packages `playerProperties` and `wallProperties`.
   - The classes that are related to the Home Menu are put into a package, Instruction Page classes into one, and Debug Console into one.
2) Extracted the “Crack” nested class in “Brick” and renamed it as `BrickCrack` for better readability.
3) Renamed:
   - `GraphicsMain` into `Main`
   - `GameBoard` into `GameplayScreen`
   - `DebugPanel` into `RenderDebugConsole`
   - `Crack` into `BrickClass`
4) Rearranged Packaged into M-V-C.
   - The `model` packages are `ballProperties`, `brickProperties`, and `playerProperties`.
   - The `view` packages are `debugConsole`, `homeMenu`, `gameBoard` and `instructionPage`.
   - The `controller` packages are `gameFrameController`,and `wallProperties`, and class `GameFrame` in it, and class `Main` is in the controller package.
5) Beautify the code to improve readability by:
   - Removing extra lines
   - Method indenting
   - Constant break lines throughout the file

#GAME ADDITIONS ACTIVITIES
1) Added ‘background music’ to the game.
   - Implemented by using ‘AudioSystem.getAudioInputStream’ in `Main`
2) Added a ‘view score’ to the gameplay screen.
   - Implemented by adding a controller method in `Wall` and view method in `GameplayScreen`
3) Added an ‘instructions page’ at ‘home menu’.
   - Created a class `InstructionImage` to pass in the image to another class created called `InstructionPage`, which the `GameFrame` redirects the user to the instructions page when clicked on the ‘Info’ Button in the ‘Home Menu’
4) Added a ‘brick picture’ at ‘home menu’
   - Created an `Image` class that passes in the brick image into `HomeMenu`
5) Changed the ‘color theme’ of the game to ‘soft yellow’.
   - Changed the variable ‘BG_COLOR’ is changed to ‘new Color(255,239,150)’ in `GameplayScreen`
6) Added an ‘additional level’ to the game.
   - Changed ‘LEVEL_COUNT’ in `Wall` and set the bricks as fully ‘steel’
7) Changed the window title of `DebugConsole` from ‘Debug Console’ to ‘Settings (Debug Console)’.

#PROJECT RELATED ADDITIONS
1) Included Gradle as a build tool
2) Included a Javadocs folder that contains the Javadocs files

#REPOSITORY
url: https://github.com/KenjiLoo/COMP2042_CW_hfyxl3/

#SUMMARY
The main changes of the code to the original file is that it is a Gradle project, refactored into packages in an M-V-C style, implemented SOLID, and the additions which are background game audio, instruction page, a different background on home menu, an extra level, and a view score. 

 