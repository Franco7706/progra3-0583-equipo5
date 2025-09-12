package pe.edu.pucp.softpac.model;

public class CuentasAcreedorDTO extends CuentasBancariasDTO {
    private AcreedoresDTO acreedor;
    public CuentasAcreedorDTO(){
        super();
        acreedor=null;
    }
    
    public CuentasAcreedorDTO(String cuenta_bancaria_id, String tipo_cuenta, String cci, Boolean activa, String numero_cuenta, MonedasDTO moneda, EntidadesBancariasDTO entidad_bancaria, AcreedoresDTO acreedor){
        super(cuenta_bancaria_id,tipo_cuenta,cci,activa,numero_cuenta,moneda,entidad_bancaria);
        this.acreedor=new AcreedoresDTO(acreedor);
    }
    
    public CuentasAcreedorDTO(CuentasAcreedorDTO cuenta_acreedor){
        super(cuenta_acreedor);
        this.acreedor=new AcreedoresDTO(cuenta_acreedor.getProveedor());
    }

    /**
     * @return the acreedor
     */
    public AcreedoresDTO getProveedor() {
        return acreedor;
    }

    /**
     * @param acreedor the acreedor to set
     */
    public void setProveedor(AcreedoresDTO acreedor) {
        this.acreedor = acreedor;
    }

    
}
