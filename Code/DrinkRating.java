import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;


/* Each table you wish to access in your database requires a model class, like this example: */
public class DrinkRating
{
    /* First, map each of the fields (columns) in your table to some public variables. */
    public int UserID;
    public int DrinkID;
    public int Score;
    public String Description;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public DrinkRating(int UserID, int DrinkID, int Score, String Description)
    {
        this.UserID = UserID;        
        this.DrinkID = DrinkID;
        this.Score = Score;
        this.Description = Description;

    }
    
    public void save()    
    {
        PreparedStatement statement;

        try 
        {

            if (DrinkID == 0)
            {

                statement = Application.database.newStatement("SELECT DrinkID FROM DrinkRating ORDER BY UserID DESC");             

                if (statement != null)	
                {
                    ResultSet results = Application.database.runQuery(statement);
                    if (results != null)
                    {
                        DrinkID = results.getInt("DrinkID") + 1;
                    }
                }

                statement = Application.database.newStatement("INSERT INTO DrinkRating  (Score , Description) VALUES (?, ?)");             
                statement.setInt(1, Score);
                statement.setString(2, Description);         

            }
            else
            {
            statement = Application.database.newStatement("UPDATE DrinkRating SET Score = ?, Description = ? ");             
                statement.setInt(1, Score);
                statement.setString(2, Description);   
              
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
    
    

}