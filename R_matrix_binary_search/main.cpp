#include <iostream>
#include <vector>
#include <assert.h>

using namespace std;


struct Location {
    int x;
    int y;

    bool operator==(const Location&other) {
        return x == other.x && y == other.y;
    }
};

static Location NOT_FOUND = {-1, -1};


/**
 * time complexity log 4/3(MxN). Space complexity - recursie callstack for log 4/3 (MxN) calls
 * @brief find
 * @param matrix
 * @param value
 * @param start
 * @param end
 * @return
 */
Location find(const vector<vector<int>> &matrix, int value, Location start, Location end) {

    if ((start.x > end.x) || (start.y > end.y)) {
        return NOT_FOUND;
    }

    Location mid = { (start.x + end.x) / 2, (start.y + end.y) / 2};
    int pivot = matrix[mid.y][mid.x];
    Location result;

    if (pivot == value) {
        return mid;
    }

    if (start == end) {
        return NOT_FOUND;
    }

    if (pivot > value) {
        result = find(matrix, value, start, {mid.x - 1, end.y});
    } else {
        result = find(matrix, value, {start.x, mid.y + 1}, end);
    }

    if (result == NOT_FOUND) {

        int yAdjust = 0;
        int xAdjust = 0;

        if (start.x == end.x) {
            yAdjust = -1;

            if (pivot < value) {
                return result;
            }
        }

        if (start.y == end.y) {
            xAdjust = 1;

            if (pivot > value) {
                  return result;
            }
        }

        result = find(matrix, value, {mid.x + xAdjust, start.y}, {end.x, mid.y + yAdjust});
    }

    return result;
}


Location find(const vector<vector<int>> &matrix, int value) {
    if (matrix.size() == 0){
        return NOT_FOUND;
    }

    for (int y = 1; y < matrix.size(); y++) {
        if (matrix[y].size() != matrix[y - 1].size()) {
            throw "Invalid matrix";
        }
    }

    return find(matrix, value, {0, 0}, {matrix[0].size() - 1 , matrix.size() - 1});
}

void testMatrix(const vector<vector<int>> &matrix) {
    for (int x = 0; x < matrix[0].size(); x++) {
        for (int y = 0; y < matrix.size(); y++) {
            int searchFor = matrix[y][x];
            Location location = find(matrix, searchFor);
            assert(location.x == x);
            assert(location.y == y);
        }
    }

    Location location = find(matrix, matrix[matrix.size() - 1][matrix[0].size() - 1] + 1);
    assert(location == NOT_FOUND);
    location = find(matrix, matrix[0][0] - 1);
    assert(location == NOT_FOUND);
}

int main(int argc, char *argv[])
{

    vector<vector<int>> matrix1 = {
      {   0,  5,   10,  15},
      {   2,  8,   12,  16},
      {   3,  9,   13,  17},
      { 100,110,  120, 121}
    };

    vector<vector<int>> matrix2 = {
      { 0, 1, 2, 3, 4, 5 }
    };

    vector<vector<int>> matrix3 = {
        {   0 },
        {   2 },
        {   3 },
        { 100 }
    };

    vector<vector<int>> matrix4 = {
        {   0 , 5},
        {   2 , 6}
    };

    vector<vector<int>> matrix5 = {
        {   0 , 5, 8 },
        {   2 , 6, 9 },
        {   3 , 7, 10 }
    };

    testMatrix(matrix1);
    testMatrix(matrix2);
    testMatrix(matrix3);
    testMatrix(matrix4);
    testMatrix(matrix5);

    return 0;
}
