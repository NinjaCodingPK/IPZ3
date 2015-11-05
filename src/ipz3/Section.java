/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz3;


import Page.VirtualPage;
import java.util.ArrayList;
/**
 *
 * @author wookie
 */
public class Section {
    private ArrayList<VirtualPage> pages = new ArrayList<>();
    
    
    public Section(int count, int size) {
        for(int i = 0; i < count; i++) {
            pages.add(new VirtualPage(size));
        }
    }

    public ArrayList<VirtualPage> getPages() {
        return pages;
    }
    
    public void AddPage(VirtualPage page) {
        pages.add(page);
    }
    
}
