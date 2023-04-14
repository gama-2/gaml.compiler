/*******************************************************************************************************
 *
 * IGamlBuilderListener.java, in msi.gama.lang.gaml, is part of the source code of the GAMA modeling and simulation
 * platform (v.1.9.0).
 *
 * (c) 2007-2022 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama for license information and contacts.
 *
 ********************************************************************************************************/
package gaml.compiler.validation;

import gaml.core.descriptions.IDescription;
import gaml.core.descriptions.ModelDescription;
import gaml.core.descriptions.ValidationContext;

/**
 * The class IGamlBuilder.
 *
 * @author drogoul
 * @since 2 mars 2012
 *
 */
public interface IGamlBuilderListener {

	/**
	 * Validation ended.
	 *
	 * @param model
	 *
	 * @param experiments
	 *            the experiments
	 * @param status
	 *            the status
	 */
	void validationEnded(ModelDescription model, final Iterable<? extends IDescription> experiments,
			final ValidationContext status);
}
