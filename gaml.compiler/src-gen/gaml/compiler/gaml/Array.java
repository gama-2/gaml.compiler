/**
 * generated by Xtext
 */
package gaml.compiler.gaml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gaml.compiler.gaml.Array#getExprs <em>Exprs</em>}</li>
 * </ul>
 *
 * @see gaml.compiler.gaml.GamlPackage#getArray()
 * @model
 * @generated
 */
public interface Array extends Expression
{
  /**
   * Returns the value of the '<em><b>Exprs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Exprs</em>' containment reference.
   * @see #setExprs(ExpressionList)
   * @see gaml.compiler.gaml.GamlPackage#getArray_Exprs()
   * @model containment="true"
   * @generated
   */
  ExpressionList getExprs();

  /**
   * Sets the value of the '{@link gaml.compiler.gaml.Array#getExprs <em>Exprs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Exprs</em>' containment reference.
   * @see #getExprs()
   * @generated
   */
  void setExprs(ExpressionList value);

} // Array
