package juanco.m101.mongo.hw;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class HW3_1 {

  
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    /*
     Hint/spoiler: With the new schema, this problem is a lot harder and 
     that is sort of the point. One way is to find the lowest homework 
     in code and then update the scores array with the low homework pruned.
     */
    
    /*
    {
      "_id" : 19,
      "name" : "Gisela Levin",
      "scores" : [
              {
                      "type" : "exam",
                      "score" : 44.51211101958831
              },
              {
                      "type" : "quiz",
                      "score" : 0.6578497966368002
              },
              {
                      "type" : "homework",
                      "score" : 93.36341655949683
              },
              {
                      "type" : "homework",
                      "score" : 49.43132782777443
              }
          ]
    }
    */
    
    MongoClient conex = new MongoClient("localhost", 27017);
    MongoCollection<Document> students = conex.getDatabase("school").getCollection("students");
    
    System.out.println("Documentos iniciales: " + students.count() );
    
    
    // { scores: { $elemMatch:{ type: "homework" }  } }
    Bson filtro = new Document("scores", 
                              new Document("$elemMatch", 
                                           new Document("type", "homework")));

    MongoCursor<Document> cursor = students.find(filtro)
                                         .iterator();
    
    try {
      Document student;
      List<Document> scores;
      Optional<Document> lowestScoreDocument;
      int studentId;
      
      while(cursor.hasNext()) {
        student = cursor.next();
        studentId = student.getInteger("_id");
        scores = (List<Document>)student.get("scores");
        
        // search for lowest homework score.
        lowestScoreDocument = scores.stream()
              .filter(s -> "homework".equals(s.getString("type")))
              .min( Comparator.comparingDouble( s -> s.getDouble("score") ) );
        
        if(lowestScoreDocument.isPresent()) {
          scores.remove( lowestScoreDocument.get() );
          
          long modifiedDocuments = students.replaceOne(new Document("_id", studentId), student).getModifiedCount();
          
          System.out.println("Modificado " + modifiedDocuments + " documento, " + student.toJson());
        }
      }
    } finally {
      cursor.close();
    }
    
    System.out.println("Finalizando aplicación con " + students.count() + " documentos" );
    conex.close();
  }
  
}
