//Edge Detector convolution 2D application code
//Author: Rohit Sahu, Date: 3/5/2019, Iowa State University
// Algorithm adapted from Professor Andrew NG's Convolutional Neural Networks course: https://www.coursera.org/learn/convolutional-neural-networks


#include <stdio.h>
#define n 6 // Here n decides the size of input grayscale image i.e. n*n
#define k 3 // Here k decides the size of convolution kernel which is k*k
//#define HW_MULT// This line is defined only when there is an actual hardware multiplier present

//This function emulates float multiplication using integer operations for this specific application of vertical edge detection
float multiply(float a,float b){
//No operation if either of the operand is 0
if(a==0|b==0)
    return 0;

//Extracting the integer mantissa, exponent and sign values as per IEEE 754 Floating point format
union Data {
   struct {
		unsigned int mantissa : 23;
		unsigned int exponent : 8;
		unsigned int sign : 1;
	} raw;
	float f;
};


   union Data data1;
   union Data data2;
   union Data data3;
   data1.f = a;
   data2.f = b;
   data3.raw.exponent = data1.raw.exponent + data2.raw.exponent - 127;// Exponents are added and a bias of 127 is subtracted as per IEEE 754
   data3.raw.mantissa = data1.raw.mantissa;// since only the first operand is a double digit 10.0 so has a non zero mantissa and the second operand is either 1.0,-1.0 or 0.0 always has a mantissa of zero
   data3.raw.sign= data1.raw.sign^data2.raw.sign;


   return data3.f;
}


//input image for convolution
float Image[n][n]={

{10.0,10.0,10.0,0,0,0},
{10.0,10.0,10.0,0,0,0},
{10.0,10.0,10.0,0,0,0},
{10.0,10.0,10.0,0,0,0},
{10.0,10.0,10.0,0,0,0},
{10.0,10.0,10.0,0,0,0}
};

//output array which holds the result of convolution is a 4*4 array
float Output[n-k+1][n-k+1]= {
{ 0.0,0.0,0.0,0.0 },
{ 0.0,0.0,0.0,0.0},
{ 0.0,0.0,0.0,0.0 },
{ 0.0,0.0,0.0,0.0 }
};

//Convolution kernel here is 3*3 and it detects a vertical edge
float kernel[k][k]={
{1.0,0,-1.0},

{1.0,0,-1.0},

{1.0,0,-1.0}
};

int main()
{

  for(int i=0;i<n-k+1;i++){
    for(int j=0;j<n-k+1;j++){
      for(int l=i;l<k+i;l++){
        for(int m=j;m<k+j;m++){
#ifdef HW_MULT
          Output[i][j] += Image[l][m]*kernel[l-i][m-j];

#else
          Output[i][j] += multiply(Image[l][m],kernel[l-i][m-j]);

#endif
        }
      }
    }
  }

}
