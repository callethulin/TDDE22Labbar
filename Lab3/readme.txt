/**********************************************************************
 *  M�nsterigenk�nning readme.txt
 **********************************************************************/

 Ungef�rligt antal timmar spenderade p� labben (valfritt):

/**********************************************************************
 *  Empirisk    Fyll i tabellen nedan med riktiga k�rtider i sekunder
 *  analys      n�r det k�nns vettigt att v�nta p� hela ber�kningen.
 *              Ge uppskattningar av k�rtiden i �vriga fall.
 *
 **********************************************************************/
    
      N       brute       sortering
 ----------------------------------
    150        0.019        0.013
    200        0.041        0.016
    300        0.053        0.031
    400        0.087        0.044
    800        0.346        0.127
   1600        2.978        0.391
   3200        25.498       1.498
   6400        205.657      6.646
  12800        1815.985     28.708


/**********************************************************************
 *  Teoretisk   Ge ordo-uttryck f�r v�rstafallstiden f�r programmen som
 *  analys      en funktion av N. Ge en kort motivering.
 *
 **********************************************************************/

Brute: ordo(n^4), då det är fyra nästlade forloopar.

Sortering: ordo(n^2), då det är två näslade forloopar.
