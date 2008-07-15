package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IterationStatement5 extends IterationStatement {
  public IterationStatement5(VariableDeclarationNoIn variableDeclarationNoIn, Expression expression6, Statement statement4, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<VariableDeclarationNoIn>("variableDeclarationNoIn", variableDeclarationNoIn),
      new PropertyOne<Expression>("expression6", expression6),
      new PropertyOne<Statement>("statement4", statement4)
    }, firstToken, lastToken);
  }
  public IterationStatement5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IterationStatement5(cloneProperties(),firstToken,lastToken);
  }
  public VariableDeclarationNoIn getVariableDeclarationNoIn() {
    return ((PropertyOne<VariableDeclarationNoIn>)getProperty("variableDeclarationNoIn")).getValue();
  }
  public Expression getExpression6() {
    return ((PropertyOne<Expression>)getProperty("expression6")).getValue();
  }
  public Statement getStatement4() {
    return ((PropertyOne<Statement>)getProperty("statement4")).getValue();
  }
}
