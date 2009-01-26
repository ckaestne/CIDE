package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName18 extends AnyName {
  public AnyName18(ASTStringNode print, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("print", print)
    }, firstToken, lastToken);
  }
  public AnyName18(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName18(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPrint() {
    return ((PropertyOne<ASTStringNode>)getProperty("print")).getValue();
  }
}
