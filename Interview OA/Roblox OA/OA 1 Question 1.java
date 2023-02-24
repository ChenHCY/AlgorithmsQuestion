/* Roblox OA Question 1

Count All the numbers that are not equal to first or second number of array

Example: Input: int[] nums = [3, 4, 1, 1, 3, 1];
         Output: 3
*/

class Question1 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, 1, 1, 3, 1};
        System.out.println(solution(nums));
    }
    
    public static int solution(int[] nums){
        if(nums.length <= 1){
            return 0;
        }
        
        int res = 0;
        //use a for-loop traverse all the element from nums list
        //and check whether equals first or second number
        for(int i = 2; i < nums.length; i++){
            if(nums[i] != nums[0] && nums[i] != nums[1]){
                res += 1;
            }
        }
        
        return res;
    }
}
