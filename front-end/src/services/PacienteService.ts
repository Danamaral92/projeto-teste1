import {api} from "@/configs/api.ts";
import Paths from "@/constants/paths.ts";


export const listAllPacientes = async () =>{
    return await api.get(Paths.PACIENTES)
}