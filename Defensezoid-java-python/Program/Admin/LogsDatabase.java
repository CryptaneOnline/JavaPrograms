import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.text.*;
import java.time.format.*;
class LogsDatabase
{
    String readLogs()throws Exception
    {
        Process ec=Runtime.getRuntime().exec("python logsdb.py");
        BufferedReader br=new BufferedReader(new InputStreamReader(ec.getInputStream()));
        String ln="";
        String s="";
        while((ln=br.readLine())!=null)
        {
            s+=ln+"\n";
        }
        //System.out.println(s);
        return s;
    }

    ArrayList<UserLog> parseData()throws Exception
    {
        String log=readLogs();
        StringTokenizer st=new StringTokenizer(log,"{}");
        ArrayList<UserLog> arr=new ArrayList<UserLog>();
        while(st.hasMoreTokens())
        {
            String t1=st.nextToken();
            st.nextToken();
            StringTokenizer st1=new StringTokenizer(t1,",\n");
            String name="";
            String imei="";
            String srl="";
            Date time=null;
            while(st1.hasMoreTokens())
            {                
                String t2=st1.nextToken();
                StringTokenizer st2=new StringTokenizer(t2,"\": ");

                String a1=st2.nextToken();
                String a2=st2.nextToken();
                if(a1.equals("Name"))
                {
                    name=a2;
                }
                if(a1.equals("IMEI"))
                {
                    imei=a2;
                }
                if(a1.equals("Serial"))
                {
                    srl=a2;
                }
                if(a1.equals("_ts"))
                {
                    time=new Timestamp(Long.parseLong(a2)*1000);
                }
            }
            arr.add(new UserLog(new User(new Device(imei,srl),name),time));
        }
        return arr;
    }

    void showLogs() throws Exception
    {
        ArrayList<UserLog> arr=parseData();
        System.out.println("User\tDate and Time\n===================================================================================");
        for(int i=0;i<arr.size();i++)
        {
            UserLog ob=arr.get(i);
            System.out.println(ob.getUser().getName()+"\t"+ob.getDate());
        }
    }
}