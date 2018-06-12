/*
 * Automatically generated by BuilderGen, https://github.com/misberner/buildergen.
 * DO NOT EDIT THIS FILE! Your changes will be overwritten.
 * Edit the @GenerateBuilder specification of class
 * de.learnlib.algorithms.lstar.dfa.ExtensibleLStarDFA<I>
 */

package de.learnlib.algorithms.lstar.dfa;

public final class ExtensibleLStarDFABuilder<
		I extends java.lang.Object> {

	private static<
			I extends java.lang.Object> de.learnlib.algorithms.lstar.dfa.ExtensibleLStarDFA<I> $createDispatch(
		net.automatalib.words.Alphabet<I> alphabet,
		de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle,
		java.util.List<net.automatalib.words.Word<I>> initialPrefixes,
		java.util.List<net.automatalib.words.Word<I>> initialSuffixes,
		de.learnlib.algorithms.lstar.ce.ObservationTableCEXHandler<? super I,? super java.lang.Boolean> cexHandler,
		de.learnlib.algorithms.lstar.closing.ClosingStrategy<? super I,? super java.lang.Boolean> closingStrategy
		) {
		return new de.learnlib.algorithms.lstar.dfa.ExtensibleLStarDFA<I>(
			alphabet,
			oracle,
			initialPrefixes,
			initialSuffixes,
			cexHandler,
			closingStrategy
			);
	}

	private net.automatalib.words.Alphabet<I> alphabet;
	private de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle;
	private java.util.List<net.automatalib.words.Word<I>> initialPrefixes;
	private java.util.List<net.automatalib.words.Word<I>> initialSuffixes;
	private de.learnlib.algorithms.lstar.ce.ObservationTableCEXHandler<? super I,? super java.lang.Boolean> cexHandler;
	private de.learnlib.algorithms.lstar.closing.ClosingStrategy<? super I,? super java.lang.Boolean> closingStrategy;

	public ExtensibleLStarDFABuilder() {
		this.initialPrefixes = de.learnlib.algorithms.lstar.AbstractExtensibleAutomatonLStar.BuilderDefaults.initialPrefixes();
		this.initialSuffixes = de.learnlib.algorithms.lstar.AbstractExtensibleAutomatonLStar.BuilderDefaults.initialSuffixes();
		this.cexHandler = de.learnlib.algorithms.lstar.AbstractExtensibleAutomatonLStar.BuilderDefaults.cexHandler();
		this.closingStrategy = de.learnlib.algorithms.lstar.AbstractExtensibleAutomatonLStar.BuilderDefaults.closingStrategy();
	}

	public de.learnlib.algorithms.lstar.dfa.ExtensibleLStarDFA<I> create() {
		return $createDispatch(
			this.alphabet,
			this.oracle,
			this.initialPrefixes,
			this.initialSuffixes,
			this.cexHandler,
			this.closingStrategy
			);
	}

	public net.automatalib.words.Alphabet<I> getAlphabet() {
		return this.alphabet;
	}
	public void setAlphabet(net.automatalib.words.Alphabet<I> alphabet) {
		this.alphabet = alphabet;
	}
	public ExtensibleLStarDFABuilder<I> withAlphabet(net.automatalib.words.Alphabet<I> alphabet) {
		this.alphabet = alphabet;
		return this;
	} 

	public de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> getOracle() {
		return this.oracle;
	}
	public void setOracle(de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle) {
		this.oracle = oracle;
	}
	public ExtensibleLStarDFABuilder<I> withOracle(de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle) {
		this.oracle = oracle;
		return this;
	} 

	public java.util.List<net.automatalib.words.Word<I>> getInitialPrefixes() {
		return this.initialPrefixes;
	}
	public void setInitialPrefixes(java.util.List<net.automatalib.words.Word<I>> initialPrefixes) {
		this.initialPrefixes = initialPrefixes;
	}
	public ExtensibleLStarDFABuilder<I> withInitialPrefixes(java.util.List<net.automatalib.words.Word<I>> initialPrefixes) {
		this.initialPrefixes = initialPrefixes;
		return this;
	} 

	public java.util.List<net.automatalib.words.Word<I>> getInitialSuffixes() {
		return this.initialSuffixes;
	}
	public void setInitialSuffixes(java.util.List<net.automatalib.words.Word<I>> initialSuffixes) {
		this.initialSuffixes = initialSuffixes;
	}
	public ExtensibleLStarDFABuilder<I> withInitialSuffixes(java.util.List<net.automatalib.words.Word<I>> initialSuffixes) {
		this.initialSuffixes = initialSuffixes;
		return this;
	} 

	public de.learnlib.algorithms.lstar.ce.ObservationTableCEXHandler<? super I,? super java.lang.Boolean> getCexHandler() {
		return this.cexHandler;
	}
	public void setCexHandler(de.learnlib.algorithms.lstar.ce.ObservationTableCEXHandler<? super I,? super java.lang.Boolean> cexHandler) {
		this.cexHandler = cexHandler;
	}
	public ExtensibleLStarDFABuilder<I> withCexHandler(de.learnlib.algorithms.lstar.ce.ObservationTableCEXHandler<? super I,? super java.lang.Boolean> cexHandler) {
		this.cexHandler = cexHandler;
		return this;
	} 

	public de.learnlib.algorithms.lstar.closing.ClosingStrategy<? super I,? super java.lang.Boolean> getClosingStrategy() {
		return this.closingStrategy;
	}
	public void setClosingStrategy(de.learnlib.algorithms.lstar.closing.ClosingStrategy<? super I,? super java.lang.Boolean> closingStrategy) {
		this.closingStrategy = closingStrategy;
	}
	public ExtensibleLStarDFABuilder<I> withClosingStrategy(de.learnlib.algorithms.lstar.closing.ClosingStrategy<? super I,? super java.lang.Boolean> closingStrategy) {
		this.closingStrategy = closingStrategy;
		return this;
	} 

}