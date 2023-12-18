/*Leetcode 2353. Design a Food Rating System

Design a food rating system that can do the following:

    · Modify the rating of a food item listed in the system.
    · Return the highest-rated food item for a type of cuisine in the system.

Implement the FoodRatings class:
    · FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system. The food items are described by foods, cuisines and ratings, all of which have a length of n.
        · foods[i] is the name of the ith food,
        · cuisines[i] is the type of cuisine of the ith food, and
        · ratings[i] is the initial rating of the ith food.
   · void changeRating(String food, int newRating) Changes the rating of the food item with the name food.
   · String highestRated(String cuisine) Returns the name of the food item that has the highest rating for the given type of cuisine. 
     If there is a tie, return the item with the lexicographically smaller name.

Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a 
prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.

Example 1:
Input
["FoodRatings", "highestRated", "highestRated", "changeRating", "highestRated", "changeRating", "highestRated"]
[[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16], ["japanese"]]
Output
[null, "kimchi", "ramen", null, "sushi", null, "ramen"]

Explanation
FoodRatings foodRatings = new FoodRatings(["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]);
foodRatings.highestRated("korean"); // return "kimchi"
                                    // "kimchi" is the highest rated korean food with a rating of 9.
foodRatings.highestRated("japanese"); // return "ramen"
                                      // "ramen" is the highest rated japanese food with a rating of 14.
foodRatings.changeRating("sushi", 16); // "sushi" now has a rating of 16.
foodRatings.highestRated("japanese"); // return "sushi"
                                      // "sushi" is the highest rated japanese food with a rating of 16.
foodRatings.changeRating("ramen", 16); // "ramen" now has a rating of 16.
foodRatings.highestRated("japanese"); // return "ramen"
                                      // Both "sushi" and "ramen" have a rating of 16.
                                      // However, "ramen" is lexicographically smaller than "sushi".
 
Constraints:
1 <= n <= 2 * 10^4
n == foods.length == cuisines.length == ratings.length
1 <= foods[i].length, cuisines[i].length <= 10
foods[i], cuisines[i] consist of lowercase English letters.
1 <= ratings[i] <= 10^8
All the strings in foods are distinct.
food will be the name of a food item in the system across all calls to changeRating.
cuisine will be a type of cuisine of at least one food item in the system across all calls to highestRated.
At most 2 * 10^4 calls in total will be made to changeRating and highestRated.
*/

class FoodRatings {
    //创建一个 Pair class，来连接每个食物的名字，烹饪方式 和 评分
    class Pair {
        String name; //食物名字
        String cuis; //烹饪方式
        int rating; //评分
        Pair(String n, String c, int r){
            name = n;
            cuis = c;
            rating = r;
        }
    }

    //两个HashMap, 用来储存资料和查找
    //第一个HashMap: key: 食物名字， Value: Pair(名字，烹饪方式，评分)
    HashMap<String, Pair> foodMap = new HashMap<>();

    //第二个HashMap: key: 烹饪方式，Value: TreeSet<Pair> 所以这个烹饪方式的食物，并且按照ratting排名
    HashMap<String, TreeSet<Pair>> cuisMap = new HashMap<>();
    
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for(int i = 0; i < foods.length; i++){
            Pair pair = new Pair(foods[i], cuisines[i], ratings[i]); //把信息储存进Pair
            foodMap.put(foods[i], pair); // 把食物的名字 和 对应的信息 存入 HashMap中
            
            //把食物存入对应的烹饪方式的hashmap中
            if(!cuisMap.containsKey(cuisines[i])){
                //如果之前没有这个烹饪方式，开一个新空间
                cuisMap.put(cuisines[i], new TreeSet<>((a, b) -> {
                    //TreeSet中按照rating的评分从高到低进行排序
                    if(a.rating != b.rating){
                        return b.rating - a.rating;
                    }
                    return a.name.compareTo(b.name); //如果评分一样，按照食物名字的字典序排序存入
                }));
            }
            cuisMap.get(cuisines[i]).add(pair);  //如果已经有个烹饪方式，则直接把当前食物加入到对应烹饪方式的treeset中
        }
    }
    //改变食物的评分
    public void changeRating(String food, int newRating) {
        // 首先找到当前食物所有的对应信息
        Pair pair = foodMap.get(food);
        // 在烹饪方式对应的TreeSet中 删除 当前的食物的，然后再重新加入
        cuisMap.get(pair.cuis).remove(pair); 
        pair.rating = newRating;
        cuisMap.get(pair.cuis).add(pair); //重新加入对应的烹饪方式进行排序
    }
    
    //找到烹饪方式中 最高评分的食物
    public String highestRated(String cuisine) {
        //指定cuisine对应的TreeSet的第一个数，就是当前烹饪方式的最高评分食物名字
        return cuisMap.get(cuisine).first().name; //first(): TreeSet()中的第一个食物
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
