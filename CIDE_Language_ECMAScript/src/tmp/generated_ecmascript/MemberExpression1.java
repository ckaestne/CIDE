package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MemberExpression1 extends MemberExpression {
  public MemberExpression1(MemberExpressionPre memberExpressionPre, ArrayList<MemberExpressionPart> memberExpressionPart, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MemberExpressionPre>("memberExpressionPre", memberExpressionPre),
      new PropertyZeroOrMore<MemberExpressionPart>("memberExpressionPart", memberExpressionPart)
    }, firstToken, lastToken);
  }
  public MemberExpression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MemberExpression1(cloneProperties(),firstToken,lastToken);
  }
  public MemberExpressionPre getMemberExpressionPre() {
    return ((PropertyOne<MemberExpressionPre>)getProperty("memberExpressionPre")).getValue();
  }
  public ArrayList<MemberExpressionPart> getMemberExpressionPart() {
    return ((PropertyZeroOrMore<MemberExpressionPart>)getProperty("memberExpressionPart")).getValue();
  }
}
