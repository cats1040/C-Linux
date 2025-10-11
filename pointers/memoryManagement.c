#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

/**
 * 
 */

typedef struct Node {
    size_t size;
    size_t freeSpace;
    int isFree;
    void *startAddr;
    Node *next;
} Node;

// "Constructor" function
Node *createNode(int size, Node *startAddr) {
    printf("---- Creating node ----\n");
    
    Node *node = (Node *)malloc(sizeof(Node));

    if (node == NULL) {
        printf("Uhoh! Creation of node failed.\n");
        return NULL;
    }

    node->size = size;
    node->isFree = 1;
    node->freeSpace = size;
    node->startAddr = startAddr;
    node->next = NULL;

    printf("---- Node created successfully ----\n");

    return node;
}

void *allocate_firstFit(Node *head, int size) {

}

// void makeBlocks_fix

int main() {
    void *memory = malloc(1000 * sizeof(void *));

    int processSize[] = {30, 70, 80, 20, 99};

    return 0;
}