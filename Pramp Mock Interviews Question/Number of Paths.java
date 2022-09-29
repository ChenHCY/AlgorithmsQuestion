/* Pramp Mock Interviews Question: Number of Paths

You’re testing a new driverless car that is located at the Southwest (bottom-left) 
corner of an n×n grid. The car is supposed to get to the opposite, Northeast (top-right), 
corner of the grid. Given n, the size of the grid’s axes, write a function numOfPathsToDest 
that returns the number of the possible paths the driverless car can take.

For convenience, let’s represent every square in the grid as a pair (i,j). 
The first coordinate in the pair denotes the east-to-west axis, and the second coordinate 
denotes the south-to-north axis. The initial state of the car is (0,0), and the destination is (n-1,n-1).

The car must abide by the following two rules: it cannot cross the diagonal border. 
In other words, in every step the position (i,j) needs to maintain i >= j. 
See the illustration above for n = 5. In every step, it may go one square North (up), 
or one square East (right), but not both. E.g. if the car is at (3,1), it may go to (3,2) or (4,1).

Explain the correctness of your function, and analyze its time and space complexities.

Example:

input:  n = 4

output: 5 # since there are five possibilities:
          # “EEENNN”, “EENENN”, “ENEENN”, “ENENEN”, “EENNEN”,
          # where the 'E' character stands for moving one step
          # East, and the 'N' character stands for moving one step
          # North (so, for instance, the path sequence “EEENNN”
          # stands for the following steps that the car took:
          # East, East, East, North, North, North)
*/

//Idea: Use the recursion to save value and move into next spaces

import java.io.*;
import java.util.*;

class Solution {

  static int numOfPathsToDest(int n) {
    // your code goes here
    
    // The initial state of the car is (0,0),
    int startX = 0; //start position in x
    int startY = 0; //start position in y
    
    int res = numPaths(startX, startY, n);
    
    return res;
  }
  
  //recursion method
  private static int numPaths(int x, int y, int n){
    int endX = n - 1;
    int endY = n - 1;
    int totalPath = 0; // count the total paths
    
    //exit the recursion condition, arrived the destination (n-1,n-1).
    if((x == endX) && (y == endY)){
      totalPath += 1;
    } else{
      // 1. In other words, in every step the position (i,j) needs to maintain i >= j.
      if((x >= y) && (x < n) && (y < n)){ 
        //2.  it may go one square North (up), or one square East (right), but not both. 
        //North:
        totalPath += numPaths(x, y + 1, n);
        //East:
        totalPath += numPaths(x + 1, y, n);
      }
    }
    
    return totalPath;
  }

  public static void main(String[] args) {
    System.out.println(numOfPathsToDest(4));
  }

}
