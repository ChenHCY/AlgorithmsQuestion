/* 275. H-Index II

Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper 
and citations is sorted in ascending order, return the researcher's h-index.

According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the 
given researcher has published at least h papers that have each been cited at least h times.

You must write an algorithm that runs in logarithmic time.

 
Example 1:
Input: citations = [0,1,3,5,6]
Output: 3
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had received 0, 1, 3, 5, 6 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.

Example 2:
Input: citations = [1,2,100]
Output: 2
 

Constraints:
n == citations.length
1 <= n <= 105
0 <= citations[i] <= 1000
citations is sorted in ascending order.
*/
//二分法查找，同时判断是否满足要求
//Time: O(n*logn)  Space: O(1)
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0;
        int right = citations[n - 1];

        while(left < right - 1){
            int mid = left + (right - left) / 2;
            int papers = countPapers(citations, mid);

            //如果有mid个论文引用次数大于mid次，提高标准(H-index)
            if(papers >= mid){
                left = mid; 
            } else{ //如果数量不够mid个，降低标准(H-index)
                right = mid - 1;
            }
        }

        //最后check左右指针哪个符合标准
        if(countPapers(citations, right) >= right){
            return right;
        }

        return left;
    }

    //check how many papers citations times is more than target
    public int countPapers(int[] citations, int target){
        int count = 0;

        for(int num : citations){
            if(num >= target){
                count++;
            }
        }

        return count;
    }
}
