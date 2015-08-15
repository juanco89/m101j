package juanco.m101.mongo;

import java.util.Arrays;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoClientConexion {

  public static void main(String[] args) {
    // Opciones
    MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(500).build();
    
    // Alternativas de conexion
    // MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    // MongoClient client = new MongoClient(new ServerAddress("localhost", 27017), options);
    MongoClient client = new MongoClient("localhost", 27017);
    
    // Objeto inmutable: !cuidado con las configuraciones!
    MongoDatabase db = client.getDatabase("test");
    
    // Objeto inmutable: !cuidado con las configuraciones!
    MongoCollection<Document> coll = db.getCollection("test");
    // MongoCollection<BsonDocument> coll = db.getCollection("test", BsonDocument.class);
    
    //  **Documentos
    
    // Inmutable, retorna el documento modificado.
    Document doc = new Document()
             .append("str", "Hello")
             .append("int", 42)
             .append("lon", 1L)
             .append("date", new Date())
             .append("objectId", new ObjectId())
             .append("null", null)
             .append("subdoc", new Document("x", 0))
             .append("list", Arrays.asList(1, 2, 3, 4, 5));
    
    // doc.get("str");
    // doc.getString("str);
    
    System.out.println( doc.toJson() );
  }
}
