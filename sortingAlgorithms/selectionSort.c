#include <stdio.h>

void selectionSort(int *arr, int n) {
    for (int i = 0; i < n; i++) {
        int mini = i;

        // Find the smallest in [i, n]
        for (int j = i; j < n; j++) {
            if (arr[mini] > arr[j]) {
                mini = j;
            }    
        }

        int temp = arr[i];
        arr[i] = arr[mini];
        arr[mini] = temp;
    }

    return;
}

void print(int *arr, int n) {
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main() {
    int arr[] = {64, 25, 33, 1, -1, 7};
    int n = sizeof(arr) / sizeof(int);

    selectionSort(arr, n);

    print(arr, n);

    return 0;
}
