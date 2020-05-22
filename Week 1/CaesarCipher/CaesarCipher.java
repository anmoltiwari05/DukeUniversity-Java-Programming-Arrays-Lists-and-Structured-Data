
/**
 * Write a description of CaesarCipher here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = letters.substring(key) + letters.substring(0,key);
        for (int i=0; i<encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            if (Character.isLetter(currChar)){
                if (Character.isUpperCase(currChar)){
                    int index = letters.indexOf(currChar);
                    encrypted.setCharAt(i, shifted.charAt(index));
                }
                else{
                    int index = (letters.toLowerCase()).indexOf(currChar);
                    encrypted.setCharAt(i, (shifted.toLowerCase()).charAt(index));
                }
            }
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        StringBuilder input1= new StringBuilder();
        StringBuilder input2= new StringBuilder();
        
        for (int i=0; i<encrypted.length();i++){
            char currChar = encrypted.charAt(i);
            if(i%2 == 0){input1.append(currChar);}
            if(i%2 == 1){input2.append(currChar);}
        }
        StringBuilder encrypted1 = new StringBuilder(encrypt(input1.toString(), key1));
        StringBuilder encrypted2 = new StringBuilder(encrypt(input2.toString(), key2));
        
        int cnt=0;
        for (int i=0;i<encrypted.length();i++){
            if (i%2 == 0){encrypted.setCharAt(i, encrypted1.charAt(cnt));}
            else{
                encrypted.setCharAt(i, encrypted2.charAt(cnt));
                cnt++;
            }
        }
        return encrypted.toString();
    }
    
    public void testEncryption(){
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public void testEncryptionTwoKeys(){
        int key1 = 24;
        int key2 = 6;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println("keys are " + key1 + " " + key2 + "\n" + encrypted);
    }
}
