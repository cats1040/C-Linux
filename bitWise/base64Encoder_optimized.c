#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void encoder(char *input, int len, char *output) {
    char *map = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    int n = 0;
    int i = 0;
    int j = 0;

    while (i < len) {
        n += (input[i++])<<16;
        if (i < len) {
            n += (input[i++])<<8;
        }
        if (i < len) {
            n += (input[i++]);
        }

        // 0xff = 63

        if (i%3 == 0) {
            int n1 = (n>>18)&63;
            int n2 = (n>>12)&63;
            int n3 = (n>>6)&63;
            int n4 = n&63;

            output[j++] = map[n1];
            output[j++] = map[n2];
            output[j++] = map[n3];
            output[j++] = map[n4];

            n = 0;
        }
    }

    if (len%3 == 1) {
        int n1 = (n>>18)&63;
        int n2 = (n>>12)&63;

        output[j++] = map[n1];
        output[j++] = map[n2];
        output[j++] = '=';
        output[j++] = '=';
    }

    if (len%3 == 2) {
        int n1 = (n>>18)&63;
        int n2 = (n>>12)&63;
        int n3 = (n>>6)&63;

        output[j++] = map[n1];
        output[j++] = map[n2];
        output[j++] = map[n3];
        output[j++] = '=';
    }

    return;
}

int main() {
    char *input = "abcd";
    int len = strlen(input);
    char *output = malloc(4 * (len + 2) / 3);

    encoder(input, len, output);

    printf("%s\n", input);
    printf("%s\n", output);

    return 0;
}
