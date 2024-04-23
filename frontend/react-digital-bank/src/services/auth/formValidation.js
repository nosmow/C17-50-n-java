function validarDNI(dni) {
    // Expresión regular para verificar si el DNI contiene solo números
    var patron = /^[0-9]+$/;
    // Devuelve true si el DNI solo contiene números, de lo contrario, devuelve false

    if (dni=="" || dni.length!=8)
        return false

    if(!patron.test(dni))
        return false

    return true;
}

function validarCUIT(cuit) {
    // Eliminar guiones del CUIT
    cuit = cuit.replace(/-/g, '');
  
    var patron = /^[0-9]+$/;
    
    return patron.test(cuit) && cuit.length==11;
}

function validarUsername(nombreUsuario) {
    // Longitud mínima y máxima del nombre de usuario
    var longitudMinima = 3;
    var longitudMaxima = 20;

    // Expresión regular para caracteres permitidos (letras, números y guiones bajos)
    var patron = /^[a-zA-Z0-9_.]+$/;

    if (nombreUsuario.length < longitudMinima || nombreUsuario.length > longitudMaxima) {
        return false;
    }

    if (!patron.test(nombreUsuario)) {
        return false;
    }

    return true;
}

function validarEmail(email) {
    var patron = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!patron.test(email) || email.length > 254)
        return false

    // Verificar que el dominio tenga al menos dos partes separadas por punto
    var partes = email.split("@");
    if (partes.length !== 2 || partes[1].split(".").length < 2)
        return false

    // Verificar que no haya caracteres especiales al inicio o al final del email
    if (email.startsWith(".") || email.startsWith("@") || email.endsWith(".") || email.endsWith("@")) {
        return false;
    }

    // Verificar caracteres permitidos en el nombre de usuario y el dominio
    var nombreUsuario = partes[0];
    var dominio = partes[1];
    var caracteresPermitidos = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+$/;

    if (!caracteresPermitidos.test(nombreUsuario) || !caracteresPermitidos.test(dominio))
        return false

    return true;
}

function validarCelular(phone) {
    // Eliminar espacios y guiones del número de celular
    phone = phone.replace(/\s/g, '').replace(/-/g, '');
  
    var patron = /^[0-9]{10}$/;
  
    // Verificar si el número de celular cumple con el patrón
    return patron.test(phone);
}

function validarContraseñas(password, passwordconfirm) {
    if (password != passwordconfirm) 
        return false
    
    if (password.length < 8) 
        return false

    var contieneMayuscula = /[A-Z]/.test(password);
    var contieneNumero = /[0-9]/.test(password);

    if (!contieneMayuscula || !contieneNumero) 
        return false

    return true;
}
  
  

export function validateUser( props ){
    const { name, lastname, dni, username, role, email, phone, password, passwordconfirm } = props

    const expretion = (
        name!=""                                        &&
        lastname!=""                                    &&
        validarDNI(dni)                                 &&
        validarUsername(username)                       &&
        role=="ROLE_USER"                               &&
        validarEmail(email)                             &&
        validarCelular(phone)                           &&
        validarContraseñas(password, passwordconfirm)
    )

    return (expretion)
}

export function userFormatter ( props ){
    let { name, lastname, dni, username, role, email, phone, password, passwordconfirm } = props

    phone = phone.replace(/\s/g, '').replace(/-/g, '');


    return { name, lastname, dni, username, role, email, phone, password, passwordconfirm }
}

export function isWrongUser ( props ){
    const { name, lastname, dni, username, email, phone, password } = props

    return {
        name: name=="",
        lastname: lastname=="",
        username: !validarUsername(username),
        phone: !validarCelular(phone),
        email: !validarEmail(email),
        dni: !validarDNI(dni),
        password: !validarContraseñas(password)
    }

}

export function validateEnterprise( props ){
    const { name, lastname, cuit, username, company, role, email, phone, password, passwordconfirm} = props
    console.log({ name, lastname, cuit, username, company, role, email, phone, password, passwordconfirm})
    const expretion = (
        name!=""                                        &&
        lastname!=""                                    &&
        validarCUIT(cuit)                               &&
        validarUsername(username)                       &&
        company!=""                                     &&
        role=="ROLE_BUSINESS"                           &&
        validarEmail(email)                             &&
        validarCelular(phone)                           &&
        validarContraseñas(password, passwordconfirm)
    )

    return expretion
}

export function enterpriseFormatter ( props ){
    let { name, lastname, cuit, username, company, role, email, phone, password, passwordconfirm} = props

    cuit = cuit.replace(/-/g, '');
    phone = phone.replace(/\s/g, '').replace(/-/g, '');


    return { name, lastname, cuit, username, company, role, email, phone, password, passwordconfirm }
}

export function isWrongEnterprise ( props ){
    const { name, lastname, cuit, username, company, email, phone, password} = props

    return{
        name: name=="",
        lastname: lastname=="",
        username: !validarUsername(username),
        phone: !validarCelular(phone),
        email: !validarEmail(email),
        company: company=="",
        cuit: !validarCUIT(cuit),
        password: !validarContraseñas(password)
    }

}



export function validateLogin( props ){
    const { username,password } = props

    const expretion = (
        validarUsername(username)                       &&
        validarContraseñas(password, password)
    )

    return (expretion)
}

export function loginFormatter ( props ){
    let {  username, password } = props

    return { username, password }
}