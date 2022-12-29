/* Leetcode 93. Restore IP Addresses

A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.

Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. 
You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

Example 1:

Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]

Example 2:

Input: s = "0000"
Output: ["0.0.0.0"]

Example 3:

Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 

Constraints:
1 <= s.length <= 20
s consists of digits only.
*/


//Time: O(n!) ==> n is the length of String s
//Space: O(n!) ==> The largest space used

//思路：
//a. 每次从前面取1到3个字符，看看是否合法，
//b. 如果合法的话，对后面的进行递归
//c. 递归完成后reset和move into next part
//d. 递归到有4个部分被取出来以后的话， 开始判断加入和退出的条件

//重点： 如果IP地址中，任何一段数字 有两位或者三位的话，不能是用0开头的

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        List<String> curr = new ArrayList<>();

        //call backtracking method
        backtracking(res, curr, s);

        return res;
    }

    //backtracking function
    public static void backtracking(List<String> res, List<String> curr, String s){
        //exit and add condition
        if(curr.size() == 4){  // Every IP address have 4 number parts
            if(s.length() == 0){ //arrived last element in the String s == IP address is valid

                StringBuilder sb = new StringBuilder(); // ==> Used for create IP address with String format
                
                //Every IP address have 4 number parts
                for(int i = 0; i < 4; i++){ 
                    sb.append(curr.get(i)); //add one number part into StringBuilder IP address
                    if(i < 3){
                        sb.append("."); // there is "." in the middle of ip address
                    }
                }

                //add the correct ip address string into res list
                res.add(sb.substring(0, sb.length()).toString()); 
            }
            return; // stop the function and remove back
        }

        //Main for-loop to travser all element 
        for(int i = 1; i <= 3 && i <= s.length(); i++){
            String numberPart = s.substring(0, i);

            //if number Parts is more than 2, it cannot have leading zeros
            if(numberPart.length() > 1 && numberPart.startsWith("0")){
                continue;
            }

            //Each integer is between 0 and 255
            int value = Integer.valueOf(numberPart);
            
            if(value < 0 || value > 255){
                continue;
            } else{
                curr.add(numberPart); // add the number part into curr list
                backtracking(res, curr, s.substring(i)); //call backtracking function and move to next level
                curr.remove(curr.size() - 1); // remove and back to perivous level
            }
        }
    }
}
