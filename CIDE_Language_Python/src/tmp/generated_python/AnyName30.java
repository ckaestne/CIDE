package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName30 extends AnyName {
  public AnyName30(ASTStringNode assert_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("assert_kw", assert_kw)
    }, firstToken, lastToken);
  }
  public AnyName30(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName30(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAssert_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("assert_kw")).getValue();
  }
}
