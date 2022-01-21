
# Xmen ADN API

_Este proyecto permite detectar si un humano es mutante a partir de un ADN ingresado, ademas permite conocer las estadísticas del estado de las verificaciones realizas en la API_

## Solución 🚀

**![](https://lh3.googleusercontent.com/bTBERDMh8955Wxe9rmOXMYpOWkv6EZK-Uzs2kAqhVoRxOlbSDqOQXcd4k6ditvyjDJTHSPXIWU6rwm5X7TM932Nupj4ES1z4nC2jWD1FmM0mtJAvmMpWcfqvF10RItPKwoHp2w3o)**

_Se podrá identificar que un humano es mutante si se encuentra más de una secuencia de cuatro letras iguales​, de forma oblicua, horizontal o vertical._

La solución desarrollada consiste en recorrer la matriz de forma horizontal, vertical y diagonal construyendo cada una de las secuencias y validarlas con una expresión regular para saber si contiene cuatro letras iguales.

### Construido con 🛠️

-    Java 11
-   Sprint Boot
-   Gradle 6.8
-   AWS : DynamoDB
-   AWS: Elastic Beanstalk
-   Lombok
-   Swagger
-   Spring data
-   Junit
-   Arquitectura Hexagonal
-   CQRS

### Instalación 🔧

_1. Se debe tener instalado previamente:_

```  
Java 11
Gradle 6.8
```  
_2. Clonar el repositorio:_
```  
https://github.com/mariamolina0103/xmen-adn.git  
```  
_3. Importar el proyecto como gradle en un IDE de preferencia._
_4. Ejecutar el proyecto_

## Ejecutando las pruebas ⚙️

_-Pruebas unitarias : JUnit4_
_- Pruebas de integración: JUnit4, MockMVC_


## Despliegue 📦

_**Cloud Computing AWS** : Elastic Beanstalk_  
**![](https://lh4.googleusercontent.com/qBVx37iU5wvRf-AIZYrjyHDDBW52dJobLl7DGcDWRF1TRJYcl5sdbj9R3GvXW4vg2rpjruwLHJSaaLYyI2o_tv8ZUSH3B-yP8oolx91C-jxQDlu0tF8vm2LzV42aAX1xBMKX3UQf)**

## Documentación📖

Puedes encontrar mucho más sobre la API [Aquí](http://xmenadn-env.eba-sj6s3kmt.us-east-1.elasticbeanstalk.com/mutant/swagger-ui/index.html?configUrl=/mutant/v3/api-docs/swagger-config)

## Pruebas de la API📌

_Se pueden utilizar las colecciones de postman que se encuentran en la ruta principal del proyecto  [Aquí](https://github.com/mariamolina0103/xmen-adn/tree/master/postman)
  
