#include <stdio.h>

int invert(int x, int p, int n) {
    int mask = ((~((~0)<<n))<<p);
    return (mask^x);    
}

int main() {
    int x, p, n;
    scanf("%i %i %i", &x, &p, &n);

    int ans = invert(x, p, n);

    printf("%i", ans);

    return 0;
}
