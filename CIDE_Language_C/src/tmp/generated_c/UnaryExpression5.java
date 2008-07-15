package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpression5 extends UnaryExpression {
  public UnaryExpression5(ASTStringNode sizeof, UnaryExpressionSizeOf unaryExpressionSizeOf, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("sizeof", sizeof),
      new PropertyOne<UnaryExpressionSizeOf>("unaryExpressionSizeOf", unaryExpressionSizeOf)
    }, firstToken, lastToken);
  }
  public UnaryExpression5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpression5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getSizeof() {
    return ((PropertyOne<ASTStringNode>)getProperty("sizeof")).getValue();
  }
  public UnaryExpressionSizeOf getUnaryExpressionSizeOf() {
    return ((PropertyOne<UnaryExpressionSizeOf>)getProperty("unaryExpressionSizeOf")).getValue();
  }
}
