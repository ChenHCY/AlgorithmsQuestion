/*OA Question 2 odd strings

check the string is odd or even through ascii

*/

/*

首先，题目要打印这个list的所有strings的ASCII判断求幂再求和是奇偶，等价于你需要找出什么情况下会是偶，什么情况下会是奇数，我们倒推：
1. 奇数 + 奇数 = 偶数
2. 偶数 + 偶数 = 偶数
3. 奇数 + 偶数 = 奇数
因此，第三种情况出现结果，再思考什么样的幂会出现奇数，答案是奇数的任意次幂是奇数，偶数的任意次幂为偶数，又需推导乘积如何才能是奇数，得：
奇数 x 偶数 = 偶数
奇数 x 奇数 = 奇数
偶数 x 偶数 = 偶数
所以但凡一个字符串出现偶数结果都是偶数，这就很简单了，只需统计奇数字符串的个数，比如：a, ac, ace,等等等等

*/

import java.util.*;
public class Main
{
	public static void main(String[] args) {
	    List<String> list = new ArrayList<>();
	    list.add("ace");
	    //list.add("abcdf");
	    int m = 2;
	    
	    String res = solve(m, list);
		System.out.println(res);
	}
	
	public static String solve(int m, List<String> s){
	    int countOdd = 0;
	    
	    for(String str : s){
	        boolean even = false;
	        for(char c : str.toCharArray()){
	            if((c - 'a') % 2 != 0){
	                even = true; //the character is even
	            }
	        }
	        if(!even){
	            countOdd+=1;
	        }
	    }
	    
	    if(countOdd % 2 != 0){
	        return "ODD";
	    }
	    
	    return "EVEN";
	}
}
