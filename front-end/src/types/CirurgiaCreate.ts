

export interface CirurgiaCreate {
    data: Date,
    pacienteId: number,
    descricao: string,
    medicoPrincipalId: number;
    medicosId: number[],
    instrumentosId: number[];
}