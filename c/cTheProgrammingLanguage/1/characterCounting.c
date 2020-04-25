#include <stdio.h>

int countChars(){
  int c, charCount;
  charCount = 0;

  while((c = getchar()) != EOF){
    ++charCount;
  }

  return charCount;
}

int main(){
 int charCount = countChars();
 printf("\nNumber of Characters: %d\n", charCount);

 return 0;
}
