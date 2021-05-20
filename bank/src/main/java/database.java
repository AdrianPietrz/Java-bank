

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;



public class database {

    ConnectionString connString = new ConnectionString(
            "mongodb+srv://<admin>:<admin>@cluster0.ih9pj.mongodb.net/bank?retryWrites=true&w=majority"
    );
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connString)
            .retryWrites(true)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("banking-java");


    public void addUser(user a){
        MongoCollection<user> userList = database.getCollection("user",user.class);
        userList.insertOne(a);
    }

}
