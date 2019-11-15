#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>



struct Node {
    int data;
    struct Node* next;
};



struct Node *newNode(int data, struct Node *next){
    struct Node *temp = NULL;
    temp = (struct Node*)malloc(sizeof(struct Node));

    temp->data = data;
    temp->next = next;

    return temp;
}

struct Node newNode2(int data, struct Node *next){
    struct Node *temp = NULL;
    temp = (struct Node*)malloc(sizeof(struct Node));

    temp->data = data;
    temp->next = next;

    return *temp;
}


int main(int argc, char *argv[]){
    printf("Hello World\n");


    struct Node* head = NULL;
    struct Node* second = NULL;
    struct Node* third = NULL;

    // allocate 3 nodes in the heap
    head = (struct Node*)malloc(sizeof(struct Node));
    second = (struct Node*)malloc(sizeof(struct Node));
    third = (struct Node*)malloc(sizeof(struct Node));

    head->data = 2;
    head->next = second;

    struct Node *oof = newNode(5, NULL);
    struct Node *oof2 = newNode(5, oof);
    printf("Thing: %d\n", oof->data);
    printf("Thing2: %d\n", oof2->next->data);

    struct Node ee = newNode2(7, NULL);
    struct Node aa = newNode2(8, &ee);
    printf("ee: %d\n", ee.data);
    printf("aa: %d\n", aa.next->data);

}
