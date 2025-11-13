import {Outlet} from "react-router-dom"
import {Toaster} from "react-hot-toast";

export default function App() {
    return (
        <>
            <Outlet/>
            <Toaster position="bottom-center"/>
        </>
    )
}