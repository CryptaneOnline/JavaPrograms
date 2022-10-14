class WifiOperations
{
    static WifiOperations netop=new WifiOperations();
    
    Utils util=Utils.util;
    String hotspot="RPiHotspot";
    void connect()
    {
        String s=util.runCommand("netsh wlan show networks");
        util.log("Available networks: ");
        util.log(s);
        if(!s.contains(hotspot))
        {
            util.log("Hard disk not available. Please turn on Hard disk or wait for it to boot up.");
            return;
        }
        s=util.runCommand("netsh wlan connect name="+hotspot);
        util.log(s);
    }
    
    void checkAvailable()
    {
        String s=util.runCommand("netsh wlan show profiles");
        if(!s.contains(hotspot))
        {
            util.log("Connecting for the first time? Use the network button on taskbar.");
        }
    }
}