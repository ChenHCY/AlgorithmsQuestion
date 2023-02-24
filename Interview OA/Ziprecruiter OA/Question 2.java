/*Ziprecruiter Question 2 java

There is two cities A and B

count the fight used time

*/


int solution(int[] a2b, int[] b2a, int trips) {
    int res = 0; //the final result
    int curA = 0; //cursor in a2b array
    int curB = 0; //cursor in btoa array
    int i = 0; //store the trip times
    boolean inAtoB = true; //used for in AtoB or BtoA
    
    //follow the rules and travser all the element form a2b[] array and b2a[] array
    while(curA < a2b.length && curB < b2a.length){
        if(inAtoB == true){
            if(a2b[curA] + 100 > b2a[curB]){
                curB++;
            } else{
                res = b2a[curB];
                inAtoB = false;
                i += 1;
            }
        } 
        
        if(inAtoB == false){
            if(b2a[curB] + 100 > a2b[curA]){
                curA++;
            } else{
                i += 1;
                if(i == trips * 2){
                    res = b2a[curB]; 
                    res += 100;
                    break;
                } else{
                    res = a2b[curA];
                    inAtoB = true;
                }
            }
        }
    }
    
    //if there is still have last trip from B back to A did not finished
    if(i != trips * 2){
        res = b2a[curB] + 100; 
    }
    
    return res;
}
