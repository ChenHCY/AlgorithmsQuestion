/* Codesingal OA 2 Question 1 */

String[] solution(String[] a) {
    List<String> list = new ArrayList<>();
    
    //travser all the element from array a[]
    for(int i = 0; i < a.length; i++){
        String first = a[0];
        String cur = a[i];
        
        if(i != a.length - 1){
            String next = a[i+1];
            String str = "" + cur.charAt(0) + next.charAt(next.length() - 1);
            list.add(str);
        } else{
            String str2 = "" + cur.charAt(0) + first.charAt(first.length() - 1);
            list.add(str2);
        }
    }
    
    String[] res = new String[list.size()];
    for(int j = 0; j < list.size(); j++){
        res[j] = list.get(j);
    }
    
    return res;
}
