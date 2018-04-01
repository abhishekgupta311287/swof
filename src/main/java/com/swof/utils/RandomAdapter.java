package com.swof.utils;

import com.swof.interfaces.IRandomAdapter;

import java.util.Random;

import org.springframework.stereotype.Component;


@Component
public class RandomAdapter implements IRandomAdapter {
    private Random random;

    public RandomAdapter() {
        this.random = new Random();
    }

    public final int nextInt(int max) {
        return random.nextInt(max);
    }
}
