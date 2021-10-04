import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.text.*;
import java.time.format.*;
class CheckDatabase
{
    String readDatabase()throws Exception
    {
        Process ec=Runtime.getRuntime().exec("python getdb.py");
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

    ArrayList<User> parseData()throws Exception
    {
        String log=readDatabase();
        StringTokenizer st=new StringTokenizer(log,"{}");
        ArrayList<User> arr=new ArrayList<User>();
        while(st.hasMoreTokens())
        {
            String t1=st.nextToken();
            st.nextToken();
            StringTokenizer st1=new StringTokenizer(t1,",\n");
            String name="";
            String imei="";
            String srl="";
            while(st1.hasMoreTokens())
            {                
                String t2=st1.nextToken();
                StringTokenizer st2=new StringTokenizer(t2,"\": ");
                String a1="";
                String a2="";
                try
                {
                    a1=st2.nextToken();
                    a2=st2.nextToken();
                }
                catch(Exception ec)
                {
                    continue;
                }
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
            }
            arr.add(new User(new Device(imei,srl),name));
        }
        return arr;
    }

}
