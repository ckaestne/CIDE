package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Mult2 extends Mult {
  public Mult2(ZeroOrMore zeroOrMore, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ZeroOrMore>("zeroOrMore", zeroOrMore)
    }, firstToken, lastToken);
  }
  public Mult2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Mult2(cloneProperties(),firstToken,lastToken);
  }
  public ZeroOrMore getZeroOrMore() {
    return ((PropertyOne<ZeroOrMore>)getProperty("zeroOrMore")).getValue();
  }
}
