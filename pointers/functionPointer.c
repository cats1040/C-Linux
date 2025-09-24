#include <stdio.h>
#include <stdlib.h>

int intCmp(void *a, void *b) {
    int x = atoi(a);
    int y = atoi(b);
    return x - y;
}

int charCmp(void *a, void *b) {
    char x = (char)(a);
    char y = (char)(b);
    return x - y; 
}

void swap(void* a[], int i, int j) {
    void *t = a[i];
    a[i] = a[j];
    a[j] = t;
}

void selectionSort(void *a[], int len, int(*cmp)(void *, void *)) {
    for (int i = 0; i < len; i++) {
        int min = i;
        for (int j = i; j < len; j++) {
            if ((*cmp)(a[i], a[j]) < 0) {
                min = j;
            }
        }
        swap(a, i, min);
    }
} 

int main() {
    return 0;
}
