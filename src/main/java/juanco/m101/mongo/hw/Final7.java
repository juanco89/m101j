package juanco.m101.mongo.hw;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class Final7 {

  
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    /*
    When you are done removing the orphan images from the collection, 
    there should be 89,737 documents in the images collection.

    Hint: you might consider creating an index or two or your program will take 
    a long time to run. As as a sanity check, there are 49,887 images that are 
    tagged 'sunrises' before you remove the images.
    
    What are the total number of images with the tag "sunrises" after the removal of orphans?
    */
    
    MongoClient conex = new MongoClient("localhost", 27017);
    MongoCollection<Document> images = conex.getDatabase("test").getCollection("images");
    MongoCollection<Document> albums = conex.getDatabase("test").getCollection("albums");
    
    System.out.println("Total Albums: " + albums.count() );
    System.out.println("Total images: " + images.count() );
    
    System.out.println("Total SUNRISES images: " + images.count(new Document("tags", "sunrises")) );
    
    // create index over images: 1
    // db.albums.find( { images: ImageId } );
    
    MongoCursor<Document> cursor = images.find().iterator();
    try {
      List<Document> containerAlbums;
      Document currentImage;
      
      while(cursor.hasNext()) {
        currentImage = cursor.next();
        
        containerAlbums = albums.find( new Document("images", currentImage.getInteger("_id" )) )
                                .into(new ArrayList<>());
        
        if(containerAlbums == null || containerAlbums.isEmpty()) {
          System.out.println("Encontrada imagen huérfana " + currentImage.getInteger("_id") + " - " + currentImage.toJson());
          images.deleteOne(currentImage);
        }
      }
    } finally {
      cursor.close();
    }
    
    System.out.println("Finalizando aplicación con " + images.count() + " imagenes" );
    System.out.println("Finalizando apliación con " + images.count(new Document("tags", "sunrises")) + " SUNRISES" );
    conex.close();
  }
}
