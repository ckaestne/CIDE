package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class NormalAnnotation extends GenASTNode {
  public NormalAnnotation(Name name, MemberValuePairs memberValuePairs, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Name>("name", name),
      new PropertyZeroOrOne<MemberValuePairs>("memberValuePairs", memberValuePairs)
    }, firstToken, lastToken);
  }
  public NormalAnnotation(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new NormalAnnotation(cloneProperties(),firstToken,lastToken);
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
  public MemberValuePairs getMemberValuePairs() {
    return ((PropertyZeroOrOne<MemberValuePairs>)getProperty("memberValuePairs")).getValue();
  }
}
