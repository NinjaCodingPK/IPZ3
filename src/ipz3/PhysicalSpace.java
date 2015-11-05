/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz3;

import Page.PhysicalPage;
import java.util.ArrayList;

/**
 *
 * @author wookie
 */
public class PhysicalSpace {
    private ArrayList<PhysicalPage> physical_space = new ArrayList<>();
    
    public PhysicalSpace(int size) {
        for(int i = 0; i < 10; i++) {
            physical_space.add(new PhysicalPage(i, size));
        }
    }

    public ArrayList<PhysicalPage> getPhysical_space() {
        return physical_space;
    }

    public void setPhysical_space(ArrayList<PhysicalPage> physical_space) {
        this.physical_space = physical_space;
    }
    
    public boolean IsFreePage() {
        for(PhysicalPage p : physical_space) {
            if(!p.isBusy())
                return true;
        }
        
        return false;
    }
    
    public int TakePage() {
        for(PhysicalPage p : physical_space) {
            if(!p.isBusy())
            {
                p.setBusy(true);
                return p.getNumber();
            }
        }
        
        return -1;
    }
    
    public void FreePage(int num) {
        physical_space.get(num).setBusy(false);
    }
    
    public void GetState() {
        for(PhysicalPage p : physical_space) {
            System.out.println("Number: " + p.getNumber());
            System.out.println("Business: " + p.isBusy());
        }
    }
}
