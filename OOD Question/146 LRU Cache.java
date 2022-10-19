/* Leetcode 146. LRU Cache
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.

int get(int key) Return the value of the key if the key exists, otherwise return -1.

void put(int key, int value) Update the value of the key if the key exists. Otherwise, 
add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.

The functions get and put must each run in O(1) average time complexity.

Example 1:
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
 

Constraints:
1 <= capacity <= 3000
0 <= key <= 104
0 <= value <= 105
At most 2 * 105 calls will be made to get and put.
*/

class LRUCache {
    /* head -> cache list -> tail
    */
    class Node{
        int key, value;
        Node pre, next;
        Node(int k, int v){
            this.key = k;
            this.value = v;
        }
    }
    
    //Declare the Dummy Node
    Node head, tail;
    
    //add function() ==> connection the node with dummy node head and tail
    //double LinkedList
    private void add(Node node){
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }
    
    //remove function ==> remove a node from the current position
    //change the connection
    private void remove(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    //update function = remove it and add again
    private void update(Node node){
        remove(node);
        add(node);
    }
    
    
    private int currentSize, capacity; //declare the basic number
    private Map<Integer, Node> map; //Used HashMap save the time be O(1)
    
    //Capacity size
    public LRUCache(int capacity) {
        this.capacity = capacity;
        currentSize = 0;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.next = head;
    }
    
    //exist and find -> return value ==> need upadte one time
    //not exist and can not find--> return -1
    //used the hashmap function will save the time be O(1)
    public int get(int key) {
        Node node = map.get(key);
        if(node == null){ //can not find it from Cache
            return -1;
        } 
        update(node);
        return node.value;
    }
    
    //exist and find -> updata
    //not exist -> add to cache
    //reach the capactiy size -> remove the least recently used 
    //used double linkedlist will save the time be O(1)
    public void put(int key, int value) {
        //check whether have node in the hashMao
        Node node = map.get(key);
        if(node == null){ //did not have it
            node = new Node(key, value);
            add(node); //add into double LinkedList
            map.put(key, node);
            currentSize++;
        } else{ //have in the Cache
            node.value = value;
            update(node);
        }
        
        //check the size whether is larget the capacity
        //delete the node longest not used one
        if(currentSize > capacity){
            Node toDelete = tail.pre;
            remove(toDelete);
            map.remove(toDelete.key);
            currentSize--;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
