
/**
 * Write a description of VigenereTester here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */
import edu.duke.*;
public class VigenereTester {
    public void testVigenere(){
        int[] keys = {3,20,10,4};
        VigenereCipher vc = new VigenereCipher(keys);
        FileResource fr = new FileResource();
        String text = fr.asString();
        String encrypted = vc.decrypt(text);
        System.out.println(encrypted);
    }
}
