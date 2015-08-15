package juanco.m101.mongo;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class MongoInsertarDocumentos {

  public static void main(String[] args) {
    
    MongoCollection<Document> personas = MongoHelper.coleccionPersonas(MongoHelper.obtenerConexion());
    
    
    
    Document juan = new Document("name", "Juan")
        .append("age", "26")
        .append("profession", "programer");
    
    Document galileo = new Document("name", "Galileo")
      .append("age", "1")
      .append("profession", "gato");
    
    
    Document sofia = new Document("name", "Sofia")
      .append("age", "18")
      .append("profession", "enfermera");
    
    Document alicia = new Document("name", "Alicia")
      .append("age", "26")
      .append("profession", "writer");
    
    Document jones = new Document("name", "Jones")
      .append("age", "25")
      .append("profession", "painter");
    
    personas.insertMany(Arrays.asList(juan, galileo, sofia, alicia, jones));
    
  }

}
