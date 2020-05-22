
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class CaesarCipherTwo{
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    
    public String encryptTwoKeys(String input){
        StringBuilder encrypted = new StringBuilder(input);
        StringBuilder input1= new StringBuilder();
        StringBuilder input2= new StringBuilder();
        CaesarCipher cc1 = new CaesarCipher(mainKey1);
        CaesarCipher cc2 = new CaesarCipher(mainKey2);
        for (int i=0; i<encrypted.length();i++){
            char currChar = encrypted.charAt(i);
            if(i%2 == 0){input1.append(currChar);}
            if(i%2 == 1){input2.append(currChar);}
        }
        StringBuilder encrypted1 = new StringBuilder(cc1.encrypt(input1.toString()));
        StringBuilder encrypted2 = new StringBuilder(cc2.encrypt(input2.toString()));
        
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
    
    public String decryptTwoKeys(String input){
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
        String decrypted = ccTwo.encryptTwoKeys(input);
        return decrypted;
    }
}
