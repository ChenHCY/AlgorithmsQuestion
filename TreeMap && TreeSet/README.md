# TreeMap() in JAVA
In Java, TreeMap is a class that implements the NavigableMap interface and provides a sorted, ordered map implementation based on a binary search tree. 

==> TreeMap是一个实现NavigableMap接口的类，并提供基于二叉搜索树的排序、有序映射实现。

It maintains its elements in a sorted order according to the natural ordering of the keys or a custom comparator provided during its creation.

==> 它根据key的 `自然顺序` 或 `创建过程中提供的自定义比较器` 按排序顺序维护其元素。

总结来说，TreeMap就是比HashMap多一个内部排序的功能，可以是按照Key值的自然大小顺序排序，我们也可以自己定义其内部的排序逻辑

```Java
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        // Create a TreeMap with Integer keys and String values
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        // Add key-value pairs to the TreeMap
        treeMap.put(3, "Value 3");
        treeMap.put(1, "Value 1");
        treeMap.put(2, "Value 2");

        // Access and print the values based on sorted order of keys
        for (Integer key : treeMap.keySet()) {
            String value = treeMap.get(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }
    }
}
```
Output:
``` 
Key: 1, Value: Value 1
Key: 2, Value: Value 2
Key: 3, Value: Value 3
```

# floorEntry() and floorKey() in JAVA
Both `floorKey()` and `floorEntry()` method are a part of the NavigableMap interface in Java, which is implemented by classes like TreeMap.

`floorKey()`: 返回map中 小于或等于 `指定key` 的最大key，如果不存在则返回null。
```Java
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1, "One");
        map.put(3, "Three");
        map.put(5, "Five");

        Integer floorKey = map.floorKey(4);
        System.out.println("Floor key: " + floorKey);  // Output: Floor key: 3
    }
}
```

`floorEntry()`: 返回map中 小于或等于 `指定key` 的 最大key关联 的 `Map.Entry` 对象，如果没有这样的key，则返回 null。
```Java
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1, "One");
        map.put(3, "Three");
        map.put(5, "Five");

        Map.Entry<Integer, String> floorEntry = map.floorEntry(4);
        if (floorEntry != null) {
            System.out.println("Floor entry: " + floorEntry.getKey() + " - " + floorEntry.getValue());
        } else {
            System.out.println("No floor entry found.");
        }
    }
}
```

# TreeSet() in JAVA
==> 可以自动完成排序，同时也能用来去重和HashSet一样

1. `Sorted Order`: Elements in a TreeSet are automatically sorted in their natural order (or according to a specified comparator if provided). This makes it useful for scenarios where you need to maintain elements in a specific order.

2. `No Duplicates`: Like other implementations of the Set interface, TreeSet does not allow duplicate elements. If you attempt to add a duplicate element, it will simply be ignored.

3. `Null Elements`: TreeSet does not allow null elements. If you try to add a null element, it will throw a NullPointerException.

4. `Performance`: The performance of basic operations (add, remove, and contains) in a TreeSet is generally logarithmic (O(log n)), making it efficient for large collections. However, this is in contrast to some other Set implementations like HashSet, which offer constant time (O(1)) performance on average.

5. `Navigable Operations`: TreeSet provides methods to navigate through the sorted set, such as first(), last(), lower(), higher(), floor(), and ceiling(), which allow you to find elements before, after, or exactly matching a given element.

6. `Subsets`: You can obtain a subset of a TreeSet using methods like subSet(), headSet(), and tailSet(). These methods allow you to create views on a portion of the original set.

7. `Custom Sorting`: You can provide a custom comparator when constructing a TreeSet to define a specific sorting order for the elements. If no comparator is provided, the elements are expected to be comparable and sorted in their natural order

```Java
import java.util.*;

public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        
        treeSet.add(5);
        treeSet.add(2);
        treeSet.add(8);
        treeSet.add(1);
        
        System.out.println(treeSet); // Output: [1, 2, 5, 8]
        
        System.out.println(treeSet.contains(2)); // Output: true
        
        treeSet.remove(5);
        System.out.println(treeSet); // Output: [1, 2, 8]
    }
}
```

**ceiling(E e)**：返回treeSe中大于等于给定元素的最小元素，如果不存在则返回 null。

**floor(E e)**：返回treeSe中小于等于给定元素的最大元素，如果不存在则返回 null。
