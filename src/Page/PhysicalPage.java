/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Page;

/**
 *
 * @author wookie
 */
public class PhysicalPage {
    final private int number;
    final private int size;
    private boolean modification; 
    private boolean busy;
    
    public PhysicalPage(int number, int size) {
        this.number = number;
        this.size = size;
        this.busy = false;
    }

    public boolean isModification() {
        return modification;
    }

    public void setModification(boolean modification) {
        this.modification = modification;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public int getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }
    
    
}
