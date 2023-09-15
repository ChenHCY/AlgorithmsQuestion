import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.lang.*;

/**
 * The Main class implements an application that reads lines from the standard input
 * and prints them to the standard output.
 */
public class Main {
  /**
   * Iterate through each line of input.
   */
  protected static class Node{
    String currency;
    double value;
    
    Node(String currency, double value){
      this.currency = currency;
      this.value = value;
    }
  }
  
  protected static double[] max = new double[]{-1}; //used to save the max value
  //small function to coun the max value can get of currency
  private static double countValue(HashMap<String, ArrayList<Node>> map, String original, String target, double value, HashSet<String> used){
    //the fail case, exit
    if(used.contains(original)){
      return -1;
    }
    
    if(!map.containsKey(original)){
      return -1;
    }
    
    used.add(original);
    
    //the sucess case, exit
    if(original.equals(target)){
      return value;
    }
    
    //travser all the element
    for(Node next : map.get(original)){
      double val = countValue(map, next.currency, target, value * next.value, used);
      max[0] = Math.max(max[0], val);
    }
    
    used.remove(original);
    return max[0];
  }
  
  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    HashMap<String, ArrayList<Node>> map = new HashMap<>(); // save the Original money, and the list <target moeny, value>
    String original = ""; //original moeny 
    String target = ""; //target money
    
    int indexLine = 0; //Used for to get the every test case
    ArrayList<String> testCase = new ArrayList<>(); // store the separated transactions the test case start at line 0
    
    while ((line = in.readLine()) != null) {
      if(indexLine == 0){
        String[] temp = line.split(";");
        for(String str : temp){
          testCase.add(str);
        }
      } else if (indexLine == 1){
        original = line;
      } else if (indexLine == 2){
        target = line;
      }
      indexLine++;
    }
   
    //build the graph of every currency 
    for(String str : testCase){
      String[] transientArr = str.split(",");
      //beacuse every kinds money can be change in two ends
      String u = transientArr[0]; 
      String v = transientArr[1];
      Double currValue = Double.parseDouble(transientArr[2]);
     
      //if did not meet this kinds money before
      if(!map.containsKey(u)){
        map.put(u, new ArrayList<>());
      }
      
      //if did not meet this kinds money before
       if(!map.containsKey(v)){
        map.put(v, new ArrayList<>());
      }
      //add the details into hashmap
      map.get(u).add(new Node(v, currValue));
    }
    
    // output the max value can get
    System.out.println(countValue(map, original, target, 1, new HashSet<>()));
  }
}
