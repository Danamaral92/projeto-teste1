import {createBrowserRouter} from "react-router";
import Paths from "@/constants/paths.ts";
import NotFoundPage from "@/pages/NotFoundPage.tsx";
import MainLayout from "@/layouts/MainLayout.tsx";
import ErrorPage from "@/pages/ErrorPage.tsx";
import LoadingPage from "@/pages/LoadingPage.tsx";
import MainPage from "@/pages/MainPage.tsx";
import CirurgiasPage from "@/pages/CirurgiasPage.tsx";


const router = createBrowserRouter([
    {
        path: Paths.HOME,
        element: (<MainLayout/>),
        errorElement: <ErrorPage/>,
        hydrateFallbackElement: <LoadingPage/>,
        children: [
            { index: true, element: <MainPage/> },
            { path: Paths.CIRURGIAS, element: <CirurgiasPage/>}
        ],
    },
    { path: '*', element: <NotFoundPage/> },
])

export default router;