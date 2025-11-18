import {api} from "@/configs/api.ts";
import Paths from "@/constants/paths.ts";
import {Cirurgia} from "@/types/Cirurgia.ts";


export const listAllCirurgias = async () =>{
    return await api.get(Paths.CIRURGIAS)
}

export const deleteCirurgiaById = async (id: number) => {
    return await api.delete(Paths.CIRURGIAS + id)
}

export const createCirurgia = async (cirurgia: Cirurgia) => {
    return await api.post(Paths.CIRURGIAS, cirurgia);
}