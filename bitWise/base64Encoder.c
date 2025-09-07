#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void printBits(uint64_t bits) {
    while (bits) {
        if (bits&1) {
            printf("1");
        }
        else {
            printf("0");
        }
        bits = bits >> 1;
    }

    printf("\n");
}

char getValue(int n) {
    if (n < 26) return n + 'A';
    if (n < 52) return n - 26 + 'a';
    if (n < 62) return n - 52 + '0';
    if (n == 62) return '+';
    return '/';
}

char *encodeInParts(char *substr) {
    char *ans = (char *)malloc(5 * sizeof(char));

    uint64_t bits = 0;

    int padding = 16;
    bits = (bits|((int)(substr[0]) << padding));
    padding = 8;
    bits = (bits|((int)(substr[1]) << padding)); 
    padding = 0;
    bits = (bits|((int)(substr[2]) << padding)); 

    // 11010100001001001000001

    // printBits(bits);

    // now combine in group of 6-6 bits
    // get a number such that its last 6 bits are set
    // rest all bits 0, and that number is 63 :)
    int idx = 3;
    while (bits) {
        int num = (bits&63);
        ans[idx] = (char)getValue(num);
        idx--;
        bits = bits >> 6;
    }

    ans[4] = '\0';

    return ans;
}

char *encode(char *str) {
    char *ans = (char *)malloc(1 * sizeof(char));
    ans[0] = '\0';

    // split in groups of 3-3 characters
    char *substr = (char *)malloc(4 * sizeof(char));
    substr[3] = '\0';
    for (int i = 0; i < strlen(str); i++) {
        substr[i%3] = str[i];
        if ((i + 1)%3 == 0) {
            // now split and add to answer
            char *subAns = encodeInParts(substr);
            
            ans = (char *)realloc(ans, strlen(ans) + 5);
            strcat(ans, subAns);

            free(subAns);
        }
    }

    free(substr);
    
    return ans;
}

int main() {
    char str[1000];
    gets(str);

    char *encoded_str = encode(str);
    puts(encoded_str);

    return 0;
}
