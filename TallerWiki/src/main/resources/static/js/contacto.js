document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("contact-form");
    const inputs = form.querySelectorAll("input, textarea");
    const mensaje = document.getElementById("mensaje");
    const charCount = document.getElementById("char-count");

    // contador de caracteres en mensaje
    mensaje.addEventListener("input", () => {
        const length = mensaje.value.length;
        charCount.textContent = `${length}/300`;
    });

    form.addEventListener("submit", (e) => {
        let valido = true;

        inputs.forEach(input => {
            const errorMsg = input.nextElementSibling;
            errorMsg.style.display = "none";
            input.classList.remove("error");

            if (input.id === "nombres" || input.id === "apellidos") {
                if (input.value.trim() === "" || input.value.length > 100) {
                    errorMsg.textContent = "Este campo es obligatorio y máximo 100 caracteres.";
                    errorMsg.style.display = "block";
                    input.classList.add("error");
                    valido = false;
                }
            }

            if (input.id === "correo") {
                // Regex para correo más permisivo y en minúsculas
                const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!regex.test(input.value.trim())) {
                    errorMsg.textContent = "Correo inválido.";
                    errorMsg.style.display = "block";
                    input.classList.add("error");
                    valido = false;
                }
            }

            if (input.id === "semestre") {
                const semestre = parseInt(input.value);
                if (isNaN(semestre) || semestre < 0 || semestre > 16) {
                    errorMsg.textContent = "El semestre debe estar entre 0 y 16.";
                    errorMsg.style.display = "block";
                    input.classList.add("error");
                    valido = false;
                }
            }

            if (input.id === "mensaje") {
                if (input.value.trim() === "") {
                    errorMsg.textContent = "El mensaje es obligatorio.";
                    errorMsg.style.display = "block";
                    input.classList.add("error");
                    valido = false;
                }
            }
        });

        if (!valido) {
            e.preventDefault(); // Cancela el envío solo si hay errores
        }else {
            // Si es válido, mostrar alert de éxito y limpiar formulario
            alert("¡Formulario enviado con éxito!");
            charCount.textContent = "0/300";
        }
        // Si es válido, el formulario se envía normalmente y no se cancela el evento.
    });
});
