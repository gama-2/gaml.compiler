/*******************************************************************************************************
 *
 * BlockExpressionDescription.java, in msi.gama.lang.gaml, is part of the source code of the
 * GAMA modeling and simulation platform (v.1.9.0).
 *
 * (c) 2007-2022 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama for license information and contacts.
 * 
 ********************************************************************************************************/
package gaml.compiler.expression;

import gaml.core.compilation.ast.ISyntacticElement;
import gaml.core.descriptions.IDescription;
import gaml.core.descriptions.IExpressionDescription;
import gaml.core.descriptions.SpeciesDescription;
import gaml.core.descriptions.StatementDescription;
import gaml.core.expressions.IExpression;
import gaml.core.expressions.types.DenotedActionExpression;
import gaml.core.factories.DescriptionFactory;

/**
 * The Class BlockExpressionDescription.
 */
public class BlockExpressionDescription extends EcoreBasedExpressionDescription {

	/** The element. */
	final ISyntacticElement element;

	/**
	 * Instantiates a new block expression description.
	 *
	 * @param element the element
	 */
	public BlockExpressionDescription(final ISyntacticElement element) {
		super(element.getElement());
		this.element = element;
	}

	@Override
	public IExpression compile(final IDescription context) {
		final SpeciesDescription sd = context.getSpeciesContext();
		// if (sd.isExperiment())
		// sd = sd.getModelDescription();
		final StatementDescription action = (StatementDescription) DescriptionFactory.create(element, sd, null);
		if (action != null) {
			sd.addChild(action);
			action.validate();
			//			final String name = action.getName();
			expression = new DenotedActionExpression(action);
		}
		return expression;
	}

	@Override
	public IExpressionDescription cleanCopy() {
		return new BlockExpressionDescription(element);
	}

}
