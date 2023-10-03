# stack() in Java

a. `Stack` is a legacy class in Java that represents a Last-In-First-Out (LIFO) stack of objects. **先进后出**

b. 内置函数
· `push()`: to push an element onto the stack
· `pop()`: to remove and return the top element
· `peek()`: to view the top element without removing it 
· `empty()`: to check if the stack is empty

# ArrayDeque() in Java
ArrayDeque is a more modern and versatile class for implementing stacks and queues in Java. **最常用的实现堆栈**

a. `ArrayDeque` implements a dynamic array-based double-ended queue, which means it can efficiently add and remove elements from both ends (front and rear).

b. 
· `push()`: to push an element onto the array deque
· `pop()`: to remove and return the top element
· `peek()`: to view the top element without removing it 
· `empty()`: to check if the array deque is empty

c. `ArrayDeque` generally offers better performance than `Stack` due to its non-synchronized nature.

# ArrayDeque 比 stack的优势
a. 数据结构选择:

`ArrayDeque` 是使用数组实现的双端队列，允许在队列的两端进行快速插入和删除操作。这意味着你可以在队列的前端和后端进行元素的添加和删除，

`Stack` 那样只能在一端进行操作。==> 当需要在栈的一端进行操作时。在这种情况下，Stack的性能可能仍然**足够好**，并且可以更好地反映你的意图和代码的结构。

b. 性能分析:

`ArrayDeque` 的内部实现允许在队列的两端以 **恒定的时间(常数时间)** 进行添加和删除操作。这是因为它使用了一个循环数组，这使得元素的添加和删除 **时间成本非常低**。

`Stack` 的内部实现通常使用 `LinkedList 单向链表`。在单向链表中，每次删除元素需要遍历一次全部链表以找到要删除的元素，这会导致时间复杂度为O(n)，其中n是栈的大小。
==> **时间成本 非常高**
