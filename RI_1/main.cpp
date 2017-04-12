#include <iostream>
#include <vector>

using namespace std;

struct Node {
    int value;
    Node* left;
    Node* right;
};


Node* _build(vector<int> &preorder, vector<int> &inorder, int &pix, int start, int end) {

    if (pix >= preorder.size()) {
        return NULL;
    }

    if (start > end) {
        return NULL;
    }

    int value = preorder[pix];
    pix = pix + 1;

    int location;

    for(int i = start; i<=end; i++) {
        if (inorder[i] == value){
            location = value;
            break;
        }
    }

    Node* node = new Node();
    node->value = value;
    node->left = _build(preorder, inorder, pix, start, location - 1);
    node->right = _build(preorder, inorder, pix, location + 1, end);
    return node;
}

Node* build (vector<int> &preorder, vector<int> &inorder){
    int pix = 0;
    return _build(preorder, inorder, pix, 0, inorder.size() - 1);
}

int main(int argc, char *argv[])
{
    vector<int> preorder = {1, 3, 2};
    vector<int> inorder = {2, 3, 1};
    Node* node = build(preorder, inorder);

    return 0;
}
