#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node {
    int isFree;
    int freeSpace;
    int sizeBlock;
    void *startAdd;

    struct Node* next;
} Node;

// "Constructor" function
Node *createNode(int sizeBlock, Node *startAdd) {
    printf("---- Creating node ----\n");
    
    Node *node = (Node *)malloc(sizeof(Node));
    
    // printf("0\n");

    if (node == NULL) {
        printf("Uhoh! Creation of node failed.\n");
        return NULL;
    }

    // printf("1\n");
    node->sizeBlock = sizeBlock;
    // printf("2\n");
    node->isFree = 1;
    // printf("3\n");
    node->freeSpace = sizeBlock;
    // printf("4\n");
    node->startAdd = startAdd;
    // printf("5\n");
    node->next = NULL;

    printf("---- Node created successfully ----\n");

    return node;
}

void *allocate_firstFit(Node *memory, int size) {
    // First-Fit Memory Allocation

    // search in array of blocks
    // having memory addresses, starting point

    Node *block = memory;

    if (!block) {
        printf("---- NULL ----\n");
        return NULL;   
    }

    while (block) {
        printf("---- Block size: %i ----\n", block->sizeBlock);

        if (block->isFree == 1 && block->sizeBlock >= size) {
            // allocate here
            block->isFree = 0;
            block->freeSpace -= size;
            
            printf("---- Memory allocated! ----\n");

            return block->startAdd;
        }

        block = block->next;
    }

    printf("Uhoh! No free space.\n");

    return NULL;
}

void deallocate(void *ptr, Node *memory) {
    Node *block = memory;

    while (block) {
        if (block->startAdd == ptr) {
            // allocate here
            block->isFree = 1;
            // memoryBlocks[i]->freeSpace;
            
            printf("---- Memory deallocated! ----\n");

            return;
        }

        block = block->next;
    }

    printf("ERROR!\n");
    return;
}

void makeBlocks_fixedSize(void *memory, Node *head, int *processSize, int memorySize, int n) {
    int maxSize = 0;

    printf("---- Making blocks in memory ----\n");

    for (int i = 0; i < n; i++) {
        if (maxSize < *(processSize + i)) maxSize = *(processSize + i); 
    }

    printf("---- Block size: %i ----\n", maxSize);

    int blocks = memorySize / maxSize;
    void *temp = memory;
    Node *tempHead = head; 

    printf("---- Blocks: %i ----\n", blocks);

    for (int i = 0; i < blocks; i++) {
        printf("---- Creating block %i ----\n", i + 1);
        tempHead = createNode(maxSize, temp);

        temp = temp + (maxSize * sizeof(void *));
        
        if (i != blocks - 1) {
            tempHead->next = createNode(0, NULL);
            tempHead = tempHead->next;
        }

        printf("---- Created block %i ----\n", i + 1);
    }

    printf("---- Blocks done! ----\n");

    return;
} 

int main() {
    void *memory = malloc(3000 * sizeof(void *));

    int processSize[] = {30, 89, 77, 99};

    Node *head = NULL;
    void *tempM = memory;

    makeBlocks_fixedSize(tempM, head, processSize, 300, 4);

    Node *temp = head;

    for (int i = 0; i < 4; i++) {
        allocate_firstFit(head, processSize[i]);
        head = temp;
    }

    return 0;
}