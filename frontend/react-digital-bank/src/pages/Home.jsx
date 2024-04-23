import { useContext } from "react"
import { UserContext } from "../context/User/UserContext"

export function Home(){

   const [user, setUser] = useContext(UserContext)

   console.log("EN EL HOME!!", user)
   return <>
      <h1>Home! user-</h1>
   </>
}