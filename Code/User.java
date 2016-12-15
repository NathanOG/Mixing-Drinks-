import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

/* Each table you wish to access in your database requires a model class, like this example: */
public class User
{
    /* First, map each of the fields (columns) in your table to some public variables. */
    public int id;
    public String Username;
    public String Password;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public User(int id, String Username, String Password)
    {
        this.id = id;        
        this.Username = Username;
        this.Password = Password;
    }

    /* A toString method is vital so that your model items can be sensibly displayed as text. */
    @Override public String toString()
    {
        return Username;
    }

    public static User getById(int id)
    {
        User user = null;

        PreparedStatement statement = Application.database.newStatement("SELECT UserId, Username, Password FROM User WHERE UserId = ?"); 

        try 
        {
            if (statement != null)
            {
                statement.setInt(1, id);
                ResultSet results = Application.database.runQuery(statement);

                if (results != null)
                {
                    user = new User(results.getInt("UserId"), results.getString("Username"), results.getString("Password"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        return user;
    }
    
    public void save()    
    {
        PreparedStatement statement;

        try 
        {

            if (id == 0)
            {

                statement = Application.database.newStatement("SELECT UserID FROM User ORDER BY UserID DESC");             

                if (statement != null)	
                {
                    ResultSet results = Application.database.runQuery(statement);
                    if (results != null)
                    {
                        id = results.getInt("UserID") + 1;
                    }
                }

                statement = Application.database.newStatement("INSERT INTO User (Username, Password) VALUES (?, ?)");             
                statement.setString(1, Username);
                statement.setString(2, Password);         

            }
            else
            {
                statement = Application.database.newStatement("UPDATE User SET Username = ?, Password = ? ");             
                statement.setString(1, Username);
                statement.setString(2, Password);   
              
            }

            if (statement != null)
            {
                Application.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

    }
    
     public static User getByUsername(String username)
    {
        User user = null;

        PreparedStatement statement = Application.database.newStatement("SELECT UserId, Username, Password FROM User WHERE Username = ?"); 

        try 
        {
            if (statement != null)
            {
                statement.setString(1, username);
                ResultSet results = Application.database.runQuery(statement);

                if (results != null)
                {
                    user = new User(results.getInt("UserId"), results.getString("Username"), results.getString("Password"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        return user;
    }

}