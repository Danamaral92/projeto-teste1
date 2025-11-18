import { Outlet } from 'react-router';
import {Navbar} from "@/components/Navbar.tsx";


const MainLayout = () => {

    return (
        <div>
            <Navbar/>
            <Outlet/>
        </div>
    );
};

export default MainLayout;