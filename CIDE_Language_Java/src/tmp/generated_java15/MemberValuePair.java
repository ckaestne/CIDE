package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MemberValuePair extends GenASTNode {
  public MemberValuePair(ASTStringNode identifier, MemberValue memberValue, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<MemberValue>("memberValue", memberValue)
    }, firstToken, lastToken);
  }
  public MemberValuePair(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MemberValuePair(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public MemberValue getMemberValue() {
    return ((PropertyOne<MemberValue>)getProperty("memberValue")).getValue();
  }
}
