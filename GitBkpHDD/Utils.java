import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.text.*;
import java.io.*;
import com.jcraft.jsch.*;



public class Utils
{
    static Utils util=new Utils();
    public String getDirectory()
    {
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showSaveDialog(null);
        String k=f.getSelectedFile().toString();
        return k;
    }      

    public String getDateAndTime()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");  
        Date date = new Date();  
        String k=formatter.format(date);  
        return k;
    }
    
    String runCommand(String cmd)
    {
        log("> "+cmd);
        String s="";
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd","/c",cmd);      
        try 
        {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line="";
            while ((line = br.readLine()) != null) {
                output.append(line + "\n");
            }
            BufferedReader br1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            line="";
            while ((line = br.readLine()) != null) {
                output.append(line + "\n");
            }
            int exitVal = process.waitFor();
            s=output.toString();
        } catch (Exception excep) {
            excep.printStackTrace();
        }
        return s;
    }
    
    String remoteRunCommand(String command1)
    {
        String host="192.168.50.5";
        String user="git";
        String password="adi5102";
        StringBuilder sb=new StringBuilder();
        try{

            java.util.Properties config = new java.util.Properties(); 
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            util.log("Connected");

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command1);
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            while(true)
            {
                while(in.available()>0)
                {
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)
                        break;
                    sb.append(new String(tmp, 0, i));
                }
                if(channel.isClosed())
                {
                    util.log("exit-status: "+channel.getExitStatus());
                    break;
                }
            }
            channel.disconnect();
            session.disconnect();

        }catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
    
    void log(String s)
    {
        JTextArea logs=gui.logs;
        StringBuilder sb=new StringBuilder(logs.getText());
        sb.append(s+"\n");
        logs.setText(sb.toString());
    }
    
    String listToString(ArrayList<String> arr)
    {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<arr.size();i++)
        {
            sb.append(arr.get(i)+"\n");
        }
        return sb.toString();
    }
}