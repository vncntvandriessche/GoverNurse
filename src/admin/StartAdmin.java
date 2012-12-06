package admin;

import client.*;
import client.domain.DataClient;
import generic.configuration.Connection;
import generic.domain.ClientData;
import generic.interfaces.IDataCollector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.ws.WebServiceException;

/**
 *
 * @author Frederik Thuysbaert
 */
public class StartAdmin //ADMIN
{

    private boolean ended;
    private IDataCollector dataCollector;
    private List<ClientData> clientList;

    public static void main(String[] args) {
        //Threadpool
        //DataClient thread (beter StartUp, want dan behouden wij de huidige CLI)
        //Remote thread
        //Remote moet DataClient kennen
        //ClientData moet Remote kennen

        try {
            new StartAdmin();
        } catch (WebServiceException wse) {
            System.out.println("The server could not be found at the expected location.");
            System.out.println("Make sure the server is online and configured correctly.");
            System.exit(1);
        }
    }

    public StartAdmin() {
        String serverAddress = Connection.PUBLICATION_URL;
        try {
            Registry registry = LocateRegistry.getRegistry(serverAddress);
            for (String s : registry.list()) {
                System.out.println("Found service: " + s);
            }
            dataCollector = (IDataCollector) (registry.lookup("DataCollector"));
        } catch (RemoteException e) {
            System.err.println(e);
        } catch (NotBoundException e) {
            System.err.println(e);
        }

        ended = false;
        int option;
        do {
            displayOptions();
            option = readChoice(false);
            performChoice(option);
        } while (!ended);
        System.exit(0);
    }

    private void displayOptions() {
        System.out.println("---------------MENU---------------");
        System.out.println("1: View clients");
        System.out.println("2: Get specific client information");
        System.out.println("3: Execute a job remotely");
        System.out.println("4: Log Off and Exit");
    }

    private int readChoice(boolean client) {
        BufferedReader choiceReader = new BufferedReader(new InputStreamReader(System.in));
        if (!client)
            System.out.println("Please make a choice from the menu:");
        else
            System.out.println("Please pick a clientnumber:");
        String input;
        int ret;
        try {
            input = choiceReader.readLine();
        } catch (IOException ioe) {
            input = "0";
        }
        try {
            ret = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            ret = 0;
        }
        return ret;
    }

    private void performChoice(int option) {
        switch (option) {
            case 1:
                viewNetwork();
                break;
            case 2:
                getClientInfo();
                break;
            case 3:
                executeJob();
                break;
            case 4:
                logOff();
                break;
            default:
                invalidChoice();
        }
        System.out.println();
    }

    private void viewNetwork() {
        try {
            clientList = Arrays.asList(dataCollector.getClientDataList());
            System.out.print(clientList);
        } catch (WebServiceException wse) {
            System.out.println("The connection with the server was lost.");
            System.exit(3);
        }
    }

    private void getClientInfo() {
        int id = readChoice(true);
        ClientData client = clientList.get(id);
        System.out.println(client.toString());
    }

    private void executeJob() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void logOff() {
        System.out.println("Logged Off");
        ended = true;
    }

    private void invalidChoice() {
        System.out.println("Your choice wasn't recognised.");
        System.out.println("Please type the number representing your choice.");
    }
}
