import java.io.*;
class UserDatabase
{
    static void writeDatabase(User u)throws Exception
    {
        String name=u.getName();
        Device d=u.getDevice();
        String imei=d.getIMEI();
        String serial=d.getSerial();
        Process ec=Runtime.getRuntime().exec("python dbupdate.py "+name+" "+imei+" "+serial);
        BufferedReader br=new BufferedReader(new InputStreamReader(ec.getInputStream()));
        try
        {
            if(Integer.parseInt(br.readLine())==1)
            {
                System.out.println("Write successful");
            }
        }
        catch(Exception excep)
        {
            System.out.println("Write failed or device already present");
        }
    }
}