/*Codesingal Zoom OA 2 Question 2*/

int solution(int finish, int[] scooters) {
    
    if(scooters.length == 0){
        return 0;
    }
    
    /*scooters: 4 7 14  Finish: 23 Output: 19 */  
  
    Arrays.sort(scooters);
    int res = 0;
    
    int position = scooters[0];
    int nextP = position + 10;
    
    while(nextP <= finish){
        res += 10;
    
        for(int j = 0; j < scooters.length; j++){
            if(nextP <= scooters[j]){
                position = scooters[j];
                break;
            }
            
            if(nextP > scooters[scooters.length - 1]){
                return res;
            }
        }
        
        nextP = position + 10;
    }
    
    int temp = finish - position;
    return res + temp;
}
