import { Link } from 'react-router-dom'
import { routes } from '../routes/routes'

export function Error404() {
  return (
    <section className='w-full h-screen flex justify-center bg-primary text-gray-100 relative text-center'>
      <div className="flex flex-col w-3/4 h-1/2 relative z-10 bg-red-00 justify-center items-center gap-7 m-auto">

        <figure className="w-52 drop-shadow-[0_5px_5px_rgba(255,255,255,0.5)]">
         <img src="../../public/img/Error-Image-404.png"/>
        </figure>
 
        <p className='text-3xl font-semibold'>Oops !</p>

        <p className="text-xl ">
          La pagina que estas buscando no esta disponible.
        </p>

        <Link to={routes.home}><button className='bg-gradient-to-r  py-4 px-7 rounded-full from-[#4736C9] to-[#2E0E64] font-semibold'>Regresar al Inicio</button></Link>
      </div>

      <div className='absolute text-[10rem]  md:text-[20rem] font-bold text-primary w-full h-full flex justify-center items-center'>
        <div className='h-3/4 w-3/4 flex justify-center items-center'>
          <img className=' max-h-full w-full object-contain' src='../../../public/img/404icon.png'/>
        </div>
      </div>
    </section>
  )
}
