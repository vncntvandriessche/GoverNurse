package client;

import client.domain.DataClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.ws.WebServiceException;

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
        try
        {
            new StartUp();
        }
        catch (WebServiceException wse)
        {
            System.out.println("The server could not be found at the expected location.");
            System.out.println("Make sure the server is online and configured correctly.");
            System.exit(1);
        }
    }

    public StartUp()
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
        System.out.println("1: Send Client Information");
        System.out.println("2: Update Client Information");
        System.out.println("3: View Current Data for This Client");
        System.out.println("4: View Entire Network");
        System.out.println("5: Log Off and Exit");
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
