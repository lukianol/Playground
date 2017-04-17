
public class Main {

	public static void main(String[] args) throws Exception {
		DataSource source = new DataSource();
		DataService dataService = new DataService(source);
		CommonFriendsService commonFriends = new CommonFriendsService(dataService);
		ConnectionsRecommendationService recommendations = new ConnectionsRecommendationService(dataService, commonFriends);
		SocialGraph graph = new SocialGraph();
		for(UserNode node : recommendations.getTopRecommendations("paulina", 3, graph)) {
			System.out.printf("[%s, %s]\n", node.id, node.name);
		}
	}

}
