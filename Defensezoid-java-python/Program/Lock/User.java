class User
{
    private Device d;
    private String name;
    User(Device d, String name)
    {
        this.d=new Device(d);
        this.name=name;
    }
    
    User (User u)
    {
        this(u.getDevice(), u.getName());
    }
    Device getDevice()
    {
        return d;
    }
    
    String getName()
    {
        return name;
    }
}