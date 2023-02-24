/* Zoom OA Question 2

Bird move
*/

int[] solution(int[] forest, int bird) {
    List<Integer> list = new ArrayList<>();
    boolean[] visited = new boolean[forest.length];
    int step = 1;
    int sum = 0;
    
    for(int i = 0; i < forest.length; i++){
        visited[i] = true;
    }
    
    while(sum < 100){
        if(step % 2 != 0){
            for(int i = bird; i < forest.length; i++){
                if(forest[i] != 0 && visited[i]){
                    sum += forest[i];
                    list.add(i);
                    visited[i] = false;
                    break;
                }
            }
        } else{
            for(int i = bird; i >= 0; i--){
                if(forest[i] != 0 && visited[i]){
                    sum += forest[i];
                    list.add(i);
                    visited[i] = false;
                    break;
                }
            }
        }
        step++;
    }
    
    int[] res = new int[list.size()];
    for(int j = 0; j < list.size(); j++){
        res[j] = list.get(j);
    }
    return res;
}
