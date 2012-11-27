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
    
    public static void main(String[] args)
    {
        new StartUp();
    }
    
    public StartUp(){
        dataClient = new DataClient();
        
        boolean ended = false;
        int option;
        do{
            displayOptions();
            option = readChoice();
            performChoice(option);
        }while(!ended);
    }

    private void displayOptions() {
        System.out.println("1: Send Up-To-Date Client Information");
        System.out.println("2: View Current Data for This Client");
        System.out.println("3: View Entire Network");
        System.out.println("4: Log Off and Exit");
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
        System.out.println(option);
        switch(option){
            case 49: fullSend(); break;
            case 50: viewOwnData(); break;
            case 51: viewNetwork(); break;
            case 52: logOff(); break;
            default: invalidChoice();
        }
    }

    private void fullSend() {
        System.out.println("Full Send");
    }

    private void viewOwnData() {
        System.out.println("View Data");
    }

    private void viewNetwork() {
        System.out.println("View Network");
    }

    private void logOff() {
        System.out.println("Log Off");
    }

    private void invalidChoice() {
        System.out.println("Invalid choice");
    }
}
