package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class local_lookahead extends GenASTNode {
  public local_lookahead(ASTStringNode integer_literal, ASTTextNode text470, expansion_choices expansion_choices, ASTTextNode text471, Expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("integer_literal", integer_literal),
      new PropertyZeroOrOne<ASTTextNode>("text470", text470),
      new PropertyZeroOrOne<expansion_choices>("expansion_choices", expansion_choices),
      new PropertyZeroOrOne<ASTTextNode>("text471", text471),
      new PropertyZeroOrOne<Expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public local_lookahead(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new local_lookahead(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getInteger_literal() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("integer_literal")).getValue();
  }
  public ASTTextNode getText470() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text470")).getValue();
  }
  public expansion_choices getExpansion_choices() {
    return ((PropertyZeroOrOne<expansion_choices>)getProperty("expansion_choices")).getValue();
  }
  public ASTTextNode getText471() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text471")).getValue();
  }
  public Expression getExpression() {
    return ((PropertyZeroOrOne<Expression>)getProperty("expression")).getValue();
  }
}
