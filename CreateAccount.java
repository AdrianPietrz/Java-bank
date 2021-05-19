import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccount{

    private JLabel name;
    private JTextField nameT;
    private JLabel surname;
    private JTextField surnameT;
    private JLabel money;
    private JTextField moneyT;
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

        surname= new JLabel("Surname:");
        surname.setBounds(10,40,80,20);
        panel.add(surname);
        surnameT=new JTextField(20);
        surnameT.setBounds(100,40,120,20);
        panel.add(surnameT);

        money= new JLabel("Money:");
        money.setBounds(10,70,80,20);
        panel.add(money);
        moneyT=new JTextField(20);
        moneyT.setBounds(100,70,120,20);
        panel.add(moneyT);


        button=new JButton("Register");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                double money=Double.valueOf(moneyT.getText());
                user user = new user(nameT.getText(),surnameT.getText(),money);
                System.out.println(user.money);
            }
        });
        button.setBounds(50,100,100,20);
        panel.add(button);

        frame.setVisible(true);
    }



}
