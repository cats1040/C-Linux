// Copyright 2025 Shreya Sharma

#include <stdio.h>
#include <stdlib.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;

    return;
}

void sort(int *arr, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = i; j > 0; j--) {
            if (arr[j] < arr[j - 1]) {
                swap(&arr[j], &arr[j - 1]);
            }
        }
    }

    return;
}

void print(int *arr, int n) {
    for (int i = 0; i < n; i++) {
        printf("%i ", arr[i]);
    }
    printf("\n");
}

int main() {
    int arr[] = {1, 4, 2, 88, 9, -1, 44, 0};
    int n = sizeof(arr) / sizeof(int);

    sort(arr, n);

    print(arr, n);

    return 0;
}
