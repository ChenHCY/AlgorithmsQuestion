/* BFE.dev 13. Implement a Queue by using Stack

In JavaScript, we could use array to work as both a Stack or a queue.

const arr = [1, 2, 3, 4]

arr.push(5) // now array is [1, 2, 3, 4, 5]
arr.pop() // 5, now the array is [1, 2, 3, 4]
Above code is a Stack, while below is a Queue

const arr = [1, 2, 3, 4]

arr.push(5) // now the array is [1, 2, 3, 4, 5]
arr.shift() // 1, now the array is [2, 3, 4, 5]
now suppose you have a stack, which has only follow interface:

class Stack {
  push(element) { /* add element to stack */ }
  peek() { /* get the top element */ }
  pop() { /* remove the top element */}
  size() { /* count of elements */}
}
Could you implement a Queue by using only above Stack? A Queue must have following interface
*/
  
class Queue {
  enqueue(element) { /* add element to queue, similar to Array.prototype.push */ }
  peek() { /* get the head element*/ }
  dequeue() { /* remove the head element, similar to Array.prototype.pop */ }
  size() { /* count of elements */ }
}
*/

/* you can use this Class which is bundled together with your code*/

class Stack {
  push(element) { // add element to stack }
  peek() { // get the top element }
  pop() { // remove the top element}
  size() { // count of element }
}

/* Array is disabled in your code */

// you need to complete the following Class
class Queue {
  // constructor() used as part of the process of creating and initializing objects from a class.
  // 用作在calss 创建和初始化对象的过程的一部分。==》声明时 使用 new 
  // 在创建对象时设置初始值、初始化属性以及执行任何必要的设置任务
  constructor() {
    this.stack1 = new Stack();
    this.stack2 = new Stack();
  }
  
  //stack() is first in first out
  enqueue(element) { 
    //Add new element to the rare
    //because stack() is Last in first out, and Queue is first in first out
    
    // 1. Put all the element numbers of stack1 into stack2 
    for(let i = 0; i < this.stack1.size(); i++){  //[1, 2, 3, 4]
      this.stack2.push(this.stack1.pop()); //[4, 3, 2, 1]
    }

    //2. put the new number into stack2 
    this.stack1.push(element); //[5]

    //3. Put all the element numbers from stack2 back stack1 
    for(let j = 0; j < this.stack2.size(); j++){
      this.stack1.push(this.stack2.pop()); //[1, 2, 3, 4, 5]
    }
  }
  peek() { 
    // get the head element
    return this.stack1.peek();
  }
  size() { 
    // return count of element
    return this.stack1.size();
  }
  dequeue() {
    // remove the head element
    return this.stack1.pop();
  }
}
