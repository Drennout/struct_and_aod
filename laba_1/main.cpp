#include <iostream>
#include "string.h"
#include "queue"
#include "stack"
using namespace std;

int priority(char s){
    switch (s) {
        case '+':
            return 1;
            break;
        case '-':
            return 1;
            break;
        case '*':
            return 2;
            break;
        case '/':
            return 2;
            break;
        case '(':
            return 0;
            break;
        case ')':
            return 0;
            break;
    }
}

bool  isOper(char s){
    switch (s) {
        case '+':
            return true;
            break;
        case '-':
            return true;
            break;
        case '*':
            return true;
            break;
        case '/':
            return true;
            break;
        case '(':
            return true;
            break;
        case ')':
            return true;
            break;
        default:
            return false;
            break;
    }
}

int main() {
    queue<char> result; //очередь для записи в постфиксную нотацию
    stack<char> temp;// стек для хранения операций

    string line, tmp;
    cin >> line;

    // инвентируем строку
    tmp = line;
    int i = line.size() - 1;
    int j = 0;
    while(i != -1) {
        line[i--] = tmp[j++];
    }
    for (i = 0; i < line.size(); ++i) {
        if(line[i] == '('){
            line[i] = ')';
        continue;
    }
        if (line[i] == ')')
            line[i] = '(';
    }

    //алгоритм перевода из инфиксной в постфиксную

    line = '(' + line + ')';

    for (i = 0; i < line.size(); i++){
        if (isdigit(line[i]))
            result.push(line[i]);
        else if (line[i] == '(')
            temp.push(line[i]);
        else if(line[i] == ')'){
            while(temp.top() != '('){
                result.push(temp.top());
                temp.pop();
            }
            temp.pop();
        }
        else{
            if(isOper(temp.top())){
                while(priority(line[i]) <= priority(temp.top())){
                    result.push(temp.top());
                    temp.pop();
                }
                temp.push(line[i]);
            }
        }
    }
    string res = "";
    while (!result.empty()){
        res+=result.front();
        result.pop();
    }
    for (i = res.size() - 1; i >= 0; i--)
        cout << res[i];
    return 0;
}
