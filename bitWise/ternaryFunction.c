#include <stdio.h>

int conditional(int x, int y, int z) {
    // all bits set to 1 for non-zeros
    // else all bits set to 0 for 0
    int mask = ~(!(!x)) + 1;
    
    int ans = (mask&y)|((~mask)&z);

    return ans;
}

int main() {
    int x, y, z;
    scanf("%i %i %i", &x, &y,&z);

    int ans = conditional(x, y, z);
    
    printf("%i", ans);

    return 0;
}
