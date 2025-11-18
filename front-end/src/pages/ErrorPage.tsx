import {useRouteError} from 'react-router';

const ErrorPage = () => {
    const error = useRouteError() as Error | undefined;

    if (error instanceof Error) {
        console.error(error);
    }

    return (
        <section>
            <h1>Something is not right...</h1>
            <p>{error?.message || 'Unknown error'}</p>
        </section>
    );
};

export default ErrorPage;