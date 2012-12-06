/**
 * Universal startup file.
 * @author
 */
public class StartUp
{
    public static final int SERVER = 1;
    public static final int CLIENT = 0;

    /**
     * Start the program.
     * @param args : The parameters with which the program can be started.
     * --type 0 = Client
     * --type 1 = Server
     */
    public static void main(String[] args)
    {
        int startValue = -1;
        boolean isNext = false;

        for (String currentArg : args)
        {
            if (isNext)
            {
                startValue = Integer.parseInt(currentArg);
                break;
            }
            isNext = currentArg.equalsIgnoreCase("--type");
        }

        new StartUp(startValue);
    }

    /**
     * Carries out the execution of the server or client.
     * @param startNumber : defines whether the server or client is started.
     */
    private StartUp(int startNumber)
    {
        switch (startNumber)
        {
            case StartUp.CLIENT:
                printStarting("Client");
                new client.StartClient();
                break;
            case StartUp.SERVER:
                printStarting("Server");
                new server.StartServer();
                break;
            default:
                System.err.println("wrong parameter");
        }
    }
    
    /**
     * Displays which part of the program was started.
     * @param applicationName : The name of the service that was executed.
     */
    private void printStarting(String applicationName)
    {
        System.out.println(String.format("Starting %s...", applicationName));
    }
}
