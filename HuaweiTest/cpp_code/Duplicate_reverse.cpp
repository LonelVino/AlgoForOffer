#include<iostream>
#include<vector>
using namespace std;

int main(){
    int num, n;
    vector<int> hash(10,0);
    cin >> n;
    while(n > 0) {
        num = n % 10;
        n /= 10;
        if (hash[num] == 0) {
            hash[num] = 1;
            cout << num;
        }
    }
}