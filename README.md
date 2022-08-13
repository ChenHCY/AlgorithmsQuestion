# LeetCode_Chen
LeetCode Question _Chenyu Yang

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


# HashMap：
(Linkcode 242. Valid Anagram  && Leetcode 137. Single Number II ) 

1. Java HashMap getOrDefault(): this method returns the specified default value if the mapping for the specified key is not found in the hashmap. Otherwise, the method returns the value corresponding to the specified key. 


# PriorityQueuez:
(Leetcode 23. Merge k Sorted Lists) 

1.  Java PriorityQueue(int initialCapacity): Creates a PriorityQueue with the specified initial capacity that orders its elements according to their natural ordering.

2. Java PriorityQueue(int initialCapacity, Comparator<E> comparator): Creates a PriorityQueue with the specified initial capacity that orders its elements according to the specified comparator.
   1. Small -> Large: (o1, o2) -> o1.val - o2.val
   2. Large -> Small: (o1, o2) -> o2.val - o1.val

3. Java PriorityQueue(PriorityQueue<E> c): Creates a PriorityQueue containing the elements in the specified priority queue.


