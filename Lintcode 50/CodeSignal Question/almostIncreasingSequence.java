/* CodeSingal Question: Almost Increasing Sequence
Given a sequence of integers as an array, determine whether it is possible to obtain a strictly 
increasing sequence by removing no more than one element from the array.

Note: sequence a0, a1, ..., an is considered to be a strictly increasing if a0 < a1 < ... < an. 
Sequence containing only one element is also considered to be strictly increasing.

Example

For sequence = [1, 3, 2, 1], the output should be
solution(sequence) = false.
There is no one element in this array that can be removed in order to get a strictly increasing sequence.

For sequence = [1, 3, 2], the output should be
solution(sequence) = true.
You can remove 3 from the array to get the strictly increasing sequence [1, 2]. Alternately, you can remove 2 to get the strictly increasing sequence [1, 3].

Input/Output
[execution time limit] 3 seconds (java)
[input] array.integer sequence

Guaranteed constraints:
2 ≤ sequence.length ≤ 105,
-105 ≤ sequence[i] ≤ 105.

[output] boolean
Return true if it is possible to remove one element from the array in order to get a strictly increasing sequence, otherwise return false.
*/

boolean solution(int[] sequence) {
    int times = 0;
    
    for(int i = 0; i < sequence.length - 1; i++){
        if(sequence[i+1] <= sequence[i]){
            times += 1;
        }
        
      //check the solution example: like [10, 1, 2, 3, 4, 5] && [1, 2, 1, 2] && [1, 2, 3, 4, 5, 3, 5, 6]
        if(i - 1 >= 0 && i + 2 < sequence.length && sequence[i-1] >= sequence[i+1] && sequence[i] >= sequence[i+2]){
         //sequence[i-1] >= sequence[i+1] is used to check remove nums[i], whether can obtain a strictly increasing sequence
          //sequence[i] >= sequence[i+2]  is used to check remove nums[i+1],  whether can obtain a strictly increasing sequence
            times += 1;
        }
        
    }
    
    if(times > 1){
        return false;
    }
    
    return true;
}
