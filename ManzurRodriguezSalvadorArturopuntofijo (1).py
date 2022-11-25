from math import *


xi=float(input("Ingrese el valor inicial: "))
tole=float(input("Ingrese el valor de la tolerancia unicamente en decimales: "))
ite_m=int(input("Ingrese el numero de iteracciones: "))


err=1
iter=0

while err>=tole:
    
    iter= iter + 1
    gxi= (cos(4*xi**2)-3*(xi**2)-2)/-5 
    err= abs((gxi-xi)/gxi)
    if err>tole:
        xi=gxi
    else:
        break 
    if(iter >= ite_m):
        break
    print("De" +"  " +str(ite_m) + " " +"solo se realizaron: " + " " + str(iter) + " " + "Iteraciones.")
    print("El valor de la raiz es: " + str (gxi))
    print("El ultimo error calculado es: " + str(err))


      
    
    


    