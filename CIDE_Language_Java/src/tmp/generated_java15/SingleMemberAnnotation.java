package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SingleMemberAnnotation extends GenASTNode {
  public SingleMemberAnnotation(Name name, MemberValue memberValue, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Name>("name", name),
      new PropertyOne<MemberValue>("memberValue", memberValue)
    }, firstToken, lastToken);
  }
  public SingleMemberAnnotation(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SingleMemberAnnotation(cloneProperties(),firstToken,lastToken);
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
  public MemberValue getMemberValue() {
    return ((PropertyOne<MemberValue>)getProperty("memberValue")).getValue();
  }
}
