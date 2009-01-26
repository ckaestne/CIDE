package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName14 extends AnyName {
  public AnyName14(ASTStringNode except, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("except", except)
    }, firstToken, lastToken);
  }
  public AnyName14(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName14(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getExcept() {
    return ((PropertyOne<ASTStringNode>)getProperty("except")).getValue();
  }
}
