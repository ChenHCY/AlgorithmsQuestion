/* 485 · Generate ArrayList with Given Size

Description
Generate an arrayList with given size, initialize the array list with numbers from 1 to size.

Example 1:
	Input:  size = 4
	Output: [1, 2, 3, 4]
	
	Explanation: 
	return the array list that contains numbers from 1 to 4: [1,2,3,4].
		
Example 2:
	Input:  size = 1
	Output: [1]
	
	Explanation: 
	return the array list that contains numbers from 1 to 1: [1].
 */ 

public class Solution {
    /**
     * @param size: An integer
     * @return: An integer list
     */
    public List<Integer> generate(int size) {
        // write your code here
         List<Integer> result = new ArrayList<>();
        
        for(int i = 1; i <= size; i++)
        {
            result.add(i);
        } 

        return result;
    }
}
