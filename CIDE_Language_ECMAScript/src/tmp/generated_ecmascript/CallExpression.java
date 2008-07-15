package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CallExpression extends GenASTNode {
  public CallExpression(MemberExpression memberExpression, Arguments arguments, ArrayList<CallExpressionPart> callExpressionPart, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MemberExpression>("memberExpression", memberExpression),
      new PropertyOne<Arguments>("arguments", arguments),
      new PropertyZeroOrMore<CallExpressionPart>("callExpressionPart", callExpressionPart)
    }, firstToken, lastToken);
  }
  public CallExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CallExpression(cloneProperties(),firstToken,lastToken);
  }
  public MemberExpression getMemberExpression() {
    return ((PropertyOne<MemberExpression>)getProperty("memberExpression")).getValue();
  }
  public Arguments getArguments() {
    return ((PropertyOne<Arguments>)getProperty("arguments")).getValue();
  }
  public ArrayList<CallExpressionPart> getCallExpressionPart() {
    return ((PropertyZeroOrMore<CallExpressionPart>)getProperty("callExpressionPart")).getValue();
  }
}
