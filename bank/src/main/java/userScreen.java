import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class userScreen {
    Semaphore sem=new Semaphore(0);
    int res=0;
    private JLabel name;
    private JLabel name2;
    private JLabel address;
    private JLabel address2;
    private JLabel surname;
    private JLabel surname2;
    private JLabel money;
    private JLabel money2;
    private JLabel number;
    private JLabel number2;
    private JButton add;
    private JButton withdraw;
    private JButton transfer;
    private JButton display;
    private JButton delete;
    private JButton button_2;



    public int userScreen(JFrame frame,User user){
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);


        name=new JLabel("Imię: ");
        name.setBounds(10,20,80,20);
        panel.add(name);
        name2=new JLabel(user.getName());
        name2.setBounds(100,20,200,20);
        panel.add(name2);

        surname=new JLabel("Nazwisko: ");
        surname.setBounds(10,50,80,20);
        panel.add(surname);
        surname2=new JLabel(user.getSurname());
        surname2.setBounds(100,50,200,20);
        panel.add(surname2);

        money=new JLabel("Saldo: ");
        money.setBounds(10,80,80,20);
        panel.add(money);
        money2=new JLabel(Double.toString(user.getMoney()));
        money2.setBounds(100,80,200,20);
        panel.add(money2);

        number=new JLabel("Numer konta: ");
        number.setBounds(10,110,80,20);
        panel.add(number);
        number2=new JLabel(user.getAccount_number());
        number2.setBounds(100,110,200,20);
        panel.add(number2);

        address=new JLabel("Adres: ");
        address.setBounds(10,140,80,20);
        panel.add(address);
        address2=new JLabel(user.getAddress());
        address2.setBounds(100,140,200,20);
        panel.add(address2);

        button_2=new JButton("Wyloguj");
        button_2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                panel.setVisible(false);
                res=0;
                sem.release();
            }
        });
        button_2.setBounds(340,30,120,30);
        panel.add(button_2);

        add=new JButton("Wpłać");
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                panel.setVisible(false);
                res=1;
                sem.release();
            }
        });
        add.setBounds(340,80,120,30);
        panel.add(add);

        withdraw=new JButton("Wypłać");
        withdraw.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                panel.setVisible(false);
                res=2;
                sem.release();
            }
        });
        withdraw.setBounds(340,130,120,30);
        panel.add(withdraw);

        transfer=new JButton("Przelew");
        transfer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                panel.setVisible(false);
                res=3;
                sem.release();
            }
        });
        transfer.setBounds(340,180,120,30);
        panel.add(transfer);

        display=new JButton("Wyświetl");
        display.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                panel.setVisible(false);
                res=4;
                sem.release();
            }
        });
        display.setBounds(340,230,120,30);
        panel.add(display);

        delete=new JButton("Usuń konto");
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                panel.setVisible(false);
                res=5;
                sem.release();
            }
        });
        delete.setBounds(340,280,120,30);
        panel.add(delete);

        panel.setVisible(true);
        frame.repaint();
        try{
            sem.acquire();
        }catch (Exception e){
            System.out.println(e);
        }
        return res;
    }
}
