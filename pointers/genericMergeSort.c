#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int intCmp(void *a, void *b) {
    int x = *(int *) a;
    int y = *(int *) b;

    return x - y;
}

void printInt(void *arr, int ele_size, int n) {
    for (int i = 0; i < n; i++) {
        printf("%i ", *(int *)(arr));
        arr = arr + ele_size;
    }
    printf("\n");

    return;
}

void merge(void *arr, void *aux, int ele_size, int l, int mid, int r, int(*cmp)(void *, void *)) {
    int i = l, j = mid + 1;

    void *dst = aux;
    dst = dst + (l*ele_size);

    void *src1 = arr;
    src1 = src1 + (i*ele_size);

    void *src2 = arr;
    src2 = src2 + (j*ele_size);

    // printf("%i src1\n%i src2\n", *(int *)src1, *(int *)src2);
    // printf("%i\n", *(int *)(arr + l*ele_size));

    for (int k = l; k < r + 1; k++) {
        if (i > mid) {
            // now copy next ele_size number of bits
            // strting from arr[p]
            memcpy(dst, src2, ele_size);
            src2 = src2 + ele_size;
            j++;
        }
        else if (j > r) {
            memcpy(dst, src1, ele_size);
            src1 = src1 + ele_size;
            i++;
        }
        else if ((*cmp)(src1, src2) > 0) {
            // printf("%i is greater than %i\n", *(int *)src1, *(int *)src2);
            memcpy(dst, src2, ele_size);
            src2 = src2 + ele_size;
            j++;
        }
        else {
            memcpy(dst, src1, ele_size);
            src1 = src1 + ele_size;
            i++;
        }

        dst = dst + ele_size;
    }

    void *src = aux;
    src = src + (l*ele_size);
    dst = arr + (l*ele_size);

    for (int k = l; k < r + 1; k++) {
        memcpy(dst, src, ele_size);
        
        src = src + ele_size;
        dst = dst + ele_size;
    }

    // print(arr, sizeof(arr));

    return;
}

void mergeSort(void *arr, void *aux, int ele_size, int l, int r, int(*cmp)(void *, void *)) {
    if (l >= r) return;

    int mid = l + (r - l) / 2;
    
    mergeSort(arr, aux, ele_size, l, mid, cmp);
    mergeSort(arr, aux, ele_size, mid + 1, r, cmp);

    merge(arr, aux, ele_size, l, mid, r, cmp);
    
    return;
}

int main() {
    int arr[] = {9, 49, 8, -10, 55, 91, 10};
    int n = sizeof(arr) / sizeof(int);
    int *aux = (int *)malloc(sizeof(int) * n);

    mergeSort(arr, aux, sizeof(arr[0]), 0, n - 1, intCmp);
    
    printInt(arr, sizeof(arr[0]), n);  
    // printf("%i", sizeof(int));

    // // EXPERIMENTING
    // char arr[] = {'a', 'b', 'c', 'd'};
    // void *vPtr = &arr;
    // vPtr = (vPtr + 2);
    // char t = 'p';
    // // change the element that the vPtr is 
    // // pointing to in arr to 'p'
    // memcpy(vPtr, &t, sizeof(t));
    // char val = *(char *)vPtr;
    // printf("%c", val); // p

    return 0;
}

/**
 * DEFERENCING A VOID POINTER
 * int n = 10;
 * void *vPtr = &num;
 * int val = *((int *) vPtr); // 10
 * 
 * VOID POINTER ARITHMETIC
 * int arr[] = {1, 2, 3, 4};
 * void *vPtr = &arr;
 * vPtr = ((int *) vPtr + 1); // same as vPtr = vPtr + sizeof(arr[0]);
 * int val = *((int *) vPtr); // 2
 * 
 * COPYING BYTES, memcpy(destination, source, size)
 */