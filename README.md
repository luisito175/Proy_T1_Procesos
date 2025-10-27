# Documentación del Proyecto: Survival App

## 1. Visión General del Proyecto

**Survival App** es una aplicación para Android diseñada como una navaja suiza de utilidades rápidas, orientada a situaciones de emergencia o necesidad. La aplicación centraliza en una única pantalla el acceso a funciones críticas del dispositivo y a configuraciones personalizadas por el usuario.

- **Nombre del Paquete**: `com.example.sosphone`
- **Versión Mínima de Android (minSdk)**: 26 (Android 8.0 Oreo)
- **Versión de SDK de Compilación (compileSdk)**: 36 (Android 15)

## 2. Funcionalidades Implementadas

La pantalla principal (`MainActivity`) da acceso a todas las funciones clave:

### a. Llamada de Emergencia (`llamada.kt`)

- **Disparador**: Botón de llamada en la pantalla principal.
- **Funcionalidad**: Inicia una llamada directa al número de emergencias "112".
- **Implementación Técnica**:
    - Se utiliza un `Intent` con la acción `Intent.ACTION_CALL`.
    - **Gestión de Permisos**: La aplicación solicita el permiso `android.permission.CALL_PHONE` en tiempo de ejecución. Si el usuario lo concede, se realiza la llamada. Si lo deniega, se muestra un mensaje `Toast` y se le redirige a los ajustes de la app para que pueda concederlo manualmente.

### b. Acceso a Ajustes del Sistema

- **Ahorro de Batería**:
    - **Disparador**: Botón de ahorro de batería.
    - **Funcionalidad**: Abre directamente la pantalla de ajustes de ahorro de batería del sistema operativo.
    - **Implementación Técnica**: Se usa un `Intent` con la acción `Settings.ACTION_BATTERY_SAVER_SETTINGS`.

- **Configuración de la App (`ConfActivity.kt`)**:
    - **Disparador**: Botón de ajustes.
    - **Funcionalidad**: Permite al usuario introducir y guardar un número de teléfono personalizado.
    - **Implementación Técnica**:
        - Los datos se guardan usando `SharedPreferences` en un fichero llamado `app_preferences`.
        - El número se almacena con la clave `"numero"`.
        - Al volver a entrar, la `Activity` carga el número guardado y lo muestra en el `EditText`.
        - Se utiliza `ViewBinding` para acceder a las vistas de forma segura.

### c. Manual de Supervivencia Web

- **Disparador**: Botón de web.
- **Funcionalidad**: Abre el navegador por defecto del dispositivo con una URL.
- **Implementación Técnica**:
    - La URL se obtiene de `SharedPreferences` (fichero "url", clave "url").
    - Si no hay ninguna URL guardada, se usa una por defecto (`https://esupervivencia.com/...`).
    - Se utiliza un `Intent` con la acción `Intent.ACTION_VIEW`.

### d. Alarma Rápida

- **Disparador**: Botón de alarma.
- **Funcionalidad**: Programa una alarma en la aplicación de reloj del sistema para que suene 2 minutos después del momento actual.
- **Implementación Técnica**: 
    - Se usa un `Intent` con la acción `AlarmClock.ACTION_SET_ALARM`.
    - Se añaden los `extras` `EXTRA_MESSAGE`, `EXTRA_HOUR` y `EXTRA_MINUTES` para configurar la alarma.

## 3. Estructura del Código y Componentes Clave

### a. Actividades

- **`MainActivity.kt`**: Es la pantalla principal y el centro de navegación. Contiene los `listeners` para todos los botones que lanzan las demás funcionalidades y `Activities`.
- **`llamada.kt`**: Actividad dedicada exclusivamente a la lógica de la llamada de emergencia y la gestión de su permiso asociado.
- **`ConfActivity.kt`**: Actividad donde el usuario puede configurar ajustes personalizados que se guardan de forma persistente.

### b. Layouts (Archivos XML)

- **`activity_main.xml`**: Define la interfaz de la pantalla principal, con una parrilla de `ImageButton` para cada función.
- **`activity_llamada.xml`**: Layout para la pantalla de llamada.
- **`activity_conf.xml`**: Contiene un `EditText` para la entrada de datos y un `Button` para guardar la configuración.

### c. Componentes de Android Utilizados

- **`Intents`**: Utilizados extensivamente para:
    - Navegar entre las `Activities` de la propia aplicación (`MainActivity` -> `ConfActivity`).
    - Comunicarse con otras aplicaciones del sistema (Navegador, Teléfono, Reloj, Ajustes).
- **`SharedPreferences`**: Empleado como mecanismo de almacenamiento persistente para guardar la configuración del usuario (URL del manual, número de teléfono personalizado).
- **Permisos en Tiempo de Ejecución**: Se implementa el flujo moderno para solicitar permisos peligrosos (`CALL_PHONE`), manejando tanto la concesión como la denegación por parte del usuario.
- **`ViewBinding`**: Se usa en `ConfActivity` para interactuar con los elementos de la UI de forma segura y eficiente, eliminando la necesidad de `findViewById`.
