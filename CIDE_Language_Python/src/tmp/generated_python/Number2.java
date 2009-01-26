package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Number2 extends Number {
  public Number2(ASTStringNode octnumber, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("octnumber", octnumber)
    }, firstToken, lastToken);
  }
  public Number2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Number2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getOctnumber() {
    return ((PropertyOne<ASTStringNode>)getProperty("octnumber")).getValue();
  }
}
