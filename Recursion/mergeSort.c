#include <stdio.h>
#include <stdlib.h>

void merge(int *arr, int *aux, int l, int mid, int r) {
    int i = l, j = mid + 1;

    for (int k = l; k < r + 1; k++) {
        if (i > mid) {
            aux[k] = arr[j];
            j++;
        }
        else if (j > r) {
            aux[k] = arr[i];
            i++;
        }
        else if (arr[i] > arr[j]) {
            aux[k] = arr[j];
            j++;
        }
        else {
            aux[k] = arr[i];
            i++;
        }
    }

    for (int k = l; k < r + 1; k++) {
        arr[k] = aux[k];
    }

    return;
}

void mergeSort(int *arr, int *aux,int l, int r) {
    if (l >= r) return;

    int mid = l + (r - l) / 2;
    
    mergeSort(arr, aux, l, mid);
    mergeSort(arr, aux, mid + 1, r);

    merge(arr, aux, l, mid, r);
    
    return;
}

void print(int *arr, int n) {
    for (int i = 0; i < n; i++) {
        printf("%i ", arr[i]);
    }
    printf("\n");

    return;
}

int main() {
    int arr[] = {9, 49, 8, 0, 55, -1, 10};
    int n = sizeof(arr) / sizeof(int);
    int *aux = (int *)malloc(sizeof(int) * n);

    mergeSort(arr, aux, 0, n - 1);
    
    print(arr, n);    

    return 0;
}
