import { useState } from "react";
import { FloatingInput } from "../components/forms/FloatingInput";
import { validateEnterprise, enterpriseFormatter, isWrongEnterprise } from "../services/auth/formValidation";
import axios from 'axios';
import { routes, urlPOST } from "../routes/routes";
import { Navigate } from "react-router-dom";

export function RegisterEnterprise(){

    const [redirect, setRedirect] = useState(false)
    const [formAlert, setFormAlert] = useState(null)

    const [isWrong, setIsWrong] = useState({
        name:false,
        lastname:false,
        company: false,
        username:false,
        phone:false,
        email:false,
        cuit: false,
        password: false
    })

    const [values, setValues]=useState({
        name:"",
        lastname:"",
        cuit:"",
        username:"",
        company:"",
        role: "ROLE_BUSINESS",
        email:"",
        phone:"",
        password:"",
        passwordconfirm:"",
    })

    const handleForm = (event) => {
        event.preventDefault()
        
        if( validateEnterprise(values) ){
            // Enviar formulario

            setFormAlert(null)
            
            axios.post(urlPOST.enterpriseRegister, enterpriseFormatter(values))
            .then(function (response) {
                setRedirect(true)
            })
            .catch(function (error) {
                console.log(error);
                setFormAlert("Ocurrio un error. Ya existe una empresa asociada a estos datos.")
            });
        }
        else{
            setFormAlert("Oh no! hubo un error. Revisa bien los campos!")
            setIsWrong( isWrongEnterprise(values) )
        }

    }

    const handleInputChange = (event) => {
        const {name, value} = event.target
        setValues({
            ...values,
            [name]: value,
        })
    }

    return ( redirect ? <Navigate to={routes.login}/>  :
        <section className="bg-primary w-screen h-screen flex justify-center items-center">
            <article className="relative hidden lg:flex bg-[#2F2C5F] w-1/2 p-5 h-full items-center justify-center">
                <figure className=" bg-[#D9D9D950] rounded-full aspect-square flex items-center justify-center max-w-[31rem] w-3/4">
                    <img src="https://cdn.discordapp.com/attachments/1225142769270657216/1227741717801861182/image.png?ex=66298293&is=66170d93&hm=46d3b92d19e7cc731546ebbb199fc5df79b29f62277952b2fa8f9de4957d1c78&"/>
                </figure>
                <figure className=" absolute w-44 aspect-square top-0 left-0">
                    <img src="../../public/img/icon.png"/>
                </figure>
            </article>

            <article className="flex justify-center items-center h-full lg:w-1/2 relative">
                <div className=" w-fit bg-gray-800 p-7 rounded-xl relative">
                            
                    <figure className="hidden md:flex absolute lg:hidden w-20 right-0 top-0">
                            <img src="../../public/img/icon.png"/>
                    </figure>
                    
                    <h1 className="text-gray-100 mb-7 text-2xl"> Registra tu empresa! </h1>
                    
                    <form className="min-w-60 max-w-md w-full" onSubmit={handleForm}>
                        
                        <div className="grid md:grid-cols-2 md:gap-6">

                            <FloatingInput wrong={isWrong.name} type="text" name="name" id="nombre"  placeholder="Tomas" label="Nombre" OnChange={handleInputChange} Value={values.name}/>
                            <FloatingInput wrong={isWrong.lastname} type="text" name="lastname" id="apellido"  placeholder="Gonzalez" label="Apellido" OnChange={handleInputChange} Value={values.lastname}/>
                
                        </div>

                        <div className="grid md:grid-cols-2 md:gap-6">
                            <FloatingInput wrong={isWrong.cuit} type="text" name="cuit" id="cuit"  placeholder="25-05268945-7" label="CUIT" OnChange={handleInputChange} Value={values.cuit}/>
                            <FloatingInput wrong={isWrong.phone} type="tel" name="phone" id="celular"  placeholder="11 5713-0217" label="Celular" OnChange={handleInputChange}  Value={values.phone}/>
                            
                        </div>
        
                        <div className="grid md:grid-cols-2 md:gap-6">
                            <FloatingInput wrong={isWrong.username} type="text" name="username" id="username"  placeholder="Tomzi.les" label="Username" OnChange={handleInputChange} Value={values.username}/>
                            <FloatingInput wrong={isWrong.company} type="text" name="company" id="company"  placeholder="Feminanzas" label="Nombre de Compañia" OnChange={handleInputChange}  Value={values.company}/>
                            
                        </div>

                        <FloatingInput wrong={isWrong.email} type="email" name="email" id="email"  placeholder="tomas.g@gmail.com" label="Email" OnChange={handleInputChange} Value={values.email}/>

                        <FloatingInput wrong={isWrong.password} type="password" name="password" id="contraseña" label="Contraseña" OnChange={handleInputChange} Value={values.password}/>
                        <FloatingInput wrong={isWrong.password} type="password" name="passwordconfirm" id="confirmarcontraseña" label="Confirmar Contraseña" OnChange={handleInputChange} Value={values.passwordconfirm}/>

                        { !!formAlert && 
                            <p className="text-red-500 my-5 text-xs">
                                {formAlert}
                            </p>
                        }
                        
                        <button type="submit" className="text-white bg-indigo-700 hover:bg-indigo-800 focus:ring-1 focus:outline-none focus:ring-indigo-500 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center">Submit</button>
                    </form>

                </div>

            </article>

        </section>
        )

   }