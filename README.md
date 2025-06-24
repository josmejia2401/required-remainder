
# 🚀 Required Remainder

Microservicio en Java 17 + Spring Boot 3.5.3 que resuelve el reto “Required Remainder” (Codeforces 1374A) aplicando arquitectura hexagonal, buenas prácticas (SOLID, tests, Sonar, cobertura, documentación Swagger), listo para Docker, despliegue en AWS ECS/ECR, y fácil integración con SonarQube.

---

## 📦 Características principales

- Spring Boot 3.5.3, Java 17, Maven
- Arquitectura hexagonal y código limpio (SOLID)
- Control de errores global y validaciones con `@Valid`
- Documentación automática de API con Swagger (springdoc-openapi)
- Test unitarios (JUnit 5)
- Análisis de calidad con SonarQube y SonarLint
- Cobertura de código con Jacoco
- Dockerfile listo para despliegue (ECS, ECR, API Gateway)
- Infraestructura como código (Terraform, ver carpeta `infra/`)
- Soporte para integración con CI/CD y SonarCloud

---

## 🚀 Ejecución local

### 1. Requisitos

- Java 17+
- Maven 3.8+
- (Opcional) Docker
- (Opcional) SonarQube local

### 2. Clonar el proyecto

```bash
git clone https://github.com/josmejia2401/required-remainder.git
cd required-remainder
```

### 3. Compilar y ejecutar

```bash
mvn clean package
java -jar target/required-remainder.jar
```

Por defecto, la app se levanta en [http://localhost:8080](http://localhost:8080)

---

## 📑 Documentación Swagger/OpenAPI

La API REST está documentada automáticamente con [springdoc-openapi](https://springdoc.org/).

**Configuración de rutas (agrega esto a tu `application.properties` si quieres usar rutas personalizadas):**
```properties
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 🧪 Tests y cobertura de código

Ejecuta todos los tests y genera reporte de cobertura con Jacoco:

```bash
mvn clean verify
```

El reporte HTML de Jacoco estará en `target/site/jacoco/index.html`.

---

## 🛡️ Análisis de calidad con SonarQube (local)

Soporta análisis de calidad estática con SonarQube local.

### 1. Levantar SonarQube con Docker

```bash
docker run -d --name sonarqube -p 9000:9000 sonarqube:latest
```

Accede a [http://localhost:9000](http://localhost:9000), usuario/contraseña: `admin` / `admin`

### 2. Crea un token en SonarQube

- Ve a tu usuario (My Account > Security)
- Crea y guarda un **token personal**

### 3. Analiza el proyecto con Maven

```bash
mvn clean verify sonar:sonar   -Dsonar.projectKey=required-remainder   -Dsonar.host.url=http://localhost:9000   -Dsonar.token=sqa_c554752e7d1a61f05a8b15d47cb98344219cd47c
```

### 4. Integra SonarQube en IntelliJ IDEA (opcional)

1. Instala el plugin **SonarLint** desde los Plugins de IntelliJ.
2. Ve a **View > Tool Windows > SonarLint** para abrir el panel.
3. Entra a Settings (⚙️) de SonarLint > **Connections**, añade una conexión SonarQube a `http://localhost:9000` con tu token.
4. En **Project Settings** de SonarLint, vincula (bind) tu proyecto local al project key correspondiente de SonarQube.
5. Ahora verás issues y sugerencias de SonarQube directamente en el IDE.

**Notas útiles:**
- Para personalizar archivos o clases excluidas de cobertura en Jacoco, revisa la sección `<excludes>` del `pom.xml`.
- Si usas otro key, ajusta el comando Maven y el binding en SonarLint.
- Recuerda destruir el contenedor SonarQube (`docker stop sonarqube && docker rm sonarqube`) si no lo usas.

---

## 🐳 Docker

### Build de imagen

```bash
docker build -t required-remainder .
```

### Ejecutar localmente

```bash
docker run -p 8080:8080 required-remainder
```

---

## ☁️ Despliegue en AWS (ECS, ECR, API Gateway)

El despliegue de este microservicio en la nube de AWS (utilizando ECS Fargate, ECR y API Gateway) está completamente automatizado mediante infraestructura como código (IaC) usando Terraform.

### 📂 Infraestructura lista para producción

Toda la configuración necesaria (VPC, ECR, ECS, roles, subredes, security groups, API Gateway, etc.) se encuentra en el repositorio:

- [**required-remainder-infra**](https://github.com/josmejia2401/required-remainder-infra)

### 🚀 Pasos básicos de despliegue

1. **Clona el repositorio de infraestructura:**

    ```bash
    git clone https://github.com/josmejia2401/required-remainder-infra.git
    cd required-remainder-infra
    ```

2. **Revisa y ajusta variables en `variables.tf` según tus necesidades (región, nombre de proyecto, etc.)**

3. **Inicializa y aplica Terraform:**

    ```bash
    terraform init
    terraform plan
    terraform apply
    ```

4. **Construye y sube tu imagen Docker al ECR creado por Terraform:**

    ```bash
    # Login en ECR
    aws ecr get-login-password --region <REGION> | docker login --username AWS --password-stdin <ACCOUNT_ID>.dkr.ecr.<REGION>.amazonaws.com

    # Construye la imagen
    docker build -t required-remainder .

    # Etiqueta y sube al ECR creado por Terraform
    docker tag required-remainder:latest <ACCOUNT_ID>.dkr.ecr.<REGION>.amazonaws.com/required-remainder:latest
    docker push <ACCOUNT_ID>.dkr.ecr.<REGION>.amazonaws.com/required-remainder:latest
    ```

5. **Actualiza el servicio ECS para usar la nueva imagen si es necesario (desde AWS Console o ajustando Terraform).**

6. **Accede a tu API desde el endpoint de API Gateway público (se muestra en los outputs de Terraform).**

---

**Consulta el [README del repositorio de infraestructura](https://github.com/josmejia2401/required-remainder-infra/blob/main/README.md) para una guía detallada y personalizada.**

---

📦 Push directo de imagen Docker a AWS ECR
Sigue estos pasos para construir y subir tu imagen al repositorio ECR:

bash
Copiar
Editar
* 7. Inicia sesión en ECR
aws ecr get-login-password --region us-east-1 \
| docker login --username AWS --password-stdin 233925838033.dkr.ecr.us-east-1.amazonaws.com/required-remainder

* 8. Construye y sube tu imagen directamente a ECR (build multiplataforma para ECS/Fargate)
docker buildx build --platform linux/amd64 \
-t 233925838033.dkr.ecr.us-east-1.amazonaws.com/required-remainder:v6 --push .

Esto construirá la imagen compatible con AWS ECS Fargate y la subirá automáticamente.

---
¡Por supuesto! Aquí tienes un bloque **listo para agregar a tu README**, dejando claro cómo probar el microservicio y accediendo a los recursos clave, incluyendo **un ejemplo de request**.

---

## 🌐 Endpoints disponibles en el backend

Tu microservicio Spring Boot expone los siguientes recursos (cuando accedes directo al contenedor o desde API Gateway configurado como proxy):

* **Procesar batch (POST):**
  `http://3.81.228.233:8080/api/required-remainder/batch`
* **OpenAPI JSON:**
  `http://3.81.228.233:8080/v3/api-docs`
* **Swagger UI:**
  `http://3.81.228.233:8080/swagger-ui.html`

> ⚠️ *Recuerda: la IP pública puede cambiar cada vez que reinicias el servicio ECS. Si no responde, revisa en AWS cuál es la IP activa.*

---

### 📋 Ejemplo de request a `/api/required-remainder/batch`

#### **Request (POST):**

```bash
curl -X POST http://3.81.228.233:8080/api/required-remainder/batch \
  -H "Content-Type: application/json" \
  -d '[
        { "x": 7, "y": 5, "n": 12345 }
      ]'
```

#### **Body esperado:**

```json
[
  {
    "x": 7,
    "y": 5,
    "n": 12345
  }
]
```

#### **Respuesta esperada (ejemplo):**

```json
[12339]
```

---

* Para explorar y probar el API con interfaz gráfica, accede a:
  **Swagger UI:** [http://3.81.228.233:8080/swagger-ui.html](http://3.81.228.233:8080/swagger-ui.html)
* Para ver la especificación OpenAPI JSON:
  **OpenAPI:** [http://3.81.228.233:8080/v3/api-docs](http://3.81.228.233:8080/v3/api-docs)

---
## 📝 Buenas prácticas y recomendaciones

- Cumple con principios SOLID y arquitectura hexagonal.
- Control global de errores con `@RestControllerAdvice`.
- Validaciones automáticas con `@Valid`.
- Logs estratégicos en controllers y services usando SLF4J (`@Slf4j`).
- Excluye archivos generados/temporales usando el `.gitignore` recomendado.

---

## 🟢 Recursos útiles

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [springdoc-openapi](https://springdoc.org/)
- [SonarQube](https://www.sonarqube.org/)
- [Jacoco](https://www.jacoco.org/jacoco/trunk/doc/maven.html)
- [Terraform AWS Provider](https://registry.terraform.io/providers/hashicorp/aws/latest/docs)
- [Docker Docs](https://docs.docker.com/)

---

## 👤 Autor

- **José Mejía** - [josmejia2401@gmail.com](mailto:josmejia.2401@gmail.com)

---
