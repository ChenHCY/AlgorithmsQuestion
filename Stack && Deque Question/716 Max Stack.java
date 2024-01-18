/* 716. Max Stack

Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.

Implement the MaxStack class:

  · MaxStack() Initializes the stack object.
  · void push(int x) Pushes element x onto the stack.
  · int pop() Removes the element on top of the stack and returns it.
  · int top() Gets the element on the top of the stack without removing it.
  · int peekMax() Retrieves the maximum element in the stack without removing it.
  · int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.

You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.

Example 1:
Input
["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
[[], [5], [1], [5], [], [], [], [], [], []]
Output
[null, null, null, null, 5, 5, 1, 5, 1, 5]

Explanation
MaxStack stk = new MaxStack();
stk.push(5);   // [5] the top of the stack and the maximum number is 5.
stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
stk.top();     // return 5, [5, 1, 5] the stack did not change.
stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
stk.top();     // return 1, [5, 1] the stack did not change.
stk.peekMax(); // return 5, [5, 1] the stack did not change.
stk.pop();     // return 1, [5] the top of the stack and the max element is now 5.
stk.top();     // return 5, [5] the stack did not change.
 

Constraints:
-10^7 <= x <= 10^7
At most 105 calls will be made to push, pop, top, peekMax, and popMax.
There will be at least one element in the stack when pop, top, peekMax, or popMax is called.
*/

// 要快速查看或弹出最大元素，我们可能会想到一个堆（或优先队列）
// 一个经典的堆栈足以快速查看或弹出最后添加的元素
// 为了记住所有被删除元素的 ID，我们使用一个哈希集 removed 来存储它们
class MaxStack {
    private Stack<int[]> stack; //正常的队列：stack是先进后出
    private Queue<int[]> heap; //优先队列：用来找最大值和删除最大值
    private HashSet<Integer> removed; //removed list储存已经删除的数字
    private int cntIndex; //序号，用来标记数字和识别已经删除了的数字

    //初始化 initializes 
    public MaxStack() {
        stack = new Stack<>();
        //因为优先队列heap是用来找到最大数字的，并且如果有多个最大元素，只要移除最靠近栈顶,
        //所以我们需要判断数值是否一样，如果一样：按照识别符的降序排列，后加入的识别符越大，越靠近栈顶
        //不一样，按照数值的降序排列，数值大的越靠近栈顶
        heap = new PriorityQueue<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]); 
        removed = new HashSet<>();
    }

    // Pushes element x onto the stack.
    // 将元素 x 压入栈中。
    public void push(int x) {
        stack.push(new int[]{x, cntIndex}); //x是数字值，cntIndex是每个数字的识别符号
        heap.add(new int[]{x, cntIndex}); //x是数字值，cntIndex是每个数字的识别符号
        cntIndex++; //数字的识别符号不能一样
    }
    
    // Removes the element on top of the stack and returns it.
    // 移除栈顶元素并返回这个元素
    public int pop() {
        //我们要检查栈顶的数字有没有被删除, 也就是识别removed list中有没有栈顶数字的cntIndex标识
        while(removed.contains(stack.peek()[1])){
            stack.pop(); //如果有，则从栈顶中删除 ==> 直到遇见没有被删除的栈顶数字
        }
        int[] top = stack.pop(); //提取这一次需要删除的栈顶数字
        removed.add(top[1]); //把数字的识别符加入到removed list, 防止重复删除
        return top[0]; //输出删除的栈顶数字
    }
    
    // Gets the element on the top of the stack without removing it.
    // 返回栈顶元素，无需移除
    public int top() {
        //首先判断栈顶的元素是否已经被删除 ==> 识别标识符
        while(removed.contains(stack.peek()[1])){
            stack.pop(); //如果有，则从栈顶中删除 ==> 直到遇见没有被删除的栈顶数字
        }
        return stack.peek()[0]; //然后输出这个当前栈顶的数字
    }
    
    // Retrieves the maximum element in the stack without removing it.
    // 检索并返回栈中最大元素，无需移除
    public int peekMax() {
        //首先判断优先队列中栈顶的元素是否已经被删除 ==> 识别标识符
        while(removed.contains(heap.peek()[1])){
            heap.poll(); //如果有，则从栈顶中删除 ==> 直到遇见没有被删除的栈顶数字
        }
        return heap.peek()[0]; //然后输出优先队列里面的栈顶元素，也就是当前栈中存在的最大值
    }
    
    // Retrieves the maximum element in the stack and removes it. 
    // 检索并返回栈中最大元素，并将其移除, 如果有多个最大元素，只要移除 最靠近栈顶 的那个
    public int popMax() {
        //首先判断优先队列中栈顶的元素是否已经被删除 ==> 识别标识符
        while(removed.contains(heap.peek()[1])){
            heap.poll(); //如果有，则从栈顶中删除 ==> 直到遇见没有被删除的栈顶数字
        }
        int[] maxRemove = heap.poll(); //删除栈中存在的最大值，并且进行记录
        removed.add(maxRemove[1]); //把删除数字的识别符合加入进remove list，避免重复删除
        return maxRemove[0]; //返回最大元素值，并且在栈中删除这个数字
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
