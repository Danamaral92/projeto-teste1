import {MenuItem} from "@/types/MenuItem.ts";
import Paths from "@/constants/paths.ts";
import {Link} from "react-router";


const links:MenuItem[] = [
    {
        nome: "Home",
        url: Paths.HOME
    },
    {
        nome: "Cirurgias",
        url: Paths.CIRURGIAS
    },
    {
        nome: "Médicos",
        url: Paths.MEDICOS
    },
    {
        nome: "Pacientes",
        url: Paths.PACIENTES
    },
    {
        nome: "Especialidades",
        url: Paths.ESPECIALIDADE
    },
    {
        nome: "Instrumentos",
        url: Paths.INSTRUMENTOS
    },
]

export const Navbar = () => {
    return (
        <div>
            <nav className="navbar rounded-box shadow-base-300/20 shadow-sm">
                <div className="w-full md:flex md:items-center md:gap-2">
                    <div className="flex items-center justify-between">
                        <div className="navbar-start items-center justify-between max-md:w-full">
                            <a className="link text-base-content link-neutral text-xl font-bold no-underline"
                               href="#">HC&A</a>
                            <div className="md:hidden">
                                <button type="button"
                                        className="collapse-toggle btn btn-outline btn-secondary btn-sm btn-square"
                                        data-collapse="#navbar-collapse" aria-controls="navbar-collapse"
                                        aria-label="Toggle navigation">
                                    <span className="icon-[tabler--menu-2] collapse-open:hidden size-4"></span>
                                    <span className="icon-[tabler--x] collapse-open:block hidden size-4"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div id="navbar-collapse"
                         className="md:navbar-end collapse hidden grow basis-full overflow-hidden transition-[height] duration-300 max-md:w-full">
                        <ul className="menu md:menu-horizontal gap-2 p-0 text-base max-md:mt-2">
                            {links.map(
                                l => (<li><Link to={l.url}>{l.nome}</Link></li>)
                            )}
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    )
}
