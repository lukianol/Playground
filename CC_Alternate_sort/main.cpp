#include <iostream>
#include <vector>

using namespace std;


bool isEven(int number){
    return (number % 2) == 0;
}

void swap(vector<int> *numbers, int i1, int i2){
    if (i1 == i2){
        return;
    }

    int tmp = (*numbers)[i2];
    (*numbers)[i2] = (*numbers)[i1];
    (*numbers)[i1] = tmp;
}

int random(int min, int max) {
    srand((int)time(0));
    int value = rand();
    int diff = max - min;
    if (diff == 0) {
        return min;
    } else {
        return (value % diff) + min;
    }
}

int partition(vector<int> *numbers, int vStart, int vEnd, int shift){
    int vPivotIx = random(vStart, vEnd);

    int pivot = (*numbers)[shift + vPivotIx];
    swap(numbers, vEnd + shift, vPivotIx + shift);
    int vWall = vStart;

    for (int vCursor = vStart; vCursor < vEnd; vCursor++) {
        int value = (*numbers)[vCursor + shift];
        if (value < pivot) {
            swap(numbers, vWall + shift, vCursor + shift);
            vWall++;
        }
    }

    swap(numbers, vWall + shift, vEnd + shift);
    return vWall;
}

int quickSelect(vector<int> *numbers, int vStart, int vEnd, int vSmallestIth, int shift) {
    if (vStart > vEnd) {
        throw "error!";
    }

    int vPivotIx = partition(numbers, vStart, vEnd, shift);
    int rPosition;
    if (vPivotIx == vSmallestIth) {
        rPosition = vPivotIx + shift;
    } else if (vPivotIx > vSmallestIth) {
        rPosition = quickSelect(numbers, vStart, vPivotIx - 1, vSmallestIth, shift);
    } else {
        rPosition = quickSelect(numbers, vPivotIx + 1, vEnd, vSmallestIth, shift);
    }
    return rPosition;
}

void sortAlternate(vector<int> *numbers) {
    if (numbers == NULL) {
        return;
    }

    int size = numbers->size();

    if (size < 2) {
        return;
    }

    int lastIndex = size - 1;

    int endIndex = lastIndex;
    int startIndex = 0;
    for(int i = 0; i < lastIndex; i++) {
        int smallestIth = isEven(i) ? lastIndex - i : 0;
        int position = quickSelect(numbers, 0, lastIndex - i, smallestIth, i);
        swap(numbers, position, i);
    }
}

int main(int argc, char *argv[])
{
    vector<int> numbers;
    numbers.push_back(2);
    numbers.push_back(4);
    numbers.push_back(3);
    numbers.push_back(5);
    numbers.push_back(1);

    sortAlternate(&numbers);

    for(int i = 0; i < numbers.size(); i++) {
        cout << numbers.at(i);
    }

    cout << endl;

    return 0;
}
