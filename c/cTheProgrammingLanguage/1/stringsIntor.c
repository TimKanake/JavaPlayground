#include <stdio.h>
#include <string.h>

#define MAXLINE 1000 /* Max String Input Size */

int getLine(char line[], int longestLine);
void copy(char to[], char from[]);
void reverseString(char s[]);
void readLinesAndPrintLongest();

int main(){
  char name[] = "Timothy";
  reverseString(name);
  printf("%s", name);  
  return 0;
}

void readLinesAndPrintLongest(){
  int len, maxStringLen;
  char line[MAXLINE];
  char longest[MAXLINE];

  maxStringLen = 0;

  while((len = getLine(line, MAXLINE)) > 0){
    if(len > maxStringLen){
      maxStringLen = len;
      copy(longest, line);
    }
  }

  if(maxStringLen > 0){
    printf("%s", longest);
  }
}

/* Read line into line and return length of line */
int getLine(char line[], int longestLine){
  int c, i;

  for(i = 0; i < longestLine - 1 && (c = getchar()) != EOF && c != '\n'; ++i){
    line[i] = c;
  }

  if (c == '\n') {
    line[i++] = c;
  }

  line[i] = '\0';

  return i;
}

/* copy from from into to; assumes buffer is big enough*/
void copy(char to[], char from[]){
  int i = 0;

  while ((to[i] = from[i]) != '\0'){
    ++i;
  }
}

void reverseString(char s[]){
  int lenOfString;
  char ch;
  
  // count the length of the string
  for(lenOfString = 0; s[lenOfString] != '\0'; ++lenOfString){};
  --lenOfString;

  // reverse
  int i;
  for(i = 0; i < lenOfString; ++i){
    char curChar = s[lenOfString];
    s[lenOfString] = s[i];
    s[i] = curChar;
    --lenOfString;
  }
}
