/* 495 · Implement Stack
Description
Implement a stack. You can use any data structure inside a stack except stack itself to implement it.

Example 1:
Input:
push(1)
pop()
push(2)
top()  // return 2
pop()
isEmpty() // return true
push(3)
isEmpty() // return false

Example 2:
Input:
isEmpty()
*/
public class Stack {
    LinkedList<Integer> result = new LinkedList<>();
    /*
     * @param x: An integer
     * @return: nothing
     */
    public void push(int x) {
        // write your code here
        result.addFirst(x);
    }

    /*
     * @return: nothing
     */
    public void pop() {
        // write your code here
        result.removeFirst();
    }

    /*
     * @return: An integer
     */
    public int top() {
        // write your code here
        return result.get(0);
    }

    /*
     * @return: True if the stack is empty
     */
    public boolean isEmpty() {
        // write your code here
        if(result.size() == 0){
            return true;
        } else {
            return false;
        }
    }
}
