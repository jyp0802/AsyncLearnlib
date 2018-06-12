/* Copyright (C) 2013-2018 TU Dortmund
 * This file is part of LearnLib, http://www.learnlib.de/.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.learnlib.oracle.equivalence.mealy;

import java.util.*;

import de.learnlib.api.SUL;
import de.learnlib.api.oracle.EquivalenceOracle.MealyEquivalenceOracle;
import de.learnlib.api.query.DefaultQuery;
import net.automatalib.automata.transout.MealyMachine;
import net.automatalib.commons.util.collections.CollectionsUtil;
import net.automatalib.words.Word;
import net.automatalib.words.WordBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Performs a random walk over the hypothesis. A random walk restarts with a fixed probability after every step and
 * terminates after a fixed number of steps or with a counterexample. The number of steps to termination may be reset
 * for every new search.
 *
 * @param <I>
 *         input symbol type
 * @param <O>
 *         output symbol type
 *
 * @author falkhowar
 */
public class RandomWalkEQOracle<I, O> implements MealyEquivalenceOracle<I, O> {

    public static boolean eq = false;
    public static boolean wait = false;

    public static boolean isEq() {
        return eq;
    }
    public static boolean isWait() {
        return wait;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomWalkEQOracle.class);

    /**
     * probability to restart before step.
     */
    private final double restartProbability;

    /**
     * maximum number of steps.
     */
    private final long maxSteps;
    /**
     * RNG.
     */
    private final Random random;
    /**
     * System under learning.
     */
    private final SUL<I, O> sul;
    /**
     * step counter.
     */
    private long steps;
    /**
     * flag for reseting step count after every search.
     */
    private boolean resetStepCount;

    public RandomWalkEQOracle(SUL<I, O> sul,
                              double restartProbability,
                              long maxSteps,
                              boolean resetStepCount,
                              Random random) {
        this(sul, restartProbability, maxSteps, random);
        this.resetStepCount = resetStepCount;
    }

    public RandomWalkEQOracle(SUL<I, O> sul,
                              double restartProbability,
                              long maxSteps,
                              Random random) {
        this.restartProbability = restartProbability;
        this.maxSteps = maxSteps;
        this.random = random;
        this.sul = sul;
    }

    @Override
    public DefaultQuery<I, Word<O>> findCounterExample(MealyMachine<?, I, ?, O> hypothesis,
                                                       Collection<? extends I> inputs) {
        return doFindCounterExample(hypothesis, inputs);
    }

    // Original function. Sends a query, receives the output, checks if output is equal to hypothesis
//    private <S, T> DefaultQuery<I, Word<O>> doFindCounterExample(MealyMachine<S, I, T, O> hypothesis,
//                                                                 Collection<? extends I> inputs) {
//        // reset termination counter?
//        if (resetStepCount) {
//            steps = 0;
//        }
//
//        if (inputs.isEmpty()) {
//            LOGGER.warn("Passed empty set of inputs to equivalence oracle; no counterexample can be found!");
//            return null;
//        }
//
//        List<? extends I> choices = CollectionsUtil.randomAccessList(inputs);
//        int bound = choices.size();
//        S cur = hypothesis.getInitialState();
//        WordBuilder<I> wbIn = new WordBuilder<>();
//        WordBuilder<O> wbOut = new WordBuilder<>();
//
//        boolean first = true;
//        sul.pre();
//        try {
//            while (steps < maxSteps) {
//
//                if (first) {
//                    first = false;
//                } else {
//                    // restart?
//                    double restart = random.nextDouble();
//                    if (restart < restartProbability) {
//                        sul.post();
//                        sul.pre();
//                        cur = hypothesis.getInitialState();
//                        wbIn.clear();
//                        wbOut.clear();
//                        first = true;
//                    }
//                }
//
//                // step
//                steps++;
//                I in = choices.get(random.nextInt(bound));
//                O outSul;
//
//                outSul = sul.step(in);
//
//                T hypTrans = hypothesis.getTransition(cur, in);
//                O outHyp = hypothesis.getTransitionOutput(hypTrans);
//                wbIn.add(in);
//                wbOut.add(outSul);
//
//                // ce?
//                if (!outSul.equals(outHyp)) {
//                    DefaultQuery<I, Word<O>> ce = new DefaultQuery<>(wbIn.toWord());
//                    ce.answer(wbOut.toWord());
//                    return ce;
//                }
//                cur = hypothesis.getSuccessor(cur, in);
//            }
//            return null;
//        } finally {
//            sul.post();
//        }
//    }

    // Modified function so that multiple equivalence queries are pipelined at once and the outputs are received all together
    // Continuously sends queries, once 10 queries are sent or needs to restart, receives all outputs for the sent queries, checks if each output is equal to hypothesis
    private <S, T> DefaultQuery<I, Word<O>> doFindCounterExample(MealyMachine<S, I, T, O> hypothesis,
                                                                 Collection<? extends I> inputs) {
        // reset termination counter?
        if (resetStepCount) {
            steps = 0;
        }

        if (inputs.isEmpty()) {
            LOGGER.warn("Passed empty set of inputs to equivalence oracle; no counterexample can be found!");
            return null;
        }

        List<? extends I> choices = CollectionsUtil.randomAccessList(inputs);
        int bound = choices.size();
        S cur = hypothesis.getInitialState();
        WordBuilder<I> wbIn = new WordBuilder<>();
        WordBuilder<O> wbOut = new WordBuilder<>();

        ArrayList<I> inList = new ArrayList<>();
        int maxPipe = 10;
        eq = true;
        wait = true;

        boolean first = true;
        sul.pre();
        try {
            while (steps < maxSteps) {

                if (first) {
                    first = false;
                } else {
                    // restart?
                    double restart = random.nextDouble();
                    if (restart < restartProbability || inList.size() >= maxPipe-1) {

                        wait = false;
                        steps++;
                        I in = choices.get(random.nextInt(bound));
                        O[] outSuls = sul.stepMultiple(in);
                        O outSul;
                        inList.add(in);

                        for (int i=0; i<inList.size(); i++) {
                            in = inList.get(i);
                            outSul = outSuls[i];
                            T hypTrans = hypothesis.getTransition(cur, in);
                            O outHyp = hypothesis.getTransitionOutput(hypTrans);
                            wbIn.add(in);
                            wbOut.add(outSul);

                            // ce?
                            if (!(outSul).equals(outHyp)) {
                                DefaultQuery<I, Word<O>> ce = new DefaultQuery<>(wbIn.toWord());
                                ce.answer(wbOut.toWord());
                                eq = false;

                                return ce;
                            }
                            cur = hypothesis.getSuccessor(cur, in);
                        }
                        wait = true;
                        inList.clear();

                        if (restart < restartProbability) {
                            sul.post();
                            sul.pre();
                            cur = hypothesis.getInitialState();
                            wbIn.clear();
                            wbOut.clear();
                            first = true;
                        }
                    }
                }

                // step
                steps++;
                I in = choices.get(random.nextInt(bound));
                sul.step(in);
                inList.add(in);

            }
            return null;
        } finally {
            eq = false;
            wait = false;
            sul.post();
        }
    }
}
