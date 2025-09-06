/**
 * Print -1 if n < 0
 * Print 0 if n == 0
 * Print 1 if n > 0
 */

#include <stdio.h>

int sign(int n) {
    return ((n>>31) | (!!n));
}

int main() {
    int n;
    scanf("%i", &n);

    int ans = sign(n);
   
    printf("%i", ans);

    return 0;
}
