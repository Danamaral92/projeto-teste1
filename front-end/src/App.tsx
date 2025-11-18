
import './App.css'
import {StrictMode} from "react";
import FlyonUI from "@/FlyonUI.tsx";
import {RouterProvider} from "react-router";
import router from "@/routes.tsx";



function App() {
  return (
      <StrictMode>
          <FlyonUI>
              <RouterProvider router={router}/>
          </FlyonUI>
      </StrictMode>
  )
}

export default App
