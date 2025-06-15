let menuVisible = false;
//Función que oculta o muestra el menu
function mostrarOcultarMenu(){
    if(menuVisible){
        document.getElementById("nav").classList ="";
        menuVisible = false;
    }else{
        document.getElementById("nav").classList ="responsive";
        menuVisible = true;
    }
}
function seleccionar(){
    //oculto el menu una vez que selecciono una opcion
    document.getElementById("nav").classList = "";
    menuVisible = false;
}

function showSuccessMessage() {
    var message = document.getElementById('successMessage');
    message.classList.remove('hidden'); // Muestra el mensaje
    message.style.opacity = 1; // Asegúrate de que la opacidad sea 1

    setTimeout(function() {
        message.style.opacity = 0; // Desvanece el mensaje
        setTimeout(function() {
            message.classList.add('hidden'); // Oculta el mensaje completamente
        }, 1000); // Espera a que termine la transición de desvanecimiento
    }, 3000); // Muestra el mensaje por 3 segundos
}
