import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class LandingScreen {
    boolean logIN = false;
    Semaphore sem= new Semaphore(0);
    public boolean LandingScreen(JFrame frame){
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        JLabel text=new JLabel();
        text= new JLabel("Welcome to LoseYourMoney");
        text.setBounds(100,10,400,100);
        panel.add(text);

        JButton button=new JButton("Register");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                logIN=false;
                sem.release();
                  }
        });
        button.setBounds(50,170,100,20);
        panel.add(button);

        JButton login=new JButton("Log In");
        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                logIN=true;
                sem.release();
            }
        });
        login.setBounds(150,170,100,20);
        panel.add(login);

        try{
            sem.acquire();
        } catch (Exception e){
            System.out.println(e);
        }
        panel.setVisible(false);
        return logIN;
    }
}

