package juanco.m101.mongo.hw;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Sorts;

public class HW2_3 {

  
  public static void main(String[] args) {
    
    MongoClient conex = new MongoClient("localhost", 27017);
    MongoCollection<Document> grades = conex.getDatabase("students").getCollection("grades");
    
    System.out.println("Documentos iniciales: " + grades.count() );
    
    
    // db.grades.find({ type: "homework" }).sort({ student_id: 1, score: 1 });
    
    Bson filtro = new Document("type", "homework");
    Bson sort = Sorts.orderBy(Sorts.ascending("student_id", "score"));

    
    MongoCursor<Document> cursor = grades.find(filtro)
                                         .sort(sort)
                                         .iterator();
    
    try {
      
      Document d;
      int currStudenId;
      int prevStudentId = -1;
      while(cursor.hasNext()) {
        d = cursor.next();
        
        currStudenId = d.getInteger("student_id");
        
        if(currStudenId != prevStudentId) {
          
          grades.deleteOne(d);
          
          System.out.println("Eliminando documento: " + d.toJson());
        }
        prevStudentId = currStudenId;
      }
    } finally {
      cursor.close();
    }
    
    System.out.println("Finalizando aplicación con " + grades.count() + " documentos" );
    conex.close();
  }
  
}
