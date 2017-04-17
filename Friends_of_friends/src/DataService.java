
public class DataService {
	private DataSource _source;

    public DataService(DataSource source) {
        this._source = source;
    }
    
    // O(1)
    public UserNode loadUserNode(String userId, SocialGraph graph) {
    	if (graph.userNodes.containsKey(userId)) {
    		return graph.userNodes.get(userId);
    	}
    	User user = this._source.getUser(userId);
    	return loadUser(user, graph);
    }
    
    // O(C)
    public void loadUserNodeConnections(UserNode user, SocialGraph graph) throws Exception {    	
      	
    	if (!graph.userNodes.containsKey(user.id)) {
    		throw new Exception("node doesn't belong to the graph");
    	}
    	
    	if (user.connectionsLoaded) {
    		return;
    	}
    	
    	User[] connections = this._source.getFriends(user.id);
    	
    	for(User connection : connections) {
    		UserNode connectionNode;
    		if (!graph.userNodes.containsKey(connection.id)){
    			connectionNode = this.loadUser(connection, graph);
    		} else {
    			connectionNode = graph.userNodes.get(connection.id);
    		}
    		
    		user.connections.putIfAbsent(connection.id, connectionNode);
    		connectionNode.connections.putIfAbsent(user.id, user);
    	}
    }
    
    private UserNode loadUser(User user, SocialGraph graph) {
    	UserNode node = new UserNode(user);
    	graph.userNodes.put(node.id, node);
    	return node;
    }
    
}