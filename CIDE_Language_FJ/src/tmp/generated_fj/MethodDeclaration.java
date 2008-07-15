package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MethodDeclaration extends GenASTNode {
  public MethodDeclaration(Type type, ASTStringNode identifier, FormalParameterList formalParameterList, Expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Type>("type", type),
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<FormalParameterList>("formalParameterList", formalParameterList),
      new PropertyOne<Expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public MethodDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MethodDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public Type getType() {
    return ((PropertyOne<Type>)getProperty("type")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public FormalParameterList getFormalParameterList() {
    return ((PropertyZeroOrOne<FormalParameterList>)getProperty("formalParameterList")).getValue();
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
}
