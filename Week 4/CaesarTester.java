
/**
 * Write a description of CaesarTester here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */
public class CaesarTester {
    public void testCaesarCipher(){
        CaesarCipher cc = new CaesarCipher(1);
        System.out.println(cc.encrypt("ATTACK AT DAWN"));
        //System.out.println(cc.encryptLetter('A'));
    }
    
    public void testCaesarCracker(){
        CaesarCracker cc = new CaesarCracker('a');
        String message = "BUUBDL BU EBXO";
        System.out.println(cc.decrypt("BUUBDL BU EBXO"));
    }
}
