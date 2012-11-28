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
        String input;
        int ret;
        try {
            input = choiceReader.readLine();
        } catch (IOException ioe) {
            input = "0";
        }
        try{
            ret = Integer.parseInt(input);
        }catch(NumberFormatException nfe){
            ret = 0;
        }
        return ret;
    }

    private void performChoice(int option) {
        switch(option){
            case 1: fullSend(); break;
            case 2: updateClient(); break;
            case 3: viewOwnData(); break;
            case 4: viewNetwork(); break;
            case 5: logOff(); break;
            default: invalidChoice();
        }
        System.out.println();
    }

    private void fullSend() {
        dataClient.sendCompleteUpdate();
        System.out.println("Data Sent");
    }
    
    private void updateClient(){
        dataClient.updateClientData();
        viewOwnData();
    }

    private void viewOwnData() {
        System.out.println(dataClient.getClientData());
    }

    private void viewNetwork() {
        System.out.println(dataClient.getNetwork());
    }

    private void logOff() {
        dataClient.removeClientData();
        System.out.println("Logged Off");
        ended = true;
    }

    private void invalidChoice() {
        System.out.println("Your choice wasn't recognised.");
        System.out.println("Please type the number representing your choice.");
    }
}
