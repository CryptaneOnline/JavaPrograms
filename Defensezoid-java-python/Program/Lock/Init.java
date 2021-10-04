class Init
{
    public static void main(String args[])
    {
        try
        {
            new Lock().run();
        }
        catch(Exception excep)
        {
            excep.printStackTrace();
        }
    }
}