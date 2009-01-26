package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName26 extends AnyName {
  public AnyName26(ASTStringNode del, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("del", del)
    }, firstToken, lastToken);
  }
  public AnyName26(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName26(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDel() {
    return ((PropertyOne<ASTStringNode>)getProperty("del")).getValue();
  }
}
