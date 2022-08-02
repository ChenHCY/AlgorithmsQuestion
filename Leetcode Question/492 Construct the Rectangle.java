/* A web developer needs to know how to design a web page's size. So, given a specific rectangular web page’s area, 
your job by now is to design a rectangular web page, whose length L and width W satisfy the following requirements:

The area of the rectangular web page you designed must equal to the given target area.
The width W should not be larger than the length L, which means L >= W.
The difference between length L and width W should be as small as possible.
Return an array [L, W] where L and W are the length and width of the web page you designed in sequence.

 
Example 1:
Input: area = 4
Output: [2,2]
Explanation: The target area is 4, and all the possible ways to construct it are [1,4], [2,2], [4,1]. 
But according to requirement 2, [1,4] is illegal; according to requirement 3,  [4,1] is not optimal compared to [2,2]. So the length L is 2, and the width W is 2.

Example 2:
Input: area = 37
Output: [37,1]

Example 3:
Input: area = 122122
Output: [427,286]
 

Constraints:
1 <= area <= 107
*/

class Solution {
    public int[] constructRectangle(int area) {
        int w = 0; // the width
        int l = 0; // the length
        int diff = 0; // the current difference value between the length and width
        int minDiff = Integer.MAX_VALUE; //use for compare and find the min difference value
        List<Integer> list = new ArrayList<>();
        int[] res = new int[2]; //output the array [L, W]
        
        // width * length = area
        for(int i = 1; i <= Math.sqrt(area); i++){
            if(area % i == 0){ //It means 'i' can be this area the width value
                w = i;  //get the current width
                l = area / i; // width * length = area => current length = area / i(current width)
                
                // 3. The difference between length L and width W should be as small as possible.
                diff = l - w; //get the difference value between length with width
                if(diff >= 0 && diff < minDiff){ //The width W should not be larger than the length L, which means L >= W. L-M must be large or equal to 0
                    list.clear(); //if find the less difference value, clear prev width value, and length value from list
                    list.add(l); //add length into array list ==> array [L, W]
                    list.add(w); // add width into array list ==> array [L, W]
                }
            }
        }
        // Through the arraylist ==> get the result list array
        int z = 0;  //help to move pointer in the list array
        for(int j = 0; j < list.size(); j++){
            res[z] = list.get(j); // add the arraly list element valut into list array
            z++;
        }
        return res; //get final result array [L, W]
    }
}
