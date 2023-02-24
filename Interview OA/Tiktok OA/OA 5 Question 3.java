/* Tiktok OA 5 Question 3: Find the shortest subsequence of array that can be divided by K。*/

class Result {

    /*
     * Complete the 'findShortestSubArray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY arr
     *  2. INTEGER k
     */

    public static List<Integer> findShortestSubArray(List<Integer> arr, int k) {
    // Write your code here
        List<Integer> res = new ArrayList<>();
        int minLength = Integer.MAX_VALUE;
        int start = -1; // the start position of shortest sub-array
        int end = -1; // the end position of shortest sub-array
        
        for(int i = 0; i < arr.size(); i++){
            int currSum = 0;
            //if the curr length is small than previous length
            for(int j = i; j < arr.size() && (j - i + 1) < minLength; j++){
                currSum += arr.get(j);
                //if the curr sub-arry can divisible by k
                if(currSum % k == 0){
                    start = i;
                    end = j;
                    minLength = end - start + 1;
                    break;
                } 
            }
        }
        
        //if there can find sub-array that sum divible by k ==> return a empty list
        if(start == -1 || end == -1)  {
             return new ArrayList<>(){};
        }
        
        //Add into the result list ==> used start and end pointer position
        while(start <= end){
            res.add(arr.get(start));
            start++;
        }
        
        return res;
    }

}
