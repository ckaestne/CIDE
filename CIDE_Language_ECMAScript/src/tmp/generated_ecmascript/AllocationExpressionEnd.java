package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AllocationExpressionEnd extends GenASTNode {
  public AllocationExpressionEnd(Arguments arguments, ArrayList<MemberExpressionPart> memberExpressionPart, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Arguments>("arguments", arguments),
      new PropertyZeroOrMore<MemberExpressionPart>("memberExpressionPart", memberExpressionPart)
    }, firstToken, lastToken);
  }
  public AllocationExpressionEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AllocationExpressionEnd(cloneProperties(),firstToken,lastToken);
  }
  public Arguments getArguments() {
    return ((PropertyOne<Arguments>)getProperty("arguments")).getValue();
  }
  public ArrayList<MemberExpressionPart> getMemberExpressionPart() {
    return ((PropertyZeroOrMore<MemberExpressionPart>)getProperty("memberExpressionPart")).getValue();
  }
}
