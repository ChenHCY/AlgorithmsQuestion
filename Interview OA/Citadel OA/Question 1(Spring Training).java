/* Citadel OA Question 1 (Spring Training) 

Example；

Input: n = 5    Sprints = [2, 4, 1, 3]

2 -> 4 -> 1 -> 3

Find the most visted and smallest value

Output: 2

*/

class Result {

    /*
     * Complete the 'getMostVisited' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY sprints
     */

    public static int getMostVisited(int n, List<Integer> sprints) {
        //Used to store every spring range
        int[] inSprings = new int[n + 2];
    
        //travser and get all the range 
        for(int i = 0; i < sprints.size() - 1; i++){
            int start = Math.min(sprints.get(i), sprints.get(i + 1));
            int end = Math.max(sprints.get(i), sprints.get(i + 1));
            
            inSprings[start]++;
            inSprings[end + 1]--;
        }
        
        //get the visited times list
        int[] visited = new int[n + 1];
        int visitedTimes = 0;
        for(int j = 1; j < n + 1; j++){
            visitedTimes += inSprings[j];
            visited[j] = visitedTimes;
        }
        
        int res = 0;
        for(int k = 1; k < n + 1; k++){
            if(visited[k] > visited[res]){
                res = k;
            }
        }
        
        return res;
    }
}
