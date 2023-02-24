/* Zoom OA Question 1
find the non-zeros number start at left; then travser to minus it in every number

Inupt: [3, 3, 3, 5, 2, 3]
Output: 6 ===> 3 +2 + 1 = 6
*/

class Question2 {
    public static void main(String[] args) {
        int[] number = new int[]{3, 3, 3, 5, 2, 3};
        int res = question(number);
        System.out.println(res);
    }
    
    public static int question(int[] nums){
        int res = 0;
        int i = 0;
        
        while(i < nums.length){
            int temp = 0;
            if(nums[i] > 0){
                temp = nums[i];
                res += temp;
                for(int j = i; j < nums.length; j++){
                    if(nums[j] >= temp){
                        nums[j] = nums[j] - temp;
                    } else{
                        break;
                    }
                }
            }
            i++;
        }
        
        return res;
    }
}
