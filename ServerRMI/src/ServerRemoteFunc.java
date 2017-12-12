/**
 * Created by Admin on 11/14/2016.
 */

import java.rmi.*; //Hello.java
import java.rmi.server.*;
import java.util.LinkedList;

public class ServerRemoteFunc extends UnicastRemoteObject implements Interface { ///////////////////overwrite the interface with functionality you want
    private String message;

    //public static int id=0;

    public ServerRemoteFunc(String msg) throws RemoteException {
        message = msg;
    }

    public String add(String s) throws RemoteException {
        message = new String(message + s);
        return message;
    }

    public String say() throws RemoteException {
        return message;
    }

    /////////////////////////////////////////////////////////////////////////
    @Override
    public int id() throws RemoteException {
        ServerMainJob.id++;
        ClientInfo tempClient=new ClientInfo();
        tempClient.setId(ServerMainJob.id);
        ServerMainJob.Clients.add(tempClient);
        System.out.print("\n inside id: client with ID "+ServerMainJob.id+ " Created ");
        return ServerMainJob.id;
    }

    @Override
    public String Register(ClientInfo me) throws RemoteException {

        int compareId;
        int i=0;
        for ( i=0; i<ServerMainJob.Clients.size(); i++) {

            compareId = ServerMainJob.Clients.get(i).getId();

            if (me.getId()==compareId){

                ServerMainJob.Clients.get(i).setX(me.getX());
                ServerMainJob.Clients.get(i).setY(me.getY());
                ServerMainJob.Clients.get(i).setName(me.getName());
                ServerMainJob.Clients.get(i).setAge(me.getAge());
                ServerMainJob.Clients.get(i).setCallBackClientInterface(me.getCallBackClientInterface());
            }

        }
        String s = "\n Client with the ID: " +me.getId()+" Registered\n";
        System.out.print("\n inside register: client with ID "+me.getId()+ " Created ");
        return (s);
    }

    @Override
    public String go(int id, int x, int y) throws RemoteException {
        System.out.print("\n inside GO for Client ID "+ id);
        int compareId;
        int i=0;
        String s=null;
        for ( i=0; i<ServerMainJob.Clients.size(); i++) {
            compareId = ServerMainJob.Clients.get(i).getId();
            //System.out.print(" inside for  finding ID " +compareId);
            if (id==compareId){
                //System.out.print(" inside if  " );
                ServerMainJob.Clients.get(i).setX(ServerMainJob.Clients.get(i).getX()+x);
                ServerMainJob.Clients.get(i).setY(ServerMainJob.Clients.get(i).getY()+y);
                s = "Client Data Updated for the Client ID " +id+" New X,Y: "+ServerMainJob.Clients.get(i).getX()+", "+ServerMainJob.Clients.get(i).getY()+"\n"  ;

            }
        }

        return (s);
    }

    @Override
    public int[] getLocation(int id) throws RemoteException {
        System.out.print("\n inside Get for Client ID "+id);
        int compareId;
        int[] x = new int[2];
        int i=0;
        for ( i=0; i<ServerMainJob.Clients.size(); i++) {
            compareId = ServerMainJob.Clients.get(i).getId();
            if (id==compareId){
                x[0] =ServerMainJob.Clients.get(i).getX();
                x[1] =ServerMainJob.Clients.get(i).getY();

            }
        }
        return (x);

    }

    @Override
    public LinkedList list(int id, int distance) throws RemoteException {
        System.out.print("\n inside list for client ID "+id);
        int compareId;
        int i = 0;
        int x = 0;
        int y = 0;
        for (i = 0; i < ServerMainJob.Clients.size(); i++) {
            compareId = ServerMainJob.Clients.get(i).getId();
            if (id == compareId) {
                x = ServerMainJob.Clients.get(i).getX();
                y = ServerMainJob.Clients.get(i).getY();
                System.out.print("\n inside list X: "+x+ ", Y: " +y);
            }
        }
        LinkedList<ClientInfo> ClientsDistance = new LinkedList<ClientInfo>();

        int i2 = 0;
        for (i2 = 0; i2 < ServerMainJob.Clients.size(); i2++) {
            if (Math.sqrt(Math.pow(ServerMainJob.Clients.get(i2).getX()-x, 2) + Math.pow(ServerMainJob.Clients.get(i2).getY()-y,2) )<= distance && ServerMainJob.Clients.get(i2).getName()!=null) {
                ClientsDistance.add(ServerMainJob.Clients.get(i2));
                System.out.print("\n inside list added Client with IDs: "+ServerMainJob.Clients.get(i2).getId());
            }
        }

        return (ClientsDistance);
    }
    @Override
    public String quit(int id) throws RemoteException {
        int compareId;
        int i=0;
        for ( i=0; i<ServerMainJob.Clients.size(); i++) {
            compareId = ServerMainJob.Clients.get(i).getId();
            if (id==compareId){
                ServerMainJob.Clients.remove(i);
            }
        }

        return ("Client With ID : Removed");
    }

    @Override
    public String sendMsg(int id, String msg) throws RemoteException {
        return null;
    }
}

