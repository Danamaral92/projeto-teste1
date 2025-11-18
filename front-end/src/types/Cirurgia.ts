import {Paciente} from "@/types/Paciente.ts";
import {Medico} from "@/types/Medico.ts";
import { Instrumento } from "./Instrumento";

export interface Cirurgia {
    id?: number,
    data: Date,
    paciente: Paciente,
    descricao: string,
    medicoPrincipalId: number;
    medicos: Medico[],
    instrumentos: Instrumento[];
}