package edu.eci.arsw.blacklistvalidator;

import java.util.LinkedList;
import java.util.logging.Level;
import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Threads extends Thread {
    private int initialServer;
    private int finalServer;
    private String ipaddress;
    private int ocurrencesCount;
    private LinkedList<Integer> blackListOcurrences;
    private static final int BLACK_LIST_ALARM_COUNT = 5;
    private int checkedListsCount;

    public void setInitialServer(int initialServer) {
        this.initialServer = initialServer;
    }
    public void setFinalServer(int finalServer) {
        this.finalServer = finalServer;
    }
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
    public LinkedList<Integer> getBlackListOcurrences() {
        return blackListOcurrences;
    }
    public int getOccurrencesCount() {
        return ocurrencesCount;
    }
    public int getCheckedListsCount() {return checkedListsCount;}

    public void run() {
        blackListOcurrences=new LinkedList<>();
        ocurrencesCount=0;
        HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();

        checkedListsCount=0;

        for (int i=initialServer;i<finalServer;i++){
            checkedListsCount++;

            if (skds.isInBlackListServer(i, ipaddress)){

                blackListOcurrences.add(i);

                ocurrencesCount++;
            }
        }
    }
}
