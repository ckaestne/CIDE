package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MethodInvocation extends GenASTNode {
  public MethodInvocation(Name name, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Name>("name", name)
    }, firstToken, lastToken);
  }
  public MethodInvocation(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MethodInvocation(cloneProperties(),firstToken,lastToken);
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
}
