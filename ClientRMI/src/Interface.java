/**
 * Created by Admin on 11/14/2016.
 */
import java.rmi.*;
import java.util.LinkedList;

public interface Interface extends Remote{
    public String add(String s) throws RemoteException;
    public String say( )        throws RemoteException;
    //////////////////////////////////////////////////
    public int id() throws RemoteException;
    public String Register(ClientInfo me) throws RemoteException;
    public String go(int id, int x, int y) throws RemoteException;
    public int[] getLocation (int id) throws RemoteException;
    public LinkedList list(int id, int distance) throws RemoteException;
    public String sendMsg(int id, String msg) throws RemoteException;
    public String quit(int id) throws RemoteException;
}