package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName7 extends AnyName {
  public AnyName7(ASTStringNode lambda, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("lambda", lambda)
    }, firstToken, lastToken);
  }
  public AnyName7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName7(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getLambda() {
    return ((PropertyOne<ASTStringNode>)getProperty("lambda")).getValue();
  }
}
