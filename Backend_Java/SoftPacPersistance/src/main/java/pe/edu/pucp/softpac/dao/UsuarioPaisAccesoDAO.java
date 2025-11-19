package pe.edu.pucp.softpac.dao;

import pe.edu.pucp.softpac.model.UsuarioPaisAccesoDTO;

public interface UsuarioPaisAccesoDAO {
    public Integer insertar(UsuarioPaisAccesoDTO usuario);

    public UsuarioPaisAccesoDTO obtenerPorUsuarioYPais(Integer usuarioId, Integer paisId);

    public Integer modificar(UsuarioPaisAccesoDTO usuario);

    public Integer eliminar(UsuarioPaisAccesoDTO usuario);
}
