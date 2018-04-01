package com.swof.impl.factory;

import com.swof.impl.service.EngineerPool;
import com.swof.interfaces.IEngineerPool;
import com.swof.interfaces.IEngineerFactory;
import com.swof.interfaces.IEngineerPoolList;
import com.swof.interfaces.IRandomAdapter;
import com.swof.model.Engineer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EngineerFactory implements IEngineerFactory {
    private IEngineerPoolList engineerRepository;
    private IRandomAdapter randomAdapter;

    @Autowired
    public EngineerFactory(IEngineerPoolList engineerRepository, IRandomAdapter randomAdapter) {
        this.randomAdapter = randomAdapter;
        this.engineerRepository = engineerRepository;
    }

    /**
     * Creates a pool, populated with the engineers names.
     * If each engineer needs to perform 2 shifts per period, then their
     * names need to go into the hat 2 times each.
     *
     * @param shiftsPerEngineerPerPeriod Number of shifts per engineer in a period
     * @return The created engineer pool
     */
    public final IEngineerPool create(int shiftsPerEngineerPerPeriod) {
        EngineerPool engineerPool = new EngineerPool(randomAdapter);
        ArrayList<Engineer> engineers = engineerRepository.getAll();
        for (int i = 0; i < shiftsPerEngineerPerPeriod; i++) {
            engineerPool.addEngineers(engineers);
        }
        return engineerPool;
    }
}
