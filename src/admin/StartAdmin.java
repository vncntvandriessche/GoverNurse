package admin;

import client.*;
import client.domain.DataClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.ws.WebServiceException;

/**
 *
 * @author Frederik Thuysbaert
 */
public class StartAdmin //ADMIN
{

    private DataClient dataClient;
    private boolean ended;

    public static void main(String[] args)
    {
        //Threadpool
        //DataClient thread (beter StartUp, want dan behouden wij de huidige CLI)
        //Remote thread
        //Remote moet DataClient kennen
        //ClientData moet Remote kennen
        
        try
        {
            new StartAdmin();
        }
        catch (WebServiceException wse)
        {
            System.out.println("The server could not be found at the expected location.");
            System.out.println("Make sure the server is online and configured correctly.");
            System.exit(1);
        }
    }

    public StartAdmin()
    {
        dataClient = new DataClient();

        ended = false;
        int option;
        do
        {
            displayOptions();
            option = readChoice();
            performChoice(option);
        }
        while (!ended);
        System.exit(0);
    }

    private void displayOptions()
    {
        System.out.println("1: View clients");
        System.out.println("2: Get specific client information");
        System.out.println("3: Log Off and Exit");
    }

    private int readChoice()
    {
        BufferedReader choiceReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please Type Your Choice: ");
        String input;
        int ret;
        try
        {
            input = choiceReader.readLine();
        }
        catch (IOException ioe)
        {
            input = "0";
        }
        try
        {
            ret = Integer.parseInt(input);
        }
        catch (NumberFormatException nfe)
        {
            ret = 0;
        }
        return ret;
    }

    private void performChoice(int option)
    {
        switch (option)
        {
            case 1:
                viewNetwork();
                break;
            case 2:
                getClientInfo();
                break;
            case 3:
                logOff();
                break;
            default:
                invalidChoice();
        }
        System.out.println();
    }

    private void fullSend()
    {
        try
        {
            dataClient.sendCompleteUpdate();
        }
        catch (WebServiceException wse)
        {
            System.out.println("The connection with the server was lost.");
            System.exit(2);
        }
        System.out.println("Data Sent");
    }

    private void updateClient()
    {
        dataClient.updateClientData();
        viewOwnData();
    }

    private void viewOwnData()
    {
        System.out.println(dataClient.getClientData());
    }

    private void viewNetwork()
    {
        try
        {
            System.out.print(dataClient.getNetwork());
        }
        catch (WebServiceException wse)
        {
            System.out.println("The connection with the server was lost.");
            System.exit(3);
        }
    }
    
    private void getClientInfo()
    {
        
    }

    private void logOff()
    {
        dataClient.removeClientData();
        System.out.println("Logged Off");
        ended = true;
    }

    private void invalidChoice()
    {
        System.out.println("Your choice wasn't recognised.");
        System.out.println("Please type the number representing your choice.");
    }
}
