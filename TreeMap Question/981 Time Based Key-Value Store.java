/* 981. Time Based Key-Value Store

Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.

Implement the TimeMap class:

TimeMap() Initializes the object of the data structure.
void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. 
If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 

Example 1:

Input
["TimeMap", "set", "get", "get", "set", "get", "get"]
[[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]

Output
[null, null, "bar", "bar", null, "bar2", "bar2"]

Explanation
TimeMap timeMap = new TimeMap();
timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
timeMap.get("foo", 1);         // return "bar"
timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
timeMap.get("foo", 4);         // return "bar2"
timeMap.get("foo", 5);         // return "bar2"
 

Constraints:

1 <= key.length, value.length <= 100
key and value consist of lowercase English letters and digits.
1 <= timestamp <= 10^7
All the timestamps timestamp of set are strictly increasing.
At most 2 * 10^5 calls will be made to set and get.
*/

class TimeMap {
    //      key             timestamp  value
    HashMap<String, TreeMap<Integer, String>> map;
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        //check whether can find the key in the previous element
        if(!map.containsKey(key)){
            //如果之前没有储存过这个key值，开一个新的treemap空间，来储存当前key值对应的value和时间戳timestamp
            TreeMap<Integer, String> treemap = new TreeMap<>();
            treemap.put(timestamp, value);
            map.put(key, treemap); //储存当前key值对应的value和时间戳timestamp
        } else{ //如果之前已经有储存过这个key值，则直接储存进对应的key值空间
            map.get(key).put(timestamp, value);
        }
    }
    
    public String get(String key, int timestamp) {
        if(map.containsKey(key)){
            if(map.get(key).containsKey(timestamp)){
                return map.get(key).get(timestamp); //输出对应的value值
            } else{ //如果当前key值在这个timestamp没有对应的 value值，
                //则需要找到小于等于 且最接近当前timestamp, 对应的 value值
                Integer prevTimeStamp = map.get(key).floorKey(timestamp); //floorKey(): 用来取的不大于当前timestamp的 最接近的previous timestamp值
                //再次检查这个timestamp值是否存在
                if(prevTimeStamp != null){
                    return map.get(key).get(prevTimeStamp); //输出这个最接近的timestamp 对应的value值
                }
            }
        }
        return "";
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
