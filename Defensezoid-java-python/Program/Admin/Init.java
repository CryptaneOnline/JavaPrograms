import java.util.*;
class Init
{
    public static void main(String args[])
    {
        try
        {
            Scanner sc=new Scanner(System.in);
            System.out.println("=====MENU=====");
            System.out.println("1. Authorize new user");
            System.out.println("2. View Logs");
            System.out.println("Enter choice");
            int ch=sc.nextInt();
            switch(ch)
            {
                case 1:
                new Admin().authUser();
                break;
                case 2:
                new LogsDatabase().showLogs();
                break;
                default:
                System.out.println("Invalid choice");
            }
        }
        catch(Exception excep)
        {
            excep.printStackTrace();
        }
    }
}