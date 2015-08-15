package juanco.m101.spark;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloSparkWeb {

	public static void main(String[] args) {
		
		Spark.get("/hello", new Route() {
			
			@Override
			public Object handle(Request request, Response response) throws Exception {
				return "Hello World Spark Web";
			}
		});
		
		Spark.get("/hello8", (req, res) -> "Hello Spark with J8");
	}

}
