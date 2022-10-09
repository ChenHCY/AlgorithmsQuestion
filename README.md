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
