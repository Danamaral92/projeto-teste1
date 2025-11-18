abstract class Paths{
    public static readonly BASE_URL = `${import.meta.env.DEV ?
        window.location.origin :
        ''}${import.meta.env.BASE_URL}`;
    public static readonly HOME = '/';
    public static readonly CIRURGIAS = '/cirurgias/';
    public static readonly MEDICOS = '/medicos/';
    public static readonly PACIENTES = '/pacientes/';
    public static readonly ESPECIALIDADE = '/especialidades/';
    public static readonly INSTRUMENTOS = '/instrumentos/';
}

export default Paths;