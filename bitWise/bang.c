#include <stdio.h>

int bang(int x) {
    int ans = ((x>>31)&1 | (~x+1)>>31&1)^1;
    return ans;
}

int main() {
    int n;
    scanf("%i", &n);

    int ans = bang(n);

    printf("%i", ans);

    return 0;
}
