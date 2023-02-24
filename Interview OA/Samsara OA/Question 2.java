/*
Give a String[] list review
if postive words more than negative words, add the "postive" into res[] 
if postive words less than negative words, add the "negative" into res[] 
if postive words is same with negative words, add the "netural" into res[] 

For Example:
review = ["very good", "bad in worst", "good bad"];
postiveWords = ["very", "good"];
negativeWords = ["bad", "wrost"];

output: ["positive", "negative", "netural"]
*/
import java.util.*;
import java.util.ArrayList; 
import java.util.HashMap;

public class Main{
    public static void main(String[] args) {
        String[] review = new String[]{"very good", "bad in worst", "good and bad"};
        String[] positiveWords = new String[]{"very", "good"};
        String[] negativeWords = new String[]{"bad", "wrost"};
        
        String[] result = findArray(review, positiveWords, negativeWords);
        
        for(String str : result){
            System.out.println(str);
        }
    }
    
    public static String[] findArray(String[] review, String[] positiveWords, String[] negativeWords){
        
        List<String> arr = new ArrayList<>();
        HashSet<String> setP = new HashSet<>();
        HashSet<String> setN = new HashSet<>();
        int index = 0;
        
        for(String str : positiveWords){
            setP.add(str);
        }
        
        for(String str : negativeWords){
            setN.add(str);
        }
        
        while(index < review.length){
            String[] temp = review[index].split(" ");
            int positiveNumber = 0;
            int negativeNumber = 0;
            
            for(int j = 0; j < temp.length; j++){
                if(setP.contains(temp[j])){
                    positiveNumber += 1;
                } 
                
                if(setN.contains(temp[j])){
                    negativeNumber += 1;
                }
            }
             
            if(positiveNumber > negativeNumber){
                arr.add("positive");
            } else if (positiveNumber < negativeNumber){
                arr.add("negative");
            } else{
                arr.add("netural");
            }
            
            index++;
        }
        
        String[] res = new String[arr.size()];
        for(int z = 0; z < res.length; z++){
            res[z] = arr.get(z);
        }
        
        return res;
    }
}
