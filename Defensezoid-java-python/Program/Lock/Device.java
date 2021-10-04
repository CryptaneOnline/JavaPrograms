import java.io.*;
class Device
{
    private String imei;
    private String serial;
    Device(String imei, String serial)
    {
        this.imei=imei;
        this.serial=serial;
    }

    Device(Device d)
    {
        this(d.getIMEI(), d.getSerial());
    }

    String getIMEI()
    {
        return imei;
    }

    String getSerial()
    {
        return serial;
    }

    boolean equals(Device d)
    {
        if(isNull()&&d.isNull())
        {
            return true;
        }
        try
        {
            return getIMEI().equals(d.getIMEI())&&getSerial().equals(d.getSerial());
        }
        catch(NullPointerException excep)
        {
            return false;
        }
    }

    boolean isNull()
    {
        return getIMEI()==null && getSerial()==null;
    }

    static Device getConnectedDevice()throws Exception
    {
        Process ec=Runtime.getRuntime().exec("adb kill-server");
        ec.waitFor();
        ec=Runtime.getRuntime().exec("./getimei.sh");
        BufferedReader br=new BufferedReader(new InputStreamReader(ec.getInputStream()));        
        String imei=br.readLine();
        ec.waitFor();
        if(imei!=null && imei.contains("not found"))
        {
            ec=Runtime.getRuntime().exec("./getimeilegacy.sh");
            br=new BufferedReader(new InputStreamReader(ec.getInputStream()));        
            imei=br.readLine();
            imei=imei.substring(imei.lastIndexOf(' ')+1,imei.length());
            ec.waitFor();

        }
        ec=Runtime.getRuntime().exec("adb get-serialno");
        br=new BufferedReader(new InputStreamReader(ec.getInputStream()));        
        String serial=br.readLine();
        ec.waitFor();
        return new Device(imei, serial);
    }

}