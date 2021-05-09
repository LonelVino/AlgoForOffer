#include<iostream>
using namespace std;

int main() {
    float num;
    cin >> num;
    if (num*10. - int(num)*10 >= 5) {
        cout << int(num) + 1 << endl;
    } else {
        cout << int(num) << endl;
    }
    return 0;
}