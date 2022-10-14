import java.util.*;
import java.io.*;
public class FileOperation
{
    static FileOperation flop=new FileOperation();

    Utils util=Utils.util;
    String getBackupDirectory()
    {
        String k=getCurrentDirectory();
        return k+"\\bkp";
    }

    String getCurrentDirectory()
    {
        return System.getProperty("user.dir");
    }
    
    ArrayList<String> getDirectories()
    {
        String cmd="dir /a:d "+getBackupDirectory();
        String s=util.runCommand(cmd);
        util.log(s);
        ArrayList<String> arr=new ArrayList<String>();
        StringTokenizer st=new StringTokenizer(s,"\n");
        while(st.hasMoreTokens())
        {
            String ln=st.nextToken();
            if(ln.contains("<JUNCTION>"))
            {
                String fln=ln.substring(ln.lastIndexOf('[')+1,ln.lastIndexOf(']'));
                arr.add(fln);
            }
        }
        return arr;
    }

    void addJunction()
    {
        util.log("Select Directory to add");
        String bkpdir=getBackupDirectory();
        String newdir=util.getDirectory();
        String k=newdir.substring(newdir.lastIndexOf("\\")+1);
        k=util.getDateAndTime()+"_"+k;
        String ndir=bkpdir+"\\"+k;
        String cmd="mklink /j \""+ndir+"\" \""+newdir+"\"";
        System.out.println(cmd);
        String s=util.runCommand(cmd);
        util.log(s);
    }
}