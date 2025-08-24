# Threads
## Integrantes
- Manuel David Robayo Vega
- William Camilo Hernandez Deaza

### Parte I - Introducción a Hilos en Java
1. Complete las clases CountThread, para que las mismas definan el ciclo de vida de un 
hilo que imprima por pantalla los números entre A y B.  

- Una vez completadas las clases CountThread para definir el ciclo de vida de 
hilos que impiman todos los números entre A y B, iniciamos un hilo de prueba que imprime los
números entre 0 y 16 dando como resultado:  
   ![CountThread](/img/img.png)

2. Complete el método main de la clase CountMainThreads para que:
   i. Cree 3 hilos de tipo CountThread, asignándole al primero el intervalo [0..99], al segundo [99..199], y al tercero [200..299].
   ii. Inicie los tres hilos con 'start()'.
   iii. Ejecute y revise la salida por pantalla.  

- Al crear tres hilos con los intervalos dados y ejecutarlos con 'start()' desde la clase main, en la salida se mostró lo siguiente:    
  ![3ThreadsStart](/img/img2.png)  

  
   iv. Cambie el incio con 'start()' por 'run()'. Cómo cambia la salida?, por qué?.  

- Cuando desde la clase principal "iniciamos" los hilos con run(), realmente estamos ejecutando la sección de código del hilo
desde la clase principal sin realmente utilizar hilos. Por esta razón, la salida muestra los números de los 3 intervalos de
manera secuencial a diferencia de la imagen anterior, donde se los 3 hilos se ejecutaban de manera simultanea pues eran inciados
correctamente con start().  
  ![3ThreadsRun1](/img/threadsRun1.png)  
  ![3ThreadsRun2](/img/threadsRun2.png)  


### Parte II - Ejercicio Black List Search
1. Cree una clase de tipo Thread que represente el ciclo de vida de un hilo que haga la búsqueda de un segmento del conjunto de servidores disponibles. 
Agregue a dicha clase un método que permita 'preguntarle' a las instancias del mismo (los hilos) cuantas ocurrencias de servidores maliciosos ha encontrado o encontró.  
- Definimos la clase 'Threads', quien se encarga de realizar la búsqueda en el segmento de servidores dado. Para poder preguntar la cantidad de ocurrencias
encontradas para la ip a otros hilos (sin la restriccion de 5 máximo) establecimos un atrbiuto que almacena el número de ocurrencias con su respectivo método get().  

2. Agregue al método 'checkHost' un parámetro entero N, correspondiente al número de hilos entre los que se va a realizar la búsqueda (recuerde tener en cuenta si N es par o impar!). 
Modifique el código de este método para que divida el espacio de búsqueda entre las N partes indicadas, y paralelice la búsqueda a través de N hilos. Haga que dicha función espere 
hasta que los N hilos terminen de resolver su respectivo sub-problema, agregue las ocurrencias encontradas por cada hilo a la lista que retorna el método, y entonces calcule 
(sumando el total de ocurrencuas encontradas por cada hilo) si el número de ocurrencias es mayor o igual a BLACK_LIST_ALARM_COUNT. Si se da este caso, al final se DEBE reportar el host 
como confiable o no confiable, y mostrar el listado con los números de las listas negras respectivas.  
- Para poder divir el espacio de búsqueda del método checkHost entre N hilos dividimos el número total de servidores entre N para estableccer los intervalos de cada hilo y se completa
el último intervalo para no tener inconvenientes con la división. Implementamos un arreglo donde se van creando los hilos y donde se utiliza join() para que el proceso espere hasta que cada
hilo resuelva su sub-problema. Finalmente se suma las cantidad ocurrencias encontradas en cada hilo  para determinar si la ip es confiable o no confiable, se muestra la lista que retorna el 
método que es la union de las listas generadas por cada hilo y muestra el LOG que informa si es confiable o no y la cantidad de listas revisadas.  
  ![IpNoTrustWorthy](/img/img3.png)  
  ![IpTrustWorthy](/img/img4.png)  

### Parte II.I Para discutir (NO para implementar aún)
La estrategia de paralelismo antes implementada es ineficiente en ciertos casos, pues la búsqueda se sigue realizando aún cuando los N hilos (en su conjunto) ya hayan encontrado 
el número mínimo de ocurrencias requeridas para reportar al servidor como malicioso. Cómo se podría modificar la implementación para minimizar el número de consultas en estos casos?, 
qué elemento nuevo traería esto al problema?  
- Para minimizar el número de consultas en caso de que los hilos encuentren los 5 registros y no tengan que buscar hasta el final...
  
### Parte III - Evaluación de Desempeño
A partir de lo anterior, implemente la siguiente secuencia de experimentos para realizar las validación de direcciones IP dispersas (por ejemplo 202.24.34.55), tomando los tiempos de 
ejecución de los mismos (asegúrese de hacerlos en la misma máquina):

1. Un solo hilo.
   ![1Thread](/img/1thread.png)
2. Tantos hilos como núcleos de procesamiento (haga que el programa determine esto haciendo uso del API Runtime).
   ![Cores](/img/img5.png)
   ![16Threads](/img/16threads.png)
3. Tantos hilos como el doble de núcleos de procesamiento.
   ![32Threads](/img/32threads.png)
4. 50 hilos.
   ![50Threads](/img/50threads.png)
5. 100 hilos.
   ![100Threads](/img/100threads.png)  
   
Con lo anterior, y con los tiempos de ejecución dados, haga una gráfica de tiempo de solución vs. número de hilos. Analice y plantee hipótesis con su compañero para las siguientes preguntas.  
   ![ThreadsVSTime](/img/img6.png)


   