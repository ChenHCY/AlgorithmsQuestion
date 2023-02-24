/* Tiktok OA3 Question 3 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner sc = new Scanner(System.in);
        int[] inputNumber = new int[2];
        for(int i = 0; i < 2; i++){
            inputNumber[i] = sc.nextInt();
        }
        //get the input from STDIN
        int N = inputNumber[0];
        int M = inputNumber[1];
        //The result used output to STDOUT
        int resWays = 0;
        
        /*there is two requirment:
        1. x + y + z = N
        2. 10x + 30y + 50z = M
        
        ==> it can get the rules: 4x + 2y = 5N - M/10
        ==> And use the x + y <= N to find the how many difference ways         
        */
        
        int x = 0;
        //travser all the element about x and y value
        while(x <= N){
            int y = 0;
            while(y <= N){
                if((4 * x) + (2 * y) == (5 * N) - (M / 10)){
                    if(x + y <= N){
                        resWays += 1;
                    }
                } 
                y++;
            }
            x++;
        }
        
        System.out.print(resWays);
        
    }
}
