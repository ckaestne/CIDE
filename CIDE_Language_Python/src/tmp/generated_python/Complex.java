package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Complex extends GenASTNode {
  public Complex(ASTStringNode float_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("float_kw", float_kw)
    }, firstToken, lastToken);
  }
  public Complex(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Complex(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFloat_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("float_kw")).getValue();
  }
}
