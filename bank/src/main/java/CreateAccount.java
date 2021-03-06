

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class CreateAccount{
    Semaphore sem=new Semaphore(0);
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
    private JLabel addres;
    private JTextField addresT;
    private JLabel addresE;

    private JLabel money;
    private JTextField moneyT;
    private JLabel moneyE;

    private JButton button;
    private JButton button_2;
    private User user=null;
    public User Create(JFrame frame){
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        name= new JLabel("Imię:");
        name.setBounds(10,10,80,20);
        panel.add(name);
        nameT=new JTextField(20);
        nameT.setBounds(100,10,120,20);
        panel.add(nameT);
        nameE= new JLabel("*Pole nie może być puste");
        nameE.setBounds(230,10,150,20);
        panel.add(nameE);
        nameE.setVisible(false);

        surname= new JLabel("Nazwisko:");
        surname.setBounds(10,40,80,20);
        panel.add(surname);
        surnameT=new JTextField(20);
        surnameT.setBounds(100,40,120,20);
        panel.add(surnameT);
        surnameE= new JLabel("*Pole nie może być puste");
        surnameE.setBounds(230,40,150,20);
        panel.add(surnameE);
        surnameE.setVisible(false);

        account_number= new JLabel("Numer konta:");
        account_number.setBounds(10,70,80,20);
        panel.add(account_number);
        account_numberT=new JTextField(20);
        account_numberT.setBounds(100,70,120,20);
        panel.add(account_numberT);
        account_numberE= new JLabel("*Pole nie może być puste");
        account_numberE.setBounds(230,70,150,20);
        panel.add(account_numberE);
        account_numberE.setVisible(false);
        account_numberE2= new JLabel("*Taki numer konta już istnieje");
        account_numberE2.setBounds(230,70,200,20);
        panel.add(account_numberE2);
        account_numberE2.setVisible(false);
        account_numberEnumeric= new JLabel("*Pole musi być liczbą");
        account_numberEnumeric.setBounds(230,70,150,20);
        panel.add(account_numberEnumeric);
        account_numberEnumeric.setVisible(false);
        account_numberElength= new JLabel("*Pole musi być 8 znakowe");
        account_numberElength.setBounds(230,70,150,20);
        panel.add(account_numberElength);
        account_numberElength.setVisible(false);


        pin= new JLabel("Pin:");
        pin.setBounds(10,100,80,20);
        panel.add(pin);
        pinT=new JTextField(20);
        pinT.setBounds(100,100,120,20);
        panel.add(pinT);
        pinE= new JLabel("*Pole nie może być puste");
        pinE.setBounds(230,100,150,20);
        panel.add(pinE);
        pinE.setVisible(false);
        pinEnumeric= new JLabel("*Pole musi być liczbą");
        pinEnumeric.setBounds(230,100,150,20);
        panel.add(pinEnumeric);
        pinEnumeric.setVisible(false);
        pinElength= new JLabel("*Pole musi być 4 znakowe");
        pinElength.setBounds(230,100,150,20);
        panel.add(pinElength);
        pinElength.setVisible(false);

        addres= new JLabel("Adres:");
        addres.setBounds(10,130,80,20);
        panel.add(addres);
        addresT=new JTextField(20);
        addresT.setBounds(100,130,120,20);
        panel.add(addresT);
        addresE= new JLabel("*Pole nie może być puste");
        addresE.setBounds(230,130,150,20);
        panel.add(addresE);
        addresE.setVisible(false);

        money= new JLabel("Stan konta:");
        money.setBounds(10,160,80,20);
        panel.add(money);
        moneyT=new JTextField(20);
        moneyT.setBounds(100,160,120,20);
        panel.add(moneyT);
        moneyE= new JLabel("*Pole musi być liczbą");
        moneyE.setBounds(230,160,150,20);
        panel.add(moneyE);
        moneyE.setVisible(false);


        button=new JButton("Utwórz konto");
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
                addresE.setVisible(false);


                boolean exit=false;
                if(addresT.getText().equals("")){
                    addresE.setVisible(true);
                    exit=true;
                }
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
                user = new User(nameT.getText(),surnameT.getText(),account_numberT.getText(),pinT.getText(),addresT.getText(),money);
                dat.addUser(user);
                panel.setVisible(false);
                sem.release();
            }
        });
        button.setBounds(100,200,120,20);
        panel.add(button);

        button_2=new JButton("Wróć");
        button_2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                user=null;
                panel.setVisible(false);
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
