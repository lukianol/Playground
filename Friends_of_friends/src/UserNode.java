import java.util.HashMap;

public class UserNode extends User {
	public UserNode(User user)
	{
		this.id = user.id;
		this.name = user.name;
	}
	public HashMap<String, UserNode> connections = new HashMap<String, UserNode>();
	public boolean connectionsLoaded;
}
