<b>Set Solver</b>

The problem at hand is to write a solver for the game of Set. In case you're not familiar with the game, you can find a tutorial here:

http://www.setgame.com/sites/default/files/tutorials/tutorial/SetTutorial.swf

Each card has symbols on it that vary across an arbitrary number of dimensions: color, number, shape, shading etc. There are 
an arbitrary number of possible values per dimension (e.g. there are three possible colors: red, green, and purple). Start 
by writing a solver that takes in a collection of these cards (e.g. 9 cards, 12 cards, 15 cards, etc.), and returns all 
possible sets that can be made from the inputs.

<b> Note: In the traditional game of set, there are 4 dimension and 3 values. In addition, a set consists of 3 cards. In this version
we solve for an arbitrary number of cards in the set specified as an argument to the constructor for our solver.</b>

<b>Unit Tests:</b> Under the 'tests' directory there are tests written in Junit4 for all classes except the 'Player' class. The player 
class includes a 'main' and can be modified to specify an arbitrary number of dimensions, values and cards. Currently these values are
specified as 4, 3 and 3 respectively.
<b>Running the code:</b>
* Under Eclipse: Import the project. Run unit tests or player class directly
* Command Line: Build the jar file (mvn clean install), and run (java -jar BoardGameSet-0.0.1-SNAPSHOT.jar).
