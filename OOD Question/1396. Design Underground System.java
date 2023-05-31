/* Leetcode 1396. Design Underground System

An underground railway system is keeping track of customer travel times between different stations. They are using this data to calculate the average time it takes to travel from one station to another.

Implement the UndergroundSystem class:

void checkIn(int id, string stationName, int t)
A customer with a card ID equal to id, checks in at the station stationName at time t.
A customer can only be checked into one place at a time.
void checkOut(int id, string stationName, int t)
A customer with a card ID equal to id, checks out from the station stationName at time t.
double getAverageTime(string startStation, string endStation)
Returns the average time it takes to travel from startStation to endStation.
The average time is computed from all the previous traveling times from startStation to endStation that happened directly, meaning a check in at startStation followed by a check out from endStation.
The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
You may assume all calls to the checkIn and checkOut methods are consistent. If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All events happen in chronological order.

Example 1:

Input
["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
[[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]

Output
[null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]

Explanation
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(45, "Leyton", 3);
undergroundSystem.checkIn(32, "Paradise", 8);
undergroundSystem.checkIn(27, "Leyton", 10);
undergroundSystem.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12
undergroundSystem.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10
undergroundSystem.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14
undergroundSystem.getAverageTime("Paradise", "Cambridge"); // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000. Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11
undergroundSystem.checkIn(10, "Leyton", 24);
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000
undergroundSystem.checkOut(10, "Waterloo", 38);  // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 12.00000. Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12

Constraints:

1 <= id, t <= 106
1 <= stationName.length, startStation.length, endStation.length <= 10
All strings consist of uppercase and lowercase English letters and digits.
There will be at most 2 * 104 calls in total to checkIn, checkOut, and getAverageTime.
Answers within 10-5 of the actual value will be accepted.
*/

/* 此题是计算每个乘客产生上下车产生的路径，然后这些路径平均的花费时间

所以使用两个HashMap， 

1. checkInMap()，记录每个乘客checkin的细节，

储存 key=乘客，value = Pair<Key, value> => 上车地点，上车时间

2. checkOutMap()，记录每个route的细节，route包含了上车地点和下车地点。

储存 key=行驶的路径，value = Pair<Key, value> => 总时间，customer数据的数量

最后调用和查找是否存在这个路径，总时间 /customer数据的数量 =》 计算得到这个路径的平均值

*/

class UndergroundSystem {
    //第一个Hashmap<Key, value>  储存 key=乘客，value = Pair<Key, value> => 上车地点，上车时间
    HashMap<Integer, Pair<String, Integer>> checkInMap;
    //第二个Hashmap<Key, value> 储存 key=行驶的路径，value = Pair<Key, value> => 总时间，数据的数量
    HashMap<String, Pair<Integer, Integer>> checkOutMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        checkOutMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        //key=乘客id，value = Pair<Key, value> => 上车地点，上车时间
        checkInMap.put(id, new Pair<>(stationName, t)); 
    }
    
    public void checkOut(int id, String stationName, int t) {
        //首先提取这个对应customer的上车地点和上车时间
        Pair<String, Integer> customerDetail = checkInMap.get(id);

        //然后产生对应customer的行驶路径, "startStation - endStation"
        String route = customerDetail.getKey() + "-" + stationName;
        //使用的时间， count the usedTime of curr customer, t (checkOut time) - customerDetail.getValue()(checkIn time)
        int usedTime = t - customerDetail.getValue(); 

        //check whether have the previous coustomer with the same route
        //checkOutMap中储存的就是route,和对应使用的时间，所以我们直接检查checkOutMap()中是否含有这个路径
        //通过getOrDefault(), 如果有，就提取之前的值（总时间，数据数量），如果没有，产生一个0值的pair, 用于后面计算。
        Pair<Integer, Integer> perviousCheckOut = checkOutMap.getOrDefault(route, new Pair<>(0, 0));
        //the count the total of the used time, and how many customer finished it
        checkOutMap.put(route, new Pair<>(perviousCheckOut.getKey() + usedTime, perviousCheckOut.getValue() + 1));
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "-" + endStation;
        //如果存在这个路径，拿到总的时间，和customer数量
        Pair<Integer, Integer> countAverageTime = checkOutMap.get(route);
        int totalTime = countAverageTime.getKey();
        int customerNumber = countAverageTime.getValue();
        double averageTime = (double) totalTime / customerNumber;

        return averageTime;
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
