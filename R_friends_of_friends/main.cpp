#include <iostream>
#include <unordered_map>
#include <string>
#include <vector>

using namespace std;

struct UserDto {
    string id;
    string name;
};

UserDto getUser(string userId) {

}

vector<UserDto> getFriends(string userId) {

}

class User {
    public: User(UserDto dto) {
        this->id = dto.id;
        this->name = dto.name;
    }

    string id;
    string name;
    unordered_map<string, User> friendships;
};

struct SocialGraph {
    unordered_map<string, User> users;
};

void addFriendsToGraph(SocialGraph graph, string userId) {
    vector<UserDto> friends = getFriends(userId);
    for(int i = 0; i < friends.size(); i++) {
        UserDto user = friends[i];
        graph.users.insert(user.id, User(user));
    }
}



vector<string> findCommonFriends(string userId1, string userId2) {
    SocialGraph graph = SocialGraph();
    addFriendsToGraph(graph, userId1);
    addFriendsToGraph(graph, userId2);
}

int main(int argc, char *argv[])
{
    findCommonFriends("alex", "dani");
    cout << "Hello World!" << endl;
    return 0;
}
