import {ReactNode, useEffect} from "react";


async function loadFlyonUI() {
    return import('flyonui/flyonui');
}


const FlyonUI = ({ children } : { children : ReactNode}) => {
    useEffect(() => {
        const initFlyonUI = async () => {
            await loadFlyonUI();
        };

        initFlyonUI();
    }, []);

    // const { pathname } = window.location
    useEffect(() => {
        setTimeout(() => {
            if (
                window.HSStaticMethods &&
                typeof window.HSStaticMethods.autoInit === 'function'
            ) {
                window.HSStaticMethods.autoInit();
            }
        }, 100);
    }, [location.pathname]);

    return children
}

export default FlyonUI