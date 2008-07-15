package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MemberExpressionPre1 extends MemberExpressionPre {
  public MemberExpressionPre1(FunctionExpression functionExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<FunctionExpression>("functionExpression", functionExpression)
    }, firstToken, lastToken);
  }
  public MemberExpressionPre1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MemberExpressionPre1(cloneProperties(),firstToken,lastToken);
  }
  public FunctionExpression getFunctionExpression() {
    return ((PropertyOne<FunctionExpression>)getProperty("functionExpression")).getValue();
  }
}
