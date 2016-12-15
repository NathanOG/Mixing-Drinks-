import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class SceneController
{    
    /* The stage that the scene belongs to, required to catch stage events and test for duplicate controllers. */
    private static Stage stage;     

    /* These FXML variables exactly corrispond to the controls that make up the scene, as designed in Scene 
     * Builder. It is important to ensure that these match perfectly or the controls won't be interactive. */
    @FXML   private Pane LoginPane;    
    @FXML   private Button ClickMe;
    @FXML   private TextField Username;
    @FXML   private PasswordField Password;
    @FXML   private ImageView MolotovLogin;
    @FXML   private ImageView NewuserLogin;
    @FXML   private Button SignInButton;

    public SceneController()          // The constructor method, called first when the scene is loaded.
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
            assert LoginPane != null : "Can't find LoginPane.";
            assert ClickMe != null : "Can't find ClickMe.";
            assert Username != null : "Can't find Username.";
            assert Password != null : "Can't find Password.";
            assert MolotovLogin != null : "Can't find MolotovLogin.";
            assert NewuserLogin != null : "Can't find NewuserLogin.";
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        Username.setText("LoginTest");
        Password.setText("hash");

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
                    JOptionPane.showMessageDialog(null,                                       //goodbye message window
                        "Thx for stopping by hope to see you again soon.");
                    Application.terminate();
                }
            });
    }     

    @FXML   void SignInClicked()
    {
        System.out.println("SignInButton was clicked!"); 

        User loginAttempt = User.getByUsername(Username.getText());
        String hashpassword = PasswordHelper.generateHash(Password.getText());
        System.out.println(hashpassword + hashpassword.length());
        System.out.println(Password.getText() + Password.getText().length());

        if (loginAttempt == null) 
        {
            // NO USER FOUND!

            System.out.println("No User Found");

            JOptionPane.showMessageDialog(null, "No User Found With That Username.");

        }

        else
        {
            if (loginAttempt.Password.equals(hashpassword))
            {   
                System.out.println("Login Correct");

                Application.currentUser = loginAttempt;

                // WOOO! YOU'RE IN!
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
            else
            {
                System.out.println("Wrong Password");

                JOptionPane.showMessageDialog(null, "Incorrect Password.");
                //WRONG PASSWORD
            }   
        }

    }

    /* The next three methods are event handlers for clicking on the buttons. 
     * The names of these methods are set in Scene Builder so they work automatically. */    
    @FXML   void ClickMeClicked()
    {
        System.out.println("ClickMe was clicked!");   

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("New User.fxml"));

        try
        {
            Stage stage2 = new Stage();
            stage2.setTitle("New User");
            stage2.setScene(new Scene(loader.load()));
            stage2.show();           
            Scene2Controller controller2 = loader.getController();
            controller2.prepareStageEvents(stage2);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML   void UsernameClicked()
    {
        System.out.println("Username was clicked!");
    }

    @FXML   void PasswordClicked()
    {
        System.out.println("Password was clicked!"); 

    }

}

