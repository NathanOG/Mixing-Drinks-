import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javafx.scene.control.ToggleButton; 
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
public class Scene7Controller
{    
    /* The stage that the scene belongs to, required to catch stage events and test for duplicate controllers. */
    private static Stage stage;   

    private Drinks currentDrink;

    /* These FXML variables exactly corrispond to the controls that make up the scene, as designed in Scene 
     * Builder. It is important to ensure that these match perfectly or the controls won't be interactive. */
    @FXML   private Pane SingleDrinkScreenPane; 
    @FXML   private Button ProfileSingleDrinkScreen;
    @FXML   private Button ScavengerSingleDrinkScreen;
    @FXML   private Button HomeSingleDrinkScreen;    
    @FXML   private ImageView SingleDrinkScreenHeader;
    @FXML   private Button RateIt;
    @FXML   private Text SingleDrinkScreenDrinkName;
    @FXML   private ListView<String> SingleDrinkScreenIngredients;
    @FXML   private ListView<String> SingleDrinkScreenInstructions;
    @FXML   private TextField Review;
    @FXML   private Text AvgScore;

    public Scene7Controller()          // The constructor method, called first when the scene is loaded.
    {
        System.out.println("Initialising controllers...");

        /* Our JavaFX application should only have one initial scene. The following checks to see
         * if a scene already exists (deterimed by if the stage variable has been set) and if so 
         * terminates the whole application with an error code (-1). */        

    } 

    @FXML   void initialize()           // The method automatically called by JavaFX after the constructor.
    {            
        /* The following assertions check to see if the JavaFX controls exists. If one of these fails, the
         * application won't work. If the control names in Scene Builder don't match the variables this fails. */ 
        System.out.println("Asserting controls...");
        try
        {
            assert SingleDrinkScreenPane != null : "Can't find SingleDrinkScreenPane.";
            assert SingleDrinkScreenDrinkName != null : "Can't find SingleDrinkScreenDrinkName.";
            assert SingleDrinkScreenIngredients != null : "Can't find SingleDrinkScreenIngredients.";
            assert SingleDrinkScreenInstructions != null : "Can't find SingleDrinkScreenInstructions.";
            assert  ProfileSingleDrinkScreen != null : "ProfileSingleDrinkScreen.";
            assert ScavengerSingleDrinkScreen != null : "Can't find ScavengerSingleDrinkScreen.";
            assert HomeSingleDrinkScreen != null : "Can't find HomeSingleDrinkScreen.";
            assert SingleDrinkScreenHeader !=null : "Can't find SingleDrinkScreenHeader.";
            assert RateIt !=null : "Can't find RateIt.";
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

    }

    /* In order to catch stage events (the main example being the close (X) button being clicked) we need
     * to setup event handlers. This happens after the constructor and the initialize methods. Once this is
     * complete, the scene is fully loaded and ready to use. */
    public void prepareStageEvents(Stage stage, int currentDrinkID)
    {
        System.out.println("Preparing stage events...");

        this.stage = stage;

        currentDrink = Drinks.getById(currentDrinkID);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Close button was clicked!");
                    HomeSingleDrinkScreenClicked();
                }
            });

        SingleDrinkScreenDrinkName.setText(currentDrink.DrinkName);
        List<IngredientInstruction> ingredients = DrinkContent.getDrinksIngredientsWithInstructions(currentDrink.DrinkID);
        for(IngredientInstruction i : ingredients)
        {
            SingleDrinkScreenIngredients.getItems().add(i.IngredientName);
            SingleDrinkScreenInstructions.getItems().add(i.instruction);
        }

        //  SingleDrinkScreenIngredients
        // SingleDrinkScreenInstructions

    }       

    /* The next three methods are event handlers for clicking on the buttons. 
     * The names of these methods are set in Scene Builder so they work automatically. */    
    @FXML   void ProfileSingleDrinkScreenClicked()
    {
        System.out.println("ProfileFirstPage was clicked!"); 

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("Profile.fxml"));
        try
        {
            Stage stage5 = new Stage();
            stage5.setTitle("My Profile");
            stage5.setScene(new Scene(loader.load()));
            stage5.show();           
            Scene5Controller controller5 = loader.getController();
            controller5.prepareStageEvents(stage5);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        stage.close();

    }

    @FXML   void ScavengerSingleDrinkScreenClicked()
    {
        System.out.println("ScavengerFirstPage was clicked!");   

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("Scavenger.fxml"));
        try
        {
            Stage stage4 = new Stage();
            stage4.setTitle("Scavenger");
            stage4.setScene(new Scene(loader.load()));
            stage4.show();           
            Scene4Controller controller4 = loader.getController();
            controller4.prepareStageEvents(stage4);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        stage.close();
    }

    @FXML   void HomeSingleDrinkScreenClicked()
    {
        System.out.println("HomeSingleDrinkScreen was clicked!");    

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("First page.fxml"));
        try
        {
            Stage stage3 = new Stage();
            stage3.setTitle("Scavenger");
            stage3.setScene(new Scene(loader.load()));
            stage3.show();           
            Scene3Controller controller3 = loader.getController();
            controller3.prepareStageEvents(stage3);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        stage.close();

    }

    @FXML   void RateItClicked()
    {
        System.out.println("RateIt was clicked!");    
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("Rate it.fxml"));
        try
        {
            Stage stage6 = new Stage();
            stage6.setTitle("Rate It!");
            stage6.setScene(new Scene(loader.load()));
            stage6.show();           
            Scene6Controller controller6 = loader.getController();
            controller6.prepareStageEvents(stage6);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        stage.close();
    }
}