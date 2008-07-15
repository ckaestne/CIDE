package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DefaultValue extends GenASTNode {
  public DefaultValue(MemberValue memberValue, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MemberValue>("memberValue", memberValue)
    }, firstToken, lastToken);
  }
  public DefaultValue(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DefaultValue(cloneProperties(),firstToken,lastToken);
  }
  public MemberValue getMemberValue() {
    return ((PropertyOne<MemberValue>)getProperty("memberValue")).getValue();
  }
}
