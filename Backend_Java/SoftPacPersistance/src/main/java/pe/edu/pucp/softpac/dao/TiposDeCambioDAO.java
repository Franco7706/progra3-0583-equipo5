package pe.edu.pucp.softpac.dao;

import pe.edu.pucp.softpac.model.TiposDeCambioDTO;

import java.util.ArrayList;
import java.util.List;

public interface TiposDeCambioDAO {
    public Integer insertar(TiposDeCambioDTO tipoDeCambio);

    public TiposDeCambioDTO obtenerPorId(Integer tipoDeCambioId);

    public List<TiposDeCambioDTO> listarTodos();

    public Integer modificar(TiposDeCambioDTO tipoDeCambio);

    public Integer eliminar(TiposDeCambioDTO tipoDeCambio);

}
