package juanco.m101.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

import static com.mongodb.client.model.Filters.*;

public class MongoBusquedaProyeccion {

  public static void main(String[] args) {
    MongoCollection<Document> personas = MongoHelper.coleccionPersonas(MongoHelper.obtenerConexion());
    MongoHelper.borrarPersonas(personas);
    MongoHelper.insertarPersonas(personas);
    

    // filtro
    Bson filtro = lt("age", 25);
    
    
    // Proyeccion
    Bson proyeccion = new Document("_id", 0) // 0: Excluir
                      .append("name", true) ;
    
    // Proyeccion usando Builder
    Bson proyeccion2 = Projections.fields(Projections.excludeId(), Projections.include("name"));
    
    List<Document> list = personas.find(filtro)
                                  .projection(proyeccion)
                                  .into(new ArrayList<Document>());
        
    MongoHelper.impirmir(list);
    
    
    
    // Sort
    Bson sort1 = new Document("name", 1);
    Bson sort2 = Sorts.orderBy(Sorts.ascending("name"), Sorts.descending("age"));
    
    List<Document> all = personas.find()
                                  .sort(sort1)
                                  .skip(3)
                                  .limit(10)
                                  .into(new ArrayList<Document>());
    MongoHelper.impirmir(all);
    
    
  }
}