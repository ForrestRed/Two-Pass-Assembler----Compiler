/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two.pass.assembler;
import java.util.*;

/**
 *
 * @author Forrest Red
 */
public class Symbol {
     String lexeme;
     String type;
     int address;
     int value;
    
    public Symbol (String lexeme, String type, int address, int value) {
        this.lexeme = lexeme;
        this.type = type;
        this.address = address;
        this.value = value;
    }
}
