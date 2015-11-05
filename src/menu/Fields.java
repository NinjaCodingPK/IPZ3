/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ipz3.*;
//import ipz3.PhysicalSpace;
//import ipz3.MemProcess;
import Page.*;
import java.util.ArrayList;
/**
 *
 * @author wookie
 */
public class Fields {
    //private JTable SpaceTable;
    //private JTable ProcessTable;
    
    public Fields() {
        //SpaceTable = space;
        //ProcessTable = process;   
    }
    
//    public void setMoney(Buffer input, int x) {
//         for(int i=0; i < input.getValues().length; i++)
//            MoneyTable.getModel().setValueAt(input.Get(i), 0, i);
//    }
//    
//    public int getMoney(int x, int y) {
//        return (int) MoneyTable.getModel().getValueAt(x, y);
//    }
//    
//    public int[] getAllMoney() {
//        int[] temp = new int[7];
//        
//        for(int i = 0; i < 7; i++)
//        {
//            temp[i] = getMoney(0, i);
//        }
//        
//        return temp;
//    }
    public void AddLog(String message, JTable table) {
        DefaultTableModel myModel = (DefaultTableModel) table.getModel();
        myModel.addRow(new Object[]{message});
    }
    
    public void AddSpace(PhysicalSpace space, JTable table) {
        DefaultTableModel myModel = (DefaultTableModel) table.getModel();
        for(PhysicalPage p : space.getPhysical_space()) 
            myModel.addRow(new Object[]{p.getNumber(), p.isBusy(), p.isModification()});
    }
    
    public void AddProcess(MemProcess proc, JTable table) {
        int page_num = 0;
        DefaultTableModel myModel = (DefaultTableModel) table.getModel();
        
        for(Section s : proc.getSections())
            for(VirtualPage p : s.getPages())
            {
                myModel.addRow(new Object[]{proc.getNumber(), page_num, p.isLoad(), Integer.toBinaryString(p.getPhys_number())});
                page_num++;
            }
    }
    
    public void RefreshSpace(PhysicalSpace page, JTable table) {
        int i = 0;
        for(PhysicalPage p : page.getPhysical_space()) {
            table.getModel().setValueAt(p.isBusy(), i, 1);
            i++;
        }
    }
    
    public void RefreshProcess(ArrayList<MemProcess> process_pool, JTable table) {
        int i = 0;
        
        for(MemProcess proc : process_pool) 
            for(Section s : proc.getSections())
                for(VirtualPage p : s.getPages()) {
                    table.getModel().setValueAt(p.isLoad(), i, 2);
                    table.getModel().setValueAt(Integer.toBinaryString(p.getPhys_number()), i, 3);
                    i++;
                }
               
    }
}
