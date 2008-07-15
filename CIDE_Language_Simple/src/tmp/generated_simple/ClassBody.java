package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassBody extends GenASTNode {
  public ClassBody(ArrayList<Member> member, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Member>("member", member)
    }, firstToken, lastToken);
  }
  public ClassBody(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ClassBody(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Member> getMember() {
    return ((PropertyZeroOrMore<Member>)getProperty("member")).getValue();
  }
}
