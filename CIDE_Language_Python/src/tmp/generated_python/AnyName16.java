package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName16 extends AnyName {
  public AnyName16(ASTStringNode class_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("class_kw", class_kw)
    }, firstToken, lastToken);
  }
  public AnyName16(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName16(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getClass_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("class_kw")).getValue();
  }
}
