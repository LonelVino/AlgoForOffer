#include<iostream>
using namespace std;

int main() {
    int n, res=0; 
    cin >> n;
    while (n > 1) {
        if (n % 2 == 1) res++;
        n /= 2;
    }
    if (n == 1) res++;
    cout << res << endl;
    return 0;
}