#include <iostream>
#include <vector>

using namespace std;

int findInRotatedArray(vector<int>* array, int search, int start, int end) {

    if (start > end) {
        return -1;
    }

    int mid = (start + end) / 2;
    int midValue = array->at(mid);

    if (midValue == search) {
        return mid;
    }

    if (midValue > array->at(start)) {
        if (search < midValue && search >= array->at(start)) {
            return findInRotatedArray(array, search, start, mid - 1);
        }  else {
            return findInRotatedArray(array, search, mid + 1, end);
        }
    } else if (midValue < array->at(start)) {
        if (search > midValue && search <= array->at(end)) {
            return findInRotatedArray(array, search, mid + 1, end);
        }  else {
            return findInRotatedArray(array, search, start, mid - 1);
        }
    } else if (midValue == array->at(start)) {
        if (midValue != array->at(end)){
            return findInRotatedArray(array, search, mid + 1, end);
        } else {
            int result = findInRotatedArray(array, search, start, mid - 1);
            if (result == -1) {
                result = findInRotatedArray(array, search, mid + 1, end);
            }
            return result;
        }
    }
}


int findInRotatedArray(vector<int>* array, int search) {
    if (array == NULL || array->size() <= 0) {
        return -1;
    }
    return findInRotatedArray(array, search, 0, array->size() - 1);
}


int main(int argc, char *argv[])
{
    vector<int> v = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};

    for(int i = 0; i < v.size(); i++){
        int position = findInRotatedArray(&v, v[i]);
        if  (i != position) {
            throw "Error!";
        }
        cout << "Location of " << v[i] << " is " << i << endl;
    }

    return 0;
}
