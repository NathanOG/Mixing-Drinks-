import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javafx.scene.control.ToggleButton; 
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class Scene4Controller
{    
    /* The stage that the scene belongs to, required to catch stage events and test for duplicate controllers. */
    private static Stage stage;     

    /* These FXML variables exactly corrispond to the controls that make up the scene, as designed in Scene 
     * Builder. It is important to ensure that these match perfectly or the controls won't be interactive. */
    @FXML   private Pane ScavengerPane; 
    @FXML   private ScrollPane ScavengerScrollPane;
    @FXML   private Button HomeScavenger;
    @FXML   private ToggleButton AlcholicScavenger;
    @FXML   private Button ProfileScavenger;
    @FXML   private ImageView ScavengerHeader;
    @FXML   private ChoiceBox DrinkOne;
    @FXML   private ChoiceBox DrinkTwo;
    @FXML   private Label DrinkOneText;
    @FXML   private Label DrinkTwoText;

    public Scene4Controller()          // The constructor method, called first when the scene is loaded.
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
            assert ScavengerPane != null : "Can't find ScavengerPane.";
            assert ScavengerScrollPane != null : "ScavengerScrollPane.";
            assert HomeScavenger != null : "HomeScavenger.";
            assert ProfileScavenger != null : "Can't find ProfileScavenger.";
            assert AlcholicScavenger != null : "Can't find AlcholicScavenger.";
            assert DrinkOne != null : "Can't find DrinkOne.";
            assert DrinkTwo !=null : "Can't find DrinkTwo.";
            assert DrinkOneText !=null : "Can't find DrinkOneText.";
            assert DrinkTwoText !=null : "Can't find DrinkTwoText.";
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
    public void prepareStageEvents(Stage stage)
    {
        System.out.println("Preparing stage events...");

        this.stage = stage;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Close button was clicked!");
                    JOptionPane.showMessageDialog(null,
                        "Thx for stopping by hope to see yopu again soon.");
                    stage.close();
                }
            });
    }       

    /* The next three methods are event handlers for clicking on the buttons. 
     * The names of these methods are set in Scene Builder so they work automatically. */    
    @FXML   void HomeScavengerClicked()
    {
        System.out.println("HomeScavenger was clicked!");  
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("First page.fxml"));

        try
        {
            Stage stage3 = new Stage();
            stage3.setTitle("First Page");
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

    @FXML   void AlcholicScavengerClicked()
    {
        System.out.println("AlcholicScavenger was clicked!");   

    }

    @FXML   void ProfileScavengerClicked()
    {
        System.out.println("ProfileScavenger was clicked!"); 

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

    @FXML   void DrinkOneContextRequest()
    {
        System.out.println("DrinkOne context was requested!");        
    }

    @FXML   void DrinkTwoContextRequest()
    {
        System.out.println("DrinkTwo context was requested!");        

    }

}