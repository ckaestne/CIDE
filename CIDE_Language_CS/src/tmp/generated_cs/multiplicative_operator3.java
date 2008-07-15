package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class multiplicative_operator3 extends multiplicative_operator {
  public multiplicative_operator3(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public multiplicative_operator3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new multiplicative_operator3(cloneProperties(),firstToken,lastToken);
  }
}
