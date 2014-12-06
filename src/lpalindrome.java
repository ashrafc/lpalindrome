import java.io.*;
import java.util.*;

public class lpalindrome {
    private Map<String, String> map;
    private String longestSubsequence;
    
    public lpalindrome(String input){
        map = new HashMap<String, String>();
        longestSubsequence = findPSubsequence(input);
    }
    
    public String result(){
        return longestSubsequence;
    }
    
    public String findPSubsequence(String currentStr){
        String subSequence = "";
        //	Check to see if string is already processed
        if (map.containsKey(currentStr))
            subSequence = map.get(currentStr);
        else {
        	//	1 char or less
            if (currentStr.length() <= 1)
                subSequence = currentStr;
            //	2 chars that are the same
            else if ((currentStr.length() == 2) && (currentStr.charAt(0) == currentStr.charAt(1)))
                subSequence = currentStr;
            //	1st char == last char
            else if (currentStr.charAt(0) == currentStr.charAt(currentStr.length()-1)) {
                String inside = currentStr.substring(1, currentStr.length()-1);
                subSequence = currentStr.charAt(0) + findPSubsequence(inside) + currentStr.charAt(0);
            }
            //	1st char != last char
            else {
                String right = findPSubsequence(currentStr.substring(1));
                String left = findPSubsequence(currentStr.substring(0, currentStr.length()-1));
                if (right.length() > left.length())
                    subSequence = right;
                else 
                    subSequence = left;
            }
            //	Store the processed string
            map.put(currentStr, subSequence);
        }
        return subSequence;
    }
    
    public static void main(String[] args) throws IOException {
        //	Sample from the problem
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
    	String sequence = br.readLine();
        lpalindrome subsequence = new lpalindrome(sequence);
        PrintWriter pr = new PrintWriter("output.txt"); 
        pr.println("Longest subsequence: " + subsequence.result());
        pr.close();
        br.close();
    }

}
