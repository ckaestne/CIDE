package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IterationStatement4 extends IterationStatement {
  public IterationStatement4(VariableDeclarationList variableDeclarationList, Expression expression4, Expression expression5, Statement statement3, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<VariableDeclarationList>("variableDeclarationList", variableDeclarationList),
      new PropertyZeroOrOne<Expression>("expression4", expression4),
      new PropertyZeroOrOne<Expression>("expression5", expression5),
      new PropertyOne<Statement>("statement3", statement3)
    }, firstToken, lastToken);
  }
  public IterationStatement4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IterationStatement4(cloneProperties(),firstToken,lastToken);
  }
  public VariableDeclarationList getVariableDeclarationList() {
    return ((PropertyOne<VariableDeclarationList>)getProperty("variableDeclarationList")).getValue();
  }
  public Expression getExpression4() {
    return ((PropertyZeroOrOne<Expression>)getProperty("expression4")).getValue();
  }
  public Expression getExpression5() {
    return ((PropertyZeroOrOne<Expression>)getProperty("expression5")).getValue();
  }
  public Statement getStatement3() {
    return ((PropertyOne<Statement>)getProperty("statement3")).getValue();
  }
}
