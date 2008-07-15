package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LeftHandSideExpressionForIn1 extends LeftHandSideExpressionForIn {
  public LeftHandSideExpressionForIn1(CallExpressionForIn callExpressionForIn, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<CallExpressionForIn>("callExpressionForIn", callExpressionForIn)
    }, firstToken, lastToken);
  }
  public LeftHandSideExpressionForIn1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LeftHandSideExpressionForIn1(cloneProperties(),firstToken,lastToken);
  }
  public CallExpressionForIn getCallExpressionForIn() {
    return ((PropertyOne<CallExpressionForIn>)getProperty("callExpressionForIn")).getValue();
  }
}
