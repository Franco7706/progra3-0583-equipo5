package pe.edu.pucp.softpac.model;

public class CuentasAcreedorDTO extends CuentasBancariasDTO {
    private AcreedoresDTO acreedor;
    public CuentasAcreedorDTO(){
        super();
        acreedor=null;
    }
    
    public CuentasAcreedorDTO(Integer cuenta_bancaria_id, String tipo_cuenta, String cci, Boolean activa, String numero_cuenta, MonedasDTO moneda, EntidadesBancariasDTO entidad_bancaria, AcreedoresDTO acreedor){
        super(cuenta_bancaria_id,tipo_cuenta,cci,activa,numero_cuenta,moneda,entidad_bancaria);
        this.acreedor=new AcreedoresDTO(acreedor);
    }
    
    public CuentasAcreedorDTO(CuentasAcreedorDTO cuenta_acreedor){
        super(cuenta_acreedor);
        this.acreedor=new AcreedoresDTO(cuenta_acreedor.getAcreedor());
    }

    /**
     * @return the acreedor
     */
    public AcreedoresDTO getAcreedor() {
        return acreedor;
    }

    /**
     * @param acreedor the acreedor to set
     */
    public void setAcreedor(AcreedoresDTO acreedor) {
        this.acreedor = acreedor;
    }

    
}
