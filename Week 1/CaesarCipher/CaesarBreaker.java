
/**
 * Write a description of CaesarCipherDecrypt here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaesarBreaker {
    
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
        return 26-shift;
    }
    
    public String decrypt(String phrase){
        CaesarCipher cc = new CaesarCipher();
        int key = getKey(phrase);
        return cc.encrypt(phrase, key);
    }
    
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String input1 = halfOfString(encrypted, 0);
        String input2 = halfOfString(encrypted, 1);
        int key1 = getKey(input1);
        int key2 = getKey(input2);
        System.out.println("Keys are: " + key1 + " " + key2);
        String plainText = cc.encryptTwoKeys(encrypted, key1, key2);
        return plainText;
    }
    
    public void testDecryptTwoKeys(){
        FileResource fr = new FileResource();
        String phrase = fr.asString();
        String plainText = decryptTwoKeys(phrase);
        System.out.println("Plain Text of \t" + phrase + "\r\n \r\n \r\n" + plainText);
    
    }
    public void testHalfString(){
        String phrase = halfOfString("Qbkm Zgis", 0);
        System.out.println(phrase);
    }
    
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String phrase = fr.asString();
        String plainText = decrypt(phrase);
        System.out.println("Plain Text of \t" + phrase + "\r \r \r" + plainText);
    
    }
    
}

