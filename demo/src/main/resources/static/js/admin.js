/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


window.onload = function(){
    cargarUsuarios();
    botonAgregar();
    saludarUser();
};

function saludarUser() {
    document.querySelector('#user').innerHTML = localStorage.email;
}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}

function getHeaders2() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json'//,
        //'Authorization': localStorage.token
    };
}
 async function cargarUsuarios() {
    const res = await fetch('api/users', {
        method:'GET',
        headers: getHeaders()
    });
    const users = await res.json();
    console.log(users);
    tbHTML = '';
    for (user of users) {
        tbHTML += '<tr>';
        tbHTML += '<td>'+user.id+'</td>';
        tbHTML += '<td>'+user.nombre+'</td>';
        tbHTML += '<td>'+user.apellido+'</td>';
        tbHTML += '<td>'+user.telefono+'</td>';
        tbHTML += '<td>'+user.email+'</td>';
        tbHTML += '<td><input onclick="eliminar('+user.id+')" type="button" value="&#128465" /></td>';
        tbHTML += '<td><input onclick="editar('+user.id+')" type="button" value="&#128393" /></td>';
        tbHTML += '</tr>';
    }
    tbHTML += '';
    
    console.log(document.querySelector('#users'));
    document.querySelector('#users').innerHTML = tbHTML;
};

function botonAgregar() {
    bHTML = '<input onclick="nuevoUsuario()" type="button" value="Nuevo" /><br />';
    document.querySelector('#forma').innerHTML = bHTML;
}

async function eliminar(id) {
    const res = await fetch('api/delete/'+id, {
        method:'DELETE',
        headers: getHeaders()
    });
    
    cargarUsuarios();
    //window.location.reload();
};

async function guardar(id) {
    url = 'api/save';
    metodo = 'POST';
    json = {
        nombre: document.querySelector('#nombre').value,
        apellido: document.querySelector('#apellido').value,
        telefono: document.querySelector('#telefono').value,
        email: document.querySelector('#email').value,
        password: document.querySelector('#password').value
    };    
    if (id !== 0) {
        url += '/'+id;
        json.id = id;
        metodo = 'PUT';
    }
    json = JSON.stringify(json);
    
    const res = await fetch(url, {
        method: metodo,
        headers: getHeaders2(),
        body: json
    });
    
    cargarUsuarios();
    if (id == 0) {
        document.querySelector('#nombre').value = '';
        document.querySelector('#apellido').value = '';
        document.querySelector('#telefono').value = '';
        document.querySelector('#email').value = '';
        document.querySelector('#password').value = '';
    }
    //window.location.reload();
    
};

function forma(id) {
    
    mensaje = 'Nuevo usuario... ';
    if (id != 0) {
        mensaje = 'Editando usuario... ';
    }
    
    fHTML = '<form>';
    fHTML += '<input id="nombre" type="text" placeholder="Nombre" /><br />';
    fHTML += '<input id="apellido" type="text" placeholder="Apellido" /><br />';
    fHTML += '<input id="telefono" type="text" placeholder="Teléfono" /><br />';
    fHTML += '<input id="email" type="text" placeholder="Email" /><br />';
    fHTML += '<input id="password" type="text" placeholder="Password" /><br />';
    fHTML += '<p>';
    fHTML += mensaje+'<br />';
    fHTML += '<input onclick="guardar('+id+')" type="button" value="&#128427" />';
    fHTML += '<input onclick="botonAgregar()" type="button" value="&#10754" />';
    fHTML += '</p>';
    fHTML += '</form>'; 
    
    return fHTML;
}


async function editar(id) {
    
    document.querySelector('#forma').innerHTML = forma(id);
    cargarUsuario(id);
    
};

async function cargarUsuario(id) {
    const res = await fetch('api/user/'+id, {
        method:'GET',
        headers: getHeaders2() 
        
    });
    const user= await res.json();
    
    document.querySelector('#nombre').value = user.nombre;
    document.querySelector('#apellido').value = user.apellido;
    document.querySelector('#telefono').value = user.telefono;
    document.querySelector('#email').value = user.email;
    document.querySelector('#password').value = '';
    
};

function nuevoUsuario() {
    document.querySelector('#forma').innerHTML = forma(0);
};




