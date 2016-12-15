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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javafx.scene.control.ToggleButton; 
import javafx.scene.layout.TilePane;
import javafx.event.ActionEvent;

public class Scene3Controller
{    
    /* The stage that the scene belongs to, required to catch stage events and test for duplicate controllers. */
    private static Stage stage;     

    /* These FXML variables exactly corrispond to the controls that make up the scene, as designed in Scene 
     * Builder. It is important to ensure that these match perfectly or the controls won't be interactive. */
    @FXML   private Pane FirstPagePane; 
    @FXML   private ScrollPane FirstPageScrollPane;
    @FXML   private TilePane FirstImagePane; 
    @FXML   private Button ProfileFirstPage;
    @FXML   private Button ScavengerFirstPage;
    @FXML   private ToggleButton AlcoholicFirstPage;
    @FXML   private ImageView FirstPageHeader;

    public Scene3Controller()          // The constructor method, called first when the scene is loaded.
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
            assert FirstPagePane != null : "Can't find FirstPagePane.";
            assert FirstImagePane != null : "Can't find FirstImagePane.";
            assert FirstPageScrollPane != null : "FirstPageScrollPane.";
            assert ProfileFirstPage != null : "ProfileFirstPage.";
            assert ScavengerFirstPage != null : "Can't find ScavengerFirstPage.";
            assert AlcoholicFirstPage != null : "Can't find AlcoholicFirstPage.";
            assert FirstPageHeader !=null : "Can't find FirstPageHeader.";
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
                    stage.close();
                }
            });

        List<Drinks> allTheDrinks = new ArrayList<Drinks>();
        Drinks.readAll(allTheDrinks);

        for (Drinks d : allTheDrinks)
        {
            Image image = new Image("Resources/" + d.ImageFileName);
            Button imageButton = new Button("", new ImageView(image));
            FirstImagePane.getChildren().add(imageButton);            

            imageButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        System.out.println(d.DrinkName + " clicked");

                        FXMLLoader loader = new FXMLLoader(Application.class.getResource("SingleDrinkScreen.fxml"));
                        try
                        {
                            Stage stage7 = new Stage();
                            stage7.setTitle("Drink");
                            stage7.setScene(new Scene(loader.load()));
                            stage7.show();           
                            Scene7Controller controller7 = loader.getController();
                            controller7.prepareStageEvents(stage7, d.DrinkID);
                        }
                        catch (Exception ex)
                        {
                            System.out.println(ex.getMessage());
                        }
                        stage.close();
                    }
                });

        }
        
    }

    /* The next three methods are event handlers for clicking on the buttons. 
     * The names of these methods are set in Scene Builder so they work automatically. */    
    @FXML   void ProfileFirstPageClicked()
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

    @FXML   void ScavengerFirstPageClicked()
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

    @FXML   void AlcoholicFirstPageClicked()
    {
        System.out.println("AlcoholicFirstPage was clicked!");        

    }

}