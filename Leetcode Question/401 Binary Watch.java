/* Leetcode 401. Binary Watch
A binary watch has 4 LEDs on the top to represent the hours (0-11), and 6 LEDs on the bottom to represent the minutes (0-59). 
Each LED represents a zero or one, with the least significant bit on the right.

For example, the below binary watch reads "4:51".
Given an integer turnedOn which represents the number of LEDs that are currently on (ignoring the PM), 
return all possible times the watch could represent. You may return the answer in any order.

The hour must not contain a leading zero.

For example, "01:00" is not valid. It should be "1:00".
The minute must be consist of two digits and may contain a leading zero.

For example, "10:2" is not valid. It should be "10:02".
 

Example 1:
Input: turnedOn = 1
Output: ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]

Example 2:
Input: turnedOn = 9
Output: []
 

Constraints:

0 <= turnedOn <= 10
*/

class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        
        //Binary Number: 1(01) 2(10) 4(100) 8(1000) 16(10000) 32(100000)
        //So we can see every LEDs number's binary number only has "1" once  
        //Traverse hours: 1-12; and mintues: 1-60
      
        for(int hours = 0; hours < 12; hours++){
            for(int minute = 0; minute < 60; minute++){
              
                //Integer.bitCount(): returns the count of the number of one-bits in the two's to complement the binary representation of an int value.
                if(Integer.bitCount(hours) + Integer.bitCount(minute) == turnedOn){ //it means how many LEDs currently on open
                  
                    //every time renew once and delete prev, let can save every "possible time" be the independent
                    StringBuilder time = new StringBuilder(); 
                    time.append(hours).append(":");
                    if(minute < 10){ // The minute must consist of two digits and may contain a leading zero.
                        time.append(0); //Check whether the minute is less than 10 need add a leading zero
                    }
                    time.append(minute);
                    res.add(time.toString());//save every Stringbuilder("possible time") into ArrayList
                }
            }
        }
        return res;
    }
}
