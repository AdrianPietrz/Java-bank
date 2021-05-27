import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
public class database {


    MongoCollection<User> userList = null;
    MongoDatabase database = null;
    MongoClient Client = null;

    public database(){
        Client = MongoClients.create(
                "mongodb+srv://user:admin@banking.ih9pj.mongodb.net/bank-java?retryWrites=true&w=majority");
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        database = Client.getDatabase("bank-java").withCodecRegistry(pojoCodecRegistry);
    }

    public void getDatabase(){
        if(userList==null){
            userList = database.getCollection("User", User.class);
        }
    }

    public FindIterable<User> Find(){
        getDatabase();
        FindIterable<User> lista=userList.find();
        return lista;
    }

    public FindIterable<User> Find_filtred(String filter_type, String filter){
        getDatabase();
        FindIterable<User> lista=userList.find(eq(filter_type,filter));
        return lista;
    }



    public void addUser(User a){
        getDatabase();
        userList.insertOne(a);
        User temp=userList.find(eq("account_number",a.getAccount_number())).first();
        a.setId(temp.getId());
    }

    public void deleteUser(ObjectId a){
        getDatabase();
        userList.deleteOne(eq("_id", a));
    }

    public boolean checkIfUserExist(String account_number){
        getDatabase();
        User temp=userList.find(eq("account_number",account_number)).first();
        if(temp==null) return false;
        else return true;
    }

    public boolean checkIfPinMatches(String account_number, String pin){
        getDatabase();
        User temp=userList.find(eq("account_number",account_number)).first();
        if(pin.equals(temp.getPin())) return true;
        else return false;
    }
    public User LogIn(String account_number){
        User temp=userList.find(eq("account_number",account_number)).first();
        return temp;
    }

    public void addMoney(Double amount, User user){
        getDatabase();
        userList.updateOne(eq("_id", user.getId()), set("money", user.getMoney() + amount));
    }

    public void withdrawMoney(Double amount, User user){
        getDatabase();
        userList.updateOne(eq("_id", user.getId()), set("money", user.getMoney() - amount));
    }

    public void transfer(Double amount, User user, String account_number){
        getDatabase();
        User temp=userList.find(eq("account_number",account_number)).first();
        userList.updateOne(eq("_id", user.getId()), set("money", user.getMoney() - amount));
        userList.updateOne(eq("_id", temp.getId()), set("money", temp.getMoney() + amount));
    }
}
