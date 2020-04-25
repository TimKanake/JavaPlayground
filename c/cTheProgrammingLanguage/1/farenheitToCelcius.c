#include <stdio.h>

float convertFahrenheitToCelcius(float fahrenheit){
  return (5.0 / 9.0) * (fahrenheit - 32);
}

int main(){
  float fahr, celcius;
  
  printf("fahrenheit\tcelcius\n"); 
  for(fahr = 0; fahr <= 300; fahr += 20){
    printf("%11.0f\t%6.1f\n", fahr, convertFahrenheitToCelcius(fahr));
  }
}


