import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static com.mongodb.client.model.Filters.*;

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


    public void addUser(User a){
        userList = database.getCollection("User", User.class);
        userList.insertOne(a);
        User temp=userList.find(eq("account_number",a.getAccount_number())).first();
        a.setId(temp.getId());
    }

    public boolean checkIfUserExist(String account_number){
        User temp=userList.find(eq("account_number",account_number)).first();
        if(temp==null) return false;
        else return true;
    }

}
