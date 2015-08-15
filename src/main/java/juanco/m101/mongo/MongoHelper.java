package juanco.m101.mongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

public class MongoHelper {

  public static MongoClient obtenerConexion() {
    return new MongoClient("localhost", 27017);
  }
  
  public static MongoCollection<Document> coleccionPersonas(MongoClient db) {
    return db.getDatabase("test").getCollection("personas");
  }
  
  public static void insertarPersonas(MongoCollection<Document> c) {
    
    Document juan = new Document("name", "Juan")
          .append("age", 26)
          .append("profession", "programer");
    
    Document galileo = new Document("name", "Galileo")
        .append("age", 1)
        .append("profession", "gato");
    
    
    Document sofia = new Document("name", "Sofia")
        .append("age", 18)
        .append("profession", "enfermera");
    
    Document alicia = new Document("name", "Alicia")
        .append("age", 26)
        .append("profession", "writer");
    
    Document jones = new Document("name", "Jones")
        .append("age", 25)
        .append("profession", "painter");
    
    c.insertMany(Arrays.asList(juan, galileo, sofia, alicia, jones));
  }
  
  public static void borrarPersonas(MongoCollection<Document> c) {
    c.drop();
  }
  
  public static List<Document> buscar(MongoCollection<Document> c, Bson filter) {
    return c.find(filter).into(new ArrayList<Document>());
  }
  
  public static void impirmir(List<Document> l) {
    for(Document d: l) {
      System.out.println( d.toJson() );
    }
  }
  
}
