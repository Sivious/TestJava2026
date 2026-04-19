# TestJava2026 - API de Precios (BCNC Group)

Este proyecto implementa un servicio REST para la consulta de tarifas aplicables a productos financieros, siguiendo los principios de **Arquitectura Hexagonal (Puertos y Adaptadores)** y **Clean Code**.

## 1. Arquitectura del Proyecto

El sistema está diseñado para estar totalmente desacoplado de la tecnología de persistencia y de los frameworks externos. Las capas se organizan de la siguiente manera:

### 🔹 Capa de Dominio (Corazón del Negocio)
Es la capa más interna y no tiene dependencias de frameworks (como Spring) ni de la base de datos (JPA).
- **`domain.model`**: Contiene el modelo `Price`, un POJO puro que representa el negocio.
- **`domain.repository`**: Define el **Puerto de Salida** (`PriceRepository`), una interfaz que el dominio utiliza para pedir datos sin saber cómo se obtienen.
- **`domain.exception`**: Excepciones de negocio como `PriceNotFoundException`.

### 🔹 Capa de Aplicación (Orquestación)
Contiene la lógica de los casos de uso.
- **`application.usecase`**: Definición de las acciones del sistema (`GetApplicablePriceUseCase`).
- **`application.service`**: Implementación del caso de uso. Aquí reside la lógica que coordina al repositorio de dominio para obtener el resultado final.

### 🔹 Capa de Infraestructura (Detalles Técnicos)
Es la capa más externa, donde residen las implementaciones tecnológicas.
- **`infrastructure.adapter.out.db`**: Contiene el **Adaptador de Salida**. Implementa la interfaz de dominio usando Spring Data JPA (`SpringDataJpaPriceRepository`) y mapea las entidades (`PriceEntity`) al modelo de dominio.
- **`controller`**: El adaptador de entrada REST que recibe las peticiones y devuelve `PriceResponse`.
- **`infrastructure.config`**: Configuración centralizada de Beans, Swagger y gestión global de excepciones.

## 2. Tips Técnicos y Decisiones de Arquitectura

Para elevar el nivel de la solución, se han implementado los siguientes patrones:

1.  **Inversión de Dependencias (D de SOLID)**: El servicio de aplicación no depende de JPA, sino de una interfaz en el dominio. Esto permite cambiar la base de datos sin tocar una sola línea de código de negocio.
2.  **Separación Modelo vs Entidad**: Se utiliza `Price` para el negocio y `PriceEntity` para la base de datos. Esto evita que las anotaciones de persistencia contaminen la lógica empresarial.
3.  **Inmutabilidad**: El modelo de dominio utiliza objetos inmutables para garantizar que los datos de una tarifa no sufran efectos secundarios durante su procesamiento.
4.  **Gestión de Errores Centralizada**: Se utiliza `@RestControllerAdvice` para transformar excepciones técnicas o de negocio en respuestas HTTP estandarizadas y amigables para el cliente.
5.  **Configuración Programática**: Se ha evitado el uso excesivo de `@Service` en la capa de aplicación, prefiriendo la configuración manual en `UseCaseConfig` para mantener el dominio libre de anotaciones de Spring.

## 3. Stack Tecnológico

- **Java 17**
- **Spring Boot 3.4.1**
- **Spring Data JPA**
- **H2 Database** (Base de datos en memoria para la prueba)
- **Lombok** (Para reducir el código boilerplate)
- **SpringDoc OpenAPI (Swagger)** (Documentación interactiva)

## 4. Ejecución y Documentación

### Ejecutar la aplicación
```bash
./gradlew bootRun