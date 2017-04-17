
public class CommonFriendsService {
	private DataService _dataService;
	
	public CommonFriendsService(DataService dataService){
		this._dataService = dataService;
	}
	// O(C1 + C2)
	public int numberOfFriendsInCommon(String user1id, String user2id, SocialGraph graph) throws Exception {
		UserNode user1 = this._dataService.loadUserNode(user1id, graph);
		UserNode user2 = this._dataService.loadUserNode(user2id, graph);
        this._dataService.loadUserNodeConnections(user1, graph);
        this._dataService.loadUserNodeConnections(user2, graph);
        return numberOfFriendsInCommon(user1, user2);
	}
	
	// O(C1)
	public int numberOfFriendsInCommon(UserNode user1, UserNode user2) throws Exception {
        int counter = 0;
        for(UserNode connection:user1.connections.values()) {
            if (user2.connections.containsKey(connection.id)) {
                counter++;
            }
        }            
        return counter;
	}
}
