/*
 * Automatically generated by BuilderGen, https://github.com/misberner/buildergen.
 * DO NOT EDIT THIS FILE! Your changes will be overwritten.
 * Edit the @GenerateBuilder specification of class
 * de.learnlib.algorithms.lstar.dfa.ClassicLStarDFA<I>
 */

package de.learnlib.algorithms.lstar.dfa;

public final class ClassicLStarDFABuilder<
		I extends java.lang.Object> {

	private static<
			I extends java.lang.Object> de.learnlib.algorithms.lstar.dfa.ClassicLStarDFA<I> $createDispatch(
		net.automatalib.words.Alphabet<I> alphabet,
		de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle
		) {
		return new de.learnlib.algorithms.lstar.dfa.ClassicLStarDFA<I>(
			alphabet,
			oracle
			);
	}

	private net.automatalib.words.Alphabet<I> alphabet;
	private de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle;

	public ClassicLStarDFABuilder() {
	}

	public de.learnlib.algorithms.lstar.dfa.ClassicLStarDFA<I> create() {
		return $createDispatch(
			this.alphabet,
			this.oracle
			);
	}

	public net.automatalib.words.Alphabet<I> getAlphabet() {
		return this.alphabet;
	}
	public void setAlphabet(net.automatalib.words.Alphabet<I> alphabet) {
		this.alphabet = alphabet;
	}
	public ClassicLStarDFABuilder<I> withAlphabet(net.automatalib.words.Alphabet<I> alphabet) {
		this.alphabet = alphabet;
		return this;
	} 

	public de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> getOracle() {
		return this.oracle;
	}
	public void setOracle(de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle) {
		this.oracle = oracle;
	}
	public ClassicLStarDFABuilder<I> withOracle(de.learnlib.api.oracle.MembershipOracle<I,java.lang.Boolean> oracle) {
		this.oracle = oracle;
		return this;
	} 

}