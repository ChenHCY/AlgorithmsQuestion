/* Leetcode 1231 Divide Chocolate

You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.

You're going to share this chocolate with K friends, so you need to cut K times to get K + 1 pieces, each of which is made up of a series of small pieces.

Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.

Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.


Example 1:

Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
Output: 6
Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]

Example 2:

Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
Output: 1
Explanation: There is only one way to cut the bar into 9 pieces.

Example 3:

Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
Output: 5
Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
*/

public class Solution {
   
    public int maximizeSweetness(int[] sweetness, int k) {
        // write your code here
        int min = Integer.MAX_VALUE;
        int sum = 0;
        
      //找到最大值和最小值
        for(int i = 0; i < sweetness.length; i++){
            min = Math.min(min, sweetness[i]);
            sum += sweetness[i];
        }

        if(k == 0){
            return sum;
        }

        if(k == sweetness.length){
            return min;
        }

        //Binary Search Method
        int left = min;
        int right = sum;

        while(left < right - 1){
            int mid = left + (right - left) / 2;
             //符合要求 增大我的甜度 移动左指针
            if(checkSweet(sweetness, mid, k + 1)){
                left = mid;
            } else{ //不符合要求 减少我的甜度
                right = mid;
            }
        }

        return checkSweet(sweetness, left, k + 1) ? left : right;
    }

  //检查在当前甜度的下，是否能分成有K+1个块巧克力
    public boolean checkSweet(int[] sweetness, int mySweetness, int firend){
        int count = 0;
        int sweetSum = 0;
        for(int i = 0; i < sweetness.length; i++){
            sweetSum += sweetness[i];
            if(sweetSum >= mySweetness){
                count++;
                sweetSum = 0;
            }
        }
        return count >= firend;
    }
}
