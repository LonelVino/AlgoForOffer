#include<bits/stdc++.h>
using namespace std;

int main() {
    string str; cin << str;
    unordered_set<char> set;
    for (char c: str) 
        if (c >= 0 && c <= 127) set.insert(c);
    cout << set.size() << endl;
}