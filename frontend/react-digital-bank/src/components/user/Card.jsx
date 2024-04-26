import { useContext, useState } from "react"
import { UserContext } from "../../context/User/UserContext"
import { IconEye } from "../../assets/icons/IconEye"

export function Card(){

   const [user] = useContext(UserContext)

   const [show, setShow] = useState(false)

   const prop={
    name: user.username,
    num: user.account,
    cvv: ""
   }


   const ocultNumbers= " **** **** "

   return (
    <section className=" w-96 rounded-xl overflow-hidden relative flex justify-center items-center  hover:hue-rotate-30 transition-all">
        <article className="absolute w-full h-full ">
            <div className="flex items-center">
                <figure className="w-20 h-auto ">
                    <img className="object-cover" src="/img/Icon.png"/>
                </figure>
                <span>
                    <h3 className="text-base m-[-7px] text-gray-100 font-bold">DIGITAL</h3>
                    <h3 className="text-base m-[-7px] text-gray-100 font-bold">BANK</h3>
                </span>
            </div>
        </article>
        <article className="absolute w-full h-full flex flex-col justify-end gap-3 pb-7 px-7">
                <h4 className="w-full text-justify text-2xl font-bold text-gray-100">CVV: {!show ? ocultNumbers : prop.num}</h4>
                <h4 className="w-full text-justify text-gray-100">De: {prop.name}</h4>
        </article>
        <article className="absolute w-full h-full flex justify-end items-end gap-5 pb-3 px-7">
                {/* <h4 className="text-gray-100"> {!show ? "***" : prop.cvv}</h4> */}

                <div className="mb-2" onClick={ () => {setShow( !show )}}>  <IconEye closed={!show}></IconEye>   </div>
        </article>
        <figure className="w-full h-auto min-w-[380px] min-h-[220px]">
            <img className="object-cover" src="https://media.discordapp.net/attachments/1225142769270657216/1232513398496034856/image.png?ex=6629bb0d&is=6628698d&hm=f32ebe26f06272a4ceb46fa254dacaa3f043388a80fe396c6471d7534f4392dc&=&format=webp&quality=lossless&width=1062&height=615"/>
        </figure>
    </section>
   )
}