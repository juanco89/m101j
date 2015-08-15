package juanco.m101.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class MongoBusquedas {

  public static void main(String[] args) {
    MongoCollection<Document> personas = MongoHelper.coleccionPersonas(MongoHelper.obtenerConexion());
    MongoHelper.borrarPersonas(personas);
    MongoHelper.insertarPersonas(personas);
    
    
    // BUSQUEDAS
    
    // Un solo documento
    Document dOne = personas.find().first();
    System.out.println(dOne.toJson());
    
    // Encapsular el resultado en una colleccion
    List<Document> list = personas.find().into(new ArrayList<Document>());
    System.out.println(list);
    
    
    // Usando cursores explícitamente
    MongoCursor<Document> cursor = personas.find().iterator();
    try {
      while(cursor.hasNext()) {
        Document d = cursor.next();
        System.out.println(d.toJson());
      }
    } finally {
      cursor.close();
    }
    
    
    // Conteo de resultados
    long count = personas.count();
    System.out.println("Count: " + count);
  }
}
