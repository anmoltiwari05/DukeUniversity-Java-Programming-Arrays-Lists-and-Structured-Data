
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class TestCaesarCipherTwo {
    public int maxIndex(int[] values){
        int count=0, index=0;
        for(int i=0;i<values.length;i++){
            if (values[i]>count){
                count = values[i];
                index = i;
            }
        }
        return index;
    }
    
    public int[] countLetters(String phrase){
        int[] count = new int[26];
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i=0;i<phrase.length();i++){
            char currChar= phrase.charAt(i);
            int index = letters.indexOf(Character.toUpperCase(currChar));
            if (index != -1){count[index] += 1;}
        }
        return count;
    }
    
    public String halfOfString(String message, int start){
        StringBuilder halfString = new StringBuilder();
        for (int i=start;i<message.length();i+=2){
            halfString.append(message.charAt(i));
        }
        return halfString.toString();
    }
    
    public int getKey(String s){
        int[] count = countLetters(s);
        int maxDex = maxIndex(count);
        int shift = maxDex - 4;
        if(maxDex < 4){
            shift = 26- (4 -maxDex);
        }
        return shift;
    }
    
    public String breakCaesarCipher(String input){
        String input1 = halfOfString(input, 0);
        String input2 = halfOfString(input, 1);
        int key1 = getKey(input1);
        int key2 = getKey(input2);
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(key1, key2);
        return ccTwo.decryptTwoKeys(input);
    }
    
    public void simpleTests (){
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(12,2);
        String encrypted = ccTwo.encryptTwoKeys(input);
        String plainText = ccTwo.decryptTwoKeys(encrypted);
        String decrypted = breakCaesarCipher(encrypted);
        System.out.println("Decrypted String: " + plainText);
        System.out.println("Encrypted String: " + encrypted);
        System.out.println("Decrypted String: " + decrypted);
    }
}
