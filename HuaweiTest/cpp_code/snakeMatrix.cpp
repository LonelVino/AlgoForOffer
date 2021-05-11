#include <iostream>
using namespace std;

int main() {
    int n; cin >> n;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j < n-(i-1); j++) {
            cout << ((j + i - 1)*(j + i - 1) + (j + i - 1))/2 - (i - 1) << " ";
        }
        cout << (n*n + n)/2 - (i-1)<< endl;
    }
    return 0;
}