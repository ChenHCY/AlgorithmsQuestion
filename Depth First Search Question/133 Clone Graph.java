/* Leetcode 133. Clone Graph

Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}

Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, 
the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of 
neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

Example 1:
Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

Example 2:
Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.

Example 3:
Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.
 

Constraints:

The number of nodes in the graph is in the range [0, 100].
1 <= Node.val <= 100
Node.val is unique for each node.
There are no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node.
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    } 
}
*/

/*   DFS Though:
    1. copy the every node in the Graph
    2. connect the every node with their neighbors node
    
    Node basic information: 1. value(Integer), 2. neighbors(ArrayList)
*/
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null){
            return node;
        }
        //<key, value> => old node -> new node 
        HashMap<Node, Node> map = new HashMap<>(); //used to connect the old node with new node
        HashSet<Node> visited = new HashSet<>(); //save whether visted the node before

        //1. copy the every node in the Graph
        copyNode(node, map);
        //2. connect the every node with their neighbors node
        connectNode(node, map, visited);

        return map.get(node); //return the clone graph
    }

    //copy the every node value withe empty neighbors list
    public static void copyNode(Node node, HashMap<Node, Node> map){
        //the exit condition ==> we only need copy every nodes one time, so if there is copied before, just return
        if(map.containsKey(node)){
            return; //jump the current node
        }
        //"key" is the old node, "value" is the new node
        map.put(node, new Node(node.val, new ArrayList<Node>())); //connect the old node with new node
        
        //move to next node level
        for(Node net : node.neighbors){
            copyNode(net, map);
        }
    }

    //connect the every node with their neighbors node
    public static void connectNode(Node node, HashMap<Node, Node> map, HashSet<Node> visited){
        //the exit condition ==> we only need copy and connect one time of every node neighbors
        if(visited.contains(node)){ //used hashset to check whether is duplicate node
            return; //then jump and return it
        }
        visited.add(node); // save the visited node
        
        //node, and net means old node / map.get(node) and map.get(net) means new node
        //.negihbors.add ==》 add the correct neighbors into current node
        for(Node net : node.neighbors){
            map.get(node).neighbors.add(map.get(net));
            connectNode(net, map, visited); // move to next level node
        }
    }
}
