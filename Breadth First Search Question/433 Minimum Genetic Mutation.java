/* 433. Minimum Genetic Mutation

A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.

Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.

    · For example, "AACCGGTT" --> "AACCGGTA" is one mutation.

There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.

Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene 
to endGene. If there is no such a mutation, return -1.

Note that the starting point is assumed to be valid, so it might not be included in the bank.

Example 1:
Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
Output: 1

Example 2:
Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
Output: 2
 

Constraints:
0 <= bank.length <= 10
startGene.length == endGene.length == bank[i].length == 8
startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
*/


// 时间复杂度：O(C×n×m)，其中 n 为基因序列的长度，m 为数组 bank 的长度。对于队列中的每个合法的基因序列每次都需要计算 C×n 种变化，在这里 C=4；队列中最多有 m 个元素，因此时间复杂度为 O(C×n×m)。
// 空间复杂度： 空间复杂度：O(n×m)，其中 n 为基因序列的长度，m 为数组 bank 的长度。合法性的哈希表中一共存有 m 个元素，队列中最多有 m 个元素，每个元素的空间为 O(n)；队列中最多有 m 个元素，每个元素的空间为 O(n)，
// ==> 因此空间复杂度为 O(n×m)。

// 思路：因为每一次只有单个字母发生改变，所以 BFS 遍历 start字符串 然后试着更改每一个位置的字符： 'A', 'C', 'G', and 'T'.
// 然后检查得到的新字符串是不是在bank里面，如果是的，则存入hashmap 和 对应花费的步骤
// 不停的遍历改变 ==> 直到得到 end字符串，然后输出花费的步骤(因为我们是BFS一个一个改变，最先遇到的一定是步骤最少的)
class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        List<String> bankList = Arrays.asList(bank); //存储合法的基因序列

        HashMap<String, Integer> map = new HashMap<>(); //储存改变之后的新字符串，和花费了的步骤
        map.put(startGene, 0); //Start字符串，不需要任何改变
        
        //BFS, 储存每一个更改之后的字符串
        Deque<String> deque = new ArrayDeque<>();
        deque.offer(startGene);

        String[] gene = {"A", "T", "C", "G"}; //用来修改StartGene的每个字符

        //BFS主遍历
        while(!deque.isEmpty()){
            String curr = deque.poll(); 
            //开始更改字母 'A', 'C', 'G', and 'T'.
            for(int i = 0; i < 8; i++){ //startGene.length == endGene.length == bank[i].length == 8
                for(int j = 0; j < 4; j++){ //'A', 'C', 'G', and 'T'.
                    //如果当前字母curr.charAt(i)和gene[j]是一样的，则不用更改
                    if(String.valueOf(curr.charAt(i)) == gene[j]){
                        continue; //跳过后续步骤
                    }

                    //开始修改, 把 index i位置的字母 修改为 gene[j] ==> 得到新字符串
                    String newStr = curr.substring(0, i) + gene[j] + curr.substring(i + 1);

                    //判断得到的新字符串是不是存在于blank里面, 如果不存在，则表示无法完成改变
                    if(!bankList.contains(newStr)){
                        continue;
                    }

                    //如果存在于blanklist里面，表示是可以完成改变的
                    //然后检查是否已经出现在前面的改变中, 如果没有，进行储存和花费的步骤
                    if(!map.containsKey(newStr)){
                        map.put(newStr, map.get(curr) + 1); //旧的字符串步骤 + 1
                        deque.offer(newStr); //把新字符串加入队列 继续遍历

                        //判断这个是不是endStr，如果是的，输出步骤，结束程序
                        //Java中比较字符串内容是否一样，使用equals() 方法比较的是对象的内容，确保字符串的每个字符都相同
                        if(newStr.equals(endGene)){
                            return map.get(newStr);
                        }
                    }
                }
            }
        }

        return -1; //如果通过bank改变 start ==> end, 输出-1 
    }
}
