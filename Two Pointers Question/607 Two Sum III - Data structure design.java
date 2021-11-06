/* Description
Design and implement a TwoSum class. It should support the following operations: add and find.
add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.607 · Two Sum III - Data structure design

Example 1:

add(1); add(3); add(5);
find(4) // return true
find(7) // return false
*/

public class TwoSum {
    /**
     * @param number: An integer
     * @return: nothing
     */
    ArrayList<Integer> result = new ArrayList<>();

    public void add(int number) {
        // write your code here
        result.add(number);
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        // write your code here
        Collections.sort(result);
        int left = 0;
        int right = result.size()-1;

        while (left < right) {
            if(result.get(left) + result.get(right) == value){
                return true;
            } else if (result.get(left) + result.get(right) < value){
                left++;
            } else{
                right--;
            }
        }
        return false;
    }
}
