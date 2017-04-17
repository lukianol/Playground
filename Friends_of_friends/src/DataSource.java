
public class DataSource {
	User getUser(String userId) {
		User user = new User();
		user.id = userId;
		user.name = userId;
		return user;
    }
    
    User[] getFriends(String userId) throws Exception {
    	if (userId == "alex") {
    		return new User[]{
    		        getUser("dani"), 
    		        getUser("marat"),
    		        getUser("yegor"),
    		        getUser("paulina")
    		};
    	} else if (userId == "dani") {
    		return new User[]{
    		        getUser("alex"), 
    		        getUser("marat"),
    		        getUser("yegor"),
    		        getUser("aika"),
    		        getUser("abtisam"),
    		        getUser("paulina"),
    		        getUser("celeste")
    		};
    	} else if (userId == "yegor") {
    		return new User[]{
    		        getUser("alex"), 
    		        getUser("marat"),
    		        getUser("dani"),
    		};
    	} else if (userId == "marat") {
    		return new User[]{
    		        getUser("alex"), 
    		        getUser("yegor"),
    		        getUser("dani"),
    		};
    	} else if (userId == "abtisam") {
    		return new User[]{
    		        getUser("dani"), 
    		        getUser("aika")
    		};
    	} else if (userId == "aika") {
    		return new User[]{
    		        getUser("dani"), 
    		        getUser("abtisam")
    		};
    	} else if (userId == "paulina") {
    		return new User[]{
    		        getUser("dani"), 
    		        getUser("alex"),
    		        getUser("celeste")
    		};
    	} else if (userId == "celeste") {
    		return new User[]{
    		        getUser("dani"), 
    		        getUser("paulina")
    		};
    	} 
    	
    	throw new Exception("unkown user");    	
    	
    }
}
