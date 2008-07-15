package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ArgumentList extends GenASTNode {
  public ArgumentList(Expression expression, ArrayList<Expression> expression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyZeroOrMore<Expression>("expression1", expression1)
    }, firstToken, lastToken);
  }
  public ArgumentList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ArgumentList(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public ArrayList<Expression> getExpression1() {
    return ((PropertyZeroOrMore<Expression>)getProperty("expression1")).getValue();
  }
}
