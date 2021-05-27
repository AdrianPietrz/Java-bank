import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class deleteAccount {
    Semaphore sem=new Semaphore(0);
    private JLabel name;
    private JTextField nameT;
    private JLabel addres;
    private JTextField addresT;
    private JLabel surname;
    private JTextField surnameT;
    private JLabel account_number;
    private JTextField account_numberT;
    private JLabel money;
    private JTextField moneyT;
    private JLabel pin;
    private JTextField pinT;
    private JButton button;
    private JButton button_2;
    private JLabel error;
    boolean res=true;
    public boolean deleteAccount(JFrame frame, User user){

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);


        name=new JLabel("Imię: ");
        name.setBounds(10,20,80,20);
        panel.add(name);

        nameT=new JTextField();
        nameT.setText(user.getName());
        nameT.setEditable(false);
        nameT.setBounds(100,20,80,20);
        panel.add(nameT);

        surname=new JLabel("Nazwisko");
        surname.setBounds(10,50,80,20);
        panel.add(surname);

        surnameT=new JTextField();
        surnameT.setText(user.getSurname());
        surnameT.setEditable(false);
        surnameT.setBounds(100,50,80,20);
        panel.add(surnameT);

        account_number=new JLabel("Numer konta: ");
        account_number.setBounds(10,80,80,20);
        panel.add(account_number);

        account_numberT=new JTextField();
        account_numberT.setText(user.getAccount_number());
        account_numberT.setEditable(false);
        account_numberT.setBounds(100,80,80,20);
        panel.add(account_numberT);

        money=new JLabel("Saldo: ");
        money.setBounds(10,110,110,20);
        panel.add(money);

        moneyT=new JTextField();
        moneyT.setText(Double.toString(user.getMoney()));
        moneyT.setEditable(false);
        moneyT.setBounds(100,110,80,20);
        panel.add(moneyT);

        addres=new JLabel("Adres: ");
        addres.setBounds(10,140,110,20);
        panel.add(addres);

        addresT=new JTextField();
        addresT.setText(user.getAddress());
        addresT.setEditable(false);
        addresT.setBounds(100,140,80,20);
        panel.add(addresT);

        pin=new JLabel("Pin: ");
        pin.setBounds(10,170,80,20);
        panel.add(pin);

        pinT=new JTextField();
        pinT.setBounds(100,170,80,20);
        panel.add(pinT);

        error=new JLabel("*Błędny pin");
        error.setBounds(50,230,150,20);
        panel.add(error);
        error.setVisible(false);

        button=new JButton("Usuń konto");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                error.setVisible(false);
                boolean exit=false;
                if(!pinT.getText().equals(user.getPin())){
                    exit=true;
                    error.setVisible(true);
                }
                if(exit){
                    return;
                }
                database dat=new database();
                dat.deleteUser(user.getId());
                panel.setVisible(false);
                sem.release();
            }
        });
        button.setBounds(50,200,150,20);
        panel.add(button);

        button_2=new JButton("Wróć");
        button_2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                panel.setVisible(false);
                res=false;
                sem.release();
            }
        });
        button_2.setBounds(380,30,80,30);
        panel.add(button_2);

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
