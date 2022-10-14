import java.io.*;

class GitOperations
{
    static GitOperations git=new GitOperations();
    Utils util=Utils.util;
    FileOperation flop=FileOperation.flop;
    void initDirectory()
    {
        String s=util.runCommand("git init");
        util.log(s);
        s=util.runCommand("git remote add origin git@192.168.50.5:/media/usb/bkp.git");
        util.log(s);
    }

    void backup()
    {
        String dtm=util.getDateAndTime();
        String commit="Backup created at "+dtm;
        String s=util.runCommand("git add .");
        util.log(s);
        s=util.runCommand("git commit -m \""+commit+"\"");
        util.log(s);
        util.log("Please wait. Backing up.");
        s=util.runCommand("git push origin master");
        util.log(s);
        s=util.runCommand("git pull origin master");
        util.log(s);
        util.log("Backup Completed.");
    }

    void restore()
    {
        util.log("Restoring last backup.");
        String s=util.runCommand("git pull origin master");
        util.log(s);
        util.log("Restore Completed.");
    }

    void download()
    {
        util.log("Select download location");
        String downdir=util.getDirectory()+"\\bkp";
        util.log("Starting download");
        String s=util.runCommand("git clone git@192.168.50.5:/media/usb/bkp.git \""+downdir+"\"");
        util.log(s);
        util.log("Download Completed");
    }

    void getGit()
    {
        util.log("Installing Git");
        String s=util.runCommand("winget install -e --id Git.Git");
        util.log(s);
    }

    void getLog()
    {
        util.log("Fetching logs");
        String s=util.runCommand("git log");
        util.log(s);
        util.log("End of logs");
    }

    void hardbackup()
    {        
        String command="bash /home/git/hardbackup.sh";
        String s=util.remoteRunCommand(command);
        util.log(s);
        util.log("Reset previous backup");
        String currentdir=flop.getCurrentDirectory();
        s=util.runCommand("rmdir /Q /S "+currentdir+"\\.git");
        util.log(s);
        walk(currentdir);
        initDirectory();
        backup();
    }
    
    void remoteLog()
    {
        String command="bash /home/git/remotelog.sh";
        String s=util.remoteRunCommand(command);
        util.log(s);
        util.log("End of remote logs");
    }

    public void walk( String path ) 
    {
        File root = new File( path );
        File[] list = root.listFiles();
        if (list == null) 
            return;
        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                String s=util.runCommand("rmdir /Q /S "+f.getAbsoluteFile()+"\\.git");
            }
        }
    }
}