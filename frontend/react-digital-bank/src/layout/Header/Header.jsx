import { Link } from "react-router-dom";
import { routes } from "../../routes/routes";

// Intencionalmente Vacio
export function Header(){
    return (
            <header className=" bg-secondary text-gray-100 border-gray-500 border-b-[1px] shadow-sm">
                <nav className=" flex items-center justify-between px-[10%] h-20 " >
                    <Link to={routes.home} className="flex items-center justify-around hover:hue-rotate-30 group">
                        <figure className="w-24 aspect-square">
                            <img className="object-cover" src="../../../public/img/Icon.png"/>
                        </figure>
                        
                        <h1 className="flex flex-col uppercase group:hover:hue-rotate-30 transition-all group:hover:bg-clip-text group:hover:text-transparent group:hover:font-bolder group:hover:bg-gradient-to-r group:hover:from-indigo-600 group:hover:to-violet-500 group:hover:font-bold">
                            <span className="text-xl">Digital</span> 
                            <span className="text-xl">Bank</span>
                        </h1>
                    </Link>

                    <ul className="flex gap-5">
                        <li className="hover:hue-rotate-30 transition-all hover:bg-clip-text hover:text-transparent hover:font-bolder hover:bg-gradient-to-r hover:from-indigo-600 hover:to-violet-500 hover:font-bold"> <Link to={routes.home}>Inicio</Link> </li>
                        <li className="hover:hue-rotate-30 transition-all hover:bg-clip-text hover:text-transparent hover:font-bolder hover:bg-gradient-to-r hover:from-indigo-600 hover:to-violet-500 hover:font-bold"> <Link to={routes.transactions}>Transferencias</Link> </li>
                        <li className="hover:hue-rotate-30 transition-all hover:bg-clip-text hover:text-transparent hover:font-bolder hover:bg-gradient-to-r hover:from-indigo-600 hover:to-violet-500 hover:font-bold"> <Link to={routes.dashboard}>Dashboard</Link> </li>
                        <li className="hover:hue-rotate-30 transition-all hover:bg-clip-text hover:text-transparent hover:font-bolder hover:bg-gradient-to-r hover:from-indigo-600 hover:to-violet-500 hover:font-bold"> <Link to={routes.maintain}>Mi perfil</Link> </li>
                    </ul>
                </nav>
            </header>
    )
}