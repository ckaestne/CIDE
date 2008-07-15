package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LeftHandSideExpressionForIn2 extends LeftHandSideExpressionForIn {
  public LeftHandSideExpressionForIn2(MemberExpressionForIn memberExpressionForIn, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MemberExpressionForIn>("memberExpressionForIn", memberExpressionForIn)
    }, firstToken, lastToken);
  }
  public LeftHandSideExpressionForIn2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LeftHandSideExpressionForIn2(cloneProperties(),firstToken,lastToken);
  }
  public MemberExpressionForIn getMemberExpressionForIn() {
    return ((PropertyOne<MemberExpressionForIn>)getProperty("memberExpressionForIn")).getValue();
  }
}
