/* Ziprecruiter OA  Question 1

Find which position value in the nums[] array that adds together can be large than the target value
*/


class Question1 {
    public static void main(String[] args) {
        int[] nums = new int[]{200, 300, 100, 200, 300};
        int target = 700;
        int res = solution(nums, target);
        System.out.println(res);
    }
    
    public static int solution(int[] num, int target){
        int res = 0;
        int temp = num[0];
        
        for(int i = 1; i < num.length; i++){
            temp += num[i];
            if(temp > target){
                res = i;
                break;
            }
        }
        
        return res;
    }
}


**************************************************************************
int solution(int[] visits, int target) {
    int res = 0;
    int temp = 0;
    
    if(visits.length == 0){
        return -1;
    }
    
    if(visits[0] >= target){
        return 0;
    }
    
    for(int i = 0; i < visits.length; i++){
        temp += visits[i];
        if(temp >= target){
            res = i;
            break;
        } else{
            res = -1;
        }
    }
    
    
    return res;
}
