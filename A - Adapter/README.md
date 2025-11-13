# Patrón Adapter - Aplicación de Consulta de Empleados

Esta aplicación demuestra la implementación del **Patrón Adapter** para hacer compatibles dos servicios con diferentes firmas de métodos que consultan datos de empleados en una empresa tecnológica.

## Estructura del Proyecto

- `employee_models.py`: Modelo de datos para empleados
- `database_service.py`: Servicio que consulta directamente desde la base de datos PostgreSQL
- `rest_service.py`: Servicio que consulta a través de un servicio web REST
- `employee_adapter.py`: Implementación del patrón Adapter
- `main.py`: Aplicación principal que extrae el código de empleado desde la línea de comandos
- `requirements.txt`: Dependencias del proyecto
- `database_setup.sql`: Script SQL para crear la tabla de empleados

## Diferentes Firmas de Métodos

### DatabaseEmployeeService
- Método: `get_employee_by_id(employee_id: str) -> Optional[Employee]`
- Consulta directamente desde la base de datos

### RESTEmployeeService
- Método: `fetch_employee_data(code: str) -> Optional[Dict]`
- Consulta a través de un servicio web REST y retorna un diccionario

## Patrón Adapter

El patrón Adapter permite que ambos servicios sean compatibles mediante:

1. **DatabaseEmployeeAdapter**: Adapta `get_employee_by_id()` a `get_employee()`
2. **RESTEmployeeAdapter**: Adapta `fetch_employee_data()` a `get_employee()` y convierte el diccionario a un objeto `Employee`

Ambos adaptadores implementan la misma interfaz `get_employee(employee_code: str)`, permitiendo que el código cliente use ambos servicios de manera uniforme.

## Configuración de la Base de Datos

### 1. Instalar dependencias

```bash
pip install -r requirements.txt
```

### 2. Configurar la contraseña de la base de datos

La aplicación usa una variable de entorno para la contraseña de PostgreSQL. Configúrala antes de ejecutar:

**Windows (CMD):**
```cmd
set DB_PASSWORD=tu_contraseña_aquí
```

**Windows (PowerShell):**
```powershell
$env:DB_PASSWORD="tu_contraseña_aquí"
```

**Linux/Mac:**
```bash
export DB_PASSWORD=tu_contraseña_aquí
```

### 3. Crear la tabla de empleados

Ejecuta el script SQL `database_setup.sql` en tu base de datos Supabase para crear la tabla `employees` con la siguiente estructura:

- `code` (VARCHAR, PRIMARY KEY): Código del empleado
- `name` (VARCHAR): Nombre del empleado
- `position` (VARCHAR): Posición/Cargo
- `department` (VARCHAR): Departamento
- `salary` (DECIMAL): Salario
- `email` (VARCHAR): Correo electrónico

Puedes ejecutar el script en el SQL Editor de Supabase o usando psql.

## Uso

```bash
python main.py <employee_code>
```

### Ejemplos:

```bash
python main.py EMP001
python main.py EMP002
python main.py EMP003
```

**Nota:** Asegúrate de tener la variable de entorno `DB_PASSWORD` configurada y que la tabla `employees` exista en tu base de datos con datos de empleados.

