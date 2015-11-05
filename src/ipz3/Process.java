/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz3;

import Page.VirtualPage;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author wookie
 */
public class Process {
    private final ArrayList<Section> sections = new ArrayList<>();
    private final int size;
    private final int page_size;
    private final int pages_in_section;
    private double pages_count;
    private double section_count;
    
    public Process(int size, int page_size) {
        pages_in_section = 2;
        this.size = size;
        this.page_size = page_size;
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
        }
        
        return new Pair<>(-1, -1);
    }
    
    public void LoadPage(PhysicalSpace space) {
        Pair<Integer, Integer> id = GetUnloadPage();
        
        if(id.getKey() != -1)
            if(space.IsFreePage())
            {
                sections.get(id.getKey()).getPages().get(id.getValue()).setPhys_number(space.TakePage());
                sections.get(id.getKey()).getPages().get(id.getValue()).setLoad(true);
            }
    }
    
    public void UnloadPage(PhysicalSpace space) {
        Pair<Integer, Integer> id = GetUnloadPage();
        
        if(id.getKey() != -1) {
            space.FreePage(sections.get(id.getKey()).getPages().get(id.getValue()).getPhys_number());
            sections.get(id.getKey()).getPages().get(id.getValue()).setLoad(false);
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
    
}
