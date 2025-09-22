package pe.edu.pucp.softpac.model;

import java.math.BigDecimal;

public class CuentasPropiasDTO extends CuentasBancariasDTO{
    private BigDecimal saldo_disponible;
    public CuentasPropiasDTO(){
        super();
        saldo_disponible=new BigDecimal("0.0");
    }
    
    public CuentasPropiasDTO(Integer cuenta_bancaria_id, String tipo_cuenta, String cci, Boolean activa, String numero_cuenta, MonedasDTO moneda, EntidadesBancariasDTO entidad_bancaria, BigDecimal saldo_disponible){
        super(cuenta_bancaria_id,tipo_cuenta,cci,activa,numero_cuenta,moneda,entidad_bancaria);
        this.saldo_disponible=saldo_disponible;
    }

    public CuentasPropiasDTO(CuentasPropiasDTO cuentaPropia){
        super(cuentaPropia);
        this.saldo_disponible=cuentaPropia.getSaldo_disponible();
    }
    /**
     * @return the saldo_disponible
     */
    public BigDecimal getSaldo_disponible() {
        return saldo_disponible;
    }

    /**
     * @param saldo_disponible the saldo_disponible to set
     */
    public void setSaldo_disponible(BigDecimal saldo_disponible) {
        this.saldo_disponible = saldo_disponible;
    }
}
