#include <iostream>
#include <map>
#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

int main() {
    int num; cin >> num;
    map<int, int> my_map;
    for (int i = 0; i < num; i++) {
        int key; cin >> key;
        int val; cin >> val;
        if ((my_map.find(key)) != my_map.end()) my_map[key] += val;
        else my_map[key] = val;
    }
    vector<int> keys;
    for (auto const& element: my_map) {
        keys.push_back(element.first);
    }
    sort(keys.begin(), keys.end());
    for (int i: keys) {
        cout << i <<  ' ' << my_map[i] << endl;
    }

}