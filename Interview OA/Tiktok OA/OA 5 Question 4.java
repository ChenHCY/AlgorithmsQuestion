/*Question 4 Merge Intervals  -- 56. Merge Intervals */

class Result {

    /*
     * Complete the 'mergeIntervals' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts 2D_INTEGER_ARRAY intervals as parameter.
     */

    public static List<List<Integer>> mergeIntervals(List<List<Integer>> intervals) {
    // Write your code here
        //basic solution
        if(intervals.size() <= 1){
            return intervals;
        }
        
        List<List<Integer>> res = new ArrayList<>(); //used for save the answer
        
        //Sort all the elemnt from intervals based on the first number
        intervals.sort((x, y) -> Integer.compare(x.get(0), y.get(0)));
        
        //travser all the element from intervals and add into res list
        for(List<Integer> arr : intervals){
             //res is empty || or not Overlapping intervals
            if(res.isEmpty() || res.get(res.size() - 1).get(1) < arr.get(0)){
                res.add(arr); //just add the list into res
            } else{ //overlapping the intervals ==> move the end if needed
                int temp = Math.max(res.get(res.size() - 1).get(1), arr.get(1));
                res.get(res.size() - 1).set(1, temp);
            }
        }
        
        return res;
    }

}
