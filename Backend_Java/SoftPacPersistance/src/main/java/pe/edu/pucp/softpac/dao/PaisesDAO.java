package pe.edu.pucp.softpac.dao;

import pe.edu.pucp.softpac.model.PaisesDTO;

import java.util.ArrayList;
import java.util.List;

public interface PaisesDAO {
    public Integer insertar(PaisesDTO pais);

    public PaisesDTO obtenerPorId(Integer paisId);

    public List<PaisesDTO> listarTodos();

    public Integer modificar(PaisesDTO pais);

    public Integer eliminar(PaisesDTO pais);
}
