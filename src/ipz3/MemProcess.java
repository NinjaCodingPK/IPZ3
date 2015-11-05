/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz3;

import Page.VirtualPage;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.swing.JTable;
import menu.Fields;
/**
 *
 * @author wookie
 */
public class MemProcess {
    private final ArrayList<Section> sections = new ArrayList<>();
    private final int size;
    private final int page_size;
    private final int pages_in_section;
    private final int number;
    private double pages_count;
    private double section_count;
    
    public MemProcess(int number, int size, int page_size) {
        pages_in_section = 2;
        this.size = size;
        this.page_size = page_size;
        this.number = number;
        PagesCountForm();
        SectionsForm();
        
    }
    
    private void PagesCountForm() {
        pages_count = size/page_size;
        if(pages_count - (int)pages_count > 0)
            pages_count = (int)pages_count + 1;
    }
    
    private void SectionsForm() {
        section_count = pages_count/pages_in_section;
        if(section_count - (int)section_count > 0)
            section_count = (int)section_count + 1;
        
        for(int i = 0; i < section_count; i++) {
            sections.add(new Section(pages_in_section, page_size)); 
        }
    }
    
    public Pair<Integer, Integer> GetUnloadPage() {
        int section = 0, page = 0;
        
        for(Section s : sections) {
            for(VirtualPage p :  s.getPages()) {
                if(!p.isLoad())
                    return new Pair<>(section, page);
                else {
                    page++;
                }    
            }
            section++;
            page = 0;
        }
        
        return new Pair<>(-1, -1);
    }
    
    public Pair<Integer, Integer> GetFirstLoadPage() {
        int section = 0, page = 0;
        
        for(Section s : sections) {
            for(VirtualPage p :  s.getPages()) {
                if(p.isLoad())
                    return new Pair<>(section, page);
                else {
                    page++;
                }    
            }
            section++;
            page = 0;
        }
        
        return new Pair<>(-1, -1);
    }
    
    public void LoadPage(PhysicalSpace space, JTable table) {
        Pair<Integer, Integer> id = GetUnloadPage();
        Fields f = new Fields();
        
        if(id.getKey() != -1)
            if(space.IsFreePage())
            {
                sections.get(id.getKey()).getPages().get(id.getValue()).setPhys_number(space.TakePage());
                sections.get(id.getKey()).getPages().get(id.getValue()).setLoad(true);
                f.AddLog("Page was successfuly loaded", table);
            }
            else
                f.AddLog("Can't load page. No free Physical page.", table);
        else
            f.AddLog("Can't load any page. No free Virtual page.", table);
    }
    
    public void UnloadPage(PhysicalSpace space, JTable table) {
        Pair<Integer, Integer> id = GetFirstLoadPage();
        Fields f = new Fields();
        
        if(id.getKey() != -1) {
            int pnum = sections.get(id.getKey()).getPages().get(id.getValue()).getPhys_number();
            space.FreePage(pnum);
            sections.get(id.getKey()).getPages().get(id.getValue()).setLoad(false);
            sections.get(id.getKey()).getPages().get(id.getValue()).setPhys_number(-1);
            if(space.getPhysical_space().get(id.getKey()).isModification())
            {
                f.AddLog("Page was successfuly unloaded.", table);
                f.AddLog("Physical Number = " + Integer.toBinaryString(pnum), table);
            }
            else
                f.AddLog("Page was successfuly unloaded", table);
        }
        else {
            f.AddLog("Can't unload page", table);
        }
    }
    
    public void GetState() {
        int num = 0;
        
        for(Section s : sections) {
            for(VirtualPage p :  s.getPages()) {
                System.out.println(num);
                System.out.println(p.isLoad());
                System.out.println(Integer.toBinaryString(p.getPhys_number()));
                
                num++;
            }
        }
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public int getNumber() {
        return number;
    }
    
    public void LoadAll(PhysicalSpace space, JTable table) {
        for(Section s : sections)
            for(VirtualPage p : s.getPages()) {
                if(space.IsFreePage())
                    this.LoadPage(space, table);
            }
    }
}
