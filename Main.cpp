#include <bits/stdc++.h>

using namespace std;

int main(){
 string s;
 cin>>s;

 string temp = s;
 reverse(temp.begin(),temp.end());

 cout<<((temp == s) ? "NO" : "YES");
 return 0; 
}