document.addEventListener("DOMContentLoaded", () => {
    listarClientes();
    document.getElementById("btnGuardar").addEventListener("click", guardarCliente);
    document.getElementById("foto").addEventListener("change", mostrarPreview);
});

function listarClientes() {
    fetch("/api/clientes")
        .then(response => response.json())
        .then(clientes => {
            const tbody = document.querySelector("tbody");
            tbody.innerHTML = "";

            clientes.forEach(cliente => {
                tbody.innerHTML += `
                <tr>
                    <td>${cliente.identificacion}</td>
                    <td>${cliente.primerNombre} ${cliente.primerApellido}</td>
                    <td>${cliente.email}</td>
                    <td>${cliente.telefono}</td>
                    <td>

                        <button
                            class="btn btn-sm btn-light border"
                            onclick="editarCliente(${cliente.id})">
                            Editar
                        </button>

                        <button
                            class="btn btn-sm btn-dark"
                            onclick="eliminarCliente(${cliente.id})">
                            Eliminar
                        </button>
                         </td>
                </tr>`;
            });
        });
}

function mostrarPreview() {
    const archivo = document.getElementById("foto").files[0];
    const preview = document.getElementById("preview");

    if (archivo) {
        preview.src = URL.createObjectURL(archivo);
        preview.style.display = "block";
    } else {
        preview.src = "";
        preview.style.display = "none";
    }
}

function guardarCliente() {

    const id = document.getElementById("id").value;
    const archivo = document.getElementById("foto").files[0];

    const cliente = {
        tipoIdentificacion: document.getElementById("tipoIdentificacion").value,
        identificacion: document.getElementById("identificacion").value,
        primerNombre: document.getElementById("primerNombre").value,
        segundoNombre: document.getElementById("segundoNombre").value,
        primerApellido: document.getElementById("primerApellido").value,
        segundoApellido: document.getElementById("segundoApellido").value,
        direccion: document.getElementById("direccion").value,
        telefono: document.getElementById("telefono").value,
        email: document.getElementById("email").value,
        ocupacion: document.getElementById("ocupacion").value,
        fechaNacimiento: document.getElementById("fechaNacimiento").value,
        foto: archivo ? archivo.name : ""
    };

    fetch(id ? `/api/clientes/${id}` : "/api/clientes", {
        method: id ? "PUT" : "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(cliente)
    })
    .then(async response => {
        if (!response.ok) {
            const mensaje = await response.text();
            throw new Error(mensaje);
        }
        return response.json();
    })
    .then(() => {
        bootstrap.Modal.getInstance(document.getElementById("clienteModal")).hide();
        document.getElementById("clienteForm").reset();
        document.getElementById("id").value = "";
        document.getElementById("preview").style.display = "none";
        document.getElementById("preview").src = "";
        listarClientes();

        Swal.fire({
            icon: "success",
            title: id ? "Cliente actualizado" : "Cliente registrado",
            text: id
                ? "El cliente se actualizó correctamente."
                : "El cliente se registró correctamente."
        });
    })
    .catch(error => {
        Swal.fire({
            icon: "error",
            title: "Error",
            text: error.message
        });
    });
}

function editarCliente(id) {
    fetch(`/api/clientes/${id}`)
        .then(response => response.json())
        .then(cliente => {

            document.getElementById("id").value = cliente.id;
            document.getElementById("tipoIdentificacion").value = cliente.tipoIdentificacion;
            document.getElementById("identificacion").value = cliente.identificacion;
            document.getElementById("primerNombre").value = cliente.primerNombre;
            document.getElementById("segundoNombre").value = cliente.segundoNombre;
            document.getElementById("primerApellido").value = cliente.primerApellido;
            document.getElementById("segundoApellido").value = cliente.segundoApellido;
            document.getElementById("direccion").value = cliente.direccion;
            document.getElementById("telefono").value = cliente.telefono;
            document.getElementById("email").value = cliente.email;
            document.getElementById("ocupacion").value = cliente.ocupacion;
            document.getElementById("fechaNacimiento").value = cliente.fechaNacimiento;

            new bootstrap.Modal(document.getElementById("clienteModal")).show();
        });
}

function eliminarCliente(id) {
    Swal.fire({
        title: "¿Desea eliminar al cliente?",
        text: "Esta acción no se puede deshacer.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar",
        confirmButtonColor: "#dc3545"
    }).then(result => {

        if (!result.isConfirmed) {
            return;
        }

        fetch(`/api/clientes/${id}`, {
            method: "DELETE"
        })
        .then(async response => {
            if (!response.ok) {
                const mensaje = await response.text();
                throw new Error(mensaje);
            }

            listarClientes();

            Swal.fire({
                icon: "success",
                title: "Cliente eliminado",
                text: "El cliente fue eliminado correctamente."
            });
        })
        .catch(error => {
            Swal.fire({
                icon: "error",
                title: "Error",
                text: error.message
            });
        });
    });
}


function exportarExcel() {
    window.location.href = "/api/clientes/excel";
}