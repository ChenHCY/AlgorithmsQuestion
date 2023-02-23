/* Citadel OA Question 2 Visiting Cities 

Input: red = [2, 3, 4]  blue = [3, 1, 1]  blueCost = 3

Output: [0, 2, 5, 6]

*/

class Result {

    /*
     * Complete the 'minimumCost' function below.
     *
     * The function is expected to return a LONG_INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY red
     *  2. INTEGER_ARRAY blue
     *  3. INTEGER blueCost
     */

    public static List<Long> minimumCost(List<Integer> red, List<Integer> blue, int blueCost) {
        int length = red.size();
        List<Long> res = new ArrayList<>();
        
        //dp state:
        long[][] dp = new long[length + 1][2];
        //dp start:
        dp[0][0] = 0;
        dp[0][1] = blueCost;
        
        res.add((long) 0);
        
        //dp function:
        for(int i = 1; i <= length; i++){
            dp[i][0] = (long)Math.min(dp[i-1][0] + red.get(i - 1), dp[i-1][1] + red.get(i-1));
            dp[i][1] = (long)Math.min(dp[i-1][1] + blue.get(i - 1), dp[i-1][0] + blue.get(i-1) + blueCost);
            res.add(Math.min(dp[i][1], dp[i][0]));
        }
        
        //dp return
        return res;
    }

}
