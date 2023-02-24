/*
Give a int[] list, the number only can less than 100 and large than -100
Find the sum with even postion - odd position
Input = [101, 3, 4, 506, 2, 5]
Output = -2
*/

import java.util.*;
import java.util.ArrayList; 
import java.util.HashMap;

class Question1 {
    public static void main(String[] args) {
        int[] list = new int[]{101, 3, 4, 506, 2, 5};
        System.out.println(Question1(list));
    }
    
    public static int Question1(int[] list){
        HashMap<Integer, Integer> map = new HashMap<>();
        int even = 0;
        int odd = 0;
        for(int i = 0; i < list.length; i++){
            map.put(i, list[i]);
        }
        
        for(int key : map.keySet()){
            if((map.get(key) <= 100) && (map.get(key) > -100)){
                if(key % 2 == 0){
                    even += map.get(key);
                } else{
                    odd += map.get(key);
                }
            }
        }
        
        return even - odd;
    }
}
