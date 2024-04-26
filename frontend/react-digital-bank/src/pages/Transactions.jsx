import { useContext, useEffect, useState } from "react";
import { Header } from "../layout/Header/Header";
import axios from "axios";
import { UserContext } from "../context/User/UserContext";
import { urlPOST } from "../routes/routes";
import { Spinner } from "flowbite-react";
import { numberWithCommas } from "../services/auth/formValidation";

export function Transactions() {
    const [loading, setLoading] = useState(true)
    const [user] = useContext(UserContext)
    const [trans, setTrans] = useState([])

    const chargeTransactions =  () => {
        
        setLoading(true)

        axios.get(urlPOST.transactions, {
           headers: {
           Authorization: `Bearer ${user.token}` // Use Bearer token format
           }
        })

        .then(function (response) {
            setTrans( response.data.transactions )
            setLoading(false)
        })

        .catch(function (error) {
           console.log(error);
        });
    }

    
    useEffect(() => {

        chargeTransactions()

    }, []);

    return( loading ? <div className="w-full h-screen flex items-center justify-center"><Spinner className="h-24 w-24" color={"purple"} size="xl" /></div>:
        <section>
            <Header/>
            <article className="flex flex-col gap-3 my-16 w-full px-[10%] text-gray-100">
                <h3>Ultimos Movimientos:</h3>
                <hr className="mb-7"/>
                
                <div className="grid grid-cols-[max-content,40px,1fr,100px] grid-flow-row gap-3 text-center text-sm text-gray-200">
                {console.log("A")}
                {trans.map( (elem, key) => {
                return <>
                    <span className="" key={elem.transactionId}>{elem.transactionDate.replace(/-/g, " / ")}</span>
                    <span className="" key={elem.transactionId}> ID: {elem.transactionId}</span>
                    <span className="" key={elem.username}>{elem.username}</span>
                    {elem.amount>0 ? 
                    <span className="" key={elem.amount}>+{numberWithCommas(elem.amount)} $</span> 
                    : <span className="text-red-500" key={key}>-{numberWithCommas(elem.amount)} $</span>}
                    
                    </>
                })}

                </div>
                
                <hr className="mt-7 mb-2"/>

            </article>
        </section>
    )
}