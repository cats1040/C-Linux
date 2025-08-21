#include <stdio.h>

void swap(int *arr, int i, int j) {
    if (i == j) return;
    int t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;

    return;
}

void quickSort(int *arr, int L, int R) {
    if (L >= R) return;

    int last = L;

    int i;
    for (i = L + 1; i <= R; i++) {
        if (arr[i] < arr[L]) {
            swap(arr, i, ++last);
        }
    }

    swap(arr, L, last);

    quickSort(arr, L, last - 1);
    quickSort(arr, last + 1, R);

    return;
}

void print(int *arr, int n) {
    for (int i = 0; i < n; i++) printf("%i ", arr[i]);
    printf("\n");
}

int main() {
    int arr[] = {44, 2, 1, -1, 0, -100, 33, 2};
    int n = sizeof(arr) / sizeof(int);

    quickSort(arr, 0, n - 1);

    print(arr, n);
}
