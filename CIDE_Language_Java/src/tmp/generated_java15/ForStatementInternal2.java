package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ForStatementInternal2 extends ForStatementInternal {
  public ForStatementInternal2(ForInit forInit, Expression expression1, ForUpdate forUpdate, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ForInit>("forInit", forInit),
      new PropertyZeroOrOne<Expression>("expression1", expression1),
      new PropertyZeroOrOne<ForUpdate>("forUpdate", forUpdate)
    }, firstToken, lastToken);
  }
  public ForStatementInternal2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ForStatementInternal2(cloneProperties(),firstToken,lastToken);
  }
  public ForInit getForInit() {
    return ((PropertyZeroOrOne<ForInit>)getProperty("forInit")).getValue();
  }
  public Expression getExpression1() {
    return ((PropertyZeroOrOne<Expression>)getProperty("expression1")).getValue();
  }
  public ForUpdate getForUpdate() {
    return ((PropertyZeroOrOne<ForUpdate>)getProperty("forUpdate")).getValue();
  }
}
