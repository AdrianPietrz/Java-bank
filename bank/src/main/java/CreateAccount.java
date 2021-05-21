

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccount{

    private JLabel name;
    private JTextField nameT;
    private JLabel nameE;

    private JLabel surname;
    private JTextField surnameT;
    private JLabel surnameE;

    private JLabel account_number;
    private JTextField account_numberT;
    private JLabel account_numberE;
    private JLabel account_numberE2;
    private JLabel account_numberEnumeric;
    private JLabel account_numberElength;

    private JLabel pin;
    private JTextField pinT;
    private JLabel pinE;
    private JLabel pinEnumeric;
    private JLabel pinElength;

    private JLabel money;
    private JTextField moneyT;
    private JLabel moneyE;

    private JButton button;
    private User user=null;
    public User Create(){

        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        name= new JLabel("Name:");
        name.setBounds(10,10,80,20);
        panel.add(name);
        nameT=new JTextField(20);
        nameT.setBounds(100,10,120,20);
        panel.add(nameT);
        nameE= new JLabel("*Field can not be empty");
        nameE.setBounds(230,10,150,20);
        panel.add(nameE);
        nameE.setVisible(false);

        surname= new JLabel("Surname:");
        surname.setBounds(10,40,80,20);
        panel.add(surname);
        surnameT=new JTextField(20);
        surnameT.setBounds(100,40,120,20);
        panel.add(surnameT);
        surnameE= new JLabel("*Field can not be empty");
        surnameE.setBounds(230,40,150,20);
        panel.add(surnameE);
        surnameE.setVisible(false);

        account_number= new JLabel("Account number:");
        account_number.setBounds(10,70,80,20);
        panel.add(account_number);
        account_numberT=new JTextField(20);
        account_numberT.setBounds(100,70,120,20);
        panel.add(account_numberT);
        account_numberE= new JLabel("*Field can not be empty");
        account_numberE.setBounds(230,70,150,20);
        panel.add(account_numberE);
        account_numberE.setVisible(false);
        account_numberE2= new JLabel("*This account number already exist");
        account_numberE2.setBounds(230,70,150,20);
        panel.add(account_numberE2);
        account_numberE2.setVisible(false);
        account_numberEnumeric= new JLabel("*Must be numeric value");
        account_numberEnumeric.setBounds(230,70,150,20);
        panel.add(account_numberEnumeric);
        account_numberEnumeric.setVisible(false);

        account_numberElength= new JLabel("*Must be 8 digit");
        account_numberElength.setBounds(230,70,150,20);
        panel.add(account_numberElength);
        account_numberElength.setVisible(false);


        pin= new JLabel("Pin:");
        pin.setBounds(10,100,80,20);
        panel.add(pin);
        pinT=new JTextField(20);
        pinT.setBounds(100,100,120,20);
        panel.add(pinT);
        pinE= new JLabel("*Field can not be empty");
        pinE.setBounds(230,100,150,20);
        panel.add(pinE);
        pinE.setVisible(false);
        pinEnumeric= new JLabel("*Must be numeric value");
        pinEnumeric.setBounds(230,100,150,20);
        panel.add(pinEnumeric);
        pinEnumeric.setVisible(false);
        pinElength= new JLabel("*Must be 4 digits");
        pinElength.setBounds(230,100,150,20);
        panel.add(pinElength);
        pinElength.setVisible(false);

        money= new JLabel("Money:");
        money.setBounds(10,130,80,20);
        panel.add(money);
        moneyT=new JTextField(20);
        moneyT.setBounds(100,130,120,20);
        panel.add(moneyT);
        moneyE= new JLabel("*Must be numeric value");
        moneyE.setBounds(230,130,150,20);
        panel.add(moneyE);
        moneyE.setVisible(false);


        button=new JButton("Register");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                nameE.setVisible(false);
                surnameE.setVisible(false);
                moneyE.setVisible(false);
                pinE.setVisible(false);
                pinEnumeric.setVisible(false);
                pinElength.setVisible(false);
                account_numberE.setVisible(false);
                account_numberE2.setVisible(false);
                account_numberElength.setVisible(false);
                account_numberEnumeric.setVisible(false);


                boolean exit=false;
                if(nameT.getText().equals("")){
                    nameE.setVisible(true);
                    exit=true;
                }
                if(surnameT.getText().equals("")){
                    surnameE.setVisible(true);
                    exit=true;
                }
                double money=0;
                try{
                    money=Double.valueOf(moneyT.getText());
                } catch (Exception e){
                    if(!moneyT.getText().equals("")){
                        moneyE.setVisible(true);
                        exit=true;
                    }
                }
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
                if(dat.checkIfUserExist(account_numberT.getText())){
                    account_numberE2.setVisible(true);
                    exit=true;
                }
                if(exit){
                    return;
                }
                user = new User(nameT.getText(),surnameT.getText(),account_numberT.getText(),pinT.getText(),money);
                dat.addUser(user);
                frame.setVisible(false);
            }
        });
        button.setBounds(50,170,100,20);
        panel.add(button);

        frame.setVisible(true);
        return user;
    }



}
