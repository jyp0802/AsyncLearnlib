/*
 * Automatically generated by BuilderGen, https://github.com/misberner/buildergen.
 * DO NOT EDIT THIS FILE! Your changes will be overwritten.
 * Edit the @GenerateBuilder specification of class
 * de.learnlib.algorithms.ttt.mealy.TTTLearnerMealy<I,O>
 */

package de.learnlib.algorithms.ttt.mealy;

public final class TTTLearnerMealyBuilder<
		I extends java.lang.Object,
		O extends java.lang.Object> {

	private static<
			I extends java.lang.Object,
			O extends java.lang.Object> de.learnlib.algorithms.ttt.mealy.TTTLearnerMealy<I,O> $createDispatch(
		net.automatalib.words.Alphabet<I> alphabet,
		de.learnlib.api.oracle.MembershipOracle<I,net.automatalib.words.Word<O>> oracle,
		de.learnlib.acex.AcexAnalyzer analyzer
		) {
		return new de.learnlib.algorithms.ttt.mealy.TTTLearnerMealy<I,O>(
			alphabet,
			oracle,
			analyzer
			);
	}

	private net.automatalib.words.Alphabet<I> alphabet;
	private de.learnlib.api.oracle.MembershipOracle<I,net.automatalib.words.Word<O>> oracle;
	private de.learnlib.acex.AcexAnalyzer analyzer;

	public TTTLearnerMealyBuilder() {
		this.analyzer = de.learnlib.algorithms.ttt.base.AbstractTTTLearner.BuilderDefaults.analyzer();
	}

	public de.learnlib.algorithms.ttt.mealy.TTTLearnerMealy<I,O> create() {
		return $createDispatch(
			this.alphabet,
			this.oracle,
			this.analyzer
			);
	}

	public net.automatalib.words.Alphabet<I> getAlphabet() {
		return this.alphabet;
	}
	public void setAlphabet(net.automatalib.words.Alphabet<I> alphabet) {
		this.alphabet = alphabet;
	}
	public TTTLearnerMealyBuilder<I, O> withAlphabet(net.automatalib.words.Alphabet<I> alphabet) {
		this.alphabet = alphabet;
		return this;
	} 

	public de.learnlib.api.oracle.MembershipOracle<I,net.automatalib.words.Word<O>> getOracle() {
		return this.oracle;
	}
	public void setOracle(de.learnlib.api.oracle.MembershipOracle<I,net.automatalib.words.Word<O>> oracle) {
		this.oracle = oracle;
	}
	public TTTLearnerMealyBuilder<I, O> withOracle(de.learnlib.api.oracle.MembershipOracle<I,net.automatalib.words.Word<O>> oracle) {
		this.oracle = oracle;
		return this;
	} 

	public de.learnlib.acex.AcexAnalyzer getAnalyzer() {
		return this.analyzer;
	}
	public void setAnalyzer(de.learnlib.acex.AcexAnalyzer analyzer) {
		this.analyzer = analyzer;
	}
	public TTTLearnerMealyBuilder<I, O> withAnalyzer(de.learnlib.acex.AcexAnalyzer analyzer) {
		this.analyzer = analyzer;
		return this;
	} 

}