import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class database {


    MongoCollection<user> userList = null;
    MongoDatabase database = null;
    MongoClient Client = null;

    public database(){
        Client = MongoClients.create(
                "mongodb+srv://user:admin@banking.ih9pj.mongodb.net/bank-java?retryWrites=true&w=majority");
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        database = Client.getDatabase("bank-java").withCodecRegistry(pojoCodecRegistry);
    }


    public void addUser(user a){
        userList = database.getCollection("User", user.class);
        userList.insertOne(a);

    }

}
