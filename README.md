### Explorando las relaciones entre tablas de la base de datos

Este repositorio contiene un ejemplo de API REST incompleta, a la que le faltan las siguientes funcionalidades:

- Cada objeto Experience pertenece a una "Category". Cada Category tiene un id y un nombre (p.e. `0 -> Aventura`, `1 -> Relax`, etc)
- Podemos recuperar las experiencias de una determinada categoría usando su id. Por ejemplo, `GET /categories/0/experiences` nos
devolverá las experiencias cuya categoría es la `0`

Para implementar estas funcionalidades podemos usar la relación "many to one", en tanto que cada categoría tiene muchas experiencias, pero
cada experiencia sólo puede pertenecer a una categoría (en este ejemplo).

### Cómo trabajar en este ejercicio: 

1. Un solo repositorio por grupo

2. Haced pasar el test `getsExistingCategories` primero. Para ello tendréis que crear una clase que represente la categoría (con un id y un nombre)
y un repositorio que almacene las categorías existentes. Modificad la función `addSampleData` para añadir categorias de prueba en el repositorio.

3. Acceded a la interfaz de H2 en `localhost:8080/h2-console` y verificad que hay diferentes tablas para categorías y experiencias

4. Haced pasar el test `getsExperiencesByCategory` conectando ambas tablas usando anotaciones de Java. Para ello deberéis incluir 
un campo `Category` en la definición de clase de `Experience` y un campo de tipo `List<Experience>` dentro de la clase `Category`. De este modo, podremos
acceder desde la categoría a sus juegos y viceversa PERO para que esto funcione deberemos investigar cómo funcionan las anotaciones `@ManyToOne` y `@OneToMany`
    
    - Para ello deberéis buscar documentación sobre el uso de anotaciones como `@OneToMany` y `@ManyToOne` en Java. [Ejemplo](https://www.baeldung.com/hibernate-one-to-many)
