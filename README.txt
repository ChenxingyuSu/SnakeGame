Chenxingyu Su
20755516 c35su
openjdk version "11.0.8" 2020-07-14
Windows 10, intellij gradle Lenovo YOGA 730 13"

Regardding the generation of fruit, it will not generate on the snake's body when generating random location. It checks the location of snake, if the location 
overlaps, the generator will generate another fruit. For the positions that are set at the beginning, if the snake overlaps with the fruit, then the fruit will
simply just do not show. 

Game window is changed to 1280x680 pixels since the screen resolution on my computer is less than expected (1280x800 pixels).

Clarification on the approach of Keyboard shortcuts R, Q:
R:
When R is typed on keyboard, the snake game will go back to the splash screen. However, it will not reset the position of the snake.
When you start the game again from the splash screen (without closing the game window/terminating te program), 
the position of snake is the same as when the user switch to the splash screen.

Q:
Once the user is at final score screen, the user is unable to use any of the keyboards shortcuts. (i.e. even if the user type 'R', 
the game will not reset to the splash screen. The user must close the program and restart it again.)

The program will not return even if the final screen is shown. The user has to manually close/terminate the program.

Parts that are incomplete/still needs to work on:
During level 2 and 3, the snake body will disconnect. To fix this, I tried to change the length of each grid, or make the snake body smaller/bigger accordingly.
However, all of those approaches will change the width and height of the grid/the snake body. I know I get this problem because when I update the position of 
each piece of snack body, I set the one piece of body to equal to the piece before it.