package pe.edu.pucp.softpac.daoImpl.util;

public class ReporteFacturasPendientesParametrosBuilder {
    private Integer idAcreedor;
    private Integer idPais;
    private Integer idMoneda;

    public ReporteFacturasPendientesParametrosBuilder conIdAcreedor(Integer idAcreedor) {
        this.idAcreedor = idAcreedor;
        return this;
    }

    public ReporteFacturasPendientesParametrosBuilder conIdPais(Integer idPais) {
        this.idPais = idPais;
        return this;
    }

    public ReporteFacturasPendientesParametrosBuilder conIdMoneda(Integer idMoneda) {
        this.idMoneda = idMoneda;
        return this;
    }

    public ReporteFacturasPendientesParametros build() {
        return new ReporteFacturasPendientesParametros(idAcreedor, idPais, idMoneda);
    }
}