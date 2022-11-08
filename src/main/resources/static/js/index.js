function mostrarAlerta(){
    Swal.fire('Usted se a registrado con exito"')
}
function registrado(){
    Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Usted se a registrado con Exito',
        showConfirmButton: false,
        timer: 1500
      })
}