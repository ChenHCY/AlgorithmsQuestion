# Algorithms Question

Data Structures and Algorithms Question and OA Question review

# Memorize Search && Dynamic Programming
(Leetcode 10. Regular Expression Matching && Leetcode 1048. Longest String Chain)

==> 记忆化搜索就是保存函数的计算结果，下次通过相同的参数访问时直接返回保存的结果。

1. Memorize Search: Memorize Search means saving the calculation result of the function, and returning the saved result directly when accessing through the same parameter next time.

Memoized search can often reduce exponential time complexity down to a polynomial level.

2. Memorize Search = Dynamic Programming: 

a. Memoized search is an implementation method of dynamic programming.

b. Memoized search implements dynamic programming by means of search. 

c: 1. Dp State: Use f[i] or f[i][j] to represent the answer to a smaller problem under certain conditions

   2. Dp Initialize: Dp start position ==> Like dp[0] = 0
   
   3. Dp function: the calculate function of Dp. Like:
   f[i][j] = Derive by finding max / min / sum / or of some smaller state
   
   4. Dp return: return the dp result. Like: such as f[n][m] or max(f[n][0], f[n][1] … f[n][m])
  
# Dp Time Complexity:
一维动态规划时间复杂度一般有 O(n) 和 O(n^2) 两种，时间复杂度取决于状态转移方程。

如果第i个状态的确定需要利用前i-1个状态，即dp[i]由dp[i-1],dp[i-2],...,dp[0]的取值共同决定，那么此时的时间复杂度为O(n^2)。

# Bit Manipulation in Java
==> Bitwise XOR(^)： 二进制运算符，在coding中用"^"表示。返回输入值的异位。

`一样数字的返回 '0' / 不一样的数字返回 '1'`

For Example: original[] = [a,b,c,d,e], 所以 derived[] = [a ^ b, b ^ c, c ^ d, d ^ e, e ^ a]

==> 因为 Bitwise XOR(^)： 二进制运算符,  一样数字的返回 '0' / 不一样的数字返回 '1'

==> 所以对于 derived[]  全部数字进行XOR异运算， a ^ b ^ b ^ c ^ c ^ d ^ d ^ e ^ e ^ a 都有重复的，答案应该为 0

==> 所以如果我们对于 derived[]  全部数字， 使用XOR的进行异运算，总的结果应该为0

# Arrays.stream(nums).max().getAsInt() in Java
==> 以 int 形式提供 nums 数组中的最大值。 如果数组为空，它将抛出 NoSuchElementException。

==> 此表达式轻松更简单的查找 nums整数数组中的最大integer值。

1. `Arrays.stream(nums)`：这部分从 nums 数组创建一个stream。 Arrays.stream() 方法用于将数组转换为stream，允许对其元素执行各种操作。

2.  `max()`: 此方法在流上调用并返回表示stream中最大元素的 OptionalInt。方法比较流中的元素并返回最大值。

3. `getAsInt()`：此方法在 max() 返回的 OptionalInt 对象上调用。 它从 OptionalInt 中检索值（如果存在）并将其作为 int 返回。 
==> 如果 OptionalInt 为空（即，在流中未找到最大值），它会抛出 NoSuchElementException。 但是，如果您确信流包含至少一个元素，则可以安全地调用 getAsInt()。

# 二叉树的前序 中序 后序 遍历。递归方法

·前序遍历：打印 - 左 - 右
·中序遍历：左 - 打印 - 右
·后序遍历：左 - 右 - 打印

==》递归的思路：

1. Binary Tree Preorder Traversal (DLR)：
   1. Visit the root node 
   2. Visit the left node
   3. Visit the right node
   
先序遍历可以想象成，从树根开始绕着整棵树的外围转一圈，经过结点的顺序就是先序遍历的顺序先序遍历结果

==》从根节点开始，根节点 -> 左节点 -> 右节点  ==> 前序遍历：打印 - 左 - 右
  
2. Binary Tree Inorder Traversal (LDR)：
   1. Visit the left node 
   2. Visit the root node
   3. Visit the right node

中序遍历可以想象成，按树画好的左右位置投影下来就可以了, 

==》从左节点开始，左节点 -> 根节点 -> 右节点  ==> 中序遍历：左 - 打印 - 右

3. Binary Tree Postorder Traversal (LRD)：
   1. Visit the left node 
   2. Visit the right node 
   3. Visit the root node
 
后序遍历就像是“剪葡萄”，我们要把一串葡萄剪成一颗一颗的。

如果发现一剪刀就能剪下的葡萄（必须是一颗葡萄），就把它剪下来，组成的就是后序遍历了。

==》从左节点开始，左节点 -> 右节点 -> 根节点 ==> 后序遍历：左 - 右 - 打印
 
详细连接：http://www.hangdaowangluo.com/archives/2979

# Subsequence vs Substring vs Subarray

Subsequence(子序列): 子序列是一个字符序列，这些字符可能不连续，但出现的顺序与它们在原始字符串中出现的顺序相同。 ==> 子序列是保留其元素顺序的序列的子集。

Substring(子字符串): 子字符串是字符串中连续的字符序列。==> 子字符串是较大字符串中字符的连续子集。

Subarray(子数组):  子数组是数组的连续部分。==> 子数组是数组中元素的连续子集。

# String.contains() in Java / String.includes() in JavaScript
 
这两个function可以直接用来在一个string中查找是否存在另外一个string。

==> String temp = "01"; ==> "0" + temp + "1" = "0011"

```JavaScript
//String.contains(): ==> boolean contains(CharSequence sequence)
String str = "Hello, world!";
boolean containsWorld = str.contains("world");
System.out.println(containsWorld); // Output: true

//String.includes(): ==> str.includes(searchString [, position]) ==> `position`参数指定字符串中开始搜索的位置
const str = "Hello, world!";
const containsWorld = str.includes("world");
console.log(containsWorld); // Output: true
```

 # Java HashMap computeIfAbsent() 方法 (DFS / Leetcode 1443)
computeIfAbsent() 方法对 hashMap 中指定 key 的值进行重新计算，如果不存在这个 key，则添加到 hashMap 中。

computeIfAbsent() 方法的语法为：hashmap.computeIfAbsent(K key, Function remappingFunction)

key - 键  /  remappingFunction - 重新映射函数，用于重新计算值

For example: ==> hashmap.computeIfAbsent(start, key -> new ArrayList<Integer>()).add(node.val);


# Collections.sort in JAVA
Java中的方法 `Collections.sort()` 用于对 object list进行升序排序。它是java.util.Collections的一部分，提供了一种对实现该List接口的集合进行排序的便捷方法。

`Collections.sort()` 方法将一个List对象作为输入，并根据元素的自然顺序对其进行升序排序。

列表中的元素必须实现该`Comparator` interface 接口，该接口为对象可以让我们自定义顺序或条件对列表进行排序。

==》`Collections.sort()` 方法就地修改原始列表，并且不会创建新的排序列表。

# Java Comparator and compare() 
In Java, the `Comparator` interface is used for custom object comparison.  在Java中，该Comparator接口用于自定义对象比较。

`Comparator` interface 接口 有一个名为compare()的abstract method，该方法比较两个对象并返回一个整数值，指示它们的相对顺序。

compare()的格式：
```Jaca
int compare(T o1, T o2);
//T: represents the type of objects being compared.
//The compare() method takes two objects o1 and o2 of type T

//=> returns an integer value based on the comparison result:

1. If o1 is considered less than o2, a negative integer is returned.
2. If o1 is considered greater than o2, a positive integer is returned.
3. If o1 is considered equal to o2, zero is returned.
```

==> 简单来说：Comparator就是一个可以比较两个object顺序的接口，我们可以根据对象的特定属性或标准定义自己的比较逻辑。

# Collections.sort and Comparator in Java 
在此示例中，该Person class 具有 name 和 age属性。我们创建一个objet list: Person(people)，

然后age使用 AgeComparator自定义 `Comparator` 实现根据属性对`objet list`进行排序

=> 使得 object list(people list) 按照 age年龄 的升序进行排序
```Java
public class Person {
    private String name;
    private int age;

    // constructor, getters, setters

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 30));
        people.add(new Person("Charlie", 20));

        System.out.println("Before sorting: " + people);

        // Sort based on age using a custom Comparator
        Collections.sort(people, new AgeComparator());

        System.out.println("After sorting: " + people);
    }
}

class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }
}
```

 # Stack()的声明选择
 
 LinkedList ArrayDeque 都能作为stack的 声明 ==》时间使用比new Stack()更少 
   
 因为stack()是从vector进行的扩展，最好不要使用stack()
 
 区别：LinkedList可以让null作为元素， ArrayDeque不可以允许元素为非空
 
 # 拓扑排序的定义
 
 拓扑排序的作用就是检查图中的路径和是否存在环 ==> 这也是拓扑排序的基础
 
 扑排序的的意思就是把一幅图「拉平」，而且这个「拉平」的图里面，所有箭头方向都是一致的
 所以有环的情况下是无法进行拓扑排序的，必须是“有向无环图(Directed Acyclic Graph)”
 
 ==》如何进行拓扑排序 ==》 其实将后序遍历的结果进行反转，就是拓扑排序的结果。
 
 ==》二叉树的后序遍历：当左右子树的节点都被装到结果列表里面了，根节点才会被装进去
   
 # Long.ParseLong(String) && Long.ValueOf(String) 
 
 参数String表示，指定 String 的值的 Long 对象。该参数被解释为表示一个有符号的十进制 long，该值与用该参数作为参数的 parseLong(java.lang.String) 方法得到的值非常相似
 
1. Long.ParseLong(String): 把string中的一段字符串转化为有符号十进制 long，字符串中的字符必须都是十进制数字。
 
==》 Long.parseLong(s.substring(start, end + 1))

2. Long.ValueOf(String): String表示，指定 String 的值的 Long 对象。该参数被解释为表示一个有符号的十进制 long，该值与用该参数作为参数的 parseLong(java.lang.String) 方法得到的值非常相似

# Java split() and Java trim() and Math.celi()： 
(Linkcode 53.Reverse Words in a String)

1. Java split():
The method split() splits a String into multiple Strings given the delimiter that separates them. The returned object is an array which contains the split Strings. We can also pass a limit to the number of elements in the returned array.

Grammar for Java split():    public String[] split(String regex, int limit)

Parameter for Java split():     
     a. regex  - regular expression delimiter.
     b. limit  - the number of copies to be divided.

2. Java trim(): 
Java trim() is a built-in function that eliminates leading and trailing spaces.

3. Math.celi(): ceil方法的功能是向上取整。有任何小数 都向前取到大一位的整数

# LinkedList.toArray() Method: 
(Medium ==> Linkcode 56. Merge Intervals  && Leetcode 57. Insert Interval) 

LinkedList. toArray() method is used to convert and LinkedList into an Array. It returns the same LinkedList elements but in the form of Array only.

The toArray() method of LinkedList/ArrayList is used to return an array containing all the elements in LinkedList/ArrayList in the correct order.

# Stack() with Deque(): 
(Leetcode 71. Simplify Path Stack && Deque Question) 
Deque(): Deque interface present in java.  
==> Used pop()(last in) get the top element // And Used the removeLast() can get the bottom element(first in) in the Deque stack

It can either be used as a queue(first-in-first-out/FIFO) or as a stack(last-in-first-out/LIFO).

Stack(): A Stack is a Last In First Out (LIFO) data structure. It supports two basic operations called push and pop. 

The push operation adds an element at the top of the stack, and the pop operation removes an element from the top of the stack.

==> When using this for-each loop to get an element from the stack, it will start at the bottom of Stack(first in - first out)

# HashMap in JAVA
(Linkcode 242. Valid Anagram  && Leetcode 137. Single Number II ) 

1. Java HashMap getOrDefault(): this method returns the specified default value if the mapping for the specified key is not found in the hashmap. Otherwise, the method returns the value corresponding to the specified key.

2. Java HashMap map.entrySet()：HashMap is a data structure that stores data in key-value pairs. The entrySet() method in HashMap returns a set of all the key-value pairs in the map. ==》 entrySet()中的方法返回HashMap映射中所有键值对的集合。

# What is the difference between ++ i and ++ i?
(Leetcode 1656. Design an Ordered Stream (OOD Question)) 

In the prefix version (i.e., ++i), the value of i is incremented, and the value of the expression is the new value of i.

==>  ++i increments the number before the current expression is evaluted,

In the postfix version (i.e., i++), the value of i is incremented, but the value of the expression is the original value of i.

==> i++ increments the number after the expression is evaluated.

# Arrays.sort(intervals, (a,b) -> Integer.compare (a[0], b[0]));
(Leetcode 56. Merge Intervals && Leetcode 1029. Two City Scheduling) 

==> It used for Sort by ascending or decreasing starting point or the difference value of array

The Arrays.sort method has a ton of method signatures. In that method invocation of Arrays.sort, it’s taking 2 parameters; the array and a Comparator. This signature exists so you can provide your own custom comparator, which is exactly what’s happening.

A Comparator defines how to compare 2 objects. it can evaluate whether the first element in the array is less than, equal to, or greater than the first element in another array, Or whether the difference value of the first array is less, or larger than another array.

==> it means Sort the array[] in the ascending order by every first value of a[] - second value of a[]

For example: Leetcode 1029. Two City Scheduling

Arrays.sort(int[][] arr, (a, b) -> { return a[0] - a[1] - (b[0] - b[1])});
   1. Small -> Large: (a, b) -> a.val - b.val (Ascending order)
   2. Large -> Small: (a, b) -> b.val - a.val (Descending order)

PS: LinkedList<int[]> list = new LinkedList<>();
===> list.getLast()[1]: it means the last element of the last one int[] array from the LinkList list

# PriorityQueuez:
(PriorityQueuez: Leetcode 23. Merge k Sorted Lists) 

1.  Java PriorityQueue(int initialCapacity): Creates a PriorityQueue with the specified initial capacity that orders its elements according to their natural ordering.

2. Java PriorityQueue(int initialCapacity, Comparator<E> comparator): Creates a PriorityQueue with the specified initial capacity that orders its elements according to the specified comparator.
   1. Small -> Large: (o1, o2) -> o1.val - o2.val (Ascending order)
   2. Large -> Small: (o1, o2) -> o2.val - o1.val (Descending order)

3. Java PriorityQueue(PriorityQueue<E> c): Creates a PriorityQueue containing the elements in the specified priority queue.

4. PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder()); ==> 按反转的顺序对于数组进行排序

# 迭代和递归的区别
递归: 递归是一个树结构，从字面可以其理解为重复“递推”和“回归”的过程，当“递推”到达底部时就会开始“回归”，其过程相当于树的深度优先遍历。

迭代：迭代是一个环结构，从初始状态开始，每次迭代都遍历这个环，并更新状态，多次迭代直到到达结束状态。
   
# Breadth-first search Algorithm：
BFS 的核心思想就是把一些问题解空间抽象在一个图中，从一个点开始，向四周开始扩散。
一般来说，我们用迭代写 BFS 算法都是用「队列」这种数据结构，每次将一个节点周围的所有节点加入队列。如果是递归实现，则递归返回的是子问题的解。

Step 1: Choose any one node randomly, to start traversing.

Step 2: Visit its adjacent unvisited node.

Step 3: Mark it as visited in the boolean array and display it.

Step 4: Insert the visited node into the queue.

Step 5: If there is no adjacent node, remove the first node from the queue.

Step 6: Repeat the above steps until the queue is empty.

# Depth-first search Algorithm
DFS算法就是回溯算法的思想，迭代的实现中往往需要「栈」这种数据结构用来回退，递归的实现中往往是在递归出口的位置得到全局的解。

Step 1: Create a recursive function that takes the index of the node and a visited array.

Step 2: Mark the current node as visited and print the node.

Step 3: Traverse all the adjacent and unmarked nodes and call the recursive function with the index of the adjacent node.

# The Difference in BFS with DFS 
BFS 相对 DFS 的最主要的区别是：BFS 可以找到最短距离，但代价就是空间复杂度比 DFS 大很多。

# 为什么 BFS 可以找到最短距离，DFS 不行吗？
BFS 的逻辑是，depth 每增加一次，队列中的所有节点都向前迈一步，这保证了第一次到达终点的时候，走的步数是最少的。

所以BFS 可以找到最短距离，空间复杂度高。但DFS 的空间复杂度较低。

# How to answered Object-Oriented Design Interview Questions
1. Clarify the requirements: Make sure you understand the expectations of the interviewer. Ask clarifying questions if at all necessary — the interviewer will not mind, and will likely appreciate it. For example, “are you looking for me to demonstrate the structure of a solution, or to fully implement it?” Doing this here will take about 5–10 seconds, but save tremendous amounts of time later.

2. Hash out the primary use cases:Think about, and then talk through, use cases. Make sure you understand all the different functionality your system is expected to have. Talking about it out loud can also help you to come across expectations or ideas you might not have realized if you just jumped right in.

3. Identify key Objects: Now, identify all the objects that will play a role in your solution. For example, if you’re designing a parking lot, these will be things like vehicles, parking spots, parking garages, entrances, exits, garage operators, etc.

4. Identify Operations supported by Objects: Work out all the behaviors you’d expect each object that you identified in the previous step to have. For example, a car should be able to move, park in a given spot, and hold a license plate. A parking spot should be able to accommodate a two-wheeled vehicle or a four-wheeled vehicle — and so on.

5. Identify Interactions between Objects: Map out the relationships between the different objects that will need to interface with each other. This is where it all comes together. For example, a car should be able to park in a parking spot. Parking garages should be able to fit multiple parking spots, and so on.
