/**
 * Created by Admin on 11/14/2016.
 */
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;

public class ServerMainJob{

    public static int id =0;
    public static LinkedList<ClientInfo> Clients = new LinkedList<ClientInfo>();

    public static void main(String args[]){
        try {

            //Registry reg = LocateRegistry();
            //java.rmi.registry.LocateRegistry.createRegistry(1099);
            Naming.rebind("ServerRemoteFunc", new ServerRemoteFunc("\n'Server: ServerRemoteFunc Rebinding!' ") );
            System.out.println ("\nServer: After Rebind Done, Server is ready! ");
        } catch (Exception e) {
            System.out.println ("\nServerMainJob exception:"+e);
        }


    }
}

