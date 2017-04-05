#include <iostream>
#include <vector>
#include <set>

using namespace std;

/**
 * @brief getSums
 * @param coin1
 * @param coin2
 * @param coin3
 * @return
 * @time O(1)
 * @memory O(1)
 */
set<int> getSums(uint coin1, uint coin2, uint coin3) {
    set<int> results;

    results.insert(0);

    for(int i = 1; i <= 1000; i++) {
        if (results.find(i - coin1) != results.end()
                || results.find(i - coin2) != results.end()
                || results.find(i - coin3) != results.end()) {
            results.insert(i);
        }
    }

    return results;
}

int main(int argc, char *argv[])
{
    set<int> sums = getSums(10, 15, 55);
    set<int>::iterator iterator = sums.begin();

    while(iterator != sums.end()){
        cout << *iterator << endl;
        iterator++;
    }
    return 0;
}


