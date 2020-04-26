#include <stdio.h>
#include <string.h>

void deleteCharsIfExistsInSampleString(char s1[], char s2[]);

int main(){
  char name[] = "momomomomomommmoz";
  deleteCharsIfExistsInSampleString(name, "mo");
  printf("%s", name);
  return 0;
}


void deleteCharsIfExistsInSampleString(char s1[], char s2[]){
  int i = 0;
  int s2Len = strlen(s2);
  
  while( i < s2Len){
    char curChar, charToCheck;
    charToCheck = s2[i];
    int curIndex, nextIndexToFill;
    curIndex = nextIndexToFill = 0;

    while((curChar = s1[curIndex]) != '\0'){
      
      if(curChar != charToCheck){
	s1[nextIndexToFill++] = s1[curIndex];
      }

      curIndex++;
    }

    s1[nextIndexToFill] = '\0';

    i++;
  }
}

