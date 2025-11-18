import {useEffect, useState} from "react";
import {Cirurgia} from "@/types/Cirurgia.ts";
import {deleteCirurgiaById, listAllCirurgias} from "@/services/CirurgiaService.ts";
import React from "react";
import CirurgiaForm from "@/components/CirurgiaForm.tsx";





const CirurgiasPage = () => {
    const [cirurgias, setCirurgias] = useState<Cirurgia[]>([]);


    useEffect(() => {
        const listCirurgiasHandle = async ()=>{
            const listCirurgias = await listAllCirurgias()
            setCirurgias(listCirurgias.data)
        }


        listCirurgiasHandle()
    }, []);





    const [openRows, setOpenRows] = useState<Record<number, boolean>>({});

    const toggle = (id: number) =>
        setOpenRows(prev => ({ ...prev, [id]: !prev[id] }));

    const deleteCirurgiaHandle = async (id: number) => {
        await deleteCirurgiaById(id)
        setCirurgias([...cirurgias.filter((c)=>(c.id != id))])
    }

    return (
        <>
            <div className="w-full overflow-x-auto">
                <table className="min-w-full divide-y table-auto">
                    <thead className="bg-gray-50">
                    <tr className="text-left text-sm text-slate-600">
                        <th className="px-4 py-3">Data / Hora</th>
                        <th className="px-4 py-3">Paciente</th>
                        <th className="px-4 py-3">Descrição</th>
                        <th className="px-4 py-3">Médicos</th>
                        <th className="px-4 py-3">Instrumentos</th>
                        <th className="px-4 py-3">Ações</th>
                    </tr>
                    </thead>
                    <tbody className="bg-white divide-y">
                    {cirurgias.map(row => (
                        <React.Fragment key={row.id}>
                            <tr className="hover:bg-gray-50">
                                <td className="px-4 py-3 align-top w-40">
                                    <div className="text-sm font-medium">{new Date(row.data).toLocaleDateString()}</div>
                                    <div
                                        className="text-xs text-slate-500">Internação: {new Date(row.paciente?.dataInternamento).toLocaleDateString()}</div>
                                </td>

                                <td className="px-4 py-3 align-top w-64">
                                    <div className="flex items-start gap-3">
                                        <div>
                                            <div className="text-sm font-medium">{row.paciente.nome}</div>
                                            <div
                                                className="text-xs text-slate-500">Matricula: {row.paciente.matricula}</div>
                                            <div className="text-xs text-slate-500">CID: {row.paciente.cid ?? '-'}</div>
                                        </div>
                                    </div>
                                </td>

                                <td className="px-4 py-3 align-top">
                                    <div className="text-sm">{row.descricao}</div>
                                </td>

                                <td className="px-4 py-3 align-top max-w-xs">
                                    <div className="flex flex-col gap-2">
                                        {row.medicos.slice(0, 2).map(m => (
                                            <div key={m.id} className="flex items-center gap-3">
                                                <div className="flex-1 min-w-0">
                                                    <div
                                                        className="text-sm font-medium truncate">{m.nome} {m.id === row.medicoPrincipalId ?
                                                        <span
                                                            className="text-emerald-600 text-xs font-semibold">(Principal)</span> : null}</div>
                                                    <div
                                                        className="text-xs text-slate-500 truncate">CRM: {m.crm ?? '-'} • {m.especialidade?.descricao ?? '-'}</div>
                                                </div>
                                            </div>
                                        ))}
                                        {row.medicos.length > 2 && (
                                            <div className="text-xs text-slate-500">+{row.medicos.length - 2} médico(s)
                                                — expanda para ver todos</div>
                                        )}
                                    </div>
                                </td>

                                <td className="px-4 py-3 align-top max-w-xs">
                                    <div className="flex flex-wrap gap-2">
                                        {row.instrumentos.map(ins => (
                                            <span key={ins.id}
                                                  className="text-xs px-2 py-1 rounded-full border bg-gray-50">{ins.nome}</span>
                                        ))}
                                    </div>
                                </td>

                                <td className="px-4 py-3 align-top">
                                    <div className="flex gap-2 items-center">
                                        <button
                                            className="px-3 py-1 rounded-lg border text-sm"
                                            onClick={() => toggle(row.id!)}
                                            aria-expanded={!!openRows[row.id!]}
                                            aria-controls={`details-${row.id}`}
                                        >
                                            {openRows[row.id!] ? 'Fechar' : 'Detalhes'}
                                        </button>

                                        <button className="px-3 py-1 rounded-lg bg-sky-600 text-white text-sm">Editar
                                        </button>

                                        <button onClick={() => deleteCirurgiaHandle(row.id!)}
                                                className="px-3 py-1 rounded-lg bg-rose-600 text-white text-sm">Excluir
                                        </button>

                                    </div>
                                </td>
                            </tr>

                            {openRows[row.id!] && (
                                <tr id={`details-${row.id}`} className="bg-slate-50">
                                    <td colSpan={6} className="px-4 py-4">
                                        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                                            <div>
                                                <h3 className="text-sm font-semibold">Paciente</h3>
                                                <div className="text-xs text-slate-600">Nome: {row.paciente.nome}</div>
                                                <div
                                                    className="text-xs text-slate-600">Matrícula: {row.paciente.matricula}</div>
                                                <div className="text-xs text-slate-600">Data
                                                    internamento: {new Date(row.paciente.dataInternamento).toLocaleDateString()}</div>
                                                <div className="text-xs text-slate-600">CID: {row.paciente.cid}</div>
                                            </div>

                                            <div>
                                                <h3 className="text-sm font-semibold">Médicos</h3>
                                                <div className="flex flex-col gap-2 mt-2">
                                                    {row.medicos.map(m => (
                                                        <div key={m.id} className="p-2 rounded-lg border bg-white">
                                                            <div
                                                                className="text-sm font-medium">{m.nome} {m.id === row.medicoPrincipalId ?
                                                                <span
                                                                    className="text-emerald-600 text-xs font-semibold">(Principal)</span> : null}</div>
                                                            <div
                                                                className="text-xs text-slate-500">Matrícula: {m.matricula} •
                                                                CRM: {m.crm ?? '-'}</div>
                                                            <div
                                                                className="text-xs text-slate-500">Especialidade: {m.especialidade?.descricao ?? '-'}</div>
                                                        </div>
                                                    ))}
                                                </div>
                                            </div>

                                            <div>
                                                <h3 className="text-sm font-semibold">Instrumentos</h3>
                                                <ul className="mt-2 list-disc list-inside text-sm">
                                                    {row.instrumentos.map(i => (
                                                        <li key={i.id} className="text-slate-700">{i.nome}</li>
                                                    ))}
                                                </ul>
                                            </div>
                                        </div>

                                    </td>
                                </tr>
                            )}
                        </React.Fragment>
                    ))}

                    {cirurgias.length === 0 && (
                        <tr>
                            <td colSpan={6} className="px-4 py-6 text-center text-slate-500">Nenhuma cirurgia
                                encontrada.
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>
            <button type="button" className="btn btn-primary" aria-haspopup="dialog" aria-expanded="false"
                    aria-controls="basic-modal" data-overlay="#fullscreen-modal"> Open modal
            </button>

            <div id="fullscreen-modal"
                 className="overlay modal overlay-open:opacity-100 overlay-open:duration-300 hidden" role="dialog"
                 tabIndex={-1}>
                <div className="modal-dialog max-w-none">
                    <div className="modal-content h-full max-h-none justify-between">
                        <div className="modal-header">
                            <h3 className="modal-title">Dialog Title</h3>
                            <button type="button" className="btn btn-text btn-circle btn-sm absolute end-3 top-3"
                                    aria-label="Close" data-overlay="#fullscreen-modal">
                                <span className="icon-[tabler--x] size-4"></span>
                            </button>
                        </div>

                        <div className="modal-body grow">
                            <CirurgiaForm/>
                        </div>

                    </div>
                </div>
            </div>
        </>
    )
}

export default CirurgiasPage