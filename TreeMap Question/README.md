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
