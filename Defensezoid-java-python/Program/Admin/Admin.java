import java.io.*;
import java.util.*;
class Admin
{
    static Scanner sc=new Scanner(System.in);
    void authUser()throws Exception
    {
        System.out.println("Enter name (Don't use spaces)");
        String name=sc.next();
        System.out.println("Getting Device");
        Device d=Device.getConnectedDevice();
        if(d.isNull())
        {
            System.out.println("Either Device not connected or device read failed");
            return;
        }
        System.out.println("Device read successful. Writing to database");
        User u=new User(d,name);
        UserDatabase.writeDatabase(u);
    }
}