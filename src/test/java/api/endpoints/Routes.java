package api.endpoints;

public class Routes {
	
	//User Module  icin eklenen urller
	
  //We make it public so we can access everywhere in the project.
  //Also I make it static so that I can access this variable directly by using
  // class name without using any object.base_url ile topluyoruz.
	

	public static String base_url="https://petstore.swagger.io/v2" ;
	
	//User module
	
	public static String post_url=base_url+"/user";
    public static String get_url=base_url+"/user/{username}";
    public static String update_url=base_url+"/user/{username}";
    public static String delete_url=base_url+"/user/{username}";
    
    //Store module
    
		//Here you will create Store module URL's

    //Pet module
    		//Here you will create Pet module URL's
    
	
	
}
