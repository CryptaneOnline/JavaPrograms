import java.util.Date;
class UserLog
{
    private User u;
    private Date dt;
    UserLog(User u, Date dt)
    {
        this.u=u;
        this.dt=dt;
    }
    
    UserLog(UserLog ul)
    {
        this(ul.getUser(), ul.getDate());
    }
    
    User getUser()
    {
        return u;
    }
    
    Date getDate()
    {
        return dt;
    }
}