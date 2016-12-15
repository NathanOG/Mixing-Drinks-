import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javafx.scene.control.PasswordField;

public class Scene2Controller
{    
    /* The stage that the scene belongs to, required to catch stage events and test for duplicate controllers. */
    private static Stage stage;     

    /* These FXML variables exactly corrispond to the controls that make up the scene, as designed in Scene 
     * Builder. It is important to ensure that these match perfectly or the controls won't be interactive. */
    @FXML   private Pane NewUserPane;    
    @FXML   private Button MakeAccount;
    @FXML   private TextField NewUsername;
    @FXML   private PasswordField NewPassword;
    @FXML   private ImageView NewUserHeader;
    @FXML   private ImageView cocacola;
    @FXML   private DatePicker DateofBirth;

    public Scene2Controller()          // The constructor method, called first when the scene is loaded.
    {
        System.out.println("Initialising controllers...");

        /* Our JavaFX application should only have one initial scene. The following checks to see
         * if a scene already exists (deterimed by if the stage variable has been set) and if so 
         * terminates the whole application with an error code (-1). */        
        if (stage != null)
        {
            System.out.println("Error, duplicate controller - terminating application!");
            System.exit(-1);
        }        

    } 

    @FXML   void initialize()           // The method automatically called by JavaFX after the constructor.
    {            
        /* The following assertions check to see if the JavaFX controls exists. If one of these fails, the
         * application won't work. If the control names in Scene Builder don't match the variables this fails. */ 
        System.out.println("Asserting controls...");
        try
        {
            assert NewUserPane != null : "Can't find NewUserPane.";
            assert MakeAccount != null : "Can't find MakeAccount.";
            assert NewUsername != null : "Can't find NewUsername.";
            assert NewPassword != null : "Can't find NewPassword.";
            assert NewUserHeader != null : "Can't find NewUserHeader.";
            assert cocacola != null : "Can't find cocacola.";
            assert DateofBirth !=null : "Can't find DateofBirth.";
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
                        "Thx for stopping by hope to see you again soon.");
                    Application.terminate();
                }
            });
    }       

   

    @FXML   void NewUsernameClicked()
    {
        System.out.println("NewUsername was clicked!");        
    }

    @FXML   void NewPasswordClicked()
    {
        System.out.println("NewPassword was clicked!");
    }

    @FXML   void DateofBirthClicked()
    {
        System.out.println("DateofBirth was clicked!");        

    }
    
     /* The next three methods are event handlers for clicking on the buttons. 
     * The names of these methods are set in Scene Builder so they work automatically. */    
    @FXML   void MakeAccountClicked()
    {
        User newUser = new User(0, NewUsername.getText(), PasswordHelper.generateHash(NewPassword.getText()));
        newUser.save();
        
        

        System.out.println("MakeAccount was clicked!");  

    }

}