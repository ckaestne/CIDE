package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MemberValuePairs extends GenASTNode {
  public MemberValuePairs(MemberValuePair memberValuePair, ArrayList<MemberValuePair> memberValuePair1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MemberValuePair>("memberValuePair", memberValuePair),
      new PropertyZeroOrMore<MemberValuePair>("memberValuePair1", memberValuePair1)
    }, firstToken, lastToken);
  }
  public MemberValuePairs(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MemberValuePairs(cloneProperties(),firstToken,lastToken);
  }
  public MemberValuePair getMemberValuePair() {
    return ((PropertyOne<MemberValuePair>)getProperty("memberValuePair")).getValue();
  }
  public ArrayList<MemberValuePair> getMemberValuePair1() {
    return ((PropertyZeroOrMore<MemberValuePair>)getProperty("memberValuePair1")).getValue();
  }
}
