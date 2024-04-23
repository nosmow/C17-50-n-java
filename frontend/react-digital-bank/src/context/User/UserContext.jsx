import { createContext } from "react";
import { useState } from "react"

export const UserContext = createContext()


export function UserProvider ({children}) {
    const initialState = {
        idUser: null,
        idCompany: null,
        token:null,
  
        username: null,
  
        // Role can be "ROLE_USER" "ROLE_BUSINESS" "ROLE_ADMIN"
        role:null,
      }

      const [user, setUser] = useState(initialState)


      return ( 
        <UserContext.Provider value={[user, setUser]}>
            {children}
        </UserContext.Provider>
       )
}