/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpac.bo;

import org.junit.jupiter.api.Test;
import pe.edu.pucp.softpac.bo.util.PasswordUtil;

/**
 *
 * @author USUARIO
 */
public class HashTest {
    
    @Test
    public void HashContraseña(){
        String c = PasswordUtil.hashPassword("contrasenha");
        System.out.println(c);
    }
}
