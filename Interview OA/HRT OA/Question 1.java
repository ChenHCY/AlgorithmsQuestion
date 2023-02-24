/* HRT OA Question 1

Leetcode 722. Remove Comments - Diffcult Version

Count the total length of List Source[] String

*/

import java.util.ArrayList; 

public class Main
{
	public static void main(String[] args) {
	    String[] array = new String[] {"int a = 2;", "int b = 47;/*37;*///41;", "int c = 3/*4//5*/;", "return a / b * c/*a /*b / c*/;"};
		int number = removeComments(array);
		System.out.println(number);
	}
	
	public static int removeComments(String[] source) {
        boolean block = false;
        ArrayList<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        
        for (String str : source) {
            for (int i = 0; i < str.length(); i++) {
                if (block) {
                    if (str.charAt(i) == '*' && i + 1 < str.length() && str.charAt(i + 1) == '/' ) {
                        block = false;
                        i++;
                    }
                } else {
                    if (str.charAt(i) == '/' && i + 1 < str.length() && str.charAt(i + 1) == '*' ) {
                        block = true;
                        i++;
                    } else if (str.charAt(i) == '/' && i + 1 < str.length() && str.charAt(i + 1) == '/' ) {
                        break;
                    } else sb.append(str.charAt(i));
                }
            }
            if (!block && sb.length() > 0) {
                res.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        
        for(String str : res){
            String temp = str.replace(" ", "");
            System.out.println(str);
            index += temp.length();
        }
        return index;
    }
}
