package client;

import client.domain.DataClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vincent Van Driessche
 */
public class StartUp //CLIENT
{
    private DataClient dataClient;
    private boolean ended;
    
    public static void main(String[] args)
    {
        new StartUp();
    }
    
    public StartUp(){
        dataClient = new DataClient();
        
        ended = false;
        int option;
        do{
            displayOptions();
            option = readChoice();
            performChoice(option);
        }while(!ended);
        System.exit(0);
    }

    private void displayOptions() {
        System.out.println("1: Send Client Information");
        System.out.println("2: Update Client Information");
        System.out.println("3: View Current Data for This Client");
        System.out.println("4: View Entire Network");
        System.out.println("5: Log Off and Exit");
    }

    private int readChoice() {
        BufferedReader choiceReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please Type Your Choice: ");
        int ret;
        try {
            ret = choiceReader.read();
        } catch (IOException ex) {
            ret = 0;
        }
        return ret;
    }

    private void performChoice(int option) {
        switch(option){
            case 49: fullSend(); break;
            case 50: updateClient(); break;
            case 51: viewOwnData(); break;
            case 52: viewNetwork(); break;
            case 53: logOff(); break;
            default: invalidChoice();
        }
    }

    private void fullSend() {
        dataClient.sendCompleteUpdate();
    }
    
    private void updateClient(){
        dataClient.updateClientData();
    }

    private void viewOwnData() {
        System.out.println(dataClient.getClientData());
    }

    private void viewNetwork() {
        System.out.printf(dataClient.getNetwork());
        System.out.println();
    }

    private void logOff() {
        dataClient.removeClientData();
        ended = true;
    }

    private void invalidChoice() {
        System.out.println("Your choice wasn't recognised.");
        System.out.println("Please type the number representing your choice.");
    }
}
