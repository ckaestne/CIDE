package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Number4 extends Number {
  public Number4(ASTStringNode float_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("float_kw", float_kw)
    }, firstToken, lastToken);
  }
  public Number4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Number4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFloat_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("float_kw")).getValue();
  }
}
