import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class AddMoney {
    Semaphore sem=new Semaphore(0);
    private JLabel money;
    private JTextField moneyT;
    private JLabel error_1;
    private JLabel error_2;
    private JButton button;
    private JButton button_2;
    public void addMoney(JFrame frame,User user){
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        money=new JLabel("Podaj wartość do dodania na konto: "+user.getAccount_number());
        money.setBounds(75,100,400,20);
        panel.add(money);

        moneyT=new JTextField();
        moneyT.setBounds(150,140,100,30);
        panel.add(moneyT);

        error_1=new JLabel("*Pole musi być liczbą");
        error_1.setBounds(125,210,200,20);
        panel.add(error_1);
        error_1.setVisible(false);

        error_2=new JLabel("*Pole nie może być puste");
        error_2.setBounds(125,210,200,20);
        panel.add(error_2);
        error_2.setVisible(false);

        button=new JButton("Wpłać");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                boolean exit=false;
                double x=0;
                error_2.setVisible(false);
                error_1.setVisible(false);
                if(moneyT.getText().equals("")){
                    exit=true;
                    error_2.setVisible(true);
                } else{
                    try{
                        x=Double.valueOf(moneyT.getText());
                    } catch (Exception e){
                        error_1.setVisible(true);
                        exit=true;
                    }
                }

                if(exit){
                    return;
                }
                panel.setVisible(false);
                double s=user.getMoney();
                s+=x;

                database da=new database();
                da.addMoney(x,user);
                user.setMoney(s);
                sem.release();
            }
        });
        button.setBounds(125,180,150,20);
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
