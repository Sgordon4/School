#include <stdlib.h>
#include <stdio.h>
#include<time.h>

#define N 1000
#define M 1000
#define I 1000


void scale(int n, int m, int array[n][m], int scale) {
	// TODO: Complete me!
	for(int i = 0; i < n; i++){
		for(int j = 0; j < m; j++){
			array[i][j] = array[i][j] * scale;
		}
	}
	return;
}

int main(int argc, char **argv) {


  int array[N][M];

  for (int i=0; i<N; i++) {
    for (int j=0; j<M; j++) {
      array[i][j] = i*N+j;
    }
  }

	

  clock_t start = clock(); // START EXPERIMENT

  for (int i=0; i<I; i++) {
    scale(N,M,array,100);
  }

  clock_t end = clock(); // END EXPERIMENT
  clock_t total_cycles = (end - start);
  double total_time = ((double)total_cycles)/CLOCKS_PER_SEC;
  printf("Total time (s) is %f\n",total_time);
}

