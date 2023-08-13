/* Leetcode 295. Find Median from Data Stream

The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

    · For example, for arr = [2,3,4], the median is 3.
    · For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
    
Implement the MedianFinder class:

    · MedianFinder() initializes the MedianFinder object.
    · void addNum(int num) adds the integer num from the data stream to the data structure.
    · double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 

Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
 

Constraints:

-10^5 <= num <= 10^5
There will be at least one element in the data structure before calling findMedian.
At most 5 * 10^4 calls will be made to addNum and findMedian.
*/

class MedianFinder {
    //使用两个PriorityQueue 优先队列来进行排序
    PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b); //increasing order small -> large
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a); //decreasing order large -> small

    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        //判断当前num的数量
        if(minHeap.size() > maxHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        //如果nums数量是even
        if(minHeap.size() == maxHeap.size()){
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } 
        //如果nums的数量是odd
        return (double) maxHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
