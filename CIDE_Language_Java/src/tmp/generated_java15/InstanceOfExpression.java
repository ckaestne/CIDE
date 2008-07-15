package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class InstanceOfExpression extends GenASTNode {
  public InstanceOfExpression(RelationalExpression relationalExpression, Type type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<RelationalExpression>("relationalExpression", relationalExpression),
      new PropertyZeroOrOne<Type>("type", type)
    }, firstToken, lastToken);
  }
  public InstanceOfExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new InstanceOfExpression(cloneProperties(),firstToken,lastToken);
  }
  public RelationalExpression getRelationalExpression() {
    return ((PropertyOne<RelationalExpression>)getProperty("relationalExpression")).getValue();
  }
  public Type getType() {
    return ((PropertyZeroOrOne<Type>)getProperty("type")).getValue();
  }
}
