# Algorithms Question

Data Structures and Algorithms Question and OA Question review

# The traversal of binary tree (Recursive Method)：

1. Binary Tree Preorder Traversal (DLR)：
   1. Visit the root node 
   2. Visit the left node
   3. Visit the right node
  
2. Binary Tree Inorder Traversal (LDR)：
   1. Visit the left node 
   2. Visit the root node
   3. Visit the right node
  
3. Binary Tree Postorder Traversal (LRD)：
   1. Visit the left node 
   2. Visit the right node 
   3. Visit the root node

# Java split() and Java trim()： 
(Linkcode 53.Reverse Words in a String)

1. Java split():
The method split() splits a String into multiple Strings given the delimiter that separates them. The returned object is an array which contains the split Strings. We can also pass a limit to the number of elements in the returned array.

Grammar for Java split():    public String[] split(String regex, int limit)

Parameter for Java split():     
     a. regex  - regular expression delimiter.
     b. limit  - the number of copies to be divided.

2. Java trim(): 
Java trim() is a built-in function that eliminates leading and trailing spaces.

# Arrays.sort(intervals, (a,b) -> Integer.compare (a[0], b[0]));
(Leetcode 56. Merge Intervals) ==> It used for Sort by ascending or decreasing starting point

The Arrays.sort method has a ton of method signatures. In that method invocation of Arrays.sort, it’s taking 2 parameters; the array and a Comparator. This signature exists so you can provide your own custom comparator, which is exactly what’s happening.

A Comparator defines how to compare 2 objects. In this example, in order to compare two arrays, it will evaluate whether the first element in the array is less than, equal too, or greater than the first element in another array. 

PS: LinkedList<int[]> list = new LinkedList<>();

===> list.getLast()[1]: it means the last element of the last one int[] array from the LinkList list

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

# HashMap：
(Linkcode 242. Valid Anagram  && Leetcode 137. Single Number II ) 

1. Java HashMap getOrDefault(): this method returns the specified default value if the mapping for the specified key is not found in the hashmap. Otherwise, the method returns the value corresponding to the specified key.


# What is the difference between ++ i and ++ i?
(Leetcode 1656. Design an Ordered Stream (OOD Question)) 

In the prefix version (i.e., ++i), the value of i is incremented, and the value of the expression is the new value of i.

==>  ++i increments the number before the current expression is evaluted,

In the postfix version (i.e., i++), the value of i is incremented, but the value of the expression is the original value of i.

==> i++ increments the number after the expression is evaluated.

# PriorityQueuez:
(Leetcode 23. Merge k Sorted Lists) 

1.  Java PriorityQueue(int initialCapacity): Creates a PriorityQueue with the specified initial capacity that orders its elements according to their natural ordering.

2. Java PriorityQueue(int initialCapacity, Comparator<E> comparator): Creates a PriorityQueue with the specified initial capacity that orders its elements according to the specified comparator.
   1. Small -> Large: (o1, o2) -> o1.val - o2.val
   2. Large -> Small: (o1, o2) -> o2.val - o1.val

3. Java PriorityQueue(PriorityQueue<E> c): Creates a PriorityQueue containing the elements in the specified priority queue.

# Memorize Search && Dynamic Programming
(Leetcode 10. Regular Expression Matching)

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
   
# Breadth-first search Algorithm
Step 1: Choose any one node randomly, to start traversing.

Step 2: Visit its adjacent unvisited node.

Step 3: Mark it as visited in the boolean array and display it.

Step 4: Insert the visited node into the queue.

Step 5: If there is no adjacent node, remove the first node from the queue.

Step 6: Repeat the above steps until the queue is empty.

# Depth-first search Algorithm
Step 1: Create a recursive function that takes the index of the node and a visited array.

Step 2: Mark the current node as visited and print the node.

Step 3: Traverse all the adjacent and unmarked nodes and call the recursive function with the index of the adjacent node.

# How to answered Object-Oriented Design Interview Questions
1. Clarify the requirements: Make sure you understand the expectations of the interviewer. Ask clarifying questions if at all necessary — the interviewer will not mind, and will likely appreciate it. For example, “are you looking for me to demonstrate the structure of a solution, or to fully implement it?” Doing this here will take about 5–10 seconds, but save tremendous amounts of time later.

2. Hash out the primary use cases:Think about, and then talk through, use cases. Make sure you understand all the different functionality your system is expected to have. Talking about it out loud can also help you to come across expectations or ideas you might not have realized if you just jumped right in.

3. Identify key Objects: Now, identify all the objects that will play a role in your solution. For example, if you’re designing a parking lot, these will be things like vehicles, parking spots, parking garages, entrances, exits, garage operators, etc.

4. Identify Operations supported by Objects: Work out all the behaviors you’d expect each object that you identified in the previous step to have. For example, a car should be able to move, park in a given spot, and hold a license plate. A parking spot should be able to accommodate a two-wheeled vehicle or a four-wheeled vehicle — and so on.

5. Identify Interactions between Objects: Map out the relationships between the different objects that will need to interface with each other. This is where it all comes together. For example, a car should be able to park in a parking spot. Parking garages should be able to fit multiple parking spots, and so on.
