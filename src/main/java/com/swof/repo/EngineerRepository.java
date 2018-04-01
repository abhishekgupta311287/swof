package com.swof.repo;


import com.swof.interfaces.IEngineerRepository;
import com.swof.model.Engineer;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EngineerRepository implements IEngineerRepository {

    ArrayList<Engineer> engineers = new ArrayList<>();
//
//    public static IEngineerRepository getSingleton() {
//        return ApplicationContextProvider.getApplicationContext().getBean(IEngineerRepository.class);
//    }

    @Autowired
    private EngineerRepository() {
        engineers.add(new Engineer(0, "Sujal"));
        engineers.add(new Engineer(1, "Rujal"));
        engineers.add(new Engineer(2, "Pujal"));
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
