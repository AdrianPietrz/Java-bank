import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class Login {
    User user=null;
    Semaphore sem=new Semaphore(0);
    private JLabel account_number;
    private JTextField account_numberT;
    private JLabel account_numberE;
    private JLabel account_numberEnumeric;
    private JLabel account_numberElength;

    private JLabel pin;
    private JTextField pinT;
    private JLabel pinE;
    private JLabel pinEnumeric;
    private JLabel pinElength;

    private JButton button;
    private JButton button_2;
    private JLabel login_error;

    public User Login(JFrame frame){
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        panel.setVisible(true);
        account_number= new JLabel("Numer konta:");
        account_number.setBounds(10,20,80,20);
        panel.add(account_number);
        account_numberT=new JTextField(20);
        account_numberT.setBounds(100,20,120,20);
        panel.add(account_numberT);
        account_numberE= new JLabel("*Pole nie może być puste");
        account_numberE.setBounds(230,20,150,20);
        panel.add(account_numberE);
        account_numberE.setVisible(false);
        account_numberEnumeric= new JLabel("*Pole musi być liczbą");
        account_numberEnumeric.setBounds(230,20,150,20);
        panel.add(account_numberEnumeric);
        account_numberEnumeric.setVisible(false);
        account_numberElength= new JLabel("*Pole musi być 8 znakowe");
        account_numberElength.setBounds(230,20,150,20);
        panel.add(account_numberElength);
        account_numberElength.setVisible(false);


        pin= new JLabel("Pin:");
        pin.setBounds(10,50,80,20);
        panel.add(pin);
        pinT=new JTextField(20);
        pinT.setBounds(100,50,120,20);
        panel.add(pinT);
        pinE= new JLabel("*Pole nie może być puste");
        pinE.setBounds(230,50,150,20);
        panel.add(pinE);
        pinE.setVisible(false);
        pinEnumeric= new JLabel("*Pole musi być liczbą");
        pinEnumeric.setBounds(230,50,150,20);
        panel.add(pinEnumeric);
        pinEnumeric.setVisible(false);
        pinElength= new JLabel("*Pole musi być 4 znakowe");
        pinElength.setBounds(230,50,150,20);
        panel.add(pinElength);
        pinElength.setVisible(false);

        login_error=new JLabel("*Błędne dane logowania");
        login_error.setBounds(100,110,150,20);
        panel.add(login_error);
        login_error.setVisible(false);

        button=new JButton("Zaloguj się");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                pinE.setVisible(false);
                pinEnumeric.setVisible(false);
                pinElength.setVisible(false);
                account_numberE.setVisible(false);
                account_numberElength.setVisible(false);
                account_numberEnumeric.setVisible(false);
                login_error.setVisible(false);
                boolean exit=false;

                if(pinT.getText().equals("")){
                    pinE.setVisible(true);
                    exit=true;
                }else if(pinT.getText().length()!=4){
                    pinElength.setVisible(true);
                    exit=true;
                }
                else{
                    try{
                        int a=Integer.valueOf(pinT.getText());
                    } catch (Exception e){
                        pinEnumeric.setVisible(true);
                        exit=true;
                    }
                }
                if(account_numberT.getText().equals("")){
                    account_numberE.setVisible(true);
                    exit=true;
                }
                else if(account_numberT.getText().length()!=8){
                    account_numberElength.setVisible(true);
                    exit=true;
                }
                else{
                    try{
                        Integer.valueOf(account_numberT.getText());
                    } catch (Exception e){
                        account_numberEnumeric.setVisible(true);
                        exit=true;
                    }
                }
                database dat=new database();
                if(!dat.checkIfUserExist(account_numberT.getText())){
                    login_error.setVisible(true);
                    exit=true;
                } else{
                    if(dat.checkIfPinMatches(account_numberT.getText(),pinT.getText())==false){
                        login_error.setVisible(true);
                        exit=true;
                    }
                }

                if(exit){
                    return;
                }
                user=dat.LogIn(account_numberT.getText());
                panel.setVisible(false);
                sem.release();
            }
        });
        button.setBounds(100,80,120,20);
        panel.add(button);

        button_2=new JButton("Wróć");
        button_2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                panel.setVisible(false);
                user=null;
                sem.release();
            }
        });
        button_2.setBounds(380,30,80,30);
        panel.add(button_2);

        frame.repaint();


        try{
            sem.acquire();
        }catch (Exception e){
            System.out.println(e);
        }
        return user;
    }
}
