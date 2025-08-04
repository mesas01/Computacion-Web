document.addEventListener('DOMContentLoaded', function() {
    // Escuchamos el evento 'DOMContentLoaded' para asegurarnos de que todo el HTML
    // se ha cargado antes de que intentemos interactuar con los elementos.

    // Obtenemos la referencia al botón del menú de hamburguesa.
    const hamburger = document.querySelector('.hamburger-menu');

    // Obtenemos la referencia al contenedor de los enlaces de navegación.
    const navLinks = document.querySelector('.nav-links');

    // Verificamos que ambos elementos existan para evitar errores.
    if (!hamburger || !navLinks) {
        console.error('No se encontró el menú de hamburguesa o los enlaces de navegación. Asegúrate de que las clases son correctas en tu HTML.');
        return; // Salimos de la función si los elementos no están presentes.
    }

    // Agregamos un 'event listener' al botón de hamburguesa.
    // Cuando se hace clic en el botón, la función que está dentro se ejecuta.
    hamburger.addEventListener('click', function() {
        // La función 'classList.toggle' agrega la clase 'active' si no está presente,
        // y la quita si ya lo está. Esto es lo que crea el efecto de "activar/desactivar".

        // Al contenedor de enlaces, le añadimos/quitamos la clase 'active'.
        // El CSS se encarga de mostrar/ocultar el menú cuando esta clase está presente.
        navLinks.classList.toggle('active');

        // Al botón de hamburguesa, le añadimos/quitamos la clase 'active'.
        // Esto se usa en el CSS para cambiar la apariencia del botón (por ejemplo, para convertir las líneas en una 'X').
        hamburger.classList.toggle('active');
    });

    // Esta parte es opcional pero mejora la experiencia en móviles.
    // Al hacer clic en cualquier enlace dentro del menú, el menú se cierra automáticamente.
    navLinks.querySelectorAll('a').forEach(link => {
        link.addEventListener('click', () => {
            // Removemos las clases 'active' del menú y del botón para cerrar el menú.
            navLinks.classList.remove('active');
            hamburger.classList.remove('active');
        });
    });
});