package juanco.m101.spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloFreemarkerSpark {

  public static void main(String[] args) {
    
    Configuration configuration = new Configuration();
    configuration.setClassForTemplateLoading(HelloFreemarkerSpark.class, "/");
    
    Spark.get("/saludo", (req, res) -> {
      
      StringWriter writer = new StringWriter();
      try {
        Template helloTemplate = configuration.getTemplate("hello.html");
        Map<String, Object> helloMap = new HashMap<>();
        helloMap.put("name", "Juan Carlos");
        
        helloTemplate.process(helloMap, writer);

        System.out.println(writer);
      } catch (Exception e) {
        Spark.halt(500);
        e.printStackTrace();
      }
       return writer;
      
    });
    
  }
}
