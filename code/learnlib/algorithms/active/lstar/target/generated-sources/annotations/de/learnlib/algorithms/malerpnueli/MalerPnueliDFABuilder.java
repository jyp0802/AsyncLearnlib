/*
 * Automatically generated by BuilderGen, https://github.com/misberner/buildergen.
 * DO NOT EDIT THIS FILE! Your changes will be overwritten.
 * Edit the @GenerateBuilder specification of class
 * de.learnlib.algorithms.malerpnueli.MalerPnueliDFA<I>
 */

package de.learnlib.algorithms.malerpnueli;

public final class MalerPnueliDFABuilder<
		I extends java.lang.Object> {

	private static<
			I extends java.lang.Object> de.learnlib.algorithms.malerpnueli.MalerPnueliDFA<I> $createDispatch(
		net.automatalib.words.Alphabet<I> alphabet,
		de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle,
		java.util.List<net.automatalib.words.Word<I>> initialSuffixes,
		de.learnlib.algorithms.lstar.closing.ClosingStrategy<? super I,? super java.lang.Boolean> closingStrategy
		) {
		return new de.learnlib.algorithms.malerpnueli.MalerPnueliDFA<I>(
			alphabet,
			oracle,
			initialSuffixes,
			closingStrategy
			);
	}

	private net.automatalib.words.Alphabet<I> alphabet;
	private de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle;
	private java.util.List<net.automatalib.words.Word<I>> initialSuffixes;
	private de.learnlib.algorithms.lstar.closing.ClosingStrategy<? super I,? super java.lang.Boolean> closingStrategy;

	public MalerPnueliDFABuilder() {
		this.initialSuffixes = de.learnlib.algorithms.lstar.AbstractExtensibleAutomatonLStar.BuilderDefaults.initialSuffixes();
		this.closingStrategy = de.learnlib.algorithms.lstar.AbstractExtensibleAutomatonLStar.BuilderDefaults.closingStrategy();
	}

	public de.learnlib.algorithms.malerpnueli.MalerPnueliDFA<I> create() {
		return $createDispatch(
			this.alphabet,
			this.oracle,
			this.initialSuffixes,
			this.closingStrategy
			);
	}

	public net.automatalib.words.Alphabet<I> getAlphabet() {
		return this.alphabet;
	}
	public void setAlphabet(net.automatalib.words.Alphabet<I> alphabet) {
		this.alphabet = alphabet;
	}
	public MalerPnueliDFABuilder<I> withAlphabet(net.automatalib.words.Alphabet<I> alphabet) {
		this.alphabet = alphabet;
		return this;
	} 

	public de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> getOracle() {
		return this.oracle;
	}
	public void setOracle(de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle) {
		this.oracle = oracle;
	}
	public MalerPnueliDFABuilder<I> withOracle(de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle) {
		this.oracle = oracle;
		return this;
	} 

	public java.util.List<net.automatalib.words.Word<I>> getInitialSuffixes() {
		return this.initialSuffixes;
	}
	public void setInitialSuffixes(java.util.List<net.automatalib.words.Word<I>> initialSuffixes) {
		this.initialSuffixes = initialSuffixes;
	}
	public MalerPnueliDFABuilder<I> withInitialSuffixes(java.util.List<net.automatalib.words.Word<I>> initialSuffixes) {
		this.initialSuffixes = initialSuffixes;
		return this;
	} 

	public de.learnlib.algorithms.lstar.closing.ClosingStrategy<? super I,? super java.lang.Boolean> getClosingStrategy() {
		return this.closingStrategy;
	}
	public void setClosingStrategy(de.learnlib.algorithms.lstar.closing.ClosingStrategy<? super I,? super java.lang.Boolean> closingStrategy) {
		this.closingStrategy = closingStrategy;
	}
	public MalerPnueliDFABuilder<I> withClosingStrategy(de.learnlib.algorithms.lstar.closing.ClosingStrategy<? super I,? super java.lang.Boolean> closingStrategy) {
		this.closingStrategy = closingStrategy;
		return this;
	} 

}