#include <iostream>
#include <vector>
#include <iomanip>

using namespace std;

struct Location {
    int column;
    int row;
};

Location* getLocationOfValueInMatrix(std::vector<std::vector<int>> matrix, int search, int &counter) {
    int columnIx = matrix.at(0).size() - 1;
    int rowIx = 0;

    while (columnIx >= 0 && rowIx <= matrix.size()) {
        counter++;
        int value = matrix[columnIx][rowIx];
        if (value == search) {
            Location* location = new Location();
            location->column = columnIx;
            location->row = rowIx;
            return location;
        } else if (value < search) {
            rowIx++;
        } else if (value > search) {
            columnIx--;
        }
    }
    return NULL;
};

void* coalesce(void * a, void * b) {
    return a == NULL ? b : a;
}

Location* getLocationOfValueInMatrixV2(std::vector<std::vector<int>> matrix,
                                       int search,
                                       int rowStart,
                                       int rowEnd,
                                       int columnStart,
                                       int columnEnd,
                                       int &counter) {
    counter++;
    if (rowStart > rowEnd || columnStart > columnEnd) {
        return NULL;
    }

    int min = matrix[rowStart][columnStart];
    int max = matrix[rowEnd][columnEnd];

    Location* location = new Location();

    if (search == min) {
        location->column = columnStart;
        location->row = rowStart;
    } else if (search == max) {
        location->column = columnEnd;
        location->row = rowEnd;
    } else if (search < min || search > max) {
        return NULL;
    } else {
        int midRow = (rowStart + rowEnd) / 2;
        int midColumn = (columnStart + columnEnd) / 2;

        return (Location*)coalesce(getLocationOfValueInMatrixV2(matrix, search, rowStart, midRow, columnStart, midColumn, counter),
                        coalesce(getLocationOfValueInMatrixV2(matrix, search, rowStart, midRow, midColumn + 1, columnEnd, counter),
                                 coalesce(getLocationOfValueInMatrixV2(matrix, search, midRow + 1, rowEnd, columnStart, midColumn, counter),
                                          getLocationOfValueInMatrixV2(matrix, search, midRow + 1, rowEnd, midColumn + 1, columnEnd, counter))));
    }

};

vector<vector<int>> getSampleMatrix(int rows, int columns) {

    vector<vector<int>> matrix(columns, std::vector<int>(rows));
    for(int i = 0; i < columns; i++) {
        for(int j = 0; j < rows; j++) {
            matrix[i][j] = (i + j) * 10;
        }
    }
    return matrix;
};

void printMatrix(vector<vector<int>> matrix, int spotSize) {
    for(int i = 0; i < matrix.size(); i++) {
        for(int j = 0; j < matrix[i].size(); j++) {
            cout << std::setw(spotSize) << matrix[i][j];
        }
        cout << endl;
    }
}

void printLocation(Location * location, int counter) {
    if (location == NULL) {
        cout << "Not found";
    } else {
        cout << "Column: " << location->column << " Row: " << location->row << "\n";
    }
    cout << "Steps: " << counter << endl;
}

int main(int argc, char *argv[])
{
    vector<vector<int>>  matrix = getSampleMatrix(100, 100);
    matrix[0][9] = 91;
    printMatrix(matrix, 5);
    int counter = 0;

    cout << "Naive approach" << endl;
    Location* location = getLocationOfValueInMatrix(matrix, 92, counter);
    printLocation(location,  counter);

    counter = 0;
    cout << "Binary search approach" << endl;
    location = getLocationOfValueInMatrixV2(matrix, 91, 0, matrix.size() - 1, 0, matrix[0].size() - 1, counter);
    printLocation(location, counter);

    return 0;
}



