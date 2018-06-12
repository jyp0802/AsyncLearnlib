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
package de.learnlib.util.mealy;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import de.learnlib.api.algorithm.LearningAlgorithm;
import de.learnlib.api.query.DefaultQuery;
import net.automatalib.automata.transout.MealyMachine;
import net.automatalib.words.Word;

@ParametersAreNonnullByDefault
final class MealyLearnerWrapper<M extends MealyMachine<?, I, ?, O>, I, O>
        implements LearningAlgorithm.MealyLearner<I, O> {

    private final LearningAlgorithm<M, I, O> learner;

    private M hypothesis;

    MealyLearnerWrapper(LearningAlgorithm<M, I, O> learner) {
        this.learner = learner;
    }

    @Override
    public void startLearning() {
        learner.startLearning();
    }

    @Override
    public boolean refineHypothesis(DefaultQuery<I, Word<O>> ceQuery) {
        if (hypothesis == null) {
            hypothesis = learner.getHypothesisModel();
        }

        DefaultQuery<I, O> reducedQry = MealyUtil.reduceCounterExample(hypothesis, ceQuery);

        if (reducedQry == null) {
            return false;
        }

        hypothesis = null;
        return learner.refineHypothesis(reducedQry);
    }

    @Override
    @Nonnull
    public M getHypothesisModel() {
        hypothesis = learner.getHypothesisModel();
        return hypothesis;
    }

}
