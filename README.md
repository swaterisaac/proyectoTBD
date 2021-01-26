# Proyecto TBD Grupo 2

Laboratorio para la asignatura *Taller de base de datos* del departamento de Ingeniería en Informática de la Universidad de Santiago de Chile.

Sistema de Gestión de Emergencias y Voluntariado

## Despliegue

### Base de datos

Si desea correr utilizando la base de datos de desarrollo, ajustar los valores en el backend en `backend/src/main/resources/application.properties` con los entregados.


Si desea crear la base de datos, correr el script `db/createTBD.sql` en Postgresql para crear las tablas y ajustar los valores en el backend en `backend/src/main/resources/application.properties`. Puede poblar la base de datos con datos de prueba con el script `db/poblado.sql`, en la carpeta `db/Poblado por tablas/` se encuentran scripts de poblado por cada tabla.

El dump completo de la base de datos se encuentra en `db/dump.sql`.

# Backend
En la terminal dirigirse a la carpeta `backend/`.

## Documentación API REST
###` Agregar el archivo aplication.properties (que estará en el drive) a la ruta backend/src/main/resources`

Luego dar permisos de ejecución al archivo `backend/gradlew`.

```
chmod +x gradlew
```
Luego cargar el backend
```
./gradlew bootrun
```

Estará corriendo en la dirección
```
http://localhost:1818
```

### Consulta API
`GET /consult/` - Entrega un listado de tareas por orden alfabético donde muestra la cantidad de voluntarios inscritos mayores a X años
Request:
```
{
	"emid": 1, //ID de la emergencia
	"age": 20 //Edad a consultar
}
```


### Emergencies
`GET /emergency/` - Retorna todas las emergencias

`GET /emergency/10` - Retorna la emergencia 10

`POST /emergency/` - Crea una nueva emergencia

`PUT /emergency/10` - Edita la emergencia 10

`DELETE /emergency/10` - Elimina la emergencia 10

`GET emergency/lastId` - Obtiene la última emergencia distribuida.

`POST emergency/insertarDist` - Crea una nueva emergencia distribuidas.

`GET emergency/obtenerDist` - Obtiene todas las emergencias distribuidas.
### Institutions
`GET /institution/` - Retorna todas las instituciones

`GET /institution/10` - Retorna la intitución 10

`POST /institution/` - Crea una nueva institución

`PUT /institution/10` - Edita la institución 10

`DELETE /institution/10` - Elimina la institución 10

### Ranking
`GET /ranking/` - Retorna todas los rankings

`GET /ranking/10` - Retorna el ranking 10

`POST /ranking/` - Crea un nuevo ranking

`PUT /ranking/10` - Edita el ranking 10

`DELETE /ranking/10` - Elimina el ranking 10

### skill
`GET /skill/` - Retorna todas las habilidades

`GET /skill/10` - Retorna la habilidad 10

`POST /skill/` - Crea una nueva habilidad

`PUT /skill/10` - Edita la habilidad 10

`DELETE /skill/10` - Elimina la habilidad 10

### status
`GET /status/` - Retorna todas los status

`GET /status/10` - Retorna el status 10

`POST /status/` - Crea un nuevo status

`PUT /status/10` - Edita el status 10

`DELETE /status/10` - Elimina el status 10

### task
`GET /task/` - Retorna todas las tareas

`GET /task/10` - Retorna la tarea 10

`POST /task/` - Crea un nuevo task

`PUT /task/10` - Edita la tarea 10

`DELETE /task/10` - Elimina la tarea 10

### taskupdate
`GET /taskupdate/` - Retorna todas las actualizaciones

`GET /taskupdate/10` - Retorna la actualizacion 10

`POST /taskupdate/` - Crea un nuevo taskupdate

`PUT /taskupdate/10` - Edita la actualizacion 10

`DELETE /taskupdate/10` - Elimina la actualizacion 10

### Users
`GET /user/` - Retorna todas los usuarios

`GET /user/10` - Retorna el usuario 10

`POST /user/` - Crea un nuevo user

`PUT /user/10` - Edita el usuario 10

`DELETE /user/10` - Elimina el usuario 10

### Volunteers
`GET /volunteer/` - Retorna todas los voluntarios

`GET /volunteer/10` - Retorna voluntario 10

`POST /volunteer/` - Crea un nuevo voluntario

`PUT /volunteer/10` - Edita voluntario 10

`DELETE /volunteer/10` - Elimina voluntario 10

### volunteer-skill
`GET /volunteer-skill/` - Retorna todas los volunteer-skills

`GET /volunteer-skill/10` - Retorna la habilidad-voluntario 10

`POST /volunteer-skill/` - Crea un nuevo habilidad-voluntario

`PUT /volunteer-skill/10` - Edita la habilidad-voluntario 10

`DELETE /volunteer-skill/10` - Elimina la habilidad-voluntario 10

### emergency-skill
`GET /emergency-skill/` - Retorna todas los emergency-skills

`GET /emergency-skill/10` - Retorna el emergency-skill 10

`POST /emergency-skill/` - Crea un nuevo emergency-skill

`PUT /emergency-skill/10` - Edita el emergency-skill 10

`DELETE /emergency-skill/10` - Elimina el emergency-skill 10

### EmergencySkillTask
`GET /EmergencySkillTask/` - Retorna todas los EmergencySkillTasks

`GET /EmergencySkillTask/10` - Retorna el EmergencySkillTask 10

`POST /EmergencySkillTask/` - Crea un nuevo EmergencySkillTask

`PUT /EmergencySkillTask/10` - Edita el EmergencySkillTask 10

`DELETE /EmergencySkillTask/10` - Elimina el EmergencySkillTask 10


# Frontend

En la terminal dirigirse a la carpeta `frontdos/`.

Luego cargar las dependencias con *npm*

```
npm install
```

Luego correr el frontend con Vue.js con el comando:
```
npm run serve
```
Estará corriendo en la dirección:
```
http://localhost:8080
```
