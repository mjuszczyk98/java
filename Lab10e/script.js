function nextMove(x){
    
    cols = colValues(x);
    rows = rowValues(x);
    diagonal1 = calcDiagonal1(x);
    diagonal2 = calcDiagonal2(x);
    
    maxValue = 0;
    bestMoves = [];
    for (var i = 0; i < 5 ; i++){
        for(var j = 0; j < 5; j++){
            if(x[i * 5 + j] == 0){
                var value = rows[i] + cols[j];
                value += diagonal1[4 - i + j] + diagonal2[i + j];
                if(value > maxValue){
                    maxValue = value;
                    bestMoves = [];
                    bestMoves.push(i * 5 + j);
                } else if (value == maxValue){
                    bestMoves.push(i * 5 + j);
                }
            }
        }
    }
    bestMoves.sort(middleSort)
    return bestMoves[0];
}

function middleSort(first, second){
    var x = 12.5;
    return Math.abs(first - x) < Math.abs(second - x) ? -1 : 1;
}

function calcValue(my, enemy){
    return my * my  + enemy * enemy * enemy;
}

function colValues(x){
    var cols = [];
    for(var i = 0; i < 5; i++){
        var my = 0;
        var enemy = 0;
        for(var j = 0; j < 5; j++){
            if(x[j*5+i] == 2){
                my++;
            }
            else if (x[j*5+i] == 1){
                enemy++;
            }
        }
        cols.push(calcValue(my, enemy));
    }
    return cols;
}

function rowValues(x){
    var rows = [];
    for(var i = 0; i < 5; i++){
        var my = 0;
        var enemy = 0;
        for(var j = 0; j < 5; j++){
            if(x[i*5+j] == 2){
                my++;
            }
            else if(x[i*5+j] == 1){
                enemy++;
            }
        }
        rows.push(calcValue(my, enemy));
    }
    return rows;
}

function calcDiagonal1(x){
    var d1 = [];

    for(var i = 4; i >= 0; i--){
        var tmpR = i;
        var tmpC = 0;
        var my = 0;
        var enemy = 0;
        while(tmpR < 5 && tmpC < 5){
            if(x[tmpR * 5 + tmpC] == 2){
                my++
            }
            else if (x[tmpR * 5 + tmpC] == 1){
                enemy++;
            }
            tmpR++;
            tmpC++;
        }
        d1.push(calcValue(my, enemy));
    }

    for(var i = 1; i < 5; i++){
        var tmpR = 0;
        var tmpC = i;
        var my = 0;
        var enemy = 0;
        while(tmpR < 5 && tmpC < 5){
            if(x[tmpR * 5 + tmpC] == 2){
                my++
            }
            else if (x[tmpR * 5 + tmpC] == 1){
                enemy++;
            }
            tmpR++;
            tmpC++;
        }
        d1.push(calcValue(my, enemy));
    }

    return d1;
}

function calcDiagonal2(x){
    var d2 = [];

    for(var i = 0; i < 5; i++){
        var tmpR = 0;
        var tmpC = i;
        var my = 0;
        var enemy = 0;
        while(tmpR < 5 && tmpC >= 0){
            if(x[tmpR * 5 + tmpC] == 2){
                my++
            }
            else if (x[tmpR * 5 + tmpC] == 1){
                enemy++;
            }
            tmpR++;
            tmpC--;
        }
        d2.push(calcValue(my, enemy));
    }

    for(var i = 1; i < 5; i++){
        var tmpR = i;
        var tmpC = 4;
        var my = 0;
        var enemy = 0;
        while(tmpR < 5 && tmpC >= 0){
            if(x[tmpR * 5 + tmpC] == 2){
                my++
            }
            else if (x[tmpR * 5 + tmpC] == 1){
                enemy++;
            }
            tmpR++;
            tmpC--;
        }
        d2.push(calcValue(my, enemy));
    }

    

    return d2;
}