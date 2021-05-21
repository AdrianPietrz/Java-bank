import javax.swing.*;

public class main_app {

    public static void main(String args[]) {
        CreateAccount createAccount=new CreateAccount();
        LandingScreen landingScreen=new LandingScreen();
        User user=null;
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setVisible(true);

        if(landingScreen.LandingScreen(frame)){

        }
        else{
            user=createAccount.Create(frame);
        }





    }


}
