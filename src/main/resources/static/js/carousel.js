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

  let currentIndex = 0;
  // Estado interno que rastrea el índice del primer elemento visible en el carrusel.

  /* descripción: Recalcula y aplica la transformación de desplazamiento (translateX) al carrusel
     basándose en el índice actual y el tamaño de los elementos.*/
  function updatePosition() {
    const itemWidth = members[0].offsetWidth;
    // Obtiene el ancho computado del primer elemento para asegurar consistencia.
    const gap = parseInt(window.getComputedStyle(track).gap);
    // Obtiene el valor del "gap" CSS para una medición precisa de la distancia entre elementos.
    const offset = currentIndex * (itemWidth + gap);
    // Calcula el desplazamiento total necesario para alinear el elemento en 'currentIndex'.
    track.style.transform = `translateX(-${offset}px)`;
    // Aplica la transformación CSS para mover el 'track' al offset calculado.

    // Lógica de UI para los controles de navegación.
    // Se deshabilitan los botones para indicar al usuario que no hay más elementos en esa dirección.
    prevBtn.disabled = (currentIndex === 0);
    nextBtn.disabled = (currentIndex >= members.length - itemsPerView());
  }

  /* Descripción: Determina la cantidad de elementos visibles en el carrusel según el ancho de la ventana.
   * retorna: {number} El número de elementos que deben ser visibles en la vista actual. */
  function itemsPerView() {
    if (window.innerWidth <= 768) {
      return 1;
      // Retorna 1 para vistas móviles, permitiendo que el carrusel se desplace de a un elemento.
    } else {
      return 3;
      // Retorna 3 para vistas de escritorio, manteniendo la visualización de tres elementos.
    }
  }

  // Handlers de eventos para los botones de navegación.
  nextBtn.addEventListener('click', () => {
    const maxIndex = members.length - itemsPerView();
    // Limita el desplazamiento máximo para evitar que el carrusel se mueva a un espacio vacío.
    if (currentIndex < maxIndex) {
      currentIndex++;
      updatePosition();
    }
  });

  prevBtn.addEventListener('click', () => {
    if (currentIndex > 0) {
      currentIndex--;
      updatePosition();
    }
  });

  // Se agrega un 'event listener' para el evento 'resize' de la ventana.
  // Esto asegura que el carrusel se adapte correctamente si el usuario cambia el tamaño del navegador,
  // por ejemplo, al rotar un dispositivo móvil o redimensionar la ventana de un escritorio.
  window.addEventListener('resize', () => {
    currentIndex = 0; // Reinicia la posición para evitar un estado inconsistente en el reajuste.
    updatePosition();
  });

  // Inicialización del carrusel en su estado inicial.
  updatePosition();
});