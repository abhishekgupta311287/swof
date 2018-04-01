package com.swof.repo;


import com.swof.interfaces.IEngineerRepository;
import com.swof.model.Engineer;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EngineerRepository implements IEngineerRepository {

    private ArrayList<Engineer> engineers = new ArrayList<>();

    @Autowired
    private EngineerRepository() {
        engineers.add(new Engineer(0, "Abhishek"));
        engineers.add(new Engineer(1, "Ravi"));
        engineers.add(new Engineer(2, "Nilesh"));
        engineers.add(new Engineer(3, "Ruby"));
        engineers.add(new Engineer(4, "Jaya"));
        engineers.add(new Engineer(5, "Yogesh"));
        engineers.add(new Engineer(6, "Hubert"));
        engineers.add(new Engineer(7, "Paul"));
        engineers.add(new Engineer(8, "Shazia"));
        engineers.add(new Engineer(9, "Ramya"));
    }

    public final ArrayList<Engineer> getAll() {
        return engineers;
    }

    public final int add(Engineer engineer) {
        int id = engineers.size();
        engineer.setId(id);
        engineers.add(engineer);
        return id;
    }

    public final void remove(int id) {
        engineers.remove(id);
    }

    public final Engineer get(int id) {
        return engineers.get(id);
    }
}
