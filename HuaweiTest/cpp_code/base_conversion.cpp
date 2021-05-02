#include<iostream>
#include<string>
#include<cmath>
using namespace std;
int main() {
    string s;
    while (cin>>s) {
        int bit;
        int ans;
        for (int i = s.length() - 1; i > 1; i--) {
            if (s[i] <= '9' && s[i] >= '0') {
                ans += (s[i] - '0') * pow(16, bit++);
            } else if (s[i] <= 'F' && s[i] >= 'A') {
                ans += (s[i] - 'A' + 10) * pow(16, bit++);
            }
        }
        cout << ans <<endl;
    }
    return 0;
}