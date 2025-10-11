/**
 * https://hoangvankhoa.medium.com/introduction-to-multithreading-in-c-c-adf7ffbe045d
 * https://www.geeksforgeeks.org/c/use-posix-semaphores-c/
 */

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

sem_t mutex;

void *helloWorld(void *arg) {
    // wait
    sem_wait(&mutex);
    printf("Entered...\n");

    // critical section
    sleep(4);

    /**
     * if this not done and arg also 
     * is not used naywhere in this
     * function, then warning, unused
     * arg
     */
    (void)(arg);
    printf("Hello Multithreading world\n");

    // signal
    printf("Just Exiting...\n");
    sem_post(&mutex);
    return (void *)0;
}

int main() {
    sem_init(&mutex, 0, 1);

    pthread_t myThread1, myThread2;
    int exitCode = 0;

    exitCode = pthread_create(&myThread1, NULL, &helloWorld, NULL);
    if (exitCode != 0) {
        return exitCode;
    }

    exitCode = pthread_create(&myThread2, NULL, &helloWorld, NULL);
    if (exitCode != 0) {
        return exitCode;
    }

    // wait for a thread to finish
    exitCode = pthread_join(myThread1, NULL);
    if (exitCode != 0) {
        return exitCode;
    }

    exitCode = pthread_join(myThread2, NULL);
    if (exitCode != 0) {
        return exitCode;
    }

    sem_destroy(&mutex);

    return exitCode;
}