import {Especialidade} from "@/types/Especialidade.ts";


export interface Medico{
    id: number;
    matricula: number;
    nome: string;
    crm: string;
    especialidade: Especialidade;
}