import java.util.Objects;

import org.bson.types.ObjectId;


public final class user {

    private ObjectId user_ID;
    private String name;
    private String surname;
    private double money;

    public user(final String name,final String surname,final double money)
    {
        this.name=name;
        this.surname=surname;
        this.money=money;
    }
    public user() {}

    public ObjectId getId() {
        return user_ID;
    }

    public void setId(final ObjectId id) {
        this.user_ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(final String surname) {
        this.surname = surname;
    }
    public double getMoney() {
        return money;
    }

    public void setMoney(final int money) {
        this.money = money;
    }



    @Override
    public String toString() {
        return "User{"
                + "id='" + user_ID + "'"
                + ", name='" + name + "'"
                + ", surname='" + surname + "'"
                + ", money='" + money + "'}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_ID, name, surname);
    }
}
