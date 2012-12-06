package client;

import client.domain.DataClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.ws.WebServiceException;

/**
 * Client startup file.
 * @author 
 */
public class StartClient
{

    private DataClient dataClient;
    private boolean ended;

    /**
     * Starts an instance of the client program.
     * @param args : No parameters are used.
     */
    /*public static void main(String[] args)
    {
        //Threadpool
        //DataClient thread (beter StartUp, want dan behouden wij de huidige CLI)
        //Remote thread
        //Remote moet DataClient kennen
        //ClientData moet Remote kennen
        
        try
        {
            new StartClient();
        }
        catch (WebServiceException wse)
        {
            System.out.println("The server could not be found at the expected location.");
            System.out.println("Make sure the server is online and configured correctly.");
            System.exit(1);
        }
    }*/

    /**
     * Creates the client instance.
     */
    public StartClient()
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

    /**
     * Displays the valid choices to be made by the client in the standard output.
     */
    private void displayOptions()
    {
        System.out.println("1: Send Client Information");
        System.out.println("2: Update Client Information");
        System.out.println("3: View Current Data for This Client");
        System.out.println("4: View Entire Network");
        System.out.println("5: Log Off and Exit");
    }

    /**
     * Requests input from the user to define the operation to be carried out by the client.
     * @return : The number representing the choice made by the user.
     */
    private int readChoice()
    {
        BufferedReader choiceReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please Type Your Choice: ");
        String input;
        int ret;
        try
        {
            input = choiceReader.readLine().trim();
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

    /**
     * Executes the choice made by the client.
     * @param option : The number representing the choice to be executed.
     */
    private void performChoice(int option)
    {
        switch (option)
        {
            case 1:
                fullSend();
                break;
            case 2:
                updateClient();
                break;
            case 3:
                viewOwnData();
                break;
            case 4:
                viewNetwork();
                break;
            case 5:
                logOff();
                break;
            default:
                invalidChoice();
        }
        System.out.println();
    }

    /**
     * Sends an entire copy of the clientdata to the server.
     */
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

    /**
     * Updates the clientdata to the latest available from the system.
     */
    private void updateClient()
    {
        dataClient.updateClientData();
        viewOwnData();
    }

    /**
     * Prints the data that has been or will be sent to the server in a human-readable format.
     */
    private void viewOwnData()
    {
        System.out.println(dataClient.getClientData());
    }

    /**
     * Prints all the clientdata currently logged on to the server in a human-readable format.
     */
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

    /**
     * removes the clientdata from the server and exits this instance.
     */
    private void logOff()
    {
        dataClient.removeClientData();
        System.out.println("Logged Off");
        ended = true;
    }

    /**
     * Informs the user when an invalid choice was made.
     */
    private void invalidChoice()
    {
        System.out.println("Your choice wasn't recognised.");
        System.out.println("Please type the number representing your choice.");
    }
}
