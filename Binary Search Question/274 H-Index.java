/* 274. H-Index

Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return the researcher's h-index.

According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the given researcher has 
published at least h papers that have each been cited at least h times.

Example 1:
Input: citations = [3,0,6,1,5]
Output: 3
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.

Example 2:
Input: citations = [1,3,1]
Output: 1
 

Constraints:
n == citations.length
1 <= n <= 5000
0 <= citations[i] <= 1000
*/
//Time: O(n * logn)  Space: O(1)
class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations); 
        int n = citations.length;
        int left = 0;
        int right = citations[n - 1];

        //Time: O(logn)
        while(left < right - 1){
            int mid = left + (right - left) / 2;
            int citePapers = countPaper(citations, mid);
        
            //如果该作者的被引用次数的论文大等于于mid值(h-index)，因为我们是要找最大值，所以要提高标准(h-index)
            if(citePapers >= mid){
                left = mid;
            } else{ //如果数量少于mid值(h-index)，降低标准(h-index)
                right = mid - 1;
            }
        }

        //检查右指针对应的满不满足要求, 有至少right篇文章被引用right次数
        if(countPaper(citations, right) >= right){
            return right;
        }

        return left;
    }

    //check how many papers that have each been cited at least h times.
    //time: O(n)
    public int countPaper(int[] citations, int requ){
        int count = 0;

        for(int num : citations){
            if(num >= requ){
                count++;
            }
        }

        return count;
    }
}
