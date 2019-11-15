#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>


typedef struct FuckYou {
    int fuck_you;
} FuckYou;

void swap(FuckYou *fuck1, FuckYou *fuck2);

int main(int argc, char *argv[])
{
    printf("Hello world\n");

    FuckYou fuckYou = {
        .fuck_you = 1
    };
    FuckYou fuckYouToo = {
        .fuck_you = 2
    };

    printf("%d:%d\n", fuckYou.fuck_you, fuckYouToo.fuck_you);
    swap(&fuckYou, &fuckYouToo);
    printf("%d:%d\n", fuckYou.fuck_you, fuckYouToo.fuck_you);



}

void swap(FuckYou *fuck1, FuckYou *fuck2){
    FuckYou temp = *fuck1;
    *fuck1 = *fuck2;
    *fuck2 = temp;
}
