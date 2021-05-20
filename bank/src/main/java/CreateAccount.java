

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

    private JLabel money;
    private JTextField moneyT;
    private JLabel moneyE;

    private JButton button;

    public void CreateAccount(){
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

        money= new JLabel("Money:");
        money.setBounds(10,70,80,20);
        panel.add(money);
        moneyT=new JTextField(20);
        moneyT.setBounds(100,70,120,20);
        panel.add(moneyT);
        moneyE= new JLabel("*Must be numeric value");
        moneyE.setBounds(230,70,150,20);
        panel.add(moneyE);
        moneyE.setVisible(false);


        button=new JButton("Register");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                nameE.setVisible(false);
                surnameE.setVisible(false);
                moneyE.setVisible(false);
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
                if(exit){
                    return;
                }
                user user = new user(nameT.getText(),surnameT.getText(),money);
                database dat=new database();
                dat.addUser(user);
            }
        });
        button.setBounds(50,100,100,20);
        panel.add(button);

        frame.setVisible(true);
    }



}
