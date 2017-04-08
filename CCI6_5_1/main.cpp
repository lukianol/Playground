#include <iostream>
#include <bitset>

using namespace std;

int insertion(uint n, uint m, uint i, uint j) {
    uint allOnes = -1;
    uint leftNMask = allOnes << (j + 1);
    uint rightNMask = (1 << i) - 1;
    uint nMask = leftNMask | rightNMask;
    n = n & nMask;
    uint mMask = (1 << (j - i + 1)) - 1;
    m = m & mMask;
    m = m << i;
    return n | m;
}

int main(int argc, char *argv[])
{
    uint result = insertion(0b10000000000, 0b10011, 2, 6);
    cout << std::bitset<32>(result) << endl;
    return 0;
}
