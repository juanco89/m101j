package juanco.m101.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;

public class MongoActualizacion {

  public static void main(String[] args) {
    MongoCollection<Document> personas = MongoHelper.coleccionPersonas(MongoHelper.obtenerConexion());
    MongoHelper.borrarPersonas(personas);
    MongoHelper.insertarPersonas(personas);

    // Originalmente
    List<Document> all = personas.find().into(new ArrayList<Document>());
    MongoHelper.impirmir(all);
    
    
    
    // Reemplazar Dcoumento con nombre "Juan"
    
    Document replacement = new Document("name", "Juan Carlos").append("update", true);
    
    personas.replaceOne(new Document("name", "Juan"),  replacement );
    MongoHelper.impirmir(all);
    
    
    // Actualizar
    Document update = new Document("$set", new Document("age", 2));
    
    personas.replaceOne(new Document("name", "Galileo"),  update 
                          /*, new UpdateOptions().upsert(true) */ );
    MongoHelper.impirmir(all);
    
    
  }
}