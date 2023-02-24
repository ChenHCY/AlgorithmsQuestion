/* Roblox OA Question 2
Find the longest repeated characters and number of characters from a string, Return a String that including the letter and the number of repetitions 

Example 1: Input: String str = "adbcccdba";
             Output: c3
            
Example 2: Input: String str = "abcdefghijklmnz"
             Output: z1
*/

class Question2 {
    public static void main(String[] args) {
        String str1 = "adbcccdba";
        String str2 = "abcdefghijklmnz";
        System.out.println(solution(str1));
        System.out.println(solution(str2));
    }
    
    public static String solution(String str){
        if(str.length() == 0 || str == null){
            return null;
        }
        
        int count = 1;
        int max = 1;
        char res = str.charAt(0);
        
        for(int i = 1; i < str.length(); i++){
            //find the repeated character
            if(str.charAt(i) == str.charAt(i-1)){
                count += 1;
                //Update new longest repeating letter and count
                if(count >= max){
                    max = count;
                    res = str.charAt(i);
                }
            } else{ //if the element character is not repeated
                count = 1; //recount the longest repeated characters
                // if there is all the element is not repeated, should return the last element with numbers
                if(count == max){
                    res = str.charAt(i);// Update the character
                }
            }
        }
        
        return "" + res + max; //output as string format
    }
}
