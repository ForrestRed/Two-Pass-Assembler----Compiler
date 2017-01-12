/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two.pass.assembler;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Forrest Red
 */
public class TwoPassAssembler {
    
    public static void main(String[] args) throws FileNotFoundException {
        final int HALT = 0; final int PUSH = 1; final int RVALUE = 2; final int LVALUE = 3; final int POP = 4; final int COPY = 6; final int ADD = 7; final int SUB = 8;
        final int MPY = 9; final int DIV = 10; final int MOD = 11; final int OR = 12; final int AND = 13; final int LABEL = 14; final int GOTO = 15; final int GOFALSE = 16;
        final int GOTRUE = 17; final int PRINT = 18;
        System.out.println("hello");
        SymbolTable Table;
        SymbolTable symTable;
        Symbol sym1 = new Symbol("answer", "Int", 1, 0);
        Table = new SymbolTable(sym1);
        SymbolTable Table1 = new SymbolTable(sym1);
        System.out.println(Table.list.get(0).lexeme);
        System.out.println(Table1.list.get(0).lexeme);
        
        //Beginning of Pass 1
        //Section .data
        String line = null;
        String token = null;
        int count = 0;
        int counter1 = 1;
        int counter2 = 0;
        String lexTemp = null;
        String typeTemp = null;
        int addrTemp = 0;
        
        String content = new Scanner(new File("input.txt")).useDelimiter("\\Z").next();
        System.out.println(content);
        StringTokenizer st = new StringTokenizer(content, "\t");
        while(st.hasMoreTokens()){
            System.out.println(st.nextToken());
            break;
        }
        
        
        while(st.hasMoreTokens()) {
            if(count == 3)
                count = 0;
            token = st.nextToken().toString();
            Symbol sym = new Symbol(lexTemp, typeTemp, addrTemp, 0);
            switch (count) {
                case 0: 
                    token = token.replace(":", "");
                    System.out.println(token);
                    lexTemp = token;
                    System.out.println("hello from case 0");
                    break;
                case 1:
                    if("dw".equals(token))
                        typeTemp = "Int";
                    System.out.println("hello from case 1");
                    break;
                case 2: 
                    addrTemp = counter1;
                    Table.list.add(sym);
                    counter1++;
                    System.out.println("hello from case 2");
                    break;
                
            }
            count++;
        }
        
        System.out.println("Test: " + Table.list.get(5).address);
        
        
        //Section .code
        
        String content2 = new Scanner(new File("input2.txt")).useDelimiter("\\Z").next();
        System.out.println(content2);
        
        StringTokenizer st2 = new StringTokenizer(content2, "\t");
        while(st2.hasMoreTokens()){
            System.out.println(st2.nextToken());
            break;
        }
        
        while(st2.hasMoreTokens()) {
            token = st2.nextToken();
            if(token.contains("\n")) {
                counter2++;
            }
            
            if("LABEL".equals(token)){
                lexTemp = st2.nextToken();
                typeTemp = "Code";
                addrTemp = counter2;
                Symbol sym2 = new Symbol(lexTemp, typeTemp, addrTemp, 0);
                Table.list.add(sym2);
                System.out.println(lexTemp);
            }
        }
        System.out.println("Test: " + Table.list.get(7).lexeme); 
        
        //End of Pass 1
        
        
        
        //Beginning of Pass 2
        
        //Initialize data in data section
        int counter3 = 1;
        int symValue;
        StringTokenizer st4 = new StringTokenizer(content, "\t");
        while(st4.hasMoreTokens()){
            System.out.println(st4.nextToken());
            break;
        }
        
        while(st4.hasMoreTokens()) {
            if(st4.hasMoreTokens())
                token = st4.nextToken();
            else
                break;
            if(st4.hasMoreTokens())
                token = st4.nextToken();
            else
                break;
            if(st4.hasMoreTokens())
                token = st4.nextToken();
            else
                break;
            
            token = token.replaceAll("[^0-9.]", "");
            symValue = Integer.parseInt(token);
            Symbol tempSym;
            tempSym = Table.list.get(counter3);
            tempSym.value = symValue;
            Table.list.set(counter3, tempSym);
            counter3++;
        }
        System.out.println("Test Value: " +  Table.list.get(3).value);
        
        //Setting up Bytecode Opcode
        List<Integer> codeArray = new ArrayList<>();
        int pc = 0;
        int d = (int) Math.pow(2.0, 16.0);
        StringTokenizer st3 = new StringTokenizer(content2, "\t");
        while(st3.hasMoreTokens()){
            System.out.println(st3.nextToken());
            break;
        }
        
        while(st3.hasMoreTokens()) {
            token = st3.nextToken();
            if (token.contains("HALT")) {
                codeArray.add(0*d);
                pc++;
            }
            if (token.contains("PUSH")) {
                codeArray.add(1*d);
                pc++;
            }
            if (token.contains("RVALUE")) {
                codeArray.add(2*d);
                pc++;
            }
            if (token.contains("LVALUE")) {
                codeArray.add(3*d);
                pc++;
            }
            if (token.contains("POP")) {
                codeArray.add(4*d);
                pc++;
            }
            if (token.contains(":=")) {
                codeArray.add(5*d);
                pc++;
            }
            if (token.contains("COPY")) {
                codeArray.add(6*d);
                pc++;
            }
            if (token.contains("ADD")) {
                codeArray.add(7*d);
                pc++;
            }
            if (token.contains("SUB")) {
                codeArray.add(8*d);
                pc++;
            }
            if (token.contains("MPY")) {
                codeArray.add(9*d);
                pc++;
            }
            if (token.contains("DIV")) {
                codeArray.add(10*d);
                pc++;
            }
            if (token.contains("MOD")) {
                codeArray.add(11*d);
                pc++;
            }
            if (token.contains("OR")) {
                codeArray.add(12*d);
                pc++;
            }
            if (token.contains("AND")) {
                codeArray.add(13*d);
                pc++;
            }
            if (token.contains("LABEL")) {
                codeArray.add(14*d);
                pc++;
            }
            if (token.contains("GOTO")) {
                codeArray.add(15*d);
                pc++;
            }
            if (token.contains("GOFALSE")) {
                codeArray.add(16*d);
                pc++;
            }
            if (token.contains("GOTRUE")) {
                codeArray.add(17*d);
                pc++;
            }
            if (token.contains("PRINT")) {
                codeArray.add(18*d);
                pc++;
            }
        }
        
        //Setting up Bytecode Operand
        List<Integer> codeArray2 = new ArrayList<>();
        int pc2 = -1;
        int control = 1;
        int arrayListSize = Table.list.size();
        StringTokenizer st5 = new StringTokenizer(content2, "\t");
        while(st5.hasMoreTokens()){
            System.out.println(st5.nextToken());
            break;
        }
        
        while(st5.hasMoreTokens()) {
            control = 1;
            token = st5.nextToken();
            if(token.contains("\n")) {
                pc2++;
            }
            
            for(int i = 0; i <= arrayListSize - 1; i++) {
                if(token.contains(Table.list.get(i).lexeme)) {
                    codeArray.set(pc2, codeArray.get(pc2) | Table.list.get(i).value);
                    control = 0;
                }
            }
            
            if(control == 1){
                System.out.println(token);
                token = token.replaceAll("[^0-9.]", "");
            
                if(token.matches(".*\\d.*")){
                    symValue = Integer.parseInt(token);
                    codeArray.set(pc2, codeArray.get(pc2) | symValue);
                }
            }
        }
        
        String binarystr = Integer.toBinaryString(codeArray.get(3));
        System.out.println("Binary Test: " + binarystr);
        
        //Bytecode Complete
        
        //Print out Instructions
        System.out.println("Full list of Bytecodes:");
        for(int i = 0; i<= codeArray.size()-1; i++){
            binarystr = Integer.toBinaryString(codeArray.get(i));
            System.out.println(binarystr);
        }
        
        //Run Instructions
        
        int pc3 = 0;
        int instruction;
        int opcode;
        int operand;
        Stack stack = new Stack();
        boolean run = true;
        
        while(run){
            instruction = codeArray.get(pc3);
            pc3++;
            opcode = (instruction / d) & 0xFFFF;
            operand = instruction & 0xFFFF;
            
            switch (opcode){
                case HALT:
                    run = false;
                    break;
                
                case PUSH:
                    stack.push(operand);
                    break;
                    
                case RVALUE:
                    stack.push(operand);
                    break;
                
                case LVALUE:
                    stack.push(operand);
                    break;
                    
                case POP:
                    stack.pop();
                    break;
                
                case 5: //:=
                    int rvalue = (int) stack.pop();
                    int lvalue = (int) stack.pop();
                    lvalue = rvalue;
                    System.out.println("The Answer is: " + lvalue);
                    break;
                 
                case COPY:
                    int copy = (int) stack.peek();
                    stack.push(copy);
                    break;
                    
                case ADD:
                    int temp1 = (int) stack.pop();
                    int temp2 = (int) stack.pop();
                    temp1 = temp1 + temp2;
                    stack.push(temp1);
                    break;
                    
                case SUB:
                    temp1 = (int) stack.pop();
                    temp2 = (int) stack.pop();
                    temp1 = temp2 - temp1;
                    stack.push(temp1);
                    break;
                    
                case MPY:
                    temp1 = (int) stack.pop();
                    temp2 = (int) stack.pop();
                    temp1 = temp1*temp2;
                    stack.push(temp1);
                    break;
                    
                case DIV:
                    temp1 = (int) stack.pop();
                    temp2 = (int) stack.pop();
                    temp1 = temp2/temp1;
                    stack.push(temp1);
                    break;
                    
                case MOD:
                    temp1 = (int) stack.pop();
                    temp2 = (int) stack.pop();
                    temp1 = temp2%temp1;
                    stack.push(temp1);
                    break;
                    
                case OR:
                    temp1 = (int) stack.pop();
                    temp2 = (int) stack.pop();
                    temp1 = temp2 | temp1;
                    stack.push(temp1);
                    break;
                    
                case AND:
                    temp1 = (int) stack.pop();
                    temp2 = (int) stack.pop();
                    temp1 = temp2 & temp1;
                    stack.push(temp1);
                    break;
                    
                case LABEL:
                    break;
                    
                case GOTO:
                    break;
                    
                case GOFALSE:
                    break;
                
                case GOTRUE:
                    break;
                  
                case PRINT:
                    temp1 = (int) stack.pop();
                    System.out.println(temp1);
                    break;
            }
        }
    }
    
}
