/* 492 · Implement Queue by Linked List
Description
Implement a Queue by linked list. Support the following basic methods:
enqueue(item). Put a new item in the queue.
dequeue(). Move the first item out of the queue, return it.
Example 1:
Input:
enqueue(1)
enqueue(2)
enqueue(3)
dequeue() // return 1
enqueue(4)
dequeue() // return 2
Example 2:
Input:
enqueue(10)
dequeue()// return 10
*/

public class MyQueue {
    private LinkedList<Integer> result = new LinkedList<>();
    /*
     * @param item: An integer
     * @return: nothing
     */
    public void enqueue(int item) {
        // write your code here
        result.add(item);
    }

    /*
     * @return: An integer
     */
    public int dequeue() {
        // write your code here
        return result.removeFirst();
    }
}
