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
public class SymbolTable {
    List <Symbol> list = new ArrayList<Symbol>();
    Symbol entry;
    SymbolTable (Symbol entry){
        this.entry = entry;
        this.list = list;
        list.add(entry);
    }
}
