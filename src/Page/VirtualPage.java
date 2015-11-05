/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Page;

import javafx.util.Pair;

/**
 *
 * @author wookie
 */
public class VirtualPage {
    final private int size;
    final private double digit;
    private int mask;
    private int phys_number;
    private boolean load;
    //private boolean presence;
    //private boolean modification;
    //private boolean access;
    
    public VirtualPage(int size) {
        this.size = size;
        this.digit = Math.log(size)/Math.log(2);
        mask = 0;
        phys_number = -1;
        load = false;
        //presence = false;
        //modification = false;
        //access = false;
        
        MaskForm();
    }
    
    private void MaskForm() {
        for(int i = 1; i < digit; i++) {
            mask = mask << 1;
            mask = mask | 1;
        }
        
        //System.out.print(Integer.toBinaryString(mask));
    }
    
    public Pair<Integer, Integer> GetPhisycalAdress(int adr) {
        int num, offset;
        
        offset = adr & mask;
        
        num = adr ^ offset;
        num = num >> (int)digit-1;
        
        return new Pair<>(num, offset);
        
    }

    public int getPhys_number() {
        return phys_number;
    }

    public void setPhys_number(int phys_number) {
        this.phys_number = phys_number;
    }

    public boolean isLoad() {
        return load;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }
    
}
