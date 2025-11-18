import {api} from "@/configs/api.ts";
import Paths from "@/constants/paths.ts";


export const listAllMedicos = async () =>{
    return await api.get(Paths.MEDICOS)
}