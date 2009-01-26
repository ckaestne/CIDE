package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Number1 extends Number {
  public Number1(ASTStringNode hexnumber, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("hexnumber", hexnumber)
    }, firstToken, lastToken);
  }
  public Number1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Number1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getHexnumber() {
    return ((PropertyOne<ASTStringNode>)getProperty("hexnumber")).getValue();
  }
}
