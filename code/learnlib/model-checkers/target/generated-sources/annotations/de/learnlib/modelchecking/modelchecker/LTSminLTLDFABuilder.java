/*
 * Automatically generated by BuilderGen, https://github.com/misberner/buildergen.
 * DO NOT EDIT THIS FILE! Your changes will be overwritten.
 * Edit the @GenerateBuilder specification of class
 * de.learnlib.modelchecking.modelchecker.LTSminLTLDFA<I>
 */

package de.learnlib.modelchecking.modelchecker;

public final class LTSminLTLDFABuilder<
		I extends java.lang.Object> {

	private static<
			I extends java.lang.Object> de.learnlib.modelchecking.modelchecker.LTSminLTLDFA<I> $createDispatch(
		boolean keepFiles,
		java.util.function.Function<java.lang.String,I> string2Input,
		int minimumUnfolds,
		double multiplier
		) {
		return new de.learnlib.modelchecking.modelchecker.LTSminLTLDFA<I>(
			keepFiles,
			string2Input,
			minimumUnfolds,
			multiplier
			);
	}

	private boolean keepFiles;
	private java.util.function.Function<java.lang.String,I> string2Input;
	private int minimumUnfolds;
	private double multiplier;

	public LTSminLTLDFABuilder() {
		this.keepFiles = de.learnlib.modelchecking.modelchecker.AbstractLTSminLTL.BuilderDefaults.keepFiles();

		this.minimumUnfolds = de.learnlib.modelchecking.modelchecker.AbstractLTSminLTL.BuilderDefaults.minimumUnfolds();
		this.multiplier = de.learnlib.modelchecking.modelchecker.AbstractLTSminLTL.BuilderDefaults.multiplier();
	}

	public de.learnlib.modelchecking.modelchecker.LTSminLTLDFA<I> create() {
		return $createDispatch(
			this.keepFiles,
			this.string2Input,
			this.minimumUnfolds,
			this.multiplier
			);
	}

	public boolean getKeepFiles() {
		return this.keepFiles;
	}
	public void setKeepFiles(boolean keepFiles) {
		this.keepFiles = keepFiles;
	}
	public LTSminLTLDFABuilder<I> withKeepFiles(boolean keepFiles) {
		this.keepFiles = keepFiles;
		return this;
	} 

	public java.util.function.Function<java.lang.String,I> getString2Input() {
		return this.string2Input;
	}
	public void setString2Input(java.util.function.Function<java.lang.String,I> string2Input) {
		this.string2Input = string2Input;
	}
	public LTSminLTLDFABuilder<I> withString2Input(java.util.function.Function<java.lang.String,I> string2Input) {
		this.string2Input = string2Input;
		return this;
	} 

	public int getMinimumUnfolds() {
		return this.minimumUnfolds;
	}
	public void setMinimumUnfolds(int minimumUnfolds) {
		this.minimumUnfolds = minimumUnfolds;
	}
	public LTSminLTLDFABuilder<I> withMinimumUnfolds(int minimumUnfolds) {
		this.minimumUnfolds = minimumUnfolds;
		return this;
	} 

	public double getMultiplier() {
		return this.multiplier;
	}
	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
	public LTSminLTLDFABuilder<I> withMultiplier(double multiplier) {
		this.multiplier = multiplier;
		return this;
	} 

}