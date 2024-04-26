import { useContext, useState } from "react";
import { FloatingInput } from "../components/forms/FloatingInput";
import { validateLogin, loginFormatter } from "../services/auth/formValidation";
import { Link } from "react-router-dom";
import { routes, urlPOST } from "../routes/routes";
import axios from 'axios';
import { Navigate } from "react-router-dom";
import { UserContext } from "../context/User/UserContext";
import { Spinner } from "flowbite-react";

export function Login(){

    
    const [user, setUser] = useContext(UserContext)

    const [redirect, setRedirect] = useState(false)
    const [formAlert, setFormAlert] = useState(null)
    const [loading, setLoading] = useState(false)

    const [values, setValues]=useState({
        username:"",
        password:"",
    })

    const handleForm = (event) => {
        event.preventDefault()
        setLoading(true)
        
        if( validateLogin(values) ){
            setFormAlert(null)
            
            axios.post(urlPOST.login, loginFormatter(values))
            .then(function (response) {
                
                setUser({
                    ...user,
                    token: response.data.jwToken,
                })

                setRedirect(true)
            })
            .catch(function (error) {
                console.log(error);
                setFormAlert("Ocurrio un error. Intentalo mas tarde.")
            });
        }
        else{
            setFormAlert("Oh no! Revisa bien los campos!")
            setLoading(false)
        }

    }

    const handleInputChange = (event) => {
        const {name, value} = event.target
        setValues({
            ...values,
            [name]: value,
        })
    }

    return ( redirect? <Navigate to={routes.home}/> :
        <section className="bg-primary w-screen h-screen flex justify-center items-center">
            <article className="relative hidden lg:flex bg-[#2F2C5F] w-1/2 p-5 h-full items-center justify-center">
                <figure className=" bg-[#D9D9D950] rounded-full aspect-square flex items-center justify-center max-w-[31rem] w-3/4">
                    <img src="https://cdn.discordapp.com/attachments/1225142769270657216/1233143515333005412/image.png?ex=662c05e4&is=662ab464&hm=bef6b176428d8043745eb2ba6d8e35118e8fa62edb1950026180806c5108204a&"/>
                </figure>
                <figure className=" absolute w-44 aspect-square top-0 left-0">
                    <img src="../../public/img/icon.png"/>
                </figure>
            </article>

            <article className="flex justify-center items-center h-full lg:w-1/2 relative">
                <div className=" w-max-content bg-gray-800 p-11 rounded-xl relative">
                            
                    <figure className="hidden md:flex absolute lg:hidden w-20 right-0 top-0">
                            <img src="../../public/img/icon.png"/>
                    </figure>

                    <h1 className="text-gray-100 mb-7 text-2xl"> Inicio Sesion </h1>

                    <form className="min-w-60 max-w-md w-max" onSubmit={handleForm}>

                        <FloatingInput type="text" name="username" id="username"  placeholder="Tomzi.les" label="Username" OnChange={handleInputChange} Value={values.username}/>
                        <FloatingInput type="password" name="password" id="contraseña" label="Contraseña" OnChange={handleInputChange} Value={values.password}/>
                       
                        <p className="text-gray-100 mb-7 text-xs">
                                <Link to={""} className="text-violet-400 hover:text-violet-500"> ¿Olvidaste tu contraseña? </Link>
                        </p>
                    
                        { !!formAlert && 
                            <p className="text-red-500 my-5 text-xs">
                                {formAlert}
                            </p>
                        }
                        
                        <button type="submit" className="text-white bg-indigo-700 hover:bg-indigo-800 focus:ring-1 focus:outline-none focus:ring-indigo-500 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center my-5">Login</button>
                        { loading && <div className="mx-3 inline-block"><Spinner color={"purple"}/></div>}
                    </form>

                    <p className="text-gray-100 my-2 text-xs">
                            ¿Sos nuevo? <Link to={routes.register} className="text-indigo-400 hover:text-indigo-500"> Registrate </Link>
                    </p>
                    <p className="text-indigo-400 text-xs hover:text-indigo-500">
                            <Link to={routes.enterpriseRegister}> Registra tu empresa </Link>
                    </p>
                </div>

            </article>

        </section>
        )

   }