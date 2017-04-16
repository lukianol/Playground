#include <iostream>
#include <vector>
#include <unordered_map>
#include <assert.h>

using namespace std;

class Node {
public:
    Node(string value) {
        this->value = value;
    }

    string value;
    vector<Node*> connections;

    bool operator==(const Node &other) const {
        return this->value == other.value;
    }
};

namespace std {

  template <>
  struct hash<Node>
  {
    std::size_t operator()(const Node& k) const
    {
      using std::size_t;
      using std::hash;
      return hash<string>()(k.value);
    }
  };
}

class Graph {
public:
    vector<Node*> nodes;

    void addConnection(Node &n1, Node &n2) {
        n1.connections.push_back(&n2);
        n2.connections.push_back(&n1);
    }
};

/*
 * Time complexity: O(V+2E) = O(V + E) Space complexity O(V)
 */

bool isBiparitate(const Node &node, bool isRed, unordered_map<Node, bool> &markers) {
    unordered_map<Node, bool>::const_iterator position = markers.find(node);
    if (position == markers.end()) {

        markers.insert({node, isRed});

        bool newRed = !isRed;

        for(int i = 0; i < node.connections.size(); i++) {
            if (!isBiparitate(*node.connections[i], newRed, markers)){
                return false;
            }
        }

        return true;

    } else {
        return (position->second == isRed);
    }
}

bool isBiparitate(const Graph &graph) {

    unordered_map<Node, bool> markers; // true - red, false -balck

    for(int i = 0; i < graph.nodes.size(); i++) {
       Node* node = graph.nodes[i];
       if (markers.find(*node) == markers.end()) {
           if (!isBiparitate(*node, true, markers)) {
               return false;
           }
       }
    }

    return true;
}

int main(int argc, char *argv[])
{
    Graph graph;

    Node a = Node("a");
    Node b = Node("b");
    Node c = Node("c");
    Node d = Node("d");

    graph.nodes.push_back(&a);
    graph.nodes.push_back(&b);
    graph.nodes.push_back(&c);
    graph.nodes.push_back(&d);

    graph.addConnection(a, b);
    graph.addConnection(b, c);
    graph.addConnection(c, d);
    graph.addConnection(d, a);

    assert(isBiparitate(graph) == true);

    graph.addConnection(a, c);

   // assert(isBiparitate(graph) == false);

    return 0;
}
