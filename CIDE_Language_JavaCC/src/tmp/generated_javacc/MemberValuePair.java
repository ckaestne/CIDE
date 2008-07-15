package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MemberValuePair extends GenASTNode {
  public MemberValuePair(JavaIdentifier javaIdentifier, MemberValue memberValue, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JavaIdentifier>("javaIdentifier", javaIdentifier),
      new PropertyOne<MemberValue>("memberValue", memberValue)
    }, firstToken, lastToken);
  }
  public MemberValuePair(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MemberValuePair(cloneProperties(),firstToken,lastToken);
  }
  public JavaIdentifier getJavaIdentifier() {
    return ((PropertyOne<JavaIdentifier>)getProperty("javaIdentifier")).getValue();
  }
  public MemberValue getMemberValue() {
    return ((PropertyOne<MemberValue>)getProperty("memberValue")).getValue();
  }
}
