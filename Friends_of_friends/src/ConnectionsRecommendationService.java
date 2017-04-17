import java.util.LinkedList;

public class ConnectionsRecommendationService {
	private DataService _dataService;
	private CommonFriendsService _commonFriendsService;
	
	ConnectionsRecommendationService(DataService dataService, CommonFriendsService commonFriendsService) {
		this._dataService = dataService;
		this._commonFriendsService = commonFriendsService;
	}
	
	// O(C^2 + U*C + R) = O(C^3 + R)
	public LinkedList<UserNode> getTopRecommendations(String userId, int top, SocialGraph graph) throws Exception {

		UserNode user = this.loadUserAndHisFriends(userId, graph);
		this.loadFriendsOfFriends(user, graph);		
		LinkedList<UserNode>[] recommendations = getAllRecommendations(user, graph);
		return selectTopRecommendations(recommendations, top);		
    }
	// O(R)
	private LinkedList<UserNode> selectTopRecommendations(LinkedList<UserNode>[] recommendations, int top ){
		LinkedList<UserNode> topRecommendations = new LinkedList<UserNode>();
		for(int i = recommendations.length - 1; i >= 0; i--) {
			LinkedList<UserNode> bucket = recommendations[i];
			if (bucket == null) {
				continue;
			}
			for (int j = 0; j < bucket.size(); j++) {
				topRecommendations.add(bucket.get(j));
				if (topRecommendations.size() == top) {
					return topRecommendations;
				}
			}
		}        
        return topRecommendations;
	}
	
	// O(U*C)
	private LinkedList<UserNode>[] getAllRecommendations(UserNode user, SocialGraph graph) throws Exception	{
		LinkedList<UserNode>[] recommendations = new LinkedList[user.connections.size() + 1];
		
        for(UserNode node : graph.userNodes.values()) {
            if (node.id != user.id && !user.connections.containsKey(node.id)) {
                int number = this._commonFriendsService.numberOfFriendsInCommon(node, user);
                if (recommendations[number] == null) {
                	recommendations[number] = new LinkedList<UserNode>();
                }
                recommendations[number].add(node);
            }
        }
        return recommendations;		
	}
	
	
	// O(C)
	private UserNode loadUserAndHisFriends(String userId, SocialGraph graph) throws Exception{
        UserNode userNode = this._dataService.loadUserNode(userId, graph);
        this._dataService.loadUserNodeConnections(userNode, graph);
        return userNode;
	}
	
	// O(C^2))
	private void loadFriendsOfFriends(UserNode user, SocialGraph graph) throws Exception{
        for(UserNode connection : user.connections.values()) {
        	this._dataService.loadUserNodeConnections(connection, graph);              
        }
	}
}
