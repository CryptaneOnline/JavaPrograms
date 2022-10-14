import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

class gui implements ActionListener
{

    GitOperations git=GitOperations.git;
    FileOperation flop=FileOperation.flop;
    WifiOperations netop=WifiOperations.netop;
    Utils util=Utils.util;

    JFrame fr=new JFrame("Hard disk manager");
    JPanel pan=new JPanel();
    JPanel pan2=new JPanel();
    JPanel pan3=new JPanel();
    JButton bkp=new JButton("Backup");
    JButton restore=new JButton("Restore");
    JButton hbkp=new JButton("Hard Backup");
    JButton down=new JButton("Download");
    JButton conn=new JButton("Connect");
    JButton inst=new JButton("Install Git");
    JButton log=new JButton("Show logs");
    JButton rlog=new JButton("Show remote logs");
    JButton addDirec=new JButton("Add directories");
    static JTextArea logs=new JTextArea(10, 30);
    JTextArea direclist=new JTextArea(20, 30);

    gui()
    {
        init();
    }

    void init()
    {
        pan.setLayout(new BorderLayout());
        pan3.setLayout(new BorderLayout());
        pan2.add(new JLabel("Backup and Restore"));
        pan2.add(bkp);
        pan2.add(restore);
        pan2.add(hbkp);
        pan2.add(new JLabel("Download all backup"));
        pan2.add(down);
        pan2.add(new JLabel("Connect to HDD"));
        pan2.add(conn);
        pan2.add(new JLabel("Operations with Git"));
        pan2.add(inst);
        pan2.add(log);
        pan2.add(rlog);
        pan2.add(new JLabel("Add directories to backup"));
        pan2.add(addDirec);

        bkp.addActionListener(this);
        restore.addActionListener(this);
        hbkp.addActionListener(this);
        down.addActionListener(this);
        conn.addActionListener(this);
        inst.addActionListener(this);
        log.addActionListener(this);
        rlog.addActionListener(this);
        addDirec.addActionListener(this);

        logs.setEditable(false);
        direclist.setEditable(false);

        logs.setText("Logs:\n");
        direclist.setText("Directories");
        pan.add(new JScrollPane(direclist), "East");
        pan.add(new JScrollPane(logs), "South");
        pan3.add(pan2);
        pan3.add(pan,"East");
        fr.setSize(510,600);
        fr.setResizable(false);
        fr.add(pan3);
        git.initDirectory();
        updateDirec();
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        if(evt.getSource()==bkp)
        {
            git.backup();            
        }
        if(evt.getSource()==restore)
        {
            git.restore();
        }
        if(evt.getSource()==hbkp)
        {
            git.hardbackup();
        }
        if(evt.getSource()==down)
        {
            git.download();
        }
        if(evt.getSource()==conn)
        {
            netop.connect();
        }
        if(evt.getSource()==log)
        {
            git.getLog();
        }
        if(evt.getSource()==rlog)
        {
            git.remoteLog();
        }
        if(evt.getSource()==inst)
        {
            git.getGit();
        }
        if(evt.getSource()==addDirec)
        {
            flop.addJunction();
            updateDirec();
        }

    }

    void updateDirec()
    {
        ArrayList<String> flist=flop.getDirectories();
        String lst=util.listToString(flist);
        lst="Directories being monitored:\n\n"+lst;
        direclist.setText(lst);
    }

}