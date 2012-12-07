package generic.configuration;

import generic.domain.Service;
import server.domain.DataCollector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Vincent Van Driessche
 */
public final class Connection
{

    private static final Properties properties;
    private static final String LOCATION, NAME;
    public static final String SERVER_IP, PUBLICATION_URL, CHILD, PARAM;
    public static final int PORT;

    static
    {
        properties = new Properties();
        try
        {
            properties.load(new FileInputStream("settings.properties"));
        }
        catch (IOException ex)
        {
            makeDefaultFile();
        }
        SERVER_IP = properties.getProperty("server_ip");
        PUBLICATION_URL = "http://" + SERVER_IP;
        CHILD = properties.getProperty("server_child");
        PARAM = properties.getProperty("server_param");
        PORT = Integer.parseInt(properties.getProperty("server_port"));
        LOCATION = properties.getProperty("service_location");
        NAME = properties.getProperty("service_name");
    }
    public static final Service QUALIFIED_SERVICE = new Service(LOCATION, NAME);
    public static final DataCollector COLLECTOR_INSTANCE = (new DataCollector());

    private static void makeDefaultFile()
    {
        properties.setProperty("server_ip", "127.0.0.1");
        properties.setProperty("server_child", "dataserver");
        properties.setProperty("server_param", "?wsdl");
        properties.setProperty("server_port", "8889");
        properties.setProperty("service_location", "http://domain.server/");
        properties.setProperty("service_name", "DataCollectorService");
        saveSettings();
    }

    private static void saveSettings()
    {
        try
        {
            properties.store(new FileOutputStream("settings.properties"), null);
        }
        catch (IOException e)
        {
            try
            {
                new File("settings.properties").createNewFile();
            }
            catch (IOException ex)
            {
                System.out.println("Error creating properties file");
            }
        }
    }
}