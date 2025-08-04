document.addEventListener('DOMContentLoaded', function() {
  // Se ejecuta el script una vez que el DOM ha sido completamente cargado.
  // Esto asegura que todos los elementos HTML referenciados existan antes de intentar manipularlos.
  // DOM: Modelo de Objetos del Documento, interfaz que representa la estructura de un documento HTML o XML

  const track = document.querySelector('.carousel-track');
  // Referencia al contenedor que se desplaza horizontalmente.
  const members = document.querySelectorAll('.team-member');
  // Nodos de los elementos individuales dentro del carrusel.
  const prevBtn = document.querySelector('.prev-btn');
  // Botón de control para la navegación hacia atrás.
  const nextBtn = document.querySelector('.next-btn');
  // Botón de control para la navegación hacia adelante.

  // Validación de la existencia de los elementos para prevenir errores de referencia (null pointers).
  if (!track || members.length === 0 || !prevBtn || !nextBtn) {
    console.error('Elementos del carrusel no encontrados. Saliendo de la inicialización...');
    return;
  }

  const initialMemberCount = members.length;
  let itemsInView = itemsPerView();
  let currentIndex = 0; // Estado interno que rastrea el índice del primer elemento visible en el carrusel.

  /* Descripción: Clona elementos para crear un bucle infinito.
  // Clonamos los primeros 'itemsInView' elementos y los agregamos al final del track.
  // Esto crea el "espacio" para que el carrusel se mueva sin interrupciones.*/
  for (let i = 0; i < itemsInView; i++) {
    const clone = members[i].cloneNode(true);
    track.appendChild(clone);
  }
  // Refrescar la lista de miembros después de clonar.
  const allMembers = track.querySelectorAll('.team-member');

  function itemsPerView() {
    return window.innerWidth <= 768 ? 1 : 3;
  }

  function updatePosition() {
    const itemWidth = members[0].offsetWidth; // Obtiene el ancho computado del primer elemento para asegurar consistencia.
    const gap = parseInt(window.getComputedStyle(track).gap);// Obtiene el valor del "gap" CSS para una medición precisa de la distancia entre elementos.
    const offset = currentIndex * (itemWidth + gap); // Calcula el desplazamiento total necesario para alinear el elemento en 'currentIndex'.

    track.style.transform = `translateX(-${offset}px)`; // Aplica la transformación CSS para mover el 'track' al offset calculado.
  }

  // Descripción: Modificar los event listeners para el movimiento infinito.
  nextBtn.addEventListener('click', () => {
    // Si estamos en el último elemento original (o el que está justo antes del duplicado)
    if (currentIndex >= initialMemberCount) {
      // "saltamos" instantáneamente de vuelta al inicio.
      currentIndex = 0;
      updatePosition(false);// Forzamos un re-render y una nueva transición para que el movimiento continúe.
      setTimeout(() => {
        currentIndex++;
        updatePosition();
      }, 50); // Pequeño retraso para que el navegador procese el cambio de posición sin transición.
    } else {
      currentIndex++;
      updatePosition();
    }
  });

  prevBtn.addEventListener('click', () => {
    // lo mismo que el anterior pero esto es en caso de que estemos en el primer elemento
    if (currentIndex <= 0) {
      // "saltamos" instantáneamente a la posición de los clones.
      currentIndex = initialMemberCount;
      updatePosition(false);
      // Forzamos un re-render y una nueva transición para el movimiento en reversa.
      setTimeout(() => {
        currentIndex--;
        updatePosition();
      }, 50);
    } else {
      currentIndex--;
      updatePosition();
    }
  });

  window.addEventListener('resize', () => {
    itemsInView = itemsPerView();
    // No es necesario clonar de nuevo, solo re-inicializamos.
    currentIndex = 0;
    updatePosition();
  });

  updatePosition();
});