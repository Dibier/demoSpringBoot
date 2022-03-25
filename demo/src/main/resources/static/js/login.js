/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

async function login() {
    datos = {
        email: document.querySelector('#email').value,
        password: document.querySelector('#password').value
        //testPassword: document.selectQuery('#testpassword').value
    };
    
    
    if (document.querySelector('#password').value != document.querySelector('#testpassword').value) {
        alert("Las contraseñas no coinciden.");
    } else {
    
        const request = await fetch('api/login', {
            method: 'POST',
            headers: {
                'Accept': 'aplication/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        const respuesta = await request.text();

        if (respuesta != 'Fail') {
            localStorage.token = respuesta;
            localStorage.email = datos.email;
             window.location.href ='admin.html';
             
        } else {
            alert("Datos incorrectos.");
        }
    }
};
