package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class overloadable_unary_operator1 extends overloadable_unary_operator {
  public overloadable_unary_operator1(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public overloadable_unary_operator1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new overloadable_unary_operator1(cloneProperties(),firstToken,lastToken);
  }
}
