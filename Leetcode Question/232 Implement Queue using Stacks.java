/* Leetcode 232. Implement Queue using Stacks
Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:
void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
Notes:

You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 

Example 1:

Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
 

Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, peek, and empty.
All the calls to pop and peek are valid.
*/

class MyQueue {
    Stack<Integer> s1 = new Stack<Integer>();
    
    public MyQueue() {
        
    }
    
    // Pushes element x to the back of the queue.
    public void push(int x) {
        Stack<Integer> s2 = new Stack<Integer>();
        while(s1.size() > 0){ //
            s2.push(s1.pop()); // add the s1 remove element into s2
        }
        
        s2.push(x); // add the element into s2
        
        while(s2.size() > 0){ // if s2 size large than 0
            s1.push(s2.pop()); // add the s2 remove elemnt into s1
        }
    } // Because The Stack class represents a Last-In-First-Out (LIFO) stack of objects. 
    // But In here request implement the First-In-First-Out (FIFO) queue by using two stacks
    
    // Removes the element from the front of the queue and returns it.
    public int pop() {
        return s1.pop(); // equal removeFirst()
    }
    
    // Returns the element at the front of the queue.
    public int peek() {
        return s1.peek(); // equal peekFirst()
    }
    
    // Returns true if the queue is empty, false otherwise.
    public boolean empty() {
        return s1.size() == 0; // cheak whether queue is empty
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
