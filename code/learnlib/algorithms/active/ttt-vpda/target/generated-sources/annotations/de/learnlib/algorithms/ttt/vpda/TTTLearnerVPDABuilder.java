/*
 * Automatically generated by BuilderGen, https://github.com/misberner/buildergen.
 * DO NOT EDIT THIS FILE! Your changes will be overwritten.
 * Edit the @GenerateBuilder specification of class
 * de.learnlib.algorithms.ttt.vpda.TTTLearnerVPDA<I>
 */

package de.learnlib.algorithms.ttt.vpda;

public final class TTTLearnerVPDABuilder<
		I extends java.lang.Object> {

	private static<
			I extends java.lang.Object> de.learnlib.algorithms.ttt.vpda.TTTLearnerVPDA<I> $createDispatch(
		net.automatalib.words.VPDAlphabet<I> alphabet,
		de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle,
		de.learnlib.acex.AcexAnalyzer analyzer
		) {
		return new de.learnlib.algorithms.ttt.vpda.TTTLearnerVPDA<I>(
			alphabet,
			oracle,
			analyzer
			);
	}

	private net.automatalib.words.VPDAlphabet<I> alphabet;
	private de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle;
	private de.learnlib.acex.AcexAnalyzer analyzer;

	public TTTLearnerVPDABuilder() {
	}

	public de.learnlib.algorithms.ttt.vpda.TTTLearnerVPDA<I> create() {
		return $createDispatch(
			this.alphabet,
			this.oracle,
			this.analyzer
			);
	}

	public net.automatalib.words.VPDAlphabet<I> getAlphabet() {
		return this.alphabet;
	}
	public void setAlphabet(net.automatalib.words.VPDAlphabet<I> alphabet) {
		this.alphabet = alphabet;
	}
	public TTTLearnerVPDABuilder<I> withAlphabet(net.automatalib.words.VPDAlphabet<I> alphabet) {
		this.alphabet = alphabet;
		return this;
	} 

	public de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> getOracle() {
		return this.oracle;
	}
	public void setOracle(de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle) {
		this.oracle = oracle;
	}
	public TTTLearnerVPDABuilder<I> withOracle(de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle) {
		this.oracle = oracle;
		return this;
	} 

	public de.learnlib.acex.AcexAnalyzer getAnalyzer() {
		return this.analyzer;
	}
	public void setAnalyzer(de.learnlib.acex.AcexAnalyzer analyzer) {
		this.analyzer = analyzer;
	}
	public TTTLearnerVPDABuilder<I> withAnalyzer(de.learnlib.acex.AcexAnalyzer analyzer) {
		this.analyzer = analyzer;
		return this;
	} 

}