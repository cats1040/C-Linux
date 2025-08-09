#include <stdio.h>

/**
* Function to calculte sum of first n even Fibonacci numbers
* 
* @return sum of first n even Fibonacci numbers
*/
int sumEvenFibonacci(int n) {
    if (n <= 0) return 0;
    if (n == 1) return 2;
    if (n == 2) return 8;
    
    int sum = 0;
    int a = 2, b = 8;

    sum += a + b;

    for (int i = 0; i < n - 2; i++) {
        int c = a + (4 * b);
        sum += c;
        a = b;
        b = c;
    }

    return sum;
}

int main() {
    int n;
    scanf("%i", &n);

    int sum = sumEvenFibonacci(n);
    
    printf("%i\n", sum);    

    return 0;
}
