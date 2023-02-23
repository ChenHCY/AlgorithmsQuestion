# Tiktok OA Question 4
#Tom is a cat and Jerry is a mouse. Tom wants to hack Jerry's bank with his knowledge. 
#jerryPassword: a single integer representing Jerry's bank account password.
#Tom's bank account password is represented as a single integer tomPassword. 
#Jerry's bank account password is represented as a single integer jerryPassword. 

#With Tom's cat knowledge, he thinks he can hack Jerry's account by reordering his password digits and maximizing his password value. 
#However, to be undetected by the policy, Tom's reordered password value should be less or equal to Jerry's password value. 
#Return a single string representing the maximum reordered password that is less or equal to Jerry's password value. 

#If there is no such reordering, return "-1". Note that an integer (bank account password) cannot have leading zero. 
#Function Description Complete the function hackTheBank in the editor below. The function returns a string representing Tom's reordered password. 
#hackTheBank has the following parameter(s): - tomPassword: a single integer representing Tom's bank account password. 

#Example test case:

#tomPassword: "321"

#jerryPassword: "223"

# ===> ans: "232"  ==> Passed Test Case: 17/35

from itertools import permutations
def hackTheBank(tomPassword, jerryPassword):
    # Write your code here
    #change the integer to be string format
    tomPass = '{}'.format(tomPassword)
    jerryPass = '{}'.format(jerryPassword)
    
    if(len(tomPass) > 1):
        a = []
        for i in tomPass:
            a.append(i)
        perm = permutations(a, len(a))
        
        b = []
        for j in list(perm):
            str = ''
            for z in j:
                str = str + z
            b.append(str)
        
        c = []
        for k in b:
            if(int(k) > int(jerryPass)):
                pass
            else:
                c.append(k)
        return max(c)
    else:
        return -1
if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    tomPassword = int(input().strip())

    jerryPassword = int(input().strip())

    result = hackTheBank(tomPassword, jerryPassword)

    fptr.write(result + '\n')

    fptr.close()
