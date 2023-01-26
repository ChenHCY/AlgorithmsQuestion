/* Leetcode 127. Word Ladder

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

  a. Every adjacent pair of words differs by a single letter.
  b. Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
  c. sk == endWord
  
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord 
to endWord, or 0 if no such sequence exists.

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.


Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
*/

/*
思路： 

类似于BFS的想法，把wordList这个string array[] 想象成一个树状的结构，然后遍历和检查是否有从 beginWord 变化成 endWord 的一条线路，统计这个层级的长度

因为是改成了树状图的形式，然后一层一层的遍历 ==》所以当第一次找到endword时间，这个长度（层级数量）一定也是最短的

1. 使用HashSet去重 wordList 里面重复的元素，避免层级路径的错误

2. Queue<String> : BFS的思路，把有可能是下一个改变的String储存起来，形成一个树状的level
==》也是方便通过 queue.poll() 把每一个string 提取出来

3. while-loop 开始遍历每个层级（当前string有可能改变一个字母来形成下一个的新string)， level++; ==》 负责统计层级的多少 （也就是需要的答案长度）

4. 第一个for-loop: 遍历当前层级的每一个String

5. 第二个for-loop：遍历每个string的每一个character

6. 第三个for-loop：替换每一个character(从'a' - 'z')，然后检查这个转换之后的新string 是否存在于 hashset（wordList) 中

a. 如果存在，

然后 用if-statement检查 新转换成的new String, 是否是(endWord)

==》 如果是的，返回level + 1 ==》 就是结果

==》 如果不是，移动到下一个有可能形成的新String, 并且从hashset里面删除（避免计算路径的时候返回到上一个层级）

7. 如果没有找到任何正确的转换String路径，返回0

*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //Used the hashset to remove the duplicate string element from wordList
        HashSet<String> set = new HashSet<>(wordList);

        int level = 1; // the shortest length of string change process

        //BFS Idea: Used Queue to save every Level of string change process
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord); // add the begin word into queue

        while(!queue.isEmpty()){
            int size = queue.size(); //every level size of string change process

            //1. travser every the string element of current level
            for(int i = 0; i < size; i++){
                String curr = queue.poll(); //get the every string element one by one

                //2. then travser every character of the the current string element 
                for(int j = 0; j < curr.length(); j++){
                    // change the string to be char array[]
                    char[] characterS = curr.toCharArray(); //used char[] array to change and transformation

                    //3. every character of the current string element also change and check a - z
                    for(char c = 'a'; c <= 'z'; c++){
                        characterS[j] = c; //change and transformation the position j character element

                        String newS = new String(characterS); //change the char[] array back to String
                        //it used to check whether the hashset(wordList) have the string after transformation

                        //check whether the hashset(wordList) have the string after transformation
                        if(set.contains(newS)){
                            if(newS.equals(endWord)){ //if find the endWord, count the length and return output
                                return level + 1;
                            }
                            queue.offer(newS); //add the new String (after transformation) into queue
                            set.remove(newS); // remove String, avoid the process back previous string element
                        }  
                    }
                }
            }
            level++;
        }

        return 0;
    }
}
