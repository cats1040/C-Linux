#include <bits/stdc++.h>

using namespace std;

void towerOfHanoi(int n, char s, char h, char d)
{
    if (n == 0)
    {
        return;
    }

    if (n == 1)
    {
        cout << "Move " << n << " from " << s << " to " << d << "\n";
        return;
    }

    towerOfHanoi(n - 1, s, d, h);
    cout << "Move " << n << " from " << s << " to " << d << "\n";
    towerOfHanoi(n - 1, h, s, d);
}

int main()
{
    int n;
    cin >> n;

    towerOfHanoi(n, 'S', 'H', 'D');

    return 0;
}