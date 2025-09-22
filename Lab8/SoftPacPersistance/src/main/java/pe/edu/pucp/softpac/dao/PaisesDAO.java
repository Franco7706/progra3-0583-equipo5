package pe.edu.pucp.softpac.dao;

import pe.edu.pucp.softpac.model.PaisesDTO;

import java.util.ArrayList;

public interface PaisesDAO {
    public Integer insertar(PaisesDTO pais);

    public PaisesDTO obtenerPorId(Integer paisId);

    public ArrayList<PaisesDTO> listarTodos();

    public Integer modificar(PaisesDTO pais);

    public Integer eliminar(PaisesDTO pais);
}
