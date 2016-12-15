
public class IngredientInstruction extends Ingredients
{

    public String instruction;

    public IngredientInstruction(int IngredientID, String IngredientName, int AlcholPercentageMin, int AlcholPercentageMax, String instruction)
    {
        super(IngredientID, IngredientName, AlcholPercentageMin, AlcholPercentageMax);
        this.instruction = instruction;         
    }

}
