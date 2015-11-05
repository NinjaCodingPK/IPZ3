/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz3;

import Page.VirtualPage;
import Page.PhysicalPage;
import java.util.ArrayList;

/**
 *
 * @author wookie
 */
public class IPZ3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //VirtualPage p = new VirtualPage(512);
        //System.out.println(p.GetPhisycalAdress(0b10100000001).getKey());
        //System.out.println(Integer.toBinaryString(p.GetPhisycalAdress(0b10100000001).getKey()));
        //System.out.println(Integer.toBinaryString(p.GetPhisycalAdress(0b10100000001).getValue()));
        PhysicalSpace space = new PhysicalSpace(512*10);
        //space.GetState();
        
        Process p = new Process(512*4, 512);
        p.LoadPage(space);
        p.GetState();
        //space.GetState();
    }
    
}
