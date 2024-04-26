export function IconEye({closed, OnClick}){
    return(
        closed ? <figure onClick={OnClick} className=" w-5 h-auto"> <img className=" object-cover" src="/img/EyeIcon.png"/> </figure> : <figure onClick={OnClick} className=" w-5 h-auto"> <img className=" object-cover" src="/img/EyeIcon2.png"/> </figure>
    )
}