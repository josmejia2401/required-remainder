---

````markdown
# 📊 Required Remainder - Reto Técnico

Implementación en Java 17 + Spring Boot 3.5.3 de la solución al problema [Codeforces 1374A - Required Remainder](https://codeforces.com/problemset/problem/1374/A), siguiendo arquitectura hexagonal, buenas prácticas, pruebas unitarias, validaciones, documentación Swagger y análisis de calidad de código con Jacoco y SonarQube.

---

## 🚀 Tabla de contenido

- [Descripción del problema](#descripción-del-problema)
- [Arquitectura](#arquitectura)
- [Tecnologías y dependencias](#tecnologías-y-dependencias)
- [Cómo ejecutar el proyecto](#cómo-ejecutar-el-proyecto)
- [Pruebas](#pruebas)
- [Cobertura de código con Jacoco](#cobertura-de-código-con-jacoco)
- [Validaciones](#validaciones)
- [Documentación Swagger](#documentación-swagger)
- [Estructura de paquetes](#estructura-de-paquetes)
- [Ejemplo de uso](#ejemplo-de-uso)
- [Autor](#autor)

---

## 📋 Descripción del problema

Dado un número de casos de prueba, para cada uno se reciben tres enteros: `x`, `y` y `n`.  
Se debe calcular el mayor entero `k` tal que:

- `0 ≤ k ≤ n`
- `k mod x = y`

Esto debe realizarse de forma eficiente y cumplir siempre con las restricciones del problema.

---

## 🏛️ Arquitectura

Se implementa una arquitectura **hexagonal (Ports and Adapters)**:

- **Dominio**: Lógica pura y contrato (`RemainderPort`, `RemainderService`, modelos).
- **Adaptador de entrada**: Exposición vía REST (`RemainderController`).
- **Infraestructura**: Validación, documentación Swagger, tests, análisis de calidad.

---

## 🧰 Tecnologías y dependencias

- **Java 17**
- **Spring Boot 3.5.3**
- **Arquitectura Hexagonal**
- **JUnit 5** (pruebas)
- **Mockito** (mocks)
- **Springdoc OpenAPI** (`swagger-ui`)
- **Jakarta Validation** (`@Valid`, `@Min`, etc.)
- **Lombok** (opcional)
- **Jacoco** (cobertura de pruebas)
- **SonarQube/SonarCloud** (análisis de código)

---

## ⚙️ Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/josmejia2401/reto-tecnico-inclusion.git
   cd reto-tecnico-inclusion
````

2. Compila y ejecuta la aplicación:

   ```bash
   mvn clean package
   java -jar target/required-remainder-1.0.0.jar
   ```

3. El servicio REST estará disponible en:

   ```
   http://localhost:8080/api/required-remainder/batch
   ```

---

## 🧪 Pruebas

Para ejecutar los tests:

```bash
mvn test
```

Incluyen pruebas unitarias del dominio, pruebas de integración para el controlador REST, y casos para validaciones.

---

## 📊 Cobertura de código con Jacoco

El proyecto integra **Jacoco** para analizar la cobertura de pruebas unitarias y de integración.

### Ejecutar análisis de cobertura:

```bash
mvn clean verify
```

Al finalizar, abre el reporte HTML generado en:

```
target/site/jacoco/index.html
```

Verás un reporte visual detallado de cobertura por clase, método y línea.

### Excluir archivos del análisis

Si deseas excluir archivos como `Application.java` de la cobertura, la configuración del plugin Jacoco en `pom.xml` incluye:

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.11</version>
    <configuration>
        <excludes>
            <exclude>**/Application.class</exclude>
        </excludes>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>prepare-package</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Esto asegura que la clase principal no afecte negativamente el porcentaje de cobertura.

---

## 🛡️ Validaciones

Los datos de entrada se validan automáticamente:

* **x**: mínimo 2
* **y**: mínimo 0, menor que x
* **n**: mínimo 0, mayor o igual a y

Si los datos no cumplen las restricciones, se retorna un error `400 Bad Request` con detalles del campo inválido.

---

## 📑 Documentación Swagger

La API está completamente documentada.
Accede a la documentación interactiva en:

```
http://localhost:8080/swagger-ui/index.html
```

Allí puedes probar la API, ver ejemplos y consultar la estructura de datos esperada.

---

## 📁 Estructura de paquetes

```
src/main/java/com/josmejia2401/requiredremainder/
│
├── domain/
│   ├── model/          # Modelos de dominio (Remainder)
│   ├── port/           # Interfaces de dominio (RemainderPort)
│   └── service/        # Implementaciones de dominio (RemainderService)
│
├── configuration/      # Beans y wiring de Spring
│
└── adapter/
    └── in/
        └── web/        # Controlador REST (RemainderController)
```

---

## 💻 Ejemplo de uso

### **Request (JSON):**

```json
[
  { "x": 7, "y": 5, "n": 12345 },
  { "x": 5, "y": 0, "n": 4 }
]
```

### **Curl:**

```bash
curl -X POST http://localhost:8080/api/required-remainder/batch \
  -H "Content-Type: application/json" \
  -d '[{"x":7,"y":5,"n":12345},{"x":5,"y":0,"n":4}]'
```

### **Response:**

```json
[12339, 0]
```

---

## ✍️ Autor

* **Nombre:** \[Tu Nombre]
* **Contacto:** \[[tuemail@ejemplo.com](mailto:tuemail@ejemplo.com)]
* **GitHub:** [https://github.com/tu\_usuario](https://github.com/josmejia2401)

---

## 📝 Licencia

MIT. Ver [LICENSE](LICENSE).

---