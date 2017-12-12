import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Admin on 11/21/2016.
 */
public class ClientsCommunications  extends UnicastRemoteObject implements ClientInterface {
    public static int myId;

    protected ClientsCommunications() throws RemoteException {
        myId = ClientMainJob.id;
    }

    @Override
    public String sendMsg(int senderId,int receiverId, String msg) throws RemoteException {
        System.out.print("Sender ID "+senderId+" : "+msg+"\n");
        return ("Message Received by Receiver ID: "+receiverId+"\n");
    }
}
