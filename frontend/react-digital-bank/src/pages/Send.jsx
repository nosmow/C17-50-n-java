import { useContext, useState } from "react"
import { FloatingInput } from "../components/forms/FloatingInput"
import { IconArrowUp } from "../assets/icons/IconArrowUp"
import { validarUsername, numberWithCommas } from "../services/auth/formValidation";
import axios from 'axios';
import { routes, urlPOST } from "../routes/routes";
import { UserContext } from "../context/User/UserContext";
import { Link, Navigate } from "react-router-dom";
import { Alert, Spinner } from "flowbite-react";
import { IconArrowLeft } from "../assets/icons/IconArrowLeft";

export function Send(){
    
    const [user] = useContext(UserContext)

    
    const [redirect, setRedirect] = useState(false)
    const [formAlert, setFormAlert] = useState(null)
    const [loading, setLoading] = useState(false)
    const [success, setSuccess] = useState(false)

    const [values, setValues]=useState({
        accountReceiver:"",
        amount:""
    })

    const [isWrong, setIsWrong] = useState({
        accountReceiver:false,
        amount:false,
    })


    const handleForm = (event) => {
        event.preventDefault()
        setLoading(true)

        if ( validarUsername(values.accountReceiver) ){
            console.log("a")

            axios.post(urlPOST.send, 
                {
                    accountReceiver: values.accountReceiver,
                    amount: values.amount.replace(/\D/g, "")
                },
                {
                    headers: {
                    Authorization: `Bearer ${user.token}` // Use Bearer token format
                    }
                })

            .then(function (response) {
                        // Transferencia exitosa.
                        setSuccess(true)
                        setLoading(false)
                        setTimeout(() => {
                            setRedirect(true)
                        }, 2500);
            })

            .catch(function (error) {
                        setLoading(false)
                        setFormAlert("Ocurrio un error. Intentalo de nuevo mas tarde.")
                        console.log(error);
            });
        }

        else{
            setLoading(false)
            setIsWrong({
                accountReceiver:true,
                amount:false
            })
        }
        
    }

    const handleInputChange = (event) => {
        const {name, value} = event.target

        if(name == "amount"){
            setValues({
                ...values,
                [name]: numberWithCommas(value),
            })
        }

        else {
            setValues({
                ...values,
                [name]: value,
            })
        }
    }


    return ( redirect ? <Navigate to={routes.home}></Navigate> :
        <section>
            <article className="flex justify-center items-center h-screen relative bg-gradient-to-tl from-indigo-950 to-violet-900">
                <div className=" w-fit bg-gray-800 p-7 rounded-xl relative">
                    <div className="flex h-max items-center gap-5 mb-10">
                        <Link to={routes.home} className=" group ">
                            <IconArrowLeft/>
                        </Link>
                        <h1 className="text-gray-100 text-2xl"> Envia tu dinero! </h1>
                    </div>

                    <form className="min-w-60 max-w-md w-full" onSubmit={handleForm}>
                        
                        <FloatingInput wrong={isWrong.accountReceiver} type="text" name="accountReceiver" id="accountReceiver"  placeholder="CVV" label="Remitente" OnChange={handleInputChange} Value={values.accountReceiver}/>
                        
                        <FloatingInput wrong={isWrong.amount} type="tel" name="amount" id="amount"  placeholder="0,00" label="Monto:" OnChange={handleInputChange} Value={values.amount}/>


                        {
                         !!formAlert && 
                         <p className="text-red-500 my-5 text-xs">
                             {formAlert}
                         </p>
                        }

                        <div className="flex justify-between items-center h-11 mt-7">

                            <button type="submit" className="bg-gradient-to-r from-indigo-700 to-violet-800 min-w-32 h-11 rounded-lg flex items-center justify-center gap-3 text-gray-100 hover:hue-rotate-30 transition-all" disabled={(loading || success)}> <IconArrowUp/>Enviar</button>
                            { loading && <div className="mx-3 inline-block"><Spinner color={"purple"} size="lg"/></div>}

                        </div>
                        <div className="my-12">
                            { success && <Alert color="success" onDismiss={() => { setSuccess(false); setLoading(true)}}><span className="font-medium">Transferencia Exitosa!</span></Alert> }
                        </div>
                    </form>
                        <p className=" text-gray-100 my-10 text-sm">
                            - Las transferencias son en numeros enteros.
                            <span className="my-5 block"></span>
                            ¿Escribiste bien el CVV?
                        </p>
                </div>
            </article>
        </section>
    )
}