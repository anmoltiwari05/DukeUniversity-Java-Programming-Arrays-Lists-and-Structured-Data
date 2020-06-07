/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author Anmol Tiwari 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr= new FileResource(filename);
         for(String s:fr.lines()){
             records.add(WebLogParser.parseEntry(s));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le:records){
             if(!uniqueIPs.contains(le.getIpAddress())){
                 uniqueIPs.add(le.getIpAddress());  
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num){
         System.out.print("LogEntrys that have a status code greater than "+num);
         for(LogEntry record: records){
             int SCode = record.getStatusCode();
             if (SCode>num){
                 System.out.println(record);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPVisits = new ArrayList<String>();
         for(LogEntry record: records){
             String date = record.getAccessTime().toString();
             if (date.contains(someday)){
                 if(!uniqueIPVisits.contains(record.getIpAddress())){
                     uniqueIPVisits.add(record.getIpAddress());
                 }
             }
         }
         return uniqueIPVisits;
     }
     
     public ArrayList<String> countUniqueIPsInRange(int num1, int num2){
         ArrayList<String> UniqueIPsInRange = new ArrayList<String>();
         for(LogEntry entry:records){
             if(entry.getStatusCode()>= num1 && entry.getStatusCode()<=num2){
                 if(!UniqueIPsInRange.contains(entry.getIpAddress())){
                     UniqueIPsInRange.add(entry.getIpAddress());
                 }
             }
         }
         return UniqueIPsInRange;
        }
        
     public HashMap<String, Integer> countVisitsPerIP(){
     HashMap <String, Integer> counts = new HashMap<String, Integer>();
     for(LogEntry le:records){
         String ip = le.getIpAddress();
         if (counts.keySet().contains(ip)){
             counts.put(ip,counts.get(ip)+1);
            }
         else{counts.put(ip,1);}
     }
     return counts;   
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         int maxVisits = 0;
         for(String ip:counts.keySet()){
            int currVisits = counts.get(ip);
            if(currVisits>maxVisits){
                maxVisits = currVisits;
            }
            }
         return maxVisits;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
        ArrayList<String> IPs = new ArrayList<String>();
        int maxVisits = mostNumberVisitsByIP(counts);
        for(String ip:counts.keySet()){
            if(counts.get(ip) == maxVisits){
                IPs.add(ip);
            }
        }
        return IPs;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> IPsDays= new HashMap<String, ArrayList<String>>();
        for(LogEntry le:records){
            String accessTime = le.getAccessTime().toString();
            String date = accessTime.substring(4,10);
            if(!IPsDays.keySet().contains(date)){
                ArrayList<String> IPs = new ArrayList<String>();
                IPs.add(le.getIpAddress());
                IPsDays.put(date, IPs);
            }
            else{
                ArrayList<String> IPs = IPsDays.get(date);
                IPs.add(le.getIpAddress());
                IPsDays.put(date, IPs);
            }
        }
        return IPsDays;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDays){
        String day = "";
        int visits = 0;
        for(String days: iPsForDays.keySet()){
            int currVisits = iPsForDays.get(days).size();
            if(currVisits>visits){
                visits = currVisits;
                day = days;
            }
        }
        return day;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDays, String day){
        ArrayList<String> IPs = iPsForDays.get(day);
        HashMap<String, Integer> IPCounts = new HashMap<String, Integer>();
        for(String s:IPs){
            if (IPCounts.keySet().contains(s)){
                IPCounts.put(s, IPCounts.get(s)+1);
            }
            else{IPCounts.put(s,1);}
        }
        ArrayList<String> mostVisits = iPsMostVisits(IPCounts);
        return mostVisits;
    }
}
