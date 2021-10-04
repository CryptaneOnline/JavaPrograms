import java.io.*;
import java.util.*;
class Lock
{
    User authUser()throws Exception
    {
        System.out.println("Getting Device");
        Device d=Device.getConnectedDevice();
        if(d.isNull())
        {
            System.out.println("Either Device not connected or device read failed");
            return null;
        }
        System.out.println("Device read successful.");
        ArrayList<User> arr=new CheckDatabase().parseData();
        for(int i=0;i<arr.size();i++)
        {
            if(arr.get(i).getDevice().equals(d))
            {
                return arr.get(i);
            }
        }
        //Raise Alarm
        return null;
    }
    
    void run() throws Exception
    {
        User currentUser=authUser();
        if(currentUser==null)
        {
            System.out.println("Still locked");
	    Process ec=Runtime.getRuntime().exec("python closelock.py");
            ec.waitFor();
            return;
        }
        UserDatabase.writeDatabase(currentUser);
        System.out.println("Unlocked");
        //Unlock
	Process ec=Runtime.getRuntime().exec("python openlock.py");
	ec.waitFor();
    }
}
