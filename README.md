# TPI-back
Este es el Trabajo Práctico Integrador de Backend de Aplicaciones.
Cuenta con 3 proyectos Maven: `gateway`, `alquileres` y `estaciones`

### API Gateway
Nuestro API Gateway actuará como intermediario entre las peticiones del cliente y los microservicios de alquileres y estaciones (reenviando las peticiones a los microservicios correspondientes), realizando todas las verificaciones de seguridad necesarias aquí mismo, otorgando permisos según roles a los usuarios.
Nuesto API Gateway se inicializa en el puerto 8082 y es accesible a través de <http://localhost:8082>.

### Alquileres
El microservicio de alquileres se inicializa en el puerto 8083 y es accedido por el API Gateway.

Este microservicio permite, entre otras cosas, iniciar el alquiler de una bicicleta desde una estación dada, finalizar un alquiler en curso, obtener un listado de los alquileres finalizados.

Además, se conecta con el servicio externo Conversor de Moneda ubicado en <http://34.82.105.125:8080/convertir>, para la obtención de costos en otras monedas.

### Estaciones
El microservicio de estaciones se inicializa en el puerto 8084 y es accedido por el API Gateway.


Este microservicio permite, entre otras cosas, consultar estaciones disponibles por ciudad, consultar estación de cercanía por ubicación, agregar una nueva estación.


## Documentación mediante Swagger
La documentación mediante Swagger se encuentra disponible en
<http://localhost:8083/swagger-ui/index.html> para el caso de alquileres, y en <http://localhost:8084/swagger-ui/index.html> para el caso de estaciones.