/*******************************************************************************************************
 *
 * DocumentationNode.java, in msi.gama.lang.gaml, is part of the source code of the GAMA modeling and simulation
 * platform (v.1.9.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama for license information and contacts.
 *
 ********************************************************************************************************/
package gaml.compiler.documentation;

import java.io.IOException;

import gama.annotations.common.interfaces.IGamlDescription;

/**
 * The Class DocumentationNode.
 */
public class DocumentationNode implements IGamlDescription {

	/** The title. */
	String title;

	/** The doc. */
	Doc doc;

	/**
	 * Instantiates a new documentation node.
	 *
	 * @param desc
	 *            the desc
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	DocumentationNode(final IGamlDescription desc) {
		title = desc.getTitle();
		doc = desc.getDocumentation();
	}

	@Override
	public Doc getDocumentation() { return doc; }

	@Override
	public String getTitle() { return title; }

	@Override
	public String getName() { return "Online documentation"; }

	@Override
	public String getDefiningPlugin() { return ""; }

	@Override
	public void setName(final String name) {}

	@Override
	public String toString() {
		return getTitle() + " - " + getDocumentation();
	}

	/**
	 * Method serialize()
	 *
	 * @see gama.annotations.common.interfaces.IGamlable#serialize(boolean)
	 */
	@Override
	public String serialize(final boolean includingBuiltIn) {
		return toString();
	}

}