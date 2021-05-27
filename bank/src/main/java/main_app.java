import javax.swing.*;

public class main_app {

    public static void main(String args[]) {
        CreateAccount createAccount = new CreateAccount();
        LandingScreen landingScreen = new LandingScreen();
        Login login = new Login();
        userScreen userScreen = new userScreen();
        deleteAccount delAccount = new deleteAccount();
        AddMoney addMoney = new AddMoney();
        withdrawMoney withdraw=new withdrawMoney();
        transferMoney transfer=new transferMoney();
        DisplayScreen display=new DisplayScreen();

        User user = null;
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setVisible(true);
        do {
            frame.repaint();
        if (landingScreen.LandingScreen(frame)) {
            user = login.Login(frame);
        } else {
            user = createAccount.Create(frame);
        }
        }
        while(user==null);
        int action=0;
        boolean del=false;
        do{
             action=userScreen.userScreen(frame,user);
            switch(action){
                case 0: break;
                case 1:addMoney.addMoney(frame,user); break;
                case 2:withdraw.withdraw(frame,user);break;
                case 3:transfer.transfer(frame,user); break;
                case 4:display.display(frame);break;
                case 5:del=delAccount.deleteAccount(frame,user);break;
            }
            if(del==true) break;

        }
        while(action!=0);
        frame.setVisible(false);
        return;






    }


}
