import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;

/* Each table you wish to access in your database requires a model class, like this example: */
public class DrinkContent
{
    /* First, map each of the fields (columns) in your table to some public variables. */
    public int IngredientID;
    public int DrinkID;
    public String Instructions;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public DrinkContent(int DrinkID,String Instructions, int IngredientID )
    {
        this.IngredientID = IngredientID;        
        this.DrinkID = DrinkID;
        this.Instructions = Instructions;
    }

    /* A toString method is vital so that your model items can be sensibly displayed as text. */
    @Override public String toString()
    {
        return Instructions;
    }

    public static DrinkContent getById(int id)
    {
        DrinkContent drinkcontent = null;

        PreparedStatement statement = Application.database.newStatement("SELECT DrinkID, IngredientID, Instructions FROM DrinksContent WHERE DrinkID = ?"); 

        try 
        {
            if (statement != null)
            {
                statement.setInt(1, id);
                ResultSet results = Application.database.runQuery(statement);

                if (results != null)
                {
                    drinkcontent = new DrinkContent(results.getInt("DrinkID"), results.getString("Instructions"), results.getInt("IngredientID") );
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        return drinkcontent;
    }

    public static List<IngredientInstruction> getDrinksIngredientsWithInstructions(int id)
    {
        List<IngredientInstruction> ingredients = new ArrayList<>();

        String sql = "Select Ingredients.IngredientID, Ingredients.IngredientName, Ingredients.AlcoholPercentageMax,";
        sql += " Ingredients.AlcoholPercentageMin, DrinkContent.Instructions";
        sql += " From Ingredients";
        sql += " Inner Join DrinkContent On DrinkContent.IngredientID = Ingredients.IngredientID";
        sql += " Where DrinkContent.DrinkId = ?";
        PreparedStatement statement = Application.database.newStatement(sql); 

        try 
        {

            if (statement != null)
            {
                statement.setInt(1, id);
                ResultSet results = Application.database.runQuery(statement);

                if (results != null)
                {                    
                    while (results.next())
                    {
                        ingredients.add(new IngredientInstruction(results.getInt("IngredientID"), 
                                results.getString("IngredientName"), 
                                results.getInt("AlcoholPercentageMax"), 
                                results.getInt("AlcoholPercentageMin"),
                                results.getString("Instructions")));   
                    }
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