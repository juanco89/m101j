package juanco.m101.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;

public class MongoBusquedaConFiltros {

  public static void main(String[] args) {
    MongoCollection<Document> personas = MongoHelper.coleccionPersonas(MongoHelper.obtenerConexion());
    MongoHelper.borrarPersonas(personas);
    MongoHelper.insertarPersonas(personas);
    
    
    // FILTROS
    
    // Alternativa 1
    Bson filtro1 = new Document("age", new Document("$lt", 25));
    
    // Alternativa 1 (static import Filters)
    Bson filtro2 = lt("age", 25);
    
    
    List<Document> list = personas.find(filtro1).into(new ArrayList<Document>());
    System.out.println(list);
    
    
    long count = personas.count(filtro2);
    System.out.println("Count: " + count);
  }
}
