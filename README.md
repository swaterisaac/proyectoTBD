# Proyecto TBD Grupo 2

Laboratorio para la asignatura *Taller de base de datos* del departamento de Ingeniería en Informática de la Universidad de Santiago de Chile.

Sistema de Gestión de Emergencias y Voluntariado

## Despliegue

### Base de datos

Si desea correr utilizando la base de datos de desarrollo, ajustar los valores en el backend en `backend/src/main/resources/application.properties` con los entregados.


Si desea crear la base de datos, correr el script `db/createTBD.sql` en Postgresql para crear las tablas y ajustar los valores en el backend en `backend/src/main/resources/application.properties`. Puede poblar la base de datos con datos de prueba con el script `db/poblado.sql`, en la carpeta `db/Poblado por tablas/` se encuentran scripts de poblado por cada tabla.

El dump completo de la base de datos se encuentra en `db/dump.sql`.

### Backend
En la terminal dirigirse a la carpeta `backend/`.
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
### Frontend

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
