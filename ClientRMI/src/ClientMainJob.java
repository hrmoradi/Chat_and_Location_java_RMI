/**
 * Created by Admin on 11/14/2016.
 */
import java.rmi.*;
import java.security.Policy;
import java.util.*;
import java.io.*;

public class ClientMainJob{
    public static int id;
    public static void main (String[] argv) {

        //Policy.setPolicy(new MinimalPolicy());

        /*try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            //if (System.getSecurityManager()==null) System.setSecurityManager((new RMISecurityManager()));
            //System.setSecurityManager(new RMISecurityManager());

            Naming.rebind("ClientsCommunications", new ClientsCommunications());
            System.out.println("\nClient: After Rebind Done, ClientsCommunications is ready! ");
            ///////////Clients mapping his own function
            Interface CallClientsCommunications = (Interface) Naming.lookup("//localhost/ClientsCommunications");
            System.out.println("\nClient: CallClientsCommunications instance created! ");
        }catch (Exception e) {
            System.out.println ("ClientMainJob exception:"+e);
        }*/


        // create object of ClientInfo
        ClientInfo me = new ClientInfo();
        LinkedList<ClientInfo> ClientsDistanceX = new LinkedList<ClientInfo>();
        try {
            Interface CallServerRemoteFunc = (Interface) Naming.lookup("//localhost/ServerRemoteFunc");
            System.out.println(CallServerRemoteFunc.say( ));
            id = CallServerRemoteFunc.id();
            System.out.println(CallServerRemoteFunc.add("Client ID: " +id+" added / "));

            /*java.rmi.registry.LocateRegistry.createRegistry(1099+id);
            Naming.rebind("ClientsCommunications", new ClientsCommunications() );
            System.out.println ("\nClients: After Rebind Done, Server is ready! ");*/
            ////////////////////////////////////////////////////////////////////////////////////////
            //////////// Clients-SERVER- SIDE register///////////////
            /*java.rmi.registry.LocateRegistry.createRegistry(2001);
            //if (System.getSecurityManager()==null) System.setSecurityManager((new RMISecurityManager()));
            //System.setSecurityManager(new RMISecurityManager());

            Naming.rebind("ClientsCommunications", new ClientsCommunications());
            System.out.println ("\nClient: After Rebind Done, ClientsCommunications is ready! ");
            ///////////Clients mapping his own function
            Interface CallClientsCommunications = (Interface) Naming.lookup("//localhost/ClientsCommunications");
            System.out.println ("\nClient: CallClientsCommunications instance created! ");

            // create object of ClientInfo
            ClientInfo me = new ClientInfo();*/


            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //input read
            /*System.out.println("\n Give Client Information Command: template: java client –SH hostA -N name -L x y –A age\n ex: java client –SH hostA -N Hamid -L 1 -1 –A 27\n");


            InputStreamReader inputCommand1 = new InputStreamReader(System.in);
            BufferedReader reader1 = new BufferedReader(inputCommand1);
            String command1=null;
            command1 = reader1.readLine();*/



            String[] splitedCommand1 = argv;

            me.setId(id);
            for (int i = 0; i < splitedCommand1.length; i++) {
                switch (splitedCommand1[i]) {
                    /*case "java":
                        System.out.println("\n     Starting Java"); break;
                    case "client":
                        System.out.println("\n     Starting Client");
                        break;*/
                    case "-SH":

                        splitedCommand1[i+1]="detected";
                        break;
                    case "-N":
                        me.setName(splitedCommand1[i+1]);
                        splitedCommand1[i+1]="detected";
                        break;
                    case "-L":
                        me.setX(Integer.parseInt(splitedCommand1[i+1]));
                        me.setY(Integer.parseInt(splitedCommand1[i+2]));
                        splitedCommand1[i+1]="detected";
                        splitedCommand1[i+2]="detected";
                        break;
                    case "-A":
                        me.setAge(Integer.parseInt(splitedCommand1[i+1]));
                        splitedCommand1[i+1]="detected";
                        break;
                    case "detected":
                        break;
                    default:
                        System.out.println("\n     MainAPP:     switch:     Problem:     in detecting command in position " +splitedCommand1[i]);
                        break;

                }
            }


            ///////////////////////////////////////////////////////////////////////

            me.setCallBackClientInterface( new ClientsCommunications()) ;

            //OK

            System.out.print(CallServerRemoteFunc.Register(me));

            ////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////
            while (true) {
                System.out.println("\n*****     Enter Your Command: \n");
                InputStreamReader inputCommand = new InputStreamReader(System.in);
                BufferedReader reader = new BufferedReader(inputCommand);
                String command = null;

                command = reader.readLine();

                String[] splitedCommand = command.split(" ");

               // for (int i = 0; i < splitedCommand.length; i++) {
                    switch (splitedCommand[0]) {
                        case "go":
                            System.out.println("\n               Go detected");
                            System.out.print(CallServerRemoteFunc.go(id,Integer.parseInt(splitedCommand[1]),Integer.parseInt(splitedCommand[2])));
                            break;
                        case "get":
                            System.out.println("\n               Get detected");
                            int[] x = new int[2];
                            x=CallServerRemoteFunc.getLocation(id);
                            System.out.print("Your ID is: "+id + " Your Coordination is X: " +x[0]+ " Y: "+x[1]);
                            break;
                        case "list":
                            System.out.println("\n               List detected");

                            ClientsDistanceX = CallServerRemoteFunc.list(id,Integer.parseInt(splitedCommand[1]));
                            System.out.print("\n Client ID's in range of "+Integer.parseInt(splitedCommand[1])+"meter : ");
                            for (int i2 = 0; i2 < ClientsDistanceX.size(); i2++) {
                                System.out.print("\n                                          Client ID: " +ClientsDistanceX.get(i2).getId());

                            }
                            break;
                        case "send":
                            System.out.println("\n               Send detected");
                            String msg = " ";
                            String s = " ";
                            for (int i2 = 2; i2 < splitedCommand.length; i2++) {
                                msg = msg +" " + splitedCommand[i2];
                            }
                            int receiverId = Integer.parseInt(splitedCommand[1]);
                            for (int i3 =0; i3 < ClientsDistanceX.size(); i3++){
                                if (ClientsDistanceX.get(i3).getId()==receiverId){
                                    s = ClientsDistanceX.get(i3).getCallBackClientInterface().sendMsg(me.getId(),receiverId,msg);
                                }
                            }

                            System.out.print(s);
                            break;
                        case "quit":
                            System.out.println("\n               Quit detected");
                            CallServerRemoteFunc.quit(id);
                            System.exit(0);
                            break;
                        default:
                            System.out.println("\n               Error in detecting Command");
                            break;

//                    }
                }

            }






        } catch (Exception e) {
            System.out.println ("ClientMainJob exception:"+e);
        }

    }
}
