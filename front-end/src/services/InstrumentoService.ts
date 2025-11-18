import {api} from "@/configs/api.ts";
import Paths from "@/constants/paths.ts";


export const listAllInstrumentos = async () =>{
    return await api.get(Paths.INSTRUMENTOS)
}