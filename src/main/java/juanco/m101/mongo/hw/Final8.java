package juanco.m101.mongo.hw;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Final8 {

  
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    MongoClient c =  new MongoClient();
    MongoDatabase db = c.getDatabase("test");
    MongoCollection<Document> animals = db.getCollection("animals");

    Document animal = new Document("animal", "monkey");

    animals.insertOne(animal);
    animal.remove("animal");
    animal.append("animal", "cat");
    animals.insertOne(animal);
    animal.remove("animal");
    animal.append("animal", "lion");
    animals.insertOne(animal);
  }
}