export function FloatingInput(props){

    const {type, name="", id="", placeholder="", label="input", pattern, OnChange, Value, wrong} = props

    let inputClass = "block py-2.5 px-0 w-full text-sm text-gray-100 bg-transparent border-0 border-b-2 border-gray-100 appearance-none focus:outline-none focus:ring-0 focus:border-indigo-600 peer  focus:placeholder-gray-500 placeholder-transparent"
    
    let labelClass= "peer-focus:font-medium absolute text-sm text-gray-300 duration-300 transhtmlForm -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 peer-focus:text-indigo-400  peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
    if (wrong){
        inputClass= "block py-2.5 px-0 w-full text-sm text-gray-100 bg-transparent border-0 border-b-2 border-red-700 appearance-none focus:outline-none focus:ring-0 focus:border-red-700 peer  focus:placeholder-gray-500 placeholder-transparent"
        labelClass= "peer-focus:font-medium absolute text-sm text-red-700 duration-300 transhtmlForm -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 peer-focus:text-indigo-400  peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
    }
        
        return (
        <div className="relative z-0 w-full mb-5 group">
            <input 
            type={type} 
            name={name}
            id={id}
            className= {inputClass}
            placeholder={placeholder} 
            required 
            pattern={pattern}
            onChange={OnChange}
            value={Value}
            />

            <label 
            htmlFor="floating_first_name" 
            className={labelClass}>{label}</label>
        </div>

    )
}