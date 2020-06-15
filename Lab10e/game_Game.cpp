#include "game_Game.h"

int cols[5];
int rows[5];
int diagonal1[9];
int diagonal2[9];
/*
function middleSort(first, second){
    var x = 12.5;
    return Math.abs(first - x) < Math.abs(second - x) ? -1 : 1;
}*/

int calcValue(int my, int enemy){
    return my * my  + enemy * enemy * enemy;
}

void colValues(int x[]){
    for(int i = 0; i < 5; i++){
        int my = 0;
        int enemy = 0;
        for(int j = 0; j < 5; j++){
            if(x[j*5+i] == 2){
                my++;
            }
            else if (x[j*5+i] == 1){
                enemy++;
            }
        }
        cols[i] = calcValue(my, enemy);
    }
}

void rowValues(int x[]){
    for(int i = 0; i < 5; i++){
        int my = 0;
        int enemy = 0;
        for(int j = 0; j < 5; j++){
            if(x[i*5+j] == 2){
                my++;
            }
            else if(x[i*5+j] == 1){
                enemy++;
            }
        }
        rows[i] = calcValue(my, enemy);
    }
}

void calcDiagonal1(int x[]){
    for(int i = 4; i >= 0; i--){
        int tmpR = i;
        int tmpC = 0;
        int my = 0;
        int enemy = 0;
        while(tmpR < 5 && tmpC < 5){
            if(x[tmpR * 5 + tmpC] == 2){
                my++;
            }
            else if (x[tmpR * 5 + tmpC] == 1){
                enemy++;
            }
            tmpR++;
            tmpC++;
        }
        diagonal1[4-i] = calcValue(my, enemy);
    }

    for(int i = 1; i < 5; i++){
        int tmpR = 0;
        int tmpC = i;
        int my = 0;
        int enemy = 0;
        while(tmpR < 5 && tmpC < 5){
            if(x[tmpR * 5 + tmpC] == 2){
                my++;
            }
            else if (x[tmpR * 5 + tmpC] == 1){
                enemy++;
            }
            tmpR++;
            tmpC++;
        }
        diagonal1[4+i] = calcValue(my, enemy);
    }
}

void calcDiagonal2(int x[]){
    for(int i = 0; i < 5; i++){
        int tmpR = 0;
        int tmpC = i;
        int my = 0;
        int enemy = 0;
        while(tmpR < 5 && tmpC >= 0){
            if(x[tmpR * 5 + tmpC] == 2){
                my++;
            }
            else if (x[tmpR * 5 + tmpC] == 1){
                enemy++;
            }
            tmpR++;
            tmpC--;
        }
        diagonal2[i] = calcValue(my, enemy);
    }

    for(int i = 1; i < 5; i++){
        int tmpR = i;
        int tmpC = 4;
        int my = 0;
        int enemy = 0;
        while(tmpR < 5 && tmpC >= 0){
            if(x[tmpR * 5 + tmpC] == 2){
                my++;
            }
            else if (x[tmpR * 5 + tmpC] == 1){
                enemy++;
            }
            tmpR++;
            tmpC--;
        }
        diagonal2[4+i] = calcValue(my, enemy);
    }
}

JNIEXPORT jint JNICALL Java_game_Game_nextMove
  (JNIEnv * env, jobject obj, jintArray array){
        jint *body = env->GetIntArrayElements(array, 0);
        colValues(body);
        rowValues(body);
        calcDiagonal1(body);
        calcDiagonal2(body);

        int maxValue = 0;
        int bestMoves = -1;
        for (int i = 0; i < 5 ; i++){
            for(int j = 0; j < 5; j++){
                if(body[i * 5 + j] == 0){
                    int value = rows[i] + cols[j];
                    value += diagonal1[4 - i + j] + diagonal2[i + j];
                    if(value > maxValue){
                        maxValue = value;
                        bestMoves = i * 5 + j;
                    }
                }
            }
        }
        return bestMoves;
  }