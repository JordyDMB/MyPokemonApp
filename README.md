MyPokemonApp

Presentación
Aplicación Android que permite consumir una lista de Pokémon de internet, mostrar los datos en una pantalla y visualizar el detalle de cada Pokémon de la lista al hacer clic.
Características principales:
  Consumo de una lista de Pokémon mediante recursos de internet.
  Visualización de la lista en una pantalla con diseño atractivo.
  Detalles completos de cada Pokémon disponibles al hacer clic en ellos.
  Interfaz de usuario intuitiva y fácil de navegar
  
Objetivo
Mostrar los recursos y habilidades de programación tales como el manejo de lenguaje java para android, buenas prácticas, arquitectura mvvm, injección de dependencias, material design, rxJava,
lambdas e interfaces funcionales, librerias de terceros como retrofit o glide.

Uso
Para poder interactuar con la aplicación es obligatorio un dispositivo con sistema operativo android igual o superior a android 7 para poder instalar y conexión a internet en el dispositivo para consumir una fuente de datos en internet.
Una vez instada, dar clic para ejecutar y consumir y visualizar la lista de pokemon, en caso de no contar con internet se presentará un mensaje de error con la posibilidad de reintentar la búsqueda.
Se ejecutará en mismo caso al momento de dar clic al pokemon para ver su detalle.

Pasos para levantar y ejecutar el Proyecto
1. Instalar o tener instalado el IDE Android Studio, se lo puede descargar desde: https://developer.android.com/studio
2. Clonar el repositorio
   Abrir el IDE Android Studio, dar clic en Nuevo -> Project from version control -> pegar la url del repositorio: https://github.com/JordyDMB/MyPokemonApp.git -> clic en clonar.
   Alternativamente podemos clonar el repositorio desde la terminal del IDE mediante el comando -> git clone https://github.com/JordyDMB/MyPokemonApp.git
3. Abrir el proyecto clonado, el IDE procederá a descargar las dependencias y configurar el archivo gradle automáticamente, asegurarse de que la máquina tenga conexión a internet para poder ejecutar este
paso correctamente. En caso de que falle, el IDE nos mostrará los errores en la ventana de logcat que deberemos corregir manualmente.
4. Una vez terminado el proceso anterior, conectar un dispositivo físico a la máquina con las opciones de desarrollador activadas, o configurar un emulador desde android studio.
Tener en cuenta el minSdk = 24 (Android 7) como versión de android mínima para poder ejecutar el proyecto.
5. Dar clic en el boton run (flecha de color verde) y esperar a que ejecute la aplicación. En caso de existir errores, el compilador nos los mostrará en la ventana de logcat y deberemos proceder a solucionarlos.
6. Finalmente, interactuar con la aplicación como se describe en la sección *USO*.
  




