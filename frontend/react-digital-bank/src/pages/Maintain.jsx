import { Link } from 'react-router-dom'
import { routes } from '../routes/routes'

export function Maintain() {
  return (
    <section className='w-full h-screen flex justify-center bg-gradient-to-r from-indigo-950 to-violet-900 text-gray-100 relative text-center'>
      <div className="flex flex-col w-3/4 h-1/2 relative z-10 bg-red-00 justify-center items-center gap-7 m-auto">

        <figure className="w-72 drop-shadow-[0_5px_5px_rgba(255,255,255,0.5)]">
         <img src="../../public/img/MantainIcon.png"/>
        </figure>
 
        <p className='text-3xl font-semibold'>Oops !</p>

        <p className="text-xl ">
          La pagina esta en mantenimiento.
        </p>

        <Link to={routes.home}><button className='bg-gradient-to-r  py-4 px-7 rounded-full from-[#4736C9] to-[#2E0E64] font-semibold'>Regresar al Inicio</button></Link>
      </div>

    </section>
  )
}
