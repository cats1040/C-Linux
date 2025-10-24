// Copyright 2025 Shreya Sharma

#include <stdio.h>
#include <stdlib.h>

void swap(int *arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;

    return;
}

void quickSort(int *arr, int l, int r) {
    if (l >= r) return;

    int range = r - l + 1;
    int last_index_pivot = l;

    int random = (rand() % range) + l;
    swap(arr, l, random);
    int pivot = arr[l];

    for (int i = l + 1; i <= r; i++) {
        if (arr[i] < arr[l]) {
            last_index_pivot++;
            // move all the elements < arr[l] to the left
            swap(arr, i, last_index_pivot);
        }
    }

    // move pivot to its correct position
    swap(arr, l, last_index_pivot);

    int nr = last_index_pivot - 1;
    int nl = last_index_pivot + 1;

    // optimized
    while (nr >= l && arr[nr] == pivot) nr--;
    while (nl <= r && arr[nl] == pivot) nl++;

    quickSort(arr, l, nr);
    quickSort(arr, nl, r);

    return; 
}

void print(int *arr, int n) {
    for (int i = 0; i < n; i++) printf("%i ", arr[i]);
    printf("\n");

    return;
}

int main() {
    int arr[] = {6, 3, 5, 8, 99, 23, 1, -1, 0, -100, 33};
    int n = sizeof(arr)/sizeof(n);

    quickSort(arr, 0, n - 1);

    print(arr, n);
    return 0;
}
