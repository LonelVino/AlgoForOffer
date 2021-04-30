// Problem link:
// https://www.nowcoder.com/practice/3245215fffb84b7b81285493eae92ff0?tpId=37&tqId=21226&rp=1&ru=%2Fta%2Fhuawei&qru=%2Fta%2Fhuawei%2Fquestion-ranking&tab=answerKey
#include<iostream>
#include<set>
using namespace std;
int main() {
    int N,n;
    set<int> ss;
    while(cin>>N) {
        ss.clear(); // clear the last one set
        while(N--) {
            cin>>n;
            ss.insert(n);
        }
        for(set<int>::iterator it = ss.begin(); it != ss.end(); it++) {
            cout << *it << endl;
        }
    }
    return 0;
}