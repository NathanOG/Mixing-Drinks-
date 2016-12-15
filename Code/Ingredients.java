import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

/* Each table you wish to access in your database requires a model class, like this example: */
public class Ingredients
{
    /* First, map each of the fields (columns) in your table to some public variables. */
    public int IngredientID;
    public String IngredientName;
    public int AlcoholPercentageMin;
    public int AlcoholPercentageMax;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public Ingredients(int IngredientID, String IngredientName, int AlcholPercentageMin, int AlcholPercentageMax )
    {

        this.IngredientID = IngredientID;
        this.IngredientName = IngredientName;
         this.AlcoholPercentageMin = AlcoholPercentageMin;
          this. AlcoholPercentageMax =  AlcoholPercentageMax;
    }

    /* A toString method is vital so that your model items can be sensibly displayed as text. */
    @Override public String toString()
    {
        return IngredientName;
    }
    
     public static Ingredients getById(int id)
    {
        Ingredients ingredients = null;

        PreparedStatement statement = Application.database.newStatement("SELECT IngredientName, IngredientID, AlcholPercentageMin, AlcholPercentageMax FROM  Ingredients WHERE  IngredientID = ?"); 

        try 
        {
            if (statement != null)
            {
                statement.setInt(1, id);
                ResultSet results = Application.database.runQuery(statement);

                if (results != null)
                {
                    ingredients = new Ingredients(results.getInt("IngredientID"), results.getString("IngredientName"), results.getInt("AlcholPercentageMin"), results.getInt("AlcholPercentageMax") );
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        return ingredients;
    }

}