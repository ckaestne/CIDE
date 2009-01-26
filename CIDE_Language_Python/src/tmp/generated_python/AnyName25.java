package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName25 extends AnyName {
  public AnyName25(ASTStringNode from, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("from", from)
    }, firstToken, lastToken);
  }
  public AnyName25(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName25(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFrom() {
    return ((PropertyOne<ASTStringNode>)getProperty("from")).getValue();
  }
}
