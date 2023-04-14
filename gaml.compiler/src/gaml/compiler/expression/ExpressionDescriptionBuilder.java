/*******************************************************************************************************
 *
 * ExpressionDescriptionBuilder.java, in msi.gama.lang.gaml, is part of the source code of the GAMA modeling and
 * simulation platform (v.1.9.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama for license information and contacts.
 *
 ********************************************************************************************************/
package gaml.compiler.expression;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.diagnostics.Diagnostic;

import gama.annotations.common.interfaces.IKeyword;
import gaml.compiler.gaml.BooleanLiteral;
import gaml.compiler.gaml.DoubleLiteral;
import gaml.compiler.gaml.IntLiteral;
import gaml.compiler.gaml.StringLiteral;
import gaml.compiler.gaml.Unary;
import gaml.compiler.gaml.UnitName;
import gaml.compiler.gaml.util.GamlSwitch;
import gaml.compiler.EGaml;
import gaml.compiler.resource.GamlResourceServices;
import gaml.core.compilation.GAML;
import gaml.core.compilation.ast.ISyntacticElement;
import gaml.core.descriptions.ConstantExpressionDescription;
import gaml.core.descriptions.IExpressionDescription;

/**
 * The Class ExpressionDescriptionBuilder.
 */
public class ExpressionDescriptionBuilder extends GamlSwitch<IExpressionDescription> {

	// private Set<Diagnostic> currentErrors;

	/**
	 * Sets the errors.
	 *
	 * @param errors
	 *            the new errors
	 */
	void setErrors(final Set<Diagnostic> errors) {
		// currentErrors = errors;
	}

	@Override
	public IExpressionDescription caseIntLiteral(final IntLiteral object) {
		IExpressionDescription ed = null;
		try {
			ed = ConstantExpressionDescription.create(Integer.parseInt(object.getOp()));
		} catch (final NumberFormatException e) {
			// final Diagnostic d = new EObjectDiagnosticImpl(Severity.WARNING,
			// "",
			// "Impossible to parse this int value, automatically set to 0",
			// object, null, 0, null);
			// if (currentErrors != null)
			// currentErrors.add(d);
			ed = ConstantExpressionDescription.create(0);
		}
		GamlResourceServices.getResourceDocumenter().setGamlDocumentation(object, ed.getExpression(), true);
		return ed;
	}

	@Override
	public IExpressionDescription caseDoubleLiteral(final DoubleLiteral object) {
		IExpressionDescription ed = null;
		try {
			ed = ConstantExpressionDescription.create(Double.parseDouble(object.getOp()));
		} catch (final NumberFormatException e) {
			// final Diagnostic d = new EObjectDiagnosticImpl(Severity.WARNING,
			// "",
			// "Impossible to parse this float value, automatically set to 0.0",
			// object, null, 0, null);
			// if (currentErrors != null)
			// currentErrors.add(d);
			ed = ConstantExpressionDescription.create(0d);
		}
		GamlResourceServices.getResourceDocumenter().setGamlDocumentation(object, ed.getExpression(), true);
		return ed;
	}

	@Override
	public IExpressionDescription caseStringLiteral(final StringLiteral object) {
		final IExpressionDescription ed = ConstantExpressionDescription.create(object.getOp());

		// AD: Change 14/11/14
		// IExpressionDescription ed =
		// LabelExpressionDescription.create(object.getOp());
		GamlResourceServices.getResourceDocumenter().setGamlDocumentation(object, ed.getExpression(), true);
		return ed;
	}

	@Override
	public IExpressionDescription caseBooleanLiteral(final BooleanLiteral object) {
		final IExpressionDescription ed = ConstantExpressionDescription.create(IKeyword.TRUE.equals(object.getOp()));
		GamlResourceServices.getResourceDocumenter().setGamlDocumentation(object, ed.getExpression(), true);
		return ed;
	}

	@Override
	public IExpressionDescription caseUnitName(final UnitName object) {
		final String s = EGaml.getInstance().getKeyOf(object);
		if (GAML.UNITS.containsKey(s)) return GAML.UNITS.get(s);
		return null;
	}

	@Override
	public IExpressionDescription caseUnary(final Unary object) {
		final String op = EGaml.getInstance().getKeyOf(object);
		if ("°".equals(op) || "#".equals(op)) return doSwitch(object.getRight());
		return null;
	}

	@Override
	public IExpressionDescription defaultCase(final EObject object) {
		return new EcoreBasedExpressionDescription(object);
	}

	/**
	 * Creates the.
	 *
	 * @param e
	 *            the e
	 * @param errors
	 *            the errors
	 * @return the i expression description
	 */
	public static IExpressionDescription create(final ISyntacticElement e, final Set<Diagnostic> errors) {
		return new BlockExpressionDescription(e);
	}

	/**
	 * Creates the.
	 *
	 * @param expr
	 *            the expr
	 * @return the i expression description
	 */
	public IExpressionDescription create(final EObject expr/* , final Set<Diagnostic> errors */) {
		try {
			// setErrors(errors);
			final IExpressionDescription result = doSwitch(expr);
			result.setTarget(expr);
			return result;
		} finally {
			// setErrors(null);
		}
	}

}