/* 380. Insert Delete GetRandom O(1)

Implement the RandomizedSet class:

  · RandomizedSet() Initializes the RandomizedSet object.
  · bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
  · bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
  · int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.

You must implement the functions of the class such that each function works in average O(1) time complexity.

Example 1:
Input
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
Output
[null, true, false, true, 2, true, false, 2]

Explanation
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
randomizedSet.insert(2); // 2 was already in the set, so return false.
randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 

Constraints:
-2^31 <= val <= 2^31 - 1
At most 2 * 105 calls will be made to insert, remove, and getRandom.
There will be at least one element in the data structure when getRandom is called.
*/

// 思路：ArrayList + HashMap => 在remove的时候，用list末尾的那个元素替换移除的元素：
// ArrayList可以在 O(1)的时间内完成获取随机元素操作，但是由于无法在 O(1) 的时间内判断元素是否存在
// HashMap 可以在 O(1)的时间内完成判断元素是否存在，但无法根据下标定位到特定元素 ==> 无法进行获取随机元素的操作
class RandomizedSet {
    List<Integer> num;
    Map<Integer, Integer> map;
    Random random; // Java Random Class: 创建一个 Random 对象

    public RandomizedSet() {
        num = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }
    
    //加入新的num
    public boolean insert(int val) {
        //首先判断是否之前存在, 如果存在输出false
        if(map.containsKey(val)){
            return false;
        }

        num.add(val); //把新值加入到list的最后
        map.put(val, num.size() - 1); //把新值和对应的index, 储存进hashmap
        return true;
    }
    
    //删除指定的值
    public boolean remove(int val) {
        //如果不存在这个值，输出false;
        if(!map.containsKey(val)){
            return false;
        }

        //如果存在：找到需要删除值的index, 把list最后一个数字与其进行替换，保证index的连续，方便random()
        int deleteIndex = map.get(val); //删除数字的index
        int lastNum = num.get(num.size() - 1); //num list中最后一个数字
        num.set(deleteIndex, lastNum); //使用最后一个数字替换需要删除数组的位置
        map.put(lastNum, deleteIndex); //更新最后一个数组的index位置
        
        /*此时num list 有两个最后一个数字lastNum  删除最后一个数字*/
        num.remove(num.size() - 1);

        map.remove(val); //从hashmap中删除指定的数字
        return true;
    }
    
    public int getRandom() {
        // nextInt(nums.size() 生成一个介于0（包含）和 nums.size()（不包含）之间的随机整数。
        int randomIndex = random.nextInt(num.size()); //randomIndex 就是在这个集合中随机选择的索引。
        return num.get(randomIndex); //就可以随机生成一个num list的索引，用于访问数组或集合中的随机元素。
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
