package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Number3 extends Number {
  public Number3(ASTStringNode decnumber, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("decnumber", decnumber)
    }, firstToken, lastToken);
  }
  public Number3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Number3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDecnumber() {
    return ((PropertyOne<ASTStringNode>)getProperty("decnumber")).getValue();
  }
}
