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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Scene6Controller
{    
    /* The stage that the scene belongs to, required to catch stage events and test for duplicate controllers. */
    private static Stage stage;     

    /* These FXML variables exactly corrispond to the controls that make up the scene, as designed in Scene 
     * Builder. It is important to ensure that these match perfectly or the controls won't be interactive. */
    @FXML   private Pane RateItPane; 
    @FXML   private Button RateItButton;
    @FXML   private ImageView RateItHeader;
    @FXML   private ComboBox<String> OutofFive;
    @FXML   private TextField Description;
    @FXML   private Button RateItHome;

    public Scene6Controller()          // The constructor method, called first when the scene is loaded.
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
            assert RateItPane != null : "Can't find RateItPane.";
            assert RateItHome !=null : "Can't find RateItHome.";
            assert  RateItButton != null : "Can't find  RateItButton.";
            assert RateItHeader != null : "Can't find RateItHeader.";
            assert OutofFive != null : "Can't find OutofFive.";
            assert Description != null : "Can't find Description.";
        }
        catch (AssertionError ae)
        {

            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        ObservableList<String> Five = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        OutofFive.setItems(Five);

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
                        "Thx for stopping by hope to see you again soon.");
                            stage.close();
                }
            });
    }       

    /* The next three methods are event handlers for clicking on the buttons. 
     * The names of these methods are set in Scene Builder so they work automatically. */    
    @FXML   void RateItHomeClicked()
    {
        System.out.println(" RateItHome was clicked!");  

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

    @FXML   void RateItButtonClicked()
    {
        String selectedItem = OutofFive.getSelectionModel().getSelectedItem();
        DrinkRating newDrinkRating = new DrinkRating(0, Application.currentUser.id, Integer.parseInt(selectedItem), Description.getText());
        // TO DO - MAKE THE 0 THE CURRENT DRINK
        newDrinkRating.save(); 

        System.out.println("RateItButton Clicked!"); 

    }

    @FXML   void OutofFiveContextRequest()
    {
        System.out.println("OutofFive context was requested!");        
    }

}