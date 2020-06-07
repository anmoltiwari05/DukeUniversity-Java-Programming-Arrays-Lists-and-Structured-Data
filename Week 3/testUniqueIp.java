
/**
 * Write a description of testUniqueIp here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */
public class testUniqueIp {
    public void testUniqueIP(){
        LogAnalyzer object = new LogAnalyzer();
        object.readFile("weblog2_log");
        int uniqueIPs = object.countUniqueIPs();
        System.out.println("Number of Unique IPs: "+uniqueIPs);
    }
}
