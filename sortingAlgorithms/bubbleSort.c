#include <stdio.h>

void bubbleSort(int *arr, int n) {
    for (int i = 0; i < n; i++) {
        int flag = 0;
        for (int j = 0; j < n - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;

                flag = 1;
            }
        }

        if (flag == 0) {
            break;
        }
    }

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
    int arr[] = {4, 9, -100, -2, 0, 1, 22, 3, 1};
    int n = sizeof(arr) / sizeof(arr[0]);

    bubbleSort(arr, n);

    print(arr, n);
}
