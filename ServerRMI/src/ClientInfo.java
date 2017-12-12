import java.io.Serializable;

/**
 * Created by Admin on 11/18/2016.
 */
public class ClientInfo implements Serializable{
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int id;
    private int x;
    private int y;
    private String Name;
    private int age;

    public ClientInterface getCallBackClientInterface() {
        return CallBackClientInterface;
    }

    public void setCallBackClientInterface(ClientInterface callBackClientInterface) {
        CallBackClientInterface = callBackClientInterface;
    }

    private ClientInterface CallBackClientInterface;
}
