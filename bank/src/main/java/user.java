import java.util.Objects;

import org.bson.types.ObjectId;


public final class User {

    private ObjectId user_ID;
    private String name;
    private String surname;
    private String account_number;
    private String pin;
    private double money;

    public User(final String name, final String surname, String account_number, String pin,final double money)
    {
        this.name=name;
        this.surname=surname;
        this.account_number=account_number;
        this.pin=pin;
        this.money=money;
    }
    public User() {}

    public ObjectId getId() {
        return user_ID;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getPin() {
        return pin;
    }
    public String getAccount_number() {
        return account_number;
    }
    public Double getMoney() {
        return money;
    }


    public User setId(ObjectId id) {
        this.user_ID = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }
    public User setPin(String pin) {
        this.pin = pin;
        return this;
    }

    public User setAccount_number(String account_number) {
        this.account_number = account_number;
        return this;
    }

    public User setMoney(Double money) {
        this.money = money;
        return this;
    }



    @Override
    public String toString() {
        return "User{"
                + "id='" + user_ID + "'"
                + ", name='" + name + "'"
                + ", surname='" + surname + "'"
                + ", PESEL='" + account_number + "'"
                + ", money='" + money + "'}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_ID, name, surname,account_number);
    }
}