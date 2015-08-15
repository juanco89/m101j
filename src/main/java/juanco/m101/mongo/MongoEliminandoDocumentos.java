package juanco.m101.mongo;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class MongoEliminandoDocumentos {

  public static void main(String[] args) {
    
    MongoCollection<Document> personas = MongoHelper.coleccionPersonas(MongoHelper.obtenerConexion());
    MongoHelper.insertarPersonas(personas);
    
    
    personas.drop();
    
  }
}
