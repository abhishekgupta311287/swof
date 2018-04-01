package com.swof.impl.service;


import com.swof.interfaces.IEngineerPool;
import com.swof.interfaces.IRandomAdapter;
import com.swof.model.Engineer;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EngineerPool implements IEngineerPool {
    private IRandomAdapter randomAdapter;


    private ArrayList<Engineer> engineersAvailable;
    private ArrayList<Engineer> engineersPullable;
    @Autowired
    public EngineerPool(IRandomAdapter randomAdapter) {
        this.engineersAvailable = new ArrayList<>();
        this.engineersPullable = new ArrayList<>();
        this.randomAdapter = randomAdapter;
    }

    public final int getAvailableEngineers() {
        return engineersAvailable.size();
    }

    public final int getPullableEngineers() {
        return engineersPullable.size();
    }

    public final void addEngineers(ArrayList<Engineer> engineers) {
        engineersAvailable.addAll(engineers);
        engineersPullable.addAll(engineers);
    }

    public final Engineer getRandomEngineer() {
        Engineer engineer = null;

        if (!engineersPullable.isEmpty()) {
            engineer = engineersPullable.get(randomAdapter.nextInt(engineersPullable.size()));
            engineersPullable.remove(engineer);
        }
        return engineer;

    }

    public final void removeEngineer(Engineer engineer) {
        engineersAvailable.remove(engineer);
    }

    public final void resetPullableEngineers() {
        engineersPullable.clear();
        engineersPullable.addAll(engineersAvailable);
    }
}