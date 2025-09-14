#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void printBits(int bits) {
    char ans[24];
    for (int i = 0; i < 24; i++) ans[i] = '0';
    int idx = 23;
    while (bits) {
        if (bits&1) {
            ans[idx] = '1';
        }
        idx--;
        bits = bits >> 1;
    }
    for (int i = 0; i < 24; i++) {
        printf("%c", ans[i]);
    }

    printf("\n");
}

int rev(char ch) {
    int ans = -1;
    if (ch >='A' && ch <= 'Z') {
        ans = ch - 'A';
    }
    else if (ch >= 'a' && ch <= 'z') {
        ans = ch - 'a' + 26;
    }
    else if (ch >= '0' && ch <= '9') {
        ans = ch - '0' + 52;
    }
    else if (ch == '+') ans = 62;
    else ans = 63;
    
    // printf("%c %i\n", ch, ans);

    return ans;
}

void decode(char *input, int len, char *output) {
    int i = 0;
    int j = 0;
    int n = 0;    

    // write binary representation of the input
    // int 6 bits each, in goups of 4-4
    // then group them in 8-8 bits

    while (i < len) {
        n += (rev(input[i++])&63) << 18;
        if (i < len) n += (rev(input[i++])&63) << 12;
        if (i < len) n += (rev(input[i++])&63) << 6;
        if (i < len) n += (rev(input[i++])&63) << 0;

        if (i%4 == 0) {
            int n1 = (n >> 16) & 255;
            int n2 = (n >> 8) & 255;
            int n3 = (n >> 0) & 255;

            // printf("%i %i %i\n", n1, n2, n3);

            output[j++] = (char)n1;
            output[j++] = (char)n2;
            output[j++] = (char)n3;

            n = 0; 
        } 
    }

    return;
}

int main() {
    char input[1000];
    gets(input);

    int len = strlen(input);
    while (strlen(input)%4 != 0) {
        input[len++] = '=';
    }

    len = strlen(input);

    char output[(len * 6) / 8 + 1];
    decode(input, len, output);

    printf("%s\n", input);
    printf("%s\n", output);
}
