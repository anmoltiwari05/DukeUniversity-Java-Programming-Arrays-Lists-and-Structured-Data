/**
 * Write a description of class Tester here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer object = new LogAnalyzer();
        object.readFile("short-test_log");
        object.printAll();
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer object = new LogAnalyzer();
        object.readFile("weblog1_log");
        object.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer object = new LogAnalyzer();
        object.readFile("weblog2_log");
        String day = "Sep 24";
        ArrayList<String> uniqueIPVisits= object.uniqueIPVisitsOnDay(day);
        System.out.println("Number of Unique IP Visits on "+day+": "+uniqueIPVisits.size());
        System.out.println("Unique IP Visits on "+day+": ");
        for (String s:uniqueIPVisits){
            System.out.println(s);
        }
    }
    public void testUniqueIPsInRange(){
        LogAnalyzer object = new LogAnalyzer();
        int num1=400;
        int num2=499;
        object.readFile("weblog2_log");
        ArrayList<String> UniqueIPsInRange = object.countUniqueIPsInRange(num1, num2);
        System.out.println("Number of Unique IPs in range "+num1+" and "+num2+": "+UniqueIPsInRange.size());
        System.out.println("Unique IPs: ");
        for (String s:UniqueIPsInRange){
            System.out.println(s);
        }
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer object = new LogAnalyzer();
        object.readFile("short-test_log");
        HashMap<String, Integer> visitsPerIP = new HashMap<String, Integer>();
        visitsPerIP = object.countVisitsPerIP();
        for(String ip:visitsPerIP.keySet()){
            System.out.println(ip+" Visits: "+visitsPerIP.get(ip));
        }
    }
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer object = new LogAnalyzer();
        object.readFile("weblog2_log");
        HashMap<String, Integer> visitsPerIP = new HashMap<String, Integer>();
        visitsPerIP = object.countVisitsPerIP();
        int maxNumber = object.mostNumberVisitsByIP(visitsPerIP);
        System.out.println("Max Visits: "+maxNumber);
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer object = new LogAnalyzer();
        object.readFile("weblog2_log");
        HashMap<String, Integer> visitsPerIP = object.countVisitsPerIP();
        ArrayList<String> IPs = object.iPsMostVisits(visitsPerIP);
        System.out.println("IPs with most visits: ");
        for(String s:IPs){
            System.out.println(s);
        }
    }
    
    public void testIPsForDays(){
        LogAnalyzer object = new LogAnalyzer();
        object.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> IPDays = object.iPsForDays();
        for(String s:IPDays.keySet()){
            System.out.println("Day: "+s+"- IPs visited: ");
            for(String IPs:IPDays.get(s)){
                System.out.println(IPs);
            }
            System.out.println();
        }
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer object = new LogAnalyzer();
        object.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> IPDays = object.iPsForDays();
        String day = object.dayWithMostIPVisits(IPDays);
        System.out.println("Day with most IP Visits: "+day);
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer object = new LogAnalyzer();
        object.readFile("weblog2_log");
        String day = "Sep 29";
        HashMap<String, ArrayList<String>> IPDays = object.iPsForDays();
        ArrayList<String> IPs = object.iPsWithMostVisitsOnDay(IPDays, day);
        System.out.println("IPs with Most Visits on: "+day);
        for(String s:IPs){
            System.out.println(s);
        }
    }
}
