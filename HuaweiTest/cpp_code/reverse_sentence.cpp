#include<bits/stdc++.h>
using namespace std;

int main() {
    string str, res;
    while (cin >> str) {
        str += " " + res;
        res = str;
    }
    cout << res << endl;
    return 0;
}