class Result {

    public static int countNinjaWords(List<String> targetWords, String sentence) {
        int words = 0;
        String lowerCase = sentence.toLowerCase();
        String[] str = lowerCase.split(" ");
        HashSet<String> set = new HashSet<>();
        
        for(String s : targetWords){
            String sr = s.toLowerCase();
            set.add(sr);
        }
        
        //check to find there is how many words from targetWords in the sentence
        for(String c : str){
           int index = 1;
           while(index < c.length()){
               if(set.contains(c.substring(0, index))){
                   words += 1;
               }
               index++;
           }
        }
        
        String temp = lowerCase.replaceAll(" ","");
        int spaceWords = 0;
        boolean[] dp = new boolean[temp.length() + 1];
        //dp start:
        dp[0] = true;
        
        //travser all the string element from string sentence and compare with hashset
        for(int i = 1; i <= temp.length(); i++){
            for(int j = 0; j < i; j++){
                if(set.contains(temp.substring(j, i))){
                    dp[i] = true;
                    spaceWords += 1;
                    break;
                } 
            }
        }
        
        int res = spaceWords - words;
        return res;
    }
}
