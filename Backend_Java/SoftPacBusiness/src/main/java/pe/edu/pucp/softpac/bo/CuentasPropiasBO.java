package pe.edu.pucp.softpac.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softpac.dao.CuentasPropiasDAO;
import pe.edu.pucp.softpac.daoImpl.CuentasPropiasDAOImpl;
import pe.edu.pucp.softpac.model.CuentasPropiasDTO;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

public class CuentasPropiasBO {
    private CuentasPropiasDAO cuentasPropiasDAO;
    
    public CuentasPropiasBO(){
        cuentasPropiasDAO = new CuentasPropiasDAOImpl();
    }
    
    public Integer insertar(BigDecimal saldo_disponible, String tipo_de_cuenta, 
            String numero_cuenta, String cci, String activa, Integer entidad_bancaria_id,
            Integer moneda_id){
        
        // Validaciones
        if (saldo_disponible == null || saldo_disponible.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo disponible no puede ser nulo o negativo.");
        }
        if (tipo_de_cuenta == null || tipo_de_cuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de cuenta es obligatorio.");
        }
        if (numero_cuenta == null || numero_cuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de cuenta es obligatorio.");
        }
        if (entidad_bancaria_id == null || entidad_bancaria_id <= 0) {
            throw new IllegalArgumentException("La entidad bancaria es obligatoria.");
        }
        if (moneda_id == null || moneda_id <= 0) {
            throw new IllegalArgumentException("La moneda es obligatoria.");
        }
        
        CuentasPropiasDTO cuentasPropiasDTO = new CuentasPropiasDTO();
        cuentasPropiasDTO.setSaldo_disponible(saldo_disponible); // CORREGIDO: Faltaba esta línea
        cuentasPropiasDTO.setTipo_cuenta(tipo_de_cuenta);
        cuentasPropiasDTO.setNumero_cuenta(numero_cuenta);
        cuentasPropiasDTO.setCci(cci);
        cuentasPropiasDTO.setActiva(activa.equals("S") ? Boolean.TRUE : Boolean.FALSE);
        
        // Configurar entidad bancaria
        EntidadesBancariasDTO entidadBancaria = new EntidadesBancariasDTO();
        entidadBancaria.setEntidad_bancaria_id(entidad_bancaria_id);
        cuentasPropiasDTO.setEntidad_bancaria(entidadBancaria); // CORREGIDO: Era setCuenta_bancaria_id
        
        // Configurar moneda
        MonedasDTO moneda = new MonedasDTO();
        moneda.setMoneda_id(moneda_id);
        cuentasPropiasDTO.setMoneda(moneda);
        
        return insertar(cuentasPropiasDTO);
    }
    
    public Integer insertar(CuentasPropiasDTO cuentasPropias){
        if(cuentasPropias == null) return 0;
        return this.cuentasPropiasDAO.insertar(cuentasPropias);
    }
    
    public Integer modificar(Integer cuentas_propias_id, BigDecimal saldo_disponible, 
            String tipo_de_cuenta, String numero_cuenta, String cci, String activa, 
            Integer entidad_bancaria_id, Integer moneda_id){
        
        // Validaciones
        if (cuentas_propias_id == null || cuentas_propias_id <= 0) {
            throw new IllegalArgumentException("El ID de la cuenta es obligatorio.");
        }
        if (saldo_disponible == null || saldo_disponible.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo disponible no puede ser nulo o negativo.");
        }
        if (tipo_de_cuenta == null || tipo_de_cuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de cuenta es obligatorio.");
        }
        if (numero_cuenta == null || numero_cuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de cuenta es obligatorio.");
        }
        
        CuentasPropiasDTO cuentasPropiasDTO = new CuentasPropiasDTO();
        cuentasPropiasDTO.setCuenta_bancaria_id(cuentas_propias_id);
        cuentasPropiasDTO.setSaldo_disponible(saldo_disponible); // CORREGIDO: Faltaba esta línea
        cuentasPropiasDTO.setTipo_cuenta(tipo_de_cuenta);
        cuentasPropiasDTO.setNumero_cuenta(numero_cuenta);
        cuentasPropiasDTO.setCci(cci);
        cuentasPropiasDTO.setActiva(activa.equals("S") ? Boolean.TRUE : Boolean.FALSE);
        
        // Configurar entidad bancaria
        EntidadesBancariasDTO entidadBancaria = new EntidadesBancariasDTO();
        entidadBancaria.setEntidad_bancaria_id(entidad_bancaria_id);
        cuentasPropiasDTO.setEntidad_bancaria(entidadBancaria); // CORREGIDO: Era setCuenta_bancaria_id
        
        // Configurar moneda
        MonedasDTO moneda = new MonedasDTO();
        moneda.setMoneda_id(moneda_id);
        cuentasPropiasDTO.setMoneda(moneda);
        
        return modificar(cuentasPropiasDTO);
    }
    
    public Integer modificar(CuentasPropiasDTO cuentasPropias){
        if(cuentasPropias == null) return 0;
        return this.cuentasPropiasDAO.modificar(cuentasPropias);
    }
    
    public Integer modificar(CuentasPropiasDTO cuentasPropias,int usuarioId){
        if(cuentasPropias == null) return 0;
        KardexCuentasPropiasBO kardexCuentasPropiasBO = new KardexCuentasPropiasBO();
        kardexCuentasPropiasBO.RegistrarModificacion(cuentasPropias, usuarioId);
        return this.cuentasPropiasDAO.modificar(cuentasPropias);
    }
    
    
    public Integer eliminarLogico(CuentasPropiasDTO cuenta, UsuariosDTO usuarioActual){
        if (cuenta == null || cuenta.getCuenta_bancaria_id()== null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula.");
        }
        if (usuarioActual == null || usuarioActual.getUsuario_id() == null) {
            throw new IllegalArgumentException("El usuario actual es obligatorio.");
        }
        
        cuenta.setFecha_eliminacion(new Date());
        cuenta.setUsuario_eliminacion(usuarioActual);
        
        return cuentasPropiasDAO.eliminarLogico(cuenta);
    }
    
    public Integer eliminarLogico(Integer cuenta_bancaria_id, UsuariosDTO usuarioActual){
        
        CuentasPropiasDTO cuentasPropias = cuentasPropiasDAO.obtenerPorId(cuenta_bancaria_id);
                
        return eliminarLogico(cuentasPropias, usuarioActual);
    }
    
    public ArrayList<CuentasPropiasDTO> listarTodos(){
        return (ArrayList<CuentasPropiasDTO>) cuentasPropiasDAO.listarTodos();
    }
    
    public ArrayList<CuentasPropiasDTO> listarActivas(){
        ArrayList<CuentasPropiasDTO> todas = listarTodos();
        ArrayList<CuentasPropiasDTO> activas = new ArrayList<>();
        
        for (CuentasPropiasDTO cuenta : todas) {
            if (cuenta.getActiva() != null && cuenta.getActiva()) {
                activas.add(cuenta);
            }
        }
        
        return activas;
    }
    
    public CuentasPropiasDTO obtenerPorId(Integer cuentas_propias_id){
        if (cuentas_propias_id == null || cuentas_propias_id <= 0) {
            throw new IllegalArgumentException("El ID de la cuenta es obligatorio.");
        }
        
        return this.cuentasPropiasDAO.obtenerPorId(cuentas_propias_id);
    }
    
    // Método adicional para validar saldo suficiente (RF011)
    public boolean tieneSaldoSuficiente(Integer cuentas_propias_id, BigDecimal montoRequerido){
        CuentasPropiasDTO cuenta = obtenerPorId(cuentas_propias_id);
        if (cuenta == null || cuenta.getSaldo_disponible() == null) {
            return false;
        }
        
        return cuenta.getSaldo_disponible().compareTo(montoRequerido) >= 0;
    }
    
    public ArrayList<CuentasPropiasDTO> listarPorEntidadBancaria(Integer entidad_bancaria_id){
        ArrayList<CuentasPropiasDTO> todas = listarTodos();
        ArrayList<CuentasPropiasDTO> cuentasEntidad = new ArrayList<>();
        
        for (CuentasPropiasDTO cuenta : todas) {
            if (cuenta.getEntidad_bancaria() != null && cuenta.getEntidad_bancaria().getEntidad_bancaria_id().equals(entidad_bancaria_id)) {
                cuentasEntidad.add(cuenta);
            }
        }
        return cuentasEntidad;
    }
    
}