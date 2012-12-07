
/**
 *
 * @author Vincent Van Driessche
 */
public class StartUp
{

    public static final int SERVER = 0;
    public static final int CLIENT = 1;
    public static final int ADMIN = 2;

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

    public StartUp(int startNumber)
    {
        switch (startNumber)
        {
            case StartUp.SERVER:
                printStarting("Server");
                new server.StartServer();
                break;
            case StartUp.CLIENT:
                printStarting("Client");
                new client.StartClient();
                break;
            case StartUp.ADMIN:
                printStarting("Admin");
                new admin.StartAdmin();
                break;

            default:
                System.err.println("wrong parameter");
        }
    }

    private void printStarting(String applicationName)
    {
        System.out.println(String.format("Starting %s...", applicationName));
    }
}