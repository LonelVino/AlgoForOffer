#include<iostream>
using namespace std;

int main() {
    int n;
    cin >> n;
    int i = 2;
    while(i * i <= n) {
        while(n % i == 0) {
            n /= i;
            cout << i << " ";
        }
        i++;
    }
    if (n - 1 != 0) {
        cout << n << " ";
    }
    return 0;
}