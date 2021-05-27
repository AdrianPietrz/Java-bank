import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class transferMoney {
    Semaphore sem=new Semaphore(0);
    private JLabel money;
    private JLabel name2;
    private JLabel name;
    private JLabel from_number;
    private JLabel from_number2;
    private JLabel to_number;
    private JTextField to_numberT;
    private JLabel saldo;
    private JLabel saldo2;
    private JTextField moneyT;
    private JLabel error_1;
    private JLabel error_2;
    private JLabel error_3;
    private JLabel error_4;
    private JLabel error_5;
    private JLabel error_6;
    private JLabel error_7;
    private JButton button;
    private JButton button_2;
    public void transfer(JFrame frame,User user){
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        name=new JLabel("Dane: ");
        name.setBounds(10,20,80,20);
        panel.add(name);
        name2=new JLabel(user.getName()+" "+user.getSurname());
        name2.setBounds(100,20,200,20);
        panel.add(name2);


        from_number=new JLabel("Numer konta: ");
        from_number.setBounds(10,50,80,20);
        panel.add(from_number);
        from_number2=new JLabel(user.getAccount_number());
        from_number2.setBounds(100,50,200,20);
        panel.add(from_number2);

        saldo=new JLabel("Saldo: ");
        saldo.setBounds(10,80,80,20);
        panel.add(saldo);
        saldo2=new JLabel(Double.toString(user.getMoney()));
        saldo2.setBounds(100,80,200,20);
        panel.add(saldo2);

        to_number=new JLabel("Na konto: ");
        to_number.setBounds(10,110,80,20);
        panel.add(to_number);
        to_numberT=new JTextField(20);
        to_numberT.setBounds(100,110,120,20);
        panel.add(to_numberT);



        money=new JLabel("Podaj kwotę: ");
        money.setBounds(10,140,80,20);
        panel.add(money);

        moneyT=new JTextField();
        moneyT.setBounds(100,140,120,30);
        panel.add(moneyT);

        error_1=new JLabel("*Pole musi być liczbą");
        error_1.setBounds(230,140,200,20);
        panel.add(error_1);
        error_1.setVisible(false);

        error_2=new JLabel("*Pole nie może być puste");
        error_2.setBounds(230,140,200,20);
        panel.add(error_2);
        error_2.setVisible(false);

        error_3=new JLabel("*Za mało środków na koncie");
        error_3.setBounds(230,140,200,20);
        panel.add(error_3);
        error_3.setVisible(false);

        error_4= new JLabel("*Pole nie może być puste");
        error_4.setBounds(230,110,200,20);
        panel.add(error_4);
        error_4.setVisible(false);
        error_5= new JLabel("*Taki numer konta nie istnieje");
        error_5.setBounds(230,110,200,20);
        panel.add(error_5);
        error_5.setVisible(false);
        error_6= new JLabel("*Pole musi być liczbą");
        error_6.setBounds(230,110,200,20);
        panel.add(error_6);
        error_6.setVisible(false);
        error_7= new JLabel("*Pole musi być 8 znakowe");
        error_7.setBounds(230,110,200,20);
        panel.add(error_7);
        error_7.setVisible(false);



        button=new JButton("Przelej");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                boolean exit=false;
                double x=0;
                error_3.setVisible(false);
                error_2.setVisible(false);
                error_1.setVisible(false);
                error_4.setVisible(false);
                error_5.setVisible(false);
                error_6.setVisible(false);
                error_7.setVisible(false);

                if(moneyT.getText().equals("")){
                    exit=true;
                    error_2.setVisible(true);
                } else
                {
                    try{
                        x=Double.valueOf(moneyT.getText());
                    } catch (Exception e){
                        error_1.setVisible(true);
                        exit=true;
                    }
                }
                if(user.getMoney()-x<0){
                    exit=true;
                    error_2.setVisible(false);
                    error_1.setVisible(false);
                    error_3.setVisible(true);
                }
                if(to_numberT.getText().equals("")){
                    error_4.setVisible(true);
                    exit=true;
                }
                else if(to_numberT.getText().length()!=8){
                    error_7.setVisible(true);
                    exit=true;
                }
                else{
                    try{
                        Integer.valueOf(to_numberT.getText());
                    } catch (Exception e){
                        error_6.setVisible(true);
                        exit=true;
                    }
                }
                database dat=new database();
                if(!dat.checkIfUserExist(to_numberT.getText())){
                    if(error_4.isVisible()==false && error_6.isVisible()==false && error_7.isVisible()==false){
                        error_5.setVisible(true);
                        exit=true;
                    }
                }
                if(exit){
                    return;
                }
                panel.setVisible(false);
                double s=user.getMoney();
                s-=x;

                database da=new database();
                da.transfer(x,user,to_numberT.getText());
                user.setMoney(s);
                sem.release();
            }
        });
        button.setBounds(100,180,120,20);
        panel.add(button);


        button_2=new JButton("Wróć");
        button_2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
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
    }
}
