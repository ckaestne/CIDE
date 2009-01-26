package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Number5 extends Number {
  public Number5(ASTStringNode complex, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("complex", complex)
    }, firstToken, lastToken);
  }
  public Number5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Number5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getComplex() {
    return ((PropertyOne<ASTStringNode>)getProperty("complex")).getValue();
  }
}
