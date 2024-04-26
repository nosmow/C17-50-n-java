import { useContext, useEffect, useState } from "react";
import { Header } from "../layout/Header/Header";

import { DonutChart, LineChart } from '@tremor/react';
import { Spinner } from "flowbite-react";

import { UserContext } from "../context/User/UserContext";

import axios from "axios";
import { urlPOST } from "../routes/routes";

const sales = [
    {
      name: 'New York',
      sales: 980,
    },
    {
      name: 'London',
      sales: 456,
    },
    {
      name: 'Hong Kong',
      sales: 390,
    },
    {
      name: 'San Francisco',
      sales: 240,
    },
    {
      name: 'Singapore',
      sales: 190,
    },
    {
      name: 'Zurich',
      sales: 139,
    },
  ];
  
  const valueFormatter = function (number) {
    return '$ ' + new Intl.NumberFormat('us').format(number).toString();}

export function Dashboard(){
    const [loadCats, setLoadCats] = useState(true)
    const [loadTrans, setLoadTrans] = useState(true)
    const [user] = useContext(UserContext)
    const [trans, setTrans] = useState([])

    const [category, setCategory] = useState([])

    const getCategories = () => {
        setLoadCats(true)

        axios.get(urlPOST.listCategory, {
           headers: {
           Authorization: `Bearer ${user.token}` // Use Bearer token format
           }
        })

        .then(function (response) {
        //    console.log(response.data)
           setCategory(response.data)
           setLoadCats(false)
        })

        .catch(function (error) {
           console.log(error);
        });

    }
    const chargeTransactions =  () => {
        
        setLoadTrans(true)

        axios.get(urlPOST.transactions, {
           headers: {
           Authorization: `Bearer ${user.token}` // Use Bearer token format
           }
        })

        .then(function (response) {
            // setTrans( response.data.transactions )
            const info= response.data.transactions 
            let cont = user.balance

            let arr=[]

            arr.unshift(
                {                  
                    date: "Today",
                    Balance: cont,
                }
            )

            for(let i = info.length-1; i>=0; i--){
                cont= cont - info[i].amount
                arr.unshift(
                    {                  
                        date: info[i].transactionDate,
                        Balance: cont,
                    }
                )
            
            }


            setTrans(arr)
            setLoadTrans(false)
        })

        .catch(function (error) {
           console.log(error);
        });
    }


    useEffect(() => {

        getCategories()
        chargeTransactions()
  
    }, []);

    return((loadCats || loadTrans)? <div className="w-full h-screen flex items-center justify-center"><Spinner className="h-24 w-24" color={"purple"} size="xl" /></div>:
        <section className="text-gray-100">
            <Header/>

            <article className="flex flex-col items-center">

                <article className="w-1/2 flex flex-col gap-3 mt-11">
                    <h1> Tus categorias: (disclaimer, el mapa de categorias y el enlistamiento estan en mantenimiento)</h1>
                    {
                        category.map( (elem) => {
                            return(
                                <span key={elem.id} className="font-bold"> {elem.name} </span>
                            )
                        })
                    }
                </article>

                <article className="w-1/2">
                <LineChart
                        className="mt-4 h-72"
                        data={trans}
                        index="date"
                        yAxisWidth={65}
                        categories={['Balance']}
                        colors={['indigo']}
                        valueFormatter={valueFormatter}
                    />
                <DonutChart
                    data={sales}
                    category="sales"
                    index="name"
                    valueFormatter={valueFormatter}
                    colors={['blue', 'cyan', 'indigo', 'violet', 'fuchsia']}
                />
                </article>    

            </article>

        </section>
    )
}