import com.mongodb.client.FindIterable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class DisplayScreen {
    Semaphore sem=new Semaphore(0);
    Semaphore sem_inside=new Semaphore(0);
    private JLabel name2;
    private JLabel name;

    private JLabel surname;
    private JLabel surname2;

    private JLabel number;
    private JLabel number2;

    private JLabel money;
    private JLabel money2;

    private JLabel address;
    private JLabel address2;


    String[] list_f = {"Brak", "Imie", "Nazwisko", "Numer Konta","Adres"};
    private JLabel filterL;
    private JLabel filterL2;
    private JTextField filterT;
    private JButton filter;
    private JButton button;
    private JButton button_2;
    private JComboBox filter_list=new JComboBox(list_f);
    boolean quit=false;
    FindIterable<User> lista;

    private JLabel error_name;
    private JLabel error_surname;
    private JLabel error_address;
    private JLabel error_number_empty;
    private JLabel error_number_length;
    private JLabel error_number_notnumber;


    public void display(JFrame frame){
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        database data=new database();


        filter_list.setSelectedIndex(0);

        filterL=new JLabel("Wybierz filtr: ");
        filterL.setBounds(10,20,80,20);
        panel.add(filterL);

        filter_list.setBounds(100,20,100,20);;
        panel.add(filter_list);

        filterL2=new JLabel("Wpisz filtr: ");
        filterL2.setBounds(10,50,80,20);
        panel.add(filterL2);

        filterT=new JTextField();
        filterT.setBounds(100,50,100,20);
        panel.add(filterT);


        error_name=new JLabel("*Pole nie może być puste");
        error_name.setBounds(210,50,200,20);
        panel.add(error_name);

        error_address=new JLabel("*Pole nie może być puste");
        error_address.setBounds(210,50,200,20);
        panel.add(error_address);

        error_surname=new JLabel("*Pole nie może być puste");
        error_surname.setBounds(210,50,200,20);
        panel.add(error_surname);

        error_number_empty=new JLabel("*Pole nie może być puste");
        error_number_empty.setBounds(210,50,200,20);
        panel.add(error_number_empty);

        error_number_length=new JLabel("*Pole musi być 8 znakowe");
        error_number_length.setBounds(210,50,200,20);
        panel.add(error_number_length);

        error_number_notnumber=new JLabel("*Pole musi być liczbą");
        error_number_notnumber.setBounds(210,50,200,20);
        panel.add(error_number_notnumber);
        error_name.setVisible(false);
        error_surname.setVisible(false);
        error_number_empty.setVisible(false);
        error_number_length.setVisible(false);
        error_number_notnumber.setVisible(false);
        error_address.setVisible(false);



        filter=new JButton("Wyszukaj");
        filter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                error_name.setVisible(false);
                error_surname.setVisible(false);
                error_number_empty.setVisible(false);
                error_number_length.setVisible(false);
                error_number_notnumber.setVisible(false);
                error_address.setVisible(false);
                if(filter_list.getSelectedIndex()==0){
                    lista=data.Find();
                    sem.release();
                } else{
                    if(filter_list.getSelectedIndex()==1){
                        if(filterT.getText().equals("")){
                            error_name.setVisible(true);
                            return;
                        }
                        lista=data.Find_filtred("name",filterT.getText());
                        sem.release();
                    }
                    else if(filter_list.getSelectedIndex()==2){
                        if(filterT.getText().equals("")){
                            error_surname.setVisible(true);
                            return;
                        }
                        lista=data.Find_filtred("surname",filterT.getText());
                        sem.release();
                    }
                    else if(filter_list.getSelectedIndex()==4){
                        if(filterT.getText().equals("")){
                            error_address.setVisible(true);
                            return;
                        }
                        lista=data.Find_filtred("address",filterT.getText());
                        sem.release();
                    }
                    else if(filter_list.getSelectedIndex()==3){
                        if(filterT.getText().equals("")){
                            error_number_empty.setVisible(true);
                            return;
                        } else if(filterT.getText().length()!=8){
                            error_number_length.setVisible(true);
                            return;
                        }
                        else{
                            try{
                                double x;
                                x=Double.valueOf(filterT.getText());
                            } catch (Exception e){
                                error_number_notnumber.setVisible(true);
                                return;
                            }
                        }
                        lista=data.Find_filtred("account_number",filterT.getText());
                        sem.release();
                    }
                }
            }
        });
        filter.setBounds(100,80,100,20);
        panel.add(filter);

        button_2=new JButton("Wróć");
        button_2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                panel.setVisible(false);
                quit=true;
                lista=data.Find();
                sem_inside.release();
                sem.release();
            }
        });
        if(quit==true){
            return;
        }
        button_2.setBounds(380,30,80,30);
        panel.add(button_2);

        frame.repaint();
        try{
            sem.acquire();
        }catch (Exception e){
            System.out.println(e);
        }
        panel.setVisible(false);

        JPanel panel2 = new JPanel();
        frame.add(panel2);
        panel2.setLayout(null);


        name=new JLabel("Imię: ");
        name.setBounds(10,20,80,20);
        panel2.add(name);


        surname=new JLabel("Nazwisko: ");
        surname.setBounds(10,50,80,20);
        panel2.add(surname);


        money=new JLabel("Saldo: ");
        money.setBounds(10,80,80,20);
        panel2.add(money);


        number=new JLabel("Numer konta: ");
        number.setBounds(10,110,80,20);
        panel2.add(number);

        address=new JLabel("Adres: ");
        address.setBounds(10,140,80,20);
        panel2.add(address);



        for (User user:lista) {

            name2=new JLabel(user.getName());
            name2.setBounds(100,20,200,20);
            panel2.add(name2);


            surname2=new JLabel(user.getSurname());
            surname2.setBounds(100,50,200,20);
            panel2.add(surname2);

            money2=new JLabel(Double.toString(user.getMoney()));
            money2.setBounds(100,80,200,20);
            panel2.add(money2);

            number2=new JLabel(user.getAccount_number());
            number2.setBounds(100,110,200,20);
            panel2.add(number2);

            address2=new JLabel(user.getAddress());
            address2.setBounds(100,140,200,20);
            panel2.add(address2);






            button_2=new JButton("Wróć");
            button_2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    panel2.setVisible(false);
                    quit=true;
                    sem_inside.release();
                }
            });
            button_2.setBounds(380,30,80,30);
            panel2.add(button_2);

            button=new JButton("Następny");
            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    name2.setText("");
                    surname2.setText("");
                    money2.setText("");
                    number2.setText("");
                    address2.setText("");
                    sem_inside.release();
                }
            });
            button.setBounds(10,170,180,30);
            panel2.add(button);


            panel2.setVisible(true);
            frame.repaint();
            try{
                sem_inside.acquire();
            }catch (Exception e){
                System.out.println(e);
            }
            if(quit==true) {
                quit=false;
                break;
            }
        }
        panel2.setVisible(false);


    }
}
