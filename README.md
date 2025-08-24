# Threads
## Integrantes
- *Manuel David Robayo Vega*
- *William Camilo Hernandez Deaza*


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
desde la clase principal sin utilizar hilos. Por esta razón, la salida muestra los números de los 3 intervalos de
manera secuencial a diferencia de la imagen anterior, donde se los 3 hilos se ejecutaban de manera simultanea pues eran inciados
correctamente con start().  
  ![3ThreadsRun1](/img/threadsRun1.png)  
  ![3ThreadsRun2](/img/threadsRun2.png)  


### Parte II - Ejercicio Black List Search
1. Cree una clase de tipo Thread que represente el ciclo de vida de un hilo que haga la búsqueda de un segmento del conjunto de servidores disponibles. 
Agregue a dicha clase un método que permita 'preguntarle' a las instancias del mismo (los hilos) cuantas ocurrencias de servidores maliciosos ha encontrado o encontró.  
- Definimos la clase 'Threads', quien se encarga de realizar la búsqueda en el segmento de servidores dado. Para poder preguntar la cantidad de ocurrencias
encontradasa otros hilos (sin la restriccion de máximo 5 ocurrencias) establecimos un atrbiuto que almacena el número de ocurrencias con su respectivo método get().  

2. Agregue al método 'checkHost' un parámetro entero N, correspondiente al número de hilos entre los que se va a realizar la búsqueda (recuerde tener en cuenta si N es par o impar!). 
Modifique el código de este método para que divida el espacio de búsqueda entre las N partes indicadas, y paralelice la búsqueda a través de N hilos. Haga que dicha función espere 
hasta que los N hilos terminen de resolver su respectivo sub-problema, agregue las ocurrencias encontradas por cada hilo a la lista que retorna el método, y entonces calcule 
(sumando el total de ocurrencuas encontradas por cada hilo) si el número de ocurrencias es mayor o igual a BLACK_LIST_ALARM_COUNT. Si se da este caso, al final se DEBE reportar el host 
como confiable o no confiable, y mostrar el listado con los números de las listas negras respectivas.  
- Para poder dividir el espacio de búsqueda del método checkHost entre N hilos dividimos el número total de servidores entre N para estableccer los intervalos de cada hilo y se completa
el último intervalo para no tener inconvenientes con la división. Implementamos un arreglo donde se van creando los hilos y donde se utiliza join() para que el proceso espere hasta que cada
hilo resuelva su sub-problema. Finalmente se suma las cantidad de ocurrencias encontradas en cada hilo  para determinar si la ip es confiable o no confiable, se muestra la lista que retorna el 
método que es la union de las listas generadas por cada hilo y muestra el LOG que informa si es confiable o no y la cantidad de listas revisadas.  
  ![IpNoTrustWorthy](/img/img3.png)  
  ![IpTrustWorthy](/img/img4.png)  

### Parte II.I Para discutir (NO para implementar aún)
La estrategia de paralelismo antes implementada es ineficiente en ciertos casos, pues la búsqueda se sigue realizando aún cuando los N hilos (en su conjunto) ya hayan encontrado 
el número mínimo de ocurrencias requeridas para reportar al servidor como malicioso. Cómo se podría modificar la implementación para minimizar el número de consultas en estos casos?, 
qué elemento nuevo traería esto al problema?  
- Para minimizar el número de consultas en caso de que los hilos encuentren los 5 registros y no tengan que buscar hasta el final tocaria crear un atributo global donde los hilos sumen sus ocurrencias y paren cuando, al realizar la verificacion el numero de ocurrencias es mayor al numero minimo establecido de ocurrencias. Esta fraccion de codigo puede generar condicion de carrera por lo que es importante generar un bloqueo en este segmento de codigo para evitar que dos hilos modifiquen la variable al mismo tiempo y se genere una pérdida de información y rendimiento.
  
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

### Parte IV - Ejercicio Black List Search

1. Según la ley de Amdahls:

![Ley de Amdahls](/img/Ley.png)

, donde S(n) es el mejoramiento teórico del desempeño, P la fracción paralelizable del algoritmo, y n el número de hilos, a mayor n, mayor debería ser dicha mejora. Por qué el mejor desempeño no se logra con los 500 hilos?, cómo se compara este desempeño cuando se usan 200?.

- A partir de 100 hilos los cambios de mejora son imperceptibles para el entorno utilizado para medir el rendimiento de la CPU y la memoria. Creemos que a partir de este numero no hay margen de mejora pues el porcentaje de codigo paralelizable impide una mejoria con una mayor cantida de hilos.

2. Cómo se comporta la solución usando tantos hilos de procesamiento como núcleos comparado con el resultado de usar el doble de éste?

- De acuerdo a los resultados obtenidos en el laboratorio, al usar el doble de hilos que de nucleos disponibles en el dispositivo apreciamos una reducción del 50% de tiempo total en comparacion a utilizar la misma cantidad de hilos que de nucleos.

3. De acuerdo con lo anterior, si para este problema en lugar de 100 hilos en una sola CPU se pudiera usar 1 hilo en cada una de 100 máquinas hipotéticas, la ley de Amdahls se aplicaría mejor?  

- En el caso hipotetico en donde tengamos un hilo por cada 100 maquinas(100 cores reales) la ley de Amdahls se aplicaria de mejor forma debido a que todos los cores tendrian los recursos completos disponibles para si mismos, lo unico que empeoraria las cosas es la comunicación entre los dispositivos

Si en lugar de esto se usaran c hilos en 100/c máquinas distribuidas (siendo c es el número de núcleos de dichas máquinas), se mejoraría?. Explique su respuesta.

- Cuando se usan c hilos en 100/c maquinas se obtendria un resultado incluso mejor ya que cada maquina operaria en su punto optimo de nucleos, el pararelismo local evitaria una comunicacion tan extensa con otras maquinas y aprovecha el paralelismo local.

 