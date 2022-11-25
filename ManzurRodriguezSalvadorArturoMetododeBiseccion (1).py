from math import *



tolerancia=float(input("Ingrese la tolerancia: "))
xl=float(input('Cual es el valor del extremo inferior: '))
xu=float(input('Cual es el valor del extremo superior: '))



def funcion(x):
 return 4*log(2*x**2)

iteraciones=0
er=0

while  abs(er) <= tolerancia:
             
 puntomedio=(xl + xu)/2
 
 Nxl=funcion(xl)
 Nxu=funcion(xu)
 Nxr=funcion(puntomedio)
             
 err= (xu - xl)/2 
 iteraciones= iteraciones + 1
  

 
 if (Nxl*Nxr) < 0:
     xu=puntomedio
 if (Nxl * Nxr) > 0:
     xl=puntomedio
 if (err<=tolerancia):
     break      
                 
print('Numero de iteracciones: ' , str(iteraciones))           
print ('Valor de la raiz: ', puntomedio)
print('ultimo error calculado: ', err)
