/* Citadel OA Question 2: do they belong? */

//small function to get the area of trangle
float triangleArea(int x1, int y1, int x2, int y2, int x3, int y3){
    return abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
}

int pointsBelong(int x1, int y1, int x2, int y2, int x3, int y3, int xp, int yp, int xq, int yq) {
    bool p, q;
    
    //get the three lines of triangle
    float ab = sqrt(pow(abs(x2 - x1), 2) + pow(abs(y2 - y1), 2));
    float bc = sqrt(pow(abs(x3 - x2), 2) + pow(abs(y3 - y2), 2));
    float ac = sqrt(pow(abs(x3 - x1), 2) + pow(abs(y3 - y1), 2));
    
    //the rules of triangle: the length sum of two line can not small than other line
    if((ab + ac) <= bc || (ab + bc) <= ac || (bc + ac) <= ab){
        return 0;
    }
    
    //the area of triangle
    float area = triangleArea(x1, y1, x2, y2, x3, y3);
    
    //the point p
    float p1 = triangleArea(x1, y1, x2, y2, xp, yp);
    float p2 = triangleArea(x1, y1, x3, y3, xp, yp);
    float p3 = triangleArea(x3, y3, x2, y2, xp, yp);
    
    if(area == p1 + p2 + p3){
        p = true;
    } else{
        p = false;
    }
    
    //the point q
    float q1 = triangleArea(x1, y1, x2, y2, xq, yq);
    float q2 = triangleArea(x1, y1, x3, y3, xq, yq);
    float q3 = triangleArea(x3, y3, x2, y2, xq, yq);
    
    if(area == q1 + q2 + q3){
        q = true;
    } else{
        q = false;
    }
    
    //check the p and q to out put
    if(!p && !q){
        return 4;
    } else if(p && !q){
        return 1;
    } else if(!p && q){
        return 2;
    } else{
        return 3;
    }
}
