package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CallExpressionForIn extends GenASTNode {
  public CallExpressionForIn(MemberExpressionForIn memberExpressionForIn, Arguments arguments, ArrayList<CallExpressionPart> callExpressionPart, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MemberExpressionForIn>("memberExpressionForIn", memberExpressionForIn),
      new PropertyOne<Arguments>("arguments", arguments),
      new PropertyZeroOrMore<CallExpressionPart>("callExpressionPart", callExpressionPart)
    }, firstToken, lastToken);
  }
  public CallExpressionForIn(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CallExpressionForIn(cloneProperties(),firstToken,lastToken);
  }
  public MemberExpressionForIn getMemberExpressionForIn() {
    return ((PropertyOne<MemberExpressionForIn>)getProperty("memberExpressionForIn")).getValue();
  }
  public Arguments getArguments() {
    return ((PropertyOne<Arguments>)getProperty("arguments")).getValue();
  }
  public ArrayList<CallExpressionPart> getCallExpressionPart() {
    return ((PropertyZeroOrMore<CallExpressionPart>)getProperty("callExpressionPart")).getValue();
  }
}
