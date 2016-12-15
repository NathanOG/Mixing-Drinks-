import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

/* Each table you wish to access in your database requires a model class, like this example: */
public class Drinks
{
    /* First, map each of the fields (columns) in your table to some public variables. */
    public int DrinkID;
    public String DrinkName;
    public String ImageFileName;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public Drinks(int DrinkID, String DrinkName, String ImageFileName)
    {

        this.DrinkID = DrinkID;
        this.DrinkName = DrinkName;
        this.ImageFileName = ImageFileName;
    }

    /* A toString method is vital so that your model items can be sensibly displayed as text. */
    @Override public String toString()
    {
        return DrinkName;
    }

    public static Drinks getById(int id)
    {
        Drinks drinks = null;

        PreparedStatement statement = Application.database.newStatement("SELECT DrinkID, DrinkName, ImageFileName FROM Drinks WHERE DrinkID = ?"); 

        try 
        {
            if (statement != null)
            {
                statement.setInt(1, id);
                ResultSet results = Application.database.runQuery(statement);

                if (results != null)
                {
                    drinks = new Drinks(results.getInt("DrinkID"), results.getString("DrinkName"), results.getString("ImageFileName"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        return drinks;
    }

    public static void readAll(List<Drinks> list)
    {
        list.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Application.database.newStatement("SELECT DrinkID, DrinkName, ImageFileName FROM Drinks"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                        list.add( new Drinks(results.getInt("DrinkID"), results.getString("DrinkName"), results.getString("ImageFileName")));
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

    }

}