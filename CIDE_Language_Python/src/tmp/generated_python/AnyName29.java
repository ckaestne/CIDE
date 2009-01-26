package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName29 extends AnyName {
  public AnyName29(ASTStringNode exec, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("exec", exec)
    }, firstToken, lastToken);
  }
  public AnyName29(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName29(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getExec() {
    return ((PropertyOne<ASTStringNode>)getProperty("exec")).getValue();
  }
}
