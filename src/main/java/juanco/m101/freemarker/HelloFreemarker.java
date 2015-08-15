package juanco.m101.freemarker;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloFreemarker {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloFreemarker.class, "/");
		
		try {
			Template helloTemplate = configuration.getTemplate("hello.html");
			StringWriter writer = new StringWriter();
			Map<String, Object> helloMap = new HashMap<>();
			helloMap.put("name", "Juan Carlos");
			
			helloTemplate.process(helloMap, writer);
			
			System.out.println(writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
