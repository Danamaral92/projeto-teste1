import {useEffect, useState} from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
// import { zodResolver } from "@hookform/resolvers/zod";
import {Medico} from "@/types/Medico.ts";
import {Instrumento} from "@/types/Instrumento.ts";
import {Paciente} from "@/types/Paciente.ts";
import {listAllPacientes} from "@/services/PacienteService.ts";
import {listAllMedicos} from "@/services/MedicoService.ts";
import {listAllInstrumentos} from "@/services/InstrumentoService.ts";
import {createCirurgia} from "@/services/CirurgiaService.ts";
import {CirurgiaCreate} from "@/types/CirurgiaCreate.ts";

const schema = z.object({
    id: z.number().nullable(),
    data: z.string().min(1, "Data é obrigatória"),
    pacienteId: z.number().min(1, "Paciente é obrigatório"),
    descricao: z.string().min(3, "Descrição mínima 3 caracteres"),
    medicoPrincipalId: z.number().min(1, "Médico principal obrigatório"),
    medicosIds: z.array(z.number()).optional(),
    instrumentosIds: z.array(z.number()).optional(),
});

type FormValues = z.infer<typeof schema>;

const CirurgiaForm = () => {
    const { register, handleSubmit, watch, setValue, formState: { errors } } =
        useForm<FormValues>({
            // resolver: zodResolver(schema),
            defaultValues: {
                id: null,
                data: "",
                pacienteId: undefined,
                descricao: "",
                medicoPrincipalId: undefined,
                medicosIds: [],
                instrumentosIds: [],
            },
        });


    const medicosIds = watch("medicosIds") || [];
    const instrumentosIds = watch("instrumentosIds") || [];

    function toggleId(name: "medicosIds" | "instrumentosIds", id: number) {
        const arr = (name === "medicosIds" ? medicosIds : instrumentosIds) as number[];
        const next = arr.includes(id) ? arr.filter(x => x !== id) : [...arr, id];
        setValue(name, next);
    }

    const [pacientes, setPacientes] = useState<Paciente[]>([])
    const [medicos, setMedicos] = useState<Medico[]>([])
    const [instrumentos, setInstrumentos] = useState<Instrumento[]>([])

    useEffect(() => {
        const listPacientesHandle = async ()=> {
            const listPacientes = await listAllPacientes()
            setPacientes(listPacientes.data)
        }

        const listMedicossHandle = async ()=> {
            const listMedicos = await listAllMedicos()
            setMedicos(listMedicos.data)
        }
        const listInstrumentosHandle = async ()=> {
            const listInstrumentos = await listAllInstrumentos()
            setInstrumentos(listInstrumentos.data)
        }

        listPacientesHandle();
        listMedicossHandle();
        listInstrumentosHandle();

    }, []);
    const onSubmit = async (values: FormValues) => {
        const payload: CirurgiaCreate = {
            data: new Date(values.data),
            pacienteId: values.pacienteId!,
            descricao: values.descricao,
            medicoPrincipalId: values.medicoPrincipalId,
            medicosId: values.medicosIds ?? [],
            instrumentosId: values.instrumentosIds ?? [],
        };

        await createCirurgia(payload)
    };

    return (
        <div>
            <form className="space-y-4 p-4" onSubmit={handleSubmit(onSubmit)}>
                <div className="input-floating w-full">
                    <input id="data" type="datetime-local" className="input" {...register('data')} />
                    <label className="input-floating-label" htmlFor="data">Data</label>
                    {errors.data && <p className="text-red-600 text-sm mt-1">{errors.data.message}</p>}
                </div>

                <div>
                    <label className="label-text block mb-1">Paciente</label>
                    <select className="select w-full" {...register('pacienteId')}>
                        <option value="">— selecione —</option>
                        {pacientes.map((p) => (
                            <option key={p.id} value={p.id}>{p.nome} — #{p.matricula}</option>
                        ))}
                    </select>
                    {errors.pacienteId && <p className="text-red-600 text-sm mt-1">{errors.pacienteId.message}</p>}
                </div>

                <div>
                    <label className="label-text block mb-1">Descrição</label>
                    <textarea className="input w-full" {...register('descricao')} rows={3} />
                    {errors.descricao && <p className="text-red-600 text-sm mt-1">{errors.descricao.message}</p>}
                </div>

                <div>
                    <label className="label-text block mb-1">Médicos (marque os que participam)</label>
                    <div className="grid grid-cols-2 gap-2 mt-2 max-h-32 overflow-auto">
                        {medicos.map((m) => (
                            <label className="inline-flex items-center space-x-2" key={m.id}>
                                <input
                                    type="checkbox"
                                    className="checkbox checkbox-primary"
                                    checked={medicosIds.includes(m.id)}
                                    onChange={() => toggleId('medicosIds', m.id)}
                                />
                                <span className="label-text">{m.nome} ({m.especialidade?.descricao ?? '—'})</span>
                            </label>
                        ))}
                    </div>
                </div>

                <div>
                    <label className="label-text block mb-1">Médico Principal</label>
                    <select className="select w-full" {...register('medicoPrincipalId')}>
                        <option value="">— selecione —</option>
                        {medicos.filter((m) => medicosIds.includes(m.id)).map((m) => (
                            <option key={m.id} value={m.id}>{m.nome} ({m.crm})</option>
                        ))}
                    </select>
                    {errors.medicoPrincipalId && <p className="text-red-600 text-sm mt-1">{errors.medicoPrincipalId.message}</p>}
                </div>

                <div>
                    <label className="label-text block mb-1">Instrumentos</label>
                    <div className="grid grid-cols-2 gap-2 mt-2">
                        {instrumentos.map((i) => (
                            <label className="inline-flex items-center space-x-2" key={i.id}>
                                <input
                                    type="checkbox"
                                    className="checkbox"
                                    checked={instrumentosIds.includes(i.id)}
                                    onChange={() => toggleId('instrumentosIds', i.id)}
                                />
                                <span className="label-text">{i.nome}</span>
                            </label>
                        ))}
                    </div>
                </div>

                <div className="flex items-center justify-end gap-2 pt-4">
                    <button type="submit" className="btn btn-primary">Salvar</button>
                </div>
            </form>
        </div>
    );
}

export default CirurgiaForm



