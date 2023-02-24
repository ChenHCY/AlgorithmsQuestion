/* SIG Question 1: Design HashMap
You've created a new programming language, and now you've decided to add hashmap support to it. 
Actually, you are quite disappointed that in common programming languages it's impossible to add 
a number to all hashmap keys, or all its values. So you've decided to take matters into your own 
hands and implement your own hashmap in your new language that has the following operations:

insert x y - insert an object with key x and value y.
get x - return the value of an object with key x.
addToKey x - add x to all keys in map.
addToValue y - add y to all values in map.

To test out your new hashmap, you have a list of queries in the form of two arrays: queryTypes 
contains the names of the methods to be called (eg: insert, get, etc), and queries contain the arguments 
for those methods (the x and y values).

Your task is to implement this hashmap, apply the given queries, and find the sum of all the results for getting operations.

Example
For queryType = ["insert", "insert", "addToValue", "addToKey", "get"] and query = [[1, 2], [2, 3], [2], [1], [3]], the output should be hashMap(queryType, query) = 5.
The hashmap looks like this after each query:
1 query: {1: 2}
2 query: {1: 2, 2: 3}
3 query: {1: 4, 2: 5}
4 query: {2: 4, 3: 5}
5 query: answer is 5
*/

class Question1 {
    public static void main(String[] args) {
        String[] queryType = new String[]{"insert", "insert", "addToValue", "addToKey", "get"};
        int[][] query = new int[][]{{1, 2}, {2, 3}, {2}, {1}, {3}};
        long res = desginHashMap(queryType, query);
        System.out.println(res);
    }
    
    public static long desginHashMap(String[] queryType, int[][] query) {
        int currKey = 0;
        int currValue = 0;
        long sum = 0;
        Map<Integer, Integer> map = new HashMap<>(); 
      
        //trasver all the element through queryType
        for(int i = 0; i < queryType.length; i++){
            String currType = queryType[i];
            switch(currType){
                case "insert":
                    HashMap<Integer, Integer> copy = new HashMap<>();
                    if(currKey != 0 || currValue != 0) {
                        Set<Integer> keys = map.keySet();
                        for(int key : keys){
                            copy.put(key + currKey, map.get(key) + currValue);
                        }
                        map.clear();
                        map.putAll(copy);
                        currValue = 0;
                        currKey = 0;
                    }
                    map.put(query[i][0], query[i][1]);
                    break;
                    
                case "addToValue":
                    currValue += map.isEmpty() ? 0 : query[i][0];
                    break;
                
                case "addToKey":
                    currKey += map.isEmpty() ? 0 : query[i][0];
                    break;
                
                case "get":
                    copy = new HashMap<>();
                    if(currKey != 0 || currValue != 0) {
                        Set<Integer> keys = map.keySet();
                        for(int key : keys){
                            copy.put(key + currKey, map.get(key) + currValue);
                        }
                        map.clear();
                        map.putAll(copy);
                        currValue = 0;
                        currKey = 0;
                    }
                    sum += map.get(query[i][0]);
            }
        }
        return sum;
    }
}
