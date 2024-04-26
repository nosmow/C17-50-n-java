import { useContext, useEffect, useState } from "react"
import { UserContext } from "../context/User/UserContext"
import { Header } from "../layout/Header/Header"
import { Card } from "../components/user/Card"
import { IconArrowUp } from "../assets/icons/IconArrowUp"
import { IconArrowDown } from "../assets/icons/IconArrowDown"
import { Link } from "react-router-dom"
import axios from 'axios';
import { routes, urlPOST } from "../routes/routes"
import { Spinner } from "flowbite-react";
import { numberWithCommas } from "../services/auth/formValidation"

export function Home(){

   const [user, setUser] = useContext(UserContext)

   const [loading, setLoading] = useState(true)

   const recharge = () => {
         setLoading(true)

         axios.get(urlPOST.home, {
            headers: {
            Authorization: `Bearer ${user.token}` // Use Bearer token format
            }
         })

         .then(function (response) {
            console.log(response.data)

            setUser({
               ...user,
               username: response.data.accountDetails.username,
               balance: response.data.accountDetails.balance,
               account: response.data.accountDetails.account,
               transactions: response.data.transactions
            })

            console.log(user)

            setLoading(false)
         })

         .catch(function (error) {
            console.log(error);
         });
   }

   useEffect(() => {

            recharge()
      
    }, []);


   return ( 
      loading ? <div className="w-full h-screen flex items-center justify-center"><Spinner className="h-24 w-24" color={"purple"} size="xl" /></div> :
         <section className="">
            <Header></Header>
            <article className="flex gap-14 mt-20 max-w-screen-md mx-[10%]">
               <Card></Card>

               <div className="flex flex-col gap-5 text-gray-100">
                  <span>Saldo disponible:</span>
                  <h2 className=" text-5xl"> 
                     <span className=" bg-clip-text text-transparent font-bolder bg-gradient-to-r from-indigo-700 to-violet-400 mx-3">$</span>
                     {numberWithCommas( user.balance )} 
                  </h2>

                  <div className="flex gap-5">
                     <Link to={routes.send} className="bg-gradient-to-r from-indigo-700 to-violet-800 min-w-32 py-2 rounded-lg flex items-center justify-center gap-3 hover:hue-rotate-30 transition-all"> <IconArrowUp/>Enviar</Link>
                     <button onClick={ recharge } className="bg-gradient-to-r from-indigo-700 to-violet-800 min-w-32 py-2 rounded-lg flex items-center justify-center gap-3 hover:hue-rotate-30 transition-all"> <IconArrowDown/>Recibir</button>
                  </div>
               </div>
            </article>

            <article className="flex flex-col gap-3 my-16 w-full px-[10%] text-gray-100">
               <h3>Ultimos Movimientos:</h3>
               <hr className="mb-7"/>
               
               <div className="grid grid-cols-[max-content,1fr,100px] grid-flow-row gap-3 text-center text-sm text-gray-200">
                  {/* Aca van los movimientos */}
                  {user.transactions.map( (elem, key) => {
                  return <>
                     <span className="" key={key}>{elem.transactionDate.replace(/-/g, " / ")}</span>
                     <span className="" key={key*10}>{elem.username}</span>
                     {elem.amount>0 ? 
                     <span className="" key={key*100}>+{elem.amount} $</span> 
                     : <span className="text-red-500" key={key}>{elem.amount} $</span>}
                     
                     </>
                  })}
               </div>
               
               <hr className="mt-7 mb-2"/>
               <Link to={routes.transactions}>Ver Mas</Link>

            </article>
         </section>
   )
}