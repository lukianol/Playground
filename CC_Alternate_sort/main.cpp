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

int partition(vector<int> *numbers, int start, int end){
    int pivotIx = random(start, end);

    int pivot = (*numbers)[pivotIx];
    swap(numbers, end, pivotIx);
    int wall = start;

    for (int cursor = start; cursor < end; cursor++) {
        int value = (*numbers)[cursor];
        if (value < pivot) {
            swap(numbers, wall, cursor);
            wall++;
        }
    }

    swap(numbers, wall, end);
    return wall;
}

int quickSelect(vector<int> *numbers, int start, int end, int smallestIth) {
    if (start > end) {
        throw "error!";
    }

    int pivotIx = partition(numbers, start, end);

    if (pivotIx == smallestIth) {
        return pivotIx;
    } else if (pivotIx > smallestIth) {
        return quickSelect(numbers, start, pivotIx - 1, smallestIth);
    } else {
        return quickSelect(numbers, pivotIx + 1, end, smallestIth);
    }
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
        int smallestIth = isEven(i) ? endIndex-- : startIndex++;
        int position = quickSelect(numbers, i, lastIndex, smallestIth);
        swap(numbers, position, i);
    }
}

int main(int argc, char *argv[])
{
    vector<int> numbers;
    numbers.push_back(5);
    numbers.push_back(1);
    numbers.push_back(3);
    numbers.push_back(2);
    numbers.push_back(4);


    //numbers.push_back(5);
    //numbers.push_back(1);
    //numbers.push_back(3);
    //numbers.push_back(2);
    //numbers.push_back(4);

    // sortAlternate(&numbers);
    // n - 1
    // 1
    // n - 2


    int position = quickSelect(&numbers, 3, numbers.size() - 1, 3);

    cout << position << endl;

    for(int i = 0; i < numbers.size(); i++) {
        cout << numbers.at(i);
    }

    cout << endl;

    return 0;
}
